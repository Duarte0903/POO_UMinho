package escritor;

import modulos.Utilizador;
import modulos.Transportadora;
import modulos.Encomenda;
import modulos.Fatura;
import java.util.List;
import java.util.function.Predicate;


public class Escritor{

    public static void escreve(Object object){
        System.out.println(object);
    }

    public static void printMelhoresUtilizadores(List<Utilizador> ranking, Predicate<Fatura> filtro){
        ranking.stream().forEach((x) -> {
            System.out.println("Nome: " + x.getNome() +
                                "\tCodigo: " + x.getCodigo() +
                                "\tDinheiro Ganho: " + x.getFaturacao(filtro));
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