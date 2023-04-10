package parsing;

import utilizadores.Utilizador;
import utilizadores.GestorUtilizadores;
import transportadoras.Transportadora;
import transportadoras.GestorTransportadoras;


import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;



public class Collector{

    private static final int UTILIZADOR_SIZE = 3;
    private static final int TRANSPORTADORA_SIZE = 5;


    public static void collectDadosLine(String path){

        ArrayList<String> tokens;
        Parser parser = new Parser();
                
        parser.splitLine(path);
        tokens = parser.getTokens();

        switch (tokens.size()){

            case Collector.UTILIZADOR_SIZE:

                GestorUtilizadores.addUtilizador(
                    new Utilizador(
                        tokens.get(0),
                        tokens.get(1),
                        Integer.valueOf(tokens.get(2))));

                break;

            case Collector.TRANSPORTADORA_SIZE:

                GestorTransportadoras.addTransportadora(
                    new Transportadora(
                        tokens.get(0),
                        Double.valueOf(tokens.get(1)),
                        Double.valueOf(tokens.get(2)),
                        Double.valueOf(tokens.get(3)),
                        Double.valueOf(tokens.get(4))));

                break;
        }

        parser.cleanParser();
    }

    public static void collectDadosFile(String path){

        try{
            Scanner input = new Scanner(new File(path));

            while (input.hasNextLine()){
                
                Collector.collectDadosLine(input.nextLine());
            }
        }

        catch (Exception e){
            System.out.println("Não foi possivel abrir o ficheiro: " + path);
        }
    }
}