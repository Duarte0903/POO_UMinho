package parsing;

import artigos.*;
import encomendas.Encomenda;
import utilizadores.Utilizador;
import transportadoras.Transportadora;
import gestores.Gestor;
import calendario.Calendario;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;


public class Coletor{

    private static final int SHOW_DADOS = 0;
    private static final int INSERT_UTILIZADOR = 1;
    private static final int INSERT_TRANPORTADORA = 2;
    private static final int INSERT_ARTIGO_MALA = 3;
    private static final int INSERT_ARTIGO_TSHIRT = 4;
    private static final int INSERT_ARTIGO_SAPATILHA = 5;
    private static final int CREAT_ENCOMENDA = 6;
    private static final int CHECK_OUT_ENCOMENDA = 7;
    private static final int INSERT_ARTIGO_ENCOMENDA = 8;
    private static final int REMOVE_ARTIGO_ENCOMENDA = 9;
    private static final int REFUND_ENCOMENDA = 10;
    private static final int SHOW_DATA = 11;
    private static final int CHANGE_DATA = 12;

    private static Map<Integer,Integer> tabela = new HashMap<Integer,Integer>();

    public static void fillTabela(){

        Coletor.tabela.put("Ver Catalogos".hashCode(),Coletor.SHOW_DADOS);
        Coletor.tabela.put("Inserir Utilizador".hashCode(),Coletor.INSERT_UTILIZADOR);
        Coletor.tabela.put("Inserir Transportadora".hashCode(),Coletor.INSERT_TRANPORTADORA);
        Coletor.tabela.put("Inserir Artigo Mala".hashCode(),Coletor.INSERT_ARTIGO_MALA);
        Coletor.tabela.put("Inserir Artigo Tshirt".hashCode(),Coletor.INSERT_ARTIGO_TSHIRT);
        Coletor.tabela.put("Inserir Artigo Sapatilha".hashCode(),Coletor.INSERT_ARTIGO_SAPATILHA);
        Coletor.tabela.put("Criar Encomenda".hashCode(),Coletor.CREAT_ENCOMENDA);
        Coletor.tabela.put("Inserir Artigo Encomenda".hashCode(),Coletor.INSERT_ARTIGO_ENCOMENDA);
        Coletor.tabela.put("Remover Artigo Encomenda".hashCode(),Coletor.REMOVE_ARTIGO_ENCOMENDA);
        Coletor.tabela.put("Finalizar Encomenda".hashCode(),Coletor.CHECK_OUT_ENCOMENDA);
        Coletor.tabela.put("Ver Data".hashCode(),Coletor.SHOW_DATA);
        Coletor.tabela.put("Alterar Data".hashCode(),Coletor.CHANGE_DATA);
        Coletor.tabela.put("Devolver Encomenda".hashCode(),Coletor.REFUND_ENCOMENDA);
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

                case Coletor.SHOW_DADOS:

                    System.out.println(gestor.toString());
                    break;

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
                            Integer.valueOf(tokens.get(11)),
                            tokens.get(12),
                            LocalDate.parse(tokens.get(13))));

                    break;

                case Coletor.INSERT_ARTIGO_TSHIRT:

                    gestor.insertArtigo(
                        new Tshirt(
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
                            tokens.get(11),
                            tokens.get(12)));

                    break;

                case Coletor.INSERT_ARTIGO_SAPATILHA:

                    gestor.insertArtigo(
                        new Sapatilha(
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
                            tokens.get(11),
                            Integer.valueOf(tokens.get(12)),
                            Boolean.parseBoolean(tokens.get(13)),
                            LocalDate.parse(tokens.get(14))));

                    break;

                case Coletor.CREAT_ENCOMENDA:
                    
                    gestor.insertEncomenda(
                        new Encomenda(
                            Integer.valueOf(tokens.get(1))));
            
                    break;

                case Coletor.INSERT_ARTIGO_ENCOMENDA:

                    gestor.insertArtigoEncomenda(
                        Integer.valueOf(tokens.get(1)),
                        tokens.get(2));

                    break;

                case Coletor.REMOVE_ARTIGO_ENCOMENDA:

                    gestor.removeArtigoEncomenda(
                        Integer.valueOf(tokens.get(1)),
                        tokens.get(2));

                    break;

                case Coletor.CHECK_OUT_ENCOMENDA:

                    gestor.finalizarEncomenda(Integer.valueOf(tokens.get(1)));
                    break;

                case Coletor.REFUND_ENCOMENDA:

                    gestor.devolverEncomenda(Integer.valueOf(tokens.get(1)));
                    break;

                case Coletor.CHANGE_DATA:

                    gestor.updateData(LocalDate.parse(tokens.get(1)));
                    break;

                case Coletor.SHOW_DATA:

                    System.out.println(Calendario.getData().toString());
                    break;
            }
        }

        catch (Exception e){
            System.out.println("Não foi possivel efetuar o registo: " + tokens.get(0));
        }
    }
}