package gestores;

import artigos.Artigo;
import utilizadores.Utilizador;

import java.util.*;

public class GestorUtilizadores{

    private List<Utilizador> catalogo_utilizadores;

    public GestorUtilizadores(){
        this.catalogo_utilizadores = new ArrayList<Utilizador>();
    }

    public void addUtilizador(Utilizador utilizador){
        this.catalogo_utilizadores.add(utilizador.clone());
    }

    public void addUtilizadorArtigoAVenda(int codigo, Artigo artigo){
        this.catalogo_utilizadores.get(codigo).addArtigoAVenda(artigo);
    }

    public int getSize(){
        return this.catalogo_utilizadores.size();
    }

    public String toString(){

        return this.catalogo_utilizadores
                    .stream()
                    .map((x) -> x.toString())
                    .reduce("Catalogos Utilizadores:", (a,b) -> a + "\n" + b);
    }
}