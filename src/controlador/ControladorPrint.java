package controlador;

import modulos.gestores.Gestor;
import modulos.Calendario;
import escritor.Escritor;


public class ControladorPrint{

    public static void getInfo(Gestor gestor, int flag){

        switch (flag){

            case Controlador.SHOW_DADOS:
                Escritor.escreve(gestor.toString());
                break;

            case Controlador.SHOW_DATA:
                Escritor.escreve(Calendario.getData().toString());
                break;

            case Controlador.SHOW_COMISSAO:
                Escritor.escreve(Gestor.getComissao());
                break;
        }
    }       
}