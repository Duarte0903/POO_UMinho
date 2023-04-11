package gestores;

import utilizadores.*;
import transportadoras.*;

public class Gestor{

    public GestorUtilizadores gestor_utilizadores;
    public GestorTransportadoras gestor_transportadoras;

    public Gestor(){
        this.gestor_utilizadores = new GestorUtilizadores();
        this.gestor_transportadoras = new GestorTransportadoras();
    }

    public void insertUtilizador(Utilizador utilizador){
        this.gestor_utilizadores.addUtilizador(utilizador);
    }

    public void insertTransportadora(Transportadora transportadora){
        this.gestor_transportadoras.addTransportadora(transportadora);
    }

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append(this.gestor_utilizadores.toString());
        buffer.append("\n---------------------------------\n");
        buffer.append(this.gestor_transportadoras.toString());

        return buffer.toString();
    }
}