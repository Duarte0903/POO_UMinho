package estatisticas;

import comparadores.*;
import artigos.Artigo;
import utilizadores.Utilizador;
import encomendas.Encomenda;
import transportadoras.Transportadora;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.function.Predicate;


public class Estatisticas{

    public static List<Utilizador> getMelhoresVendedores(List<Utilizador> utilizadores, Predicate<Artigo> filtro){

        List<Utilizador> result = utilizadores.stream().map((x) -> x.clone()).collect(Collectors.toList());

        result.forEach((x) -> x.setArtigosVendidos(filtro.negate()));
        return result.stream().sorted(new OrdenaVendedor().reversed()).collect(Collectors.toList());
    }

    public static List<Utilizador> getMelhoresCompradores(List<Utilizador> utilizadores, Predicate<Artigo> filtro){

        List<Utilizador> result = utilizadores.stream().map((x) -> x.clone()).collect(Collectors.toList());

        result.forEach((x) -> x.setArtigosAdquiridos(filtro.negate()));
        return result.stream().sorted(new OrdenaComprador().reversed()).collect(Collectors.toList());
    }

    public static List<Transportadora> getMelhoresTransportadoras(Map<Integer,Transportadora> transportadoras){

        List<Transportadora> result = transportadoras.entrySet().stream().map((x) -> x.getValue().clone()).collect(Collectors.toList());

        return result.stream().sorted(new OrdenaTransportadora().reversed()).collect(Collectors.toList());
    }

    public static List<Encomenda> getEncomendasEmitidasVendedor(int utilizador, Map<Integer,Encomenda> encomendas){
        
        List<Encomenda> result = encomendas.entrySet().stream().map((x) -> x.getValue().clone()).collect(Collectors.toList());

        result.forEach((x) -> x.setArtigos((y) -> y.getVendedor() != utilizador));
        return result.stream().filter((x) -> x.size() > 0 && x.getEstado().equals(Encomenda.EXPEDIDA)).collect(Collectors.toList());
    }



    public static double getLucroVintage(Map<Integer,Encomenda> encomendas, double comissao){
        return encomendas.entrySet()
                        .stream()
                        .map((x) -> x.getValue())
                        .filter((x) -> x.getEstado().equals(Encomenda.FINALIZADA)
                            || x.getEstado().equals(Encomenda.EXPEDIDA))
                        .mapToDouble((x) -> x.calculaPreco())
                        .sum() * comissao;
    }
}