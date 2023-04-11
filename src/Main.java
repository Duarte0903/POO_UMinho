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
import gestor.Gestor;

import java.time.LocalDate;

public class Main{

    public static void main(String[] args){

        Gestor gestor = new Gestor();

        Collector.collectDadosFile("teste.txt",gestor);

        System.out.println(gestor.toString());
    }
}
