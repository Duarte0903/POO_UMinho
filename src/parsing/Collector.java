package parsing;

import artigos.*;
import utilizadores.*;
import transportadoras.*;
import gestores.*;


import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;



public class Collector{

    private static final int UTILIZADOR_SIZE = 3;
    private static final int TRANSPORTADORA_SIZE = 5;
    private static final int ARTIGO_MALA = 11;

    public static void collectDadosLine(String path, Gestor gestor){

        ArrayList<String> tokens;
        Parser parser = new Parser();
                
        parser.splitLine(path);
        tokens = parser.getTokens();

        switch (tokens.size()){

            case Collector.UTILIZADOR_SIZE:

                gestor.insertUtilizador(
                    new Utilizador(
                        tokens.get(0),
                        tokens.get(1),
                        Integer.valueOf(tokens.get(2))));

                break;

            case Collector.TRANSPORTADORA_SIZE:

                gestor.insertTransportadora(
                    new Transportadora(
                        tokens.get(0),
                        Double.valueOf(tokens.get(1)),
                        Double.valueOf(tokens.get(2)),
                        Double.valueOf(tokens.get(3)),
                        Double.valueOf(tokens.get(4))));

                break;

            case Collector.ARTIGO_MALA:

                gestor.insertArtigo(
                    new Mala(
                        Integer.valueOf(tokens.get(0)),
                        tokens.get(1),
                        tokens.get(2),
                        tokens.get(3),
                        tokens.get(4),
                        Double.valueOf(tokens.get(5)),
                        Double.valueOf(tokens.get(6)),
                        tokens.get(7),
                        Integer.valueOf(tokens.get(8)),
                        Integer.valueOf(tokens.get(9)),
                        Integer.valueOf(tokens.get(10))));

                break;

            default:

                System.out.println("Não foi possivel fazer o registo: " + tokens.get(0));
                break;
        }

        parser.cleanParser();
    }

    public static void collectDadosFile(String path, Gestor gestor){

        try{
            Scanner input = new Scanner(new File(path));

            while (input.hasNextLine()){
                
                Collector.collectDadosLine(input.nextLine(),gestor);
            }
        }

        catch (Exception e){
            System.out.println("Não foi possivel abrir o ficheiro: " + path);
        }
    }
}