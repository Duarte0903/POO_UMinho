import artigos.Artigo;
import artigos.Sapatilha;
import utilizadores.Utilizador;
import encomendas.Encomenda;
import calendario.Calendario;

import java.time.LocalDate;

public class Main{

    public static void main(String[] args){

        Calendario.setData(LocalDate.now());
        System.out.println("Data: " + Calendario.getData().toString());

        Calendario.setData(LocalDate.parse("2025-05-10"));
        System.out.println("Data: " + Calendario.getData().toString());

        Sapatilha sapatilha = new Sapatilha();
        Artigo artigo = new Sapatilha();

        Encomenda encomenda = new Encomenda(); 
        Utilizador utilizador = new Utilizador();


        System.out.println(artigo.equals(sapatilha));
        System.out.println(sapatilha.equals(artigo));

        System.out.println(encomenda.toString());
        System.out.println(utilizador.toString());
    }
}
