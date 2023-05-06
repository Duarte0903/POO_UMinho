package modulos;

import modulos.Fatura.TIPO;
import modulos.comparadores.*;
import java.util.Map;
import java.util.List;
import java.util.stream.*;
import java.util.function.Predicate;


public class Estatisticas{

    public static List<EstatisticasVisivel> getMelhoresUtilizadores(List<Utilizador> utilizadores, Predicate<Fatura> filtro){
        return utilizadores.stream().sorted(new OrdenaUtilizador(filtro).reversed()).collect(Collectors.toList());
    }

    public static double getLucroVintage(List<Utilizador> utilizadores){
        return utilizadores.stream().mapToDouble((x) -> x.getFaturacao((y) -> y.getTipo() == TIPO.COMISSAO)).sum();
    }

    public static List<EstatisticasVisivel> getEncomendasEmitidasVendedor(int utilizador, Map<Integer,Encomenda> encomendas){

        List<Encomenda> result = encomendas.values().stream().map((x) -> x.clone()).collect(Collectors.toList());

        result.forEach((x) -> x.setArtigos((y) -> y.getVendedor() != utilizador));
        return result.stream().filter((x) -> x.size() > 0 && x.getEstado().equals(Encomenda.EXPEDIDA)).collect(Collectors.toList());
    }

    public static List<EstatisticasVisivel> getMelhoresTransportadoras(Map<Integer,Transportadora> transportadoras){
        return transportadoras.values().stream().sorted(new OrdenaTransportadora().reversed()).collect(Collectors.toList());
    }
}