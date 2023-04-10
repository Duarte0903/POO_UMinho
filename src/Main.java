import artigos.Artigo;
import artigos.Mala;
import artigos.Tshirt;
import artigos.Sapatilha;
import utilizadores.Utilizador;
import utilizadores.GestorUtilizadores;
import encomendas.Encomenda;
import calendario.Calendario;
import parsing.Parser;
import parsing.Collector;
import transportadoras.Transportadora;
import transportadoras.GestorTransportadoras;

import java.time.LocalDate;

public class Main{

    public static void main(String[] args){

        Collector.collectDadosFile("teste.txt");

        System.out.println(GestorUtilizadores.getString());
        System.out.println("\n-------------------");
        System.out.println(GestorTransportadoras.getString());
    }
}
