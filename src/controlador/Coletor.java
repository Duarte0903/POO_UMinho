package controlador;

import modulos.*;
import modulos.artigos.*;
import modulos.gestores.Gestor;
import leitor.Leitor;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Predicate;
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

    public static void collectDadosLine(String[] tokens, Gestor gestor){

        try{

            switch (Coletor.getCodigo(tokens[0])){

                case Coletor.SHOW_DADOS:

                    System.out.println(gestor.toString());
                    break;

                case Coletor.INSERT_UTILIZADOR:

                    gestor.insertUtilizador(
                        new Utilizador(
                            tokens[1],
                            tokens[2],
                            Integer.valueOf(tokens[3]),
                            tokens[4]));

                    break;

                case Coletor.INSERT_TRANPORTADORA:

                    gestor.insertTransportadora(
                        new Transportadora(
                            tokens[1],
                            Double.valueOf(tokens[2]),
                            Double.valueOf(tokens[3]),
                            Double.valueOf(tokens[4]),
                            Double.valueOf(tokens[5]),
                            Boolean.parseBoolean(tokens[6])));

                    break;

                case Coletor.INSERT_ARTIGO_MALA:

                    gestor.insertArtigo(
                        new Mala(
                            Integer.valueOf(tokens[1]),
                            tokens[2],
                            tokens[3],
                            tokens[4],
                            tokens[5],
                            Double.valueOf(tokens[6]),
                            Double.valueOf(tokens[7]),
                            tokens[8],
                            Integer.valueOf(tokens[9]),
                            Integer.valueOf(tokens[10]),
                            Boolean.parseBoolean(tokens[11]),
                            Integer.valueOf(tokens[12]),
                            tokens[13],
                            LocalDate.parse(tokens[14])));

                    break;

                case Coletor.INSERT_ARTIGO_TSHIRT:

                    gestor.insertArtigo(
                        new Tshirt(
                            Integer.valueOf(tokens[1]),
                            tokens[2],
                            tokens[3],
                            tokens[4],
                            tokens[5],
                            Double.valueOf(tokens[6]),
                            Double.valueOf(tokens[7]),
                            tokens[8],
                            Integer.valueOf(tokens[9]),
                            Integer.valueOf(tokens[10]),
                            Boolean.parseBoolean(tokens[11]),
                            tokens[12],
                            tokens[13]));

                    break;

                case Coletor.INSERT_ARTIGO_SAPATILHA:

                    gestor.insertArtigo(
                        new Sapatilha(
                            Integer.valueOf(tokens[1]),
                            tokens[2],
                            tokens[3],
                            tokens[4],
                            tokens[5],
                            Double.valueOf(tokens[6]),
                            Double.valueOf(tokens[7]),
                            tokens[8],
                            Integer.valueOf(tokens[9]),
                            Integer.valueOf(tokens[10]),
                            Boolean.parseBoolean(tokens[11]),
                            tokens[12],
                            Integer.valueOf(tokens[13]),
                            Boolean.parseBoolean(tokens[14]),
                            LocalDate.parse(tokens[15])));

                    break;

                case Coletor.CREAT_ENCOMENDA:
                    
                    gestor.insertEncomenda(
                        new Encomenda(
                            Integer.valueOf(tokens[1])));
            
                    break;

                case Coletor.INSERT_ARTIGO_ENCOMENDA:

                    gestor.insertArtigoEncomenda(
                        Integer.valueOf(tokens[1]),
                        tokens[2]);

                    break;

                case Coletor.REMOVE_ARTIGO_ENCOMENDA:

                    gestor.removeArtigoEncomenda(
                        Integer.valueOf(tokens[1]),
                        tokens[2]);

                    break;

                case Coletor.CHECK_OUT_ENCOMENDA:

                    gestor.finalizarEncomenda(Integer.valueOf(tokens[1]));
                    break;

                case Coletor.REFUND_ENCOMENDA:

                    gestor.devolverEncomenda(Integer.valueOf(tokens[1]));
                    break;

                case Coletor.CHANGE_PRECO_ARTIGO:

                    gestor.alterarPrecoArtigo(
                        tokens[1],
                        Double.valueOf(tokens[2]));
                    
                    break;

                case Coletor.CHANGE_PRECOS_TRANSPORTADORA:

                    gestor.alterarPrecosTransportadora(
                        tokens[1],
                        Double.valueOf(tokens[2]),
                        Double.valueOf(tokens[3]),
                        Double.valueOf(tokens[4]),
                        Double.valueOf(tokens[5]));

                    break;

                case Coletor.CHANGE_DATA:

                    gestor.updateData(LocalDate.parse(tokens[1]));
                    break;

                case Coletor.CHANGE_VINTAGE_COMISSAO:

                    Gestor.setComissao(Double.valueOf(tokens[1]));
                    break;

                case Coletor.SHOW_DATA:

                    System.out.println(Calendario.getData().toString());
                    break;

                case Coletor.SHOW_COMISSAO:

                    System.out.println(Gestor.getComissao());
                    break;

                case Coletor.SAY_VINTAGE_LUCRO:

                    gestor.getLucroVintage();
                    break;

                case Coletor.SAY_ENCOMENDAS_EMITIDAS_VENDEDOR:

                    gestor.getEncomendasEmitidasVendedor(Integer.valueOf(tokens[1]));
                    break;

                case Coletor.SAY_MELHORES_TRANSPORTADORAS:

                    gestor.getMelhoresTransportadoras();
                    break;

                case Coletor.SAY_MELHORES_VENDEDORES:
                case Coletor.SAY_MELHORES_COMPRADORES:

                    Predicate<Artigo> filtro = x -> true;

                    if (tokens.length == 3){
                        filtro = x -> x.getData().isAfter(LocalDate.parse(tokens[1]))
                                    && x.getData().isBefore(LocalDate.parse(tokens[2]));
                    }

                    if (Coletor.getCodigo(tokens[0]) == Coletor.SAY_MELHORES_VENDEDORES){
                        gestor.getMelhoresVendedores(filtro);
                    }

                    else gestor.getMelhoresCompradores(filtro);
                    break;
            }
        }

        catch (Exception e){
            System.out.println("Não foi possivel efetuar o registo: " + tokens[0]);
        }
    }
}