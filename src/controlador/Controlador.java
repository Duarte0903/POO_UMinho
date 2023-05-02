package controlador;

import modulos.gestores.Gestor;
import modulos.Tratador;
import escritor.Escritor;
import java.util.Map;
import java.util.HashMap;


public class Controlador{

    public static final int SHOW_DADOS = 0;
    public static final int INSERT_UTILIZADOR = 1;
    public static final int INSERT_TRANPORTADORA = 2;
    public static final int INSERT_ARTIGO_MALA = 3;
    public static final int INSERT_ARTIGO_TSHIRT = 4;
    public static final int INSERT_ARTIGO_SAPATILHA = 5;
    public static final int INSERT_ENCOMENDA = 6;
    public static final int CHECK_OUT_ENCOMENDA = 7;
    public static final int INSERT_ARTIGO_ENCOMENDA = 8;
    public static final int REMOVE_ARTIGO_ENCOMENDA = 9;
    public static final int REFUND_ENCOMENDA = 10;
    public static final int SHOW_DATA = 11;
    public static final int CHANGE_DATA = 12;
    public static final int CHANGE_PRECOS_TRANSPORTADORA = 13;
    public static final int CHANGE_PRECO_ARTIGO = 14;
    public static final int MELHORES_VENDEDORES = 15;
    public static final int MELHORES_COMPRADORES = 16;
    public static final int MELHORES_TRANSPORTADORAS = 17;
    public static final int ENCOMENDAS_EMITIDAS_VENDEDOR = 18;
    public static final int VINTAGE_LUCRO = 19;
    public static final int CHANGE_VINTAGE_COMISSAO = 20;
    public static final int SHOW_COMISSAO = 21;
    public static final int LOGIN = 22;
    public static final int LOGOUT = 23;

    private static Map<String,Integer> tabela = new HashMap<String,Integer>();

    public static void fillTabela(){

        Controlador.tabela.put("Ver Catalogos",Controlador.SHOW_DADOS);
        Controlador.tabela.put("Inserir Utilizador",Controlador.INSERT_UTILIZADOR);
        Controlador.tabela.put("Inserir Transportadora",Controlador.INSERT_TRANPORTADORA);
        Controlador.tabela.put("Inserir Artigo Mala",Controlador.INSERT_ARTIGO_MALA);
        Controlador.tabela.put("Inserir Artigo Tshirt",Controlador.INSERT_ARTIGO_TSHIRT);
        Controlador.tabela.put("Inserir Artigo Sapatilha",Controlador.INSERT_ARTIGO_SAPATILHA);
        Controlador.tabela.put("Criar Encomenda",Controlador.INSERT_ENCOMENDA);
        Controlador.tabela.put("Inserir Artigo Encomenda",Controlador.INSERT_ARTIGO_ENCOMENDA);
        Controlador.tabela.put("Remover Artigo Encomenda",Controlador.REMOVE_ARTIGO_ENCOMENDA);
        Controlador.tabela.put("Finalizar Encomenda",Controlador.CHECK_OUT_ENCOMENDA);
        Controlador.tabela.put("Ver Data",Controlador.SHOW_DATA);
        Controlador.tabela.put("Alterar Data",Controlador.CHANGE_DATA);
        Controlador.tabela.put("Devolver Encomenda",Controlador.REFUND_ENCOMENDA);
        Controlador.tabela.put("Alterar Precos Transportadora",Controlador.CHANGE_PRECOS_TRANSPORTADORA);
        Controlador.tabela.put("Alterar Preco Artigo",Controlador.CHANGE_PRECO_ARTIGO);
        Controlador.tabela.put("Melhores Vendedores",Controlador.MELHORES_VENDEDORES);
        Controlador.tabela.put("Melhores Compradores",Controlador.MELHORES_COMPRADORES);
        Controlador.tabela.put("Melhores Transportadoras",Controlador.MELHORES_TRANSPORTADORAS);
        Controlador.tabela.put("Encomendas Emitidas Vendedor", Controlador.ENCOMENDAS_EMITIDAS_VENDEDOR);
        Controlador.tabela.put("Vintage Lucro",Controlador.VINTAGE_LUCRO);
        Controlador.tabela.put("Alterar Comissao",Controlador.CHANGE_VINTAGE_COMISSAO);
        Controlador.tabela.put("Ver Comissao",Controlador.SHOW_COMISSAO);
        Controlador.tabela.put("Login",Controlador.LOGIN);
        Controlador.tabela.put("Logout",Controlador.LOGOUT);
    }

    private static int getCodigo(String identificador){
        return Controlador.tabela.get(identificador);
    }

    public static String getEntidadeLogged(){
        return ControladorLogin.getEntidadeLogged();
    }

    public static void collectDadosLine(String[] tokens, Gestor gestor){

        try{

            switch (Controlador.getCodigo(tokens[0])){

                case Controlador.LOGIN:
                case Controlador.LOGOUT:
                    ControladorLogin.setContas(gestor,tokens,Controlador.getCodigo(tokens[0]));
                    break;

                case Controlador.SHOW_DADOS:
                case Controlador.SHOW_DATA:
                case Controlador.SHOW_COMISSAO:
                    ControladorInfo.getInfo(gestor,Controlador.getCodigo(tokens[0]));
                    break;

                case Controlador.INSERT_ARTIGO_MALA:
                case Controlador.INSERT_ARTIGO_TSHIRT:
                case Controlador.INSERT_ARTIGO_SAPATILHA:
                case Controlador.INSERT_ENCOMENDA:
                    ControladorRegistos.insertRegistoUtilizador(gestor,tokens,Controlador.getCodigo(tokens[0]),ControladorLogin.getLogged());
                    break;

                case Controlador.INSERT_UTILIZADOR:
                case Controlador.INSERT_TRANPORTADORA:
                    ControladorRegistos.insertRegistoVintage(gestor,tokens,Controlador.getCodigo(tokens[0]),ControladorLogin.getLogged());
                    break;

                case Controlador.REFUND_ENCOMENDA:
                case Controlador.CHECK_OUT_ENCOMENDA:
                case Controlador.CHANGE_PRECO_ARTIGO:
                case Controlador.INSERT_ARTIGO_ENCOMENDA:
                case Controlador.REMOVE_ARTIGO_ENCOMENDA:
                    ControladorRegistos.alterarRegistoUtilizador(gestor,tokens,Controlador.getCodigo(tokens[0]),ControladorLogin.getLogged());
                    break;

                case Controlador.CHANGE_DATA:
                case Controlador.CHANGE_VINTAGE_COMISSAO:
                case Controlador.CHANGE_PRECOS_TRANSPORTADORA:
                    ControladorRegistos.alterarRegistoVintage(gestor,tokens,Controlador.getCodigo(tokens[0]),ControladorLogin.getLogged());
                    break;

                case Controlador.VINTAGE_LUCRO:
                case Controlador.MELHORES_VENDEDORES:
                case Controlador.MELHORES_COMPRADORES:
                case Controlador.MELHORES_TRANSPORTADORAS:
                case Controlador.ENCOMENDAS_EMITIDAS_VENDEDOR:
                    ControladorEstatisticas.getEstatistica(gestor,tokens,Controlador.getCodigo(tokens[0]));
                    break;
            }
        }

        catch (Exception e){
            
            if (tokens[0].length() > 0){
                Tratador.trataException(new Exception("Registo inválido: " + tokens[0]));
            }
        }
    }
}