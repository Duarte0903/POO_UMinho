package parsing;

import artigos.*;
import encomendas.Encomenda;
import utilizadores.Utilizador;
import transportadoras.Transportadora;
import gestores.Gestor;
import calendario.Calendario;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
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
    private static final int CHANGE_PRECOS_TRANSPORTADORA = 13;
    private static final int CHANGE_PRECO_ARTIGO = 14;
    private static final int SAY_MELHORES_VENDEDORES = 15;
    private static final int SAY_MELHORES_COMPRADORES = 16;
    private static final int SAY_MELHORES_TRANSPORTADORAS = 17;
    private static final int SAY_ENCOMENDAS_EMITIDAS_VENDEDOR = 18;
    private static final int SAY_VINTAGE_LUCRO = 19;
    private static final int CHANGE_VINTAGE_COMISSAO = 20;
    private static final int SHOW_COMISSAO = 21;

    private static Map<String,Integer> tabela = new HashMap<String,Integer>();

    public static void fillTabela(){

        Coletor.tabela.put("Ver Catalogos",Coletor.SHOW_DADOS);
        Coletor.tabela.put("Inserir Utilizador",Coletor.INSERT_UTILIZADOR);
        Coletor.tabela.put("Inserir Transportadora",Coletor.INSERT_TRANPORTADORA);
        Coletor.tabela.put("Inserir Artigo Mala",Coletor.INSERT_ARTIGO_MALA);
        Coletor.tabela.put("Inserir Artigo Tshirt",Coletor.INSERT_ARTIGO_TSHIRT);
        Coletor.tabela.put("Inserir Artigo Sapatilha",Coletor.INSERT_ARTIGO_SAPATILHA);
        Coletor.tabela.put("Criar Encomenda",Coletor.CREAT_ENCOMENDA);
        Coletor.tabela.put("Inserir Artigo Encomenda",Coletor.INSERT_ARTIGO_ENCOMENDA);
        Coletor.tabela.put("Remover Artigo Encomenda",Coletor.REMOVE_ARTIGO_ENCOMENDA);
        Coletor.tabela.put("Finalizar Encomenda",Coletor.CHECK_OUT_ENCOMENDA);
        Coletor.tabela.put("Ver Data",Coletor.SHOW_DATA);
        Coletor.tabela.put("Alterar Data",Coletor.CHANGE_DATA);
        Coletor.tabela.put("Devolver Encomenda",Coletor.REFUND_ENCOMENDA);
        Coletor.tabela.put("Alterar Precos Transportadora",Coletor.CHANGE_PRECOS_TRANSPORTADORA);
        Coletor.tabela.put("Alterar Preco Artigo",Coletor.CHANGE_PRECO_ARTIGO);
        Coletor.tabela.put("Melhores Vendedores",Coletor.SAY_MELHORES_VENDEDORES);
        Coletor.tabela.put("Melhores Compradores",Coletor.SAY_MELHORES_COMPRADORES);
        Coletor.tabela.put("Melhores Transportadoras",Coletor.SAY_MELHORES_TRANSPORTADORAS);
        Coletor.tabela.put("Encomendas Emitidas Vendedor", Coletor.SAY_ENCOMENDAS_EMITIDAS_VENDEDOR);
        Coletor.tabela.put("Vintage Lucro",Coletor.SAY_VINTAGE_LUCRO);
        Coletor.tabela.put("Alterar Comissao",Coletor.CHANGE_VINTAGE_COMISSAO);
        Coletor.tabela.put("Ver Comissao",Coletor.SHOW_COMISSAO);
    }

    private static int getCodigo(String identificador){
        return Coletor.tabela.get(identificador);
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
                            Integer.valueOf(tokens.get(3)),
                            tokens.get(4)));

                    break;

                case Coletor.INSERT_TRANPORTADORA:

                    gestor.insertTransportadora(
                        new Transportadora(
                            tokens.get(1),
                            Double.valueOf(tokens.get(2)),
                            Double.valueOf(tokens.get(3)),
                            Double.valueOf(tokens.get(4)),
                            Double.valueOf(tokens.get(5)),
                            Boolean.parseBoolean(tokens.get(6))));

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
                            Boolean.parseBoolean(tokens.get(11)),
                            Integer.valueOf(tokens.get(12)),
                            tokens.get(13),
                            LocalDate.parse(tokens.get(14))));

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
                            Boolean.parseBoolean(tokens.get(11)),
                            tokens.get(12),
                            tokens.get(13)));

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
                            Boolean.parseBoolean(tokens.get(11)),
                            tokens.get(12),
                            Integer.valueOf(tokens.get(13)),
                            Boolean.parseBoolean(tokens.get(14)),
                            LocalDate.parse(tokens.get(15))));

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

                case Coletor.CHANGE_PRECO_ARTIGO:

                    gestor.alterarPrecoArtigo(
                        tokens.get(1),
                        Double.valueOf(tokens.get(2)));
                    
                    break;

                case Coletor.CHANGE_PRECOS_TRANSPORTADORA:

                    gestor.alterarPrecosTransportadora(
                        tokens.get(1),
                        Double.valueOf(tokens.get(2)),
                        Double.valueOf(tokens.get(3)),
                        Double.valueOf(tokens.get(4)),
                        Double.valueOf(tokens.get(5)));

                    break;

                case Coletor.CHANGE_DATA:

                    gestor.updateData(LocalDate.parse(tokens.get(1)));
                    break;

                case Coletor.CHANGE_VINTAGE_COMISSAO:

                    Gestor.setComissao(Double.valueOf(tokens.get(1)));
                    break;

                case Coletor.SHOW_DATA:

                    System.out.println(Calendario.getData().toString());
                    break;

                case SHOW_COMISSAO:

                    System.out.println(Gestor.getComissao());
                    break;

                case Coletor.SAY_MELHORES_TRANSPORTADORAS:

                    gestor.dizMelhoresTransportadoras();
                    break;

                case Coletor.SAY_VINTAGE_LUCRO:

                    System.out.println("Vintage lucro: " + gestor.getLucroVintage());
                    break;

                case Coletor.SAY_ENCOMENDAS_EMITIDAS_VENDEDOR:

                    gestor.getEncomendasEmitidasVendedor(Integer.valueOf(tokens.get(1)));
                    break;

                case Coletor.SAY_MELHORES_VENDEDORES:
                case Coletor.SAY_MELHORES_COMPRADORES:

                    Predicate<Artigo> filtro = x -> true;

                    if (tokens.size() == 3){
                        filtro = x -> x.getData().isAfter(LocalDate.parse(tokens.get(1)))
                                    && x.getData().isBefore(LocalDate.parse(tokens.get(2)));
                    }

                    if (Coletor.getCodigo(tokens.get(0)) == Coletor.SAY_MELHORES_VENDEDORES){
                        gestor.dizMelhoresVendedores(filtro);
                    }

                    else gestor.dizMelhoresCompradores(filtro);
                    break;
            }
        }

        catch (Exception e){
            System.out.println("Não foi possivel efetuar o registo: " + tokens.get(0));
        }
    }
}