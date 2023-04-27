package modulos;

import modulos.comparadores.*;
import modulos.gestores.Gestor;
import modulos.artigos.Artigo;
import modulos.Utilizador;
import modulos.Encomenda;
import modulos.Transportadora;
import java.util.Map;
import java.util.List;
import java.util.stream.*;
import java.util.Comparator;
import java.util.function.Predicate;


public class Estatisticas{

    public static List<Utilizador> getMelhoresVendedores(List<Utilizador> utilizadores, Predicate<Artigo> filtro){
        utilizadores.forEach((x) -> x.setDinheiro(x.getDinheiroArtigosVendidos(filtro)*(1-Gestor.getComissao())));
        return utilizadores.stream().map((x) -> x.clone()).sorted(new OrdenaUtilizador().reversed()).collect(Collectors.toList());
    }

    public static List<Utilizador> getMelhoresCompradores(List<Utilizador> utilizadores, Predicate<Artigo> filtro){
        utilizadores.forEach((x) -> x.setDinheiro(x.getDinheiroArtigosAdquiridos(filtro)));
        return utilizadores.stream().map((x) -> x.clone()).sorted(new OrdenaUtilizador().reversed()).collect(Collectors.toList());
    }

    public static List<Encomenda> getEncomendasEmitidasVendedor(int utilizador, Map<Integer,Encomenda> encomendas){

        List<Encomenda> result = encomendas.entrySet().stream().map((x) -> x.getValue().clone()).collect(Collectors.toList());

        result.forEach((x) -> x.setArtigos((y) -> y.getVendedor() != utilizador));
        return result.stream().filter((x) -> x.size() > 0 && x.getEstado().equals(Encomenda.EXPEDIDA)).collect(Collectors.toList());
    }

    public static List<Transportadora> getMelhoresTransportadoras(Map<Integer,Transportadora> transportadoras){
        return transportadoras
                .entrySet()
                .stream()
                .map((x) -> x.getValue())
                .sorted(new OrdenaTransportadora().reversed())
                .collect(Collectors.toList());
    }

    public static double getLucroVintage(Map<Integer,Encomenda> encomendas){
        return encomendas
                .entrySet()
                .stream()
                .map((x) -> x.getValue())
                .filter((x) -> x.getEstado().equals(Encomenda.FINALIZADA) || x.getEstado().equals(Encomenda.EXPEDIDA))
                .mapToDouble((x) -> x.calculaPreco())
                .sum() * Gestor.getComissao();
    }
}