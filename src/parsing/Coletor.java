package parsing;

import artigos.*;
import utilizadores.*;
import transportadoras.*;
import gestores.*;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;



public class Coletor{

    private static final int INSERT_UTILIZADOR = 1;
    private static final int INSERT_TRANPORTADORA = 2;
    private static final int INSERT_ARTIGO_MALA = 3;
    private static final int INSERT_ARTIGO_TSHIRT = 4;
    private static final int INSERT_ARTIGO_SAPATILHA = 5;

    private static Map<Integer,Integer> tabela = new HashMap<Integer,Integer>();

    public static void fillTabela(){

        Coletor.tabela.put("Inserir Utilizador".hashCode(),Coletor.INSERT_UTILIZADOR);
        Coletor.tabela.put("Inserir Transportadora".hashCode(),Coletor.INSERT_TRANPORTADORA);
        Coletor.tabela.put("Inserir Artigo Mala".hashCode(),Coletor.INSERT_ARTIGO_MALA);
        Coletor.tabela.put("Inserir Artigo Tshirt".hashCode(),Coletor.INSERT_ARTIGO_TSHIRT);
        Coletor.tabela.put("Inserir Artigo Sapatilha".hashCode(),Coletor.INSERT_ARTIGO_SAPATILHA);
    }

    private static int getCodigo(String identificador){
        return Coletor.tabela.get(identificador.hashCode());
    }

    public static void collectDadosLine(String path, Gestor gestor){

        ArrayList<String> tokens;
        Parser parser = new Parser();
                
        parser.splitLine(path);
        tokens = parser.getTokens();

        try{

            switch (Coletor.getCodigo(tokens.get(0))){

                case Coletor.INSERT_UTILIZADOR:

                    gestor.insertUtilizador(
                        new Utilizador(
                            tokens.get(1),
                            tokens.get(2),
                            Integer.valueOf(tokens.get(3))));

                    break;

                case Coletor.INSERT_TRANPORTADORA:

                    gestor.insertTransportadora(
                        new Transportadora(
                            tokens.get(1),
                            Double.valueOf(tokens.get(2)),
                            Double.valueOf(tokens.get(3)),
                            Double.valueOf(tokens.get(4)),
                            Double.valueOf(tokens.get(5))));

                    break;

                case Coletor.INSERT_ARTIGO_MALA:

                    gestor.insertArtigo(
                        new Mala(
                            Integer.valueOf(tokens.get(1)),
                            tokens.get(2),
                            tokens.get(3),
                            tokens.get(4),
                            tokens.get(5),
                            Double.valueOf(tokens.get(6)),
                            Double.valueOf(tokens.get(7)),
                            tokens.get(8),
                            Integer.valueOf(tokens.get(9)),
                            Integer.valueOf(tokens.get(10)),
                            Integer.valueOf(tokens.get(11))));

                    break;
            }
        }

        catch (Exception e){
            System.out.println("Não foi possivel fazer o registo: " + tokens.get(0));
        }
    }

    public static void collectDadosFile(String path, Gestor gestor){

        try{
            Scanner input = new Scanner(new File(path));

            while (input.hasNextLine()){
                
                Coletor.collectDadosLine(input.nextLine(),gestor);
            }
        }

        catch (Exception e){
            System.out.println("Não foi possivel abrir o ficheiro: " + path);
        }
    }
}