package gestores;

import utilizadores.Utilizador;

import java.util.*;

public class GestorUtilizadores{

    private List<Utilizador> catalogo_utilizadores;

    public GestorUtilizadores(){
        this.catalogo_utilizadores = new ArrayList<Utilizador>();
    }

    public void addUtilizador(Utilizador utilizador){
        this.catalogo_utilizadores.add(utilizador);
    }

    public String toString(){

        return this.catalogo_utilizadores
                    .stream()
                    .map((x) -> x.toString())
                    .reduce("Catalogo Utilizadores:", (a,b) -> a + "\n" + b);
    }
}