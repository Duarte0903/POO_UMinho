import gestores.*;
import parsing.*;
import artigos.*;
import encomendas.Encomenda;
import calendario.Calendario;
import utilizadores.*;
import transportadoras.*;
import transportadoras.*;


public class Main{

    public static void main(String[] args){

        Gestor gestor = new Gestor();

        Coletor.fillTabela();
        Coletor.collectDadosFile("teste.txt",gestor);

        System.out.println(gestor.toString());
    }
}
