package controlador;

import modulos.gestores.Gestor;
import modulos.Calendario;
import escritor.Escritor;


public class ControladorInfo{

    public static void getInfo(Gestor gestor, int flag){

        switch (flag){

            case Controlador.SHOW_DADOS:
                Escritor.escreveVisivel(gestor);
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