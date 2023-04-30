package controlador;

import modulos.gestores.Gestor;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import escritor.Escritor;


public class ControladorLogin{

    private static int logged = -1;
    private static String entidade_logged = "Vintage";

    public static int getLogged(){
        return ControladorLogin.logged;
    }

    public static String getEntidadeLogged(){
        return ControladorLogin.entidade_logged;
    }

    public static void setContas(Gestor gestor, String[] tokens, int flag){

        switch (flag){

            case Controlador.LOGIN:
                Map.Entry<Integer,String> registo = gestor.loginUtilizador(tokens[1],tokens[2]);
                
                if (registo != null && registo.getKey() != -1){
                    ControladorLogin.logged = registo.getKey();
                    ControladorLogin.entidade_logged = registo.getValue();
                }
                
                break;

            case Controlador.LOGOUT:
                ControladorLogin.logged = -1;
                ControladorLogin.entidade_logged = "Vintage";
                break;
        }
    }
}