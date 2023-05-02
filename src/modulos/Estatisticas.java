package modulos;

import modulos.comparadores.*;
import modulos.gestores.Gestor;
import java.util.Map;
import java.util.List;
import java.util.stream.*;
import java.util.function.Predicate;


public class Estatisticas{

    public static List<Utilizador> getMelhoresUtilizadores(List<Utilizador> utilizadores, Predicate<Fatura> filtro){
        utilizadores.stream().forEach((x) -> x.calculaFaturacao(filtro));
        return utilizadores.stream().sorted(new OrdenaUtilizador().reversed()).collect(Collectors.toList());
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