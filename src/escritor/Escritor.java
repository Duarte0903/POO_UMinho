package escritor;

import modulos.Utilizador;
import modulos.Transportadora;
import modulos.Encomenda;
import java.util.List;


public class Escritor{

    public static void escreve(Object object){
        System.out.println(object);
    }

    public static void printMelhoresUtilizadores(List<Utilizador> ranking){
        ranking.stream().forEach((x) -> {
            System.out.println("Nome: " + x.getNome() +
                                "\tCodigo: " + x.getCodigo() +
                                "\tDinheiro Ganho: " + x.getDinheiroFaturacao());
        });
    }

    public static void printMelhoresTransportadoras(List<Transportadora> ranking){
        ranking.stream().forEach((x) -> {
            System.out.println("Nome: " + x.getNome() +
                                "\tDinheiro Ganho: " + x.getFaturacao());
        });
    }

    public static void printEncomendasEmitidasVendedor(List<Encomenda> lista){
        lista.stream().forEach((x) -> System.out.println(x.toString()));
    }
}