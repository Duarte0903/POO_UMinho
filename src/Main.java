import gestores.*;
import parsing.*;
import artigos.*;
import encomendas.Encomenda;
import calendario.Calendario;
import utilizadores.*;
import transportadoras.*;
import transportadoras.*;

import java.util.Scanner;


public class Main{

    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args){

        Gestor gestor = new Gestor();
        Scanner input = new Scanner(System.in);

        Coletor.fillTabela();
        
        if (args.length != 0) Coletor.collectDadosFile(args[0],gestor);

   //     System.out.printf(Main.GREEN + "%s", ">>> ");

  /*      while (input.hasNextLine()){

            String line = input.nextLine();

            System.out.printf("%s", Main.RESET);
            Coletor.collectDadosLine(line,gestor);

            System.out.printf(Main.GREEN + "%s", ">>> ");
        }

        System.out.println("Bye!");
        */

        System.out.println(gestor.toString());
    }
}
