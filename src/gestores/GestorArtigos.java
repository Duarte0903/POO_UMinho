package gestores;

import artigos.Artigo;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.*;
import java.io.Serializable;


public class GestorArtigos implements Serializable{

    private static final long serialVersionUID = 8L;

    private Map<Integer,Artigo> catalogo_artigos;

    public GestorArtigos(){
        this.catalogo_artigos = new HashMap<Integer,Artigo>();
    }

    public void addArtigo(Artigo artigo){
        this.catalogo_artigos.put(artigo.hashCode(),artigo.clone());
    }

    public Artigo removeArtigo(String codigo_artigo){

        Artigo result = this.catalogo_artigos.get(codigo_artigo.hashCode()).clone();
        this.catalogo_artigos.remove(codigo_artigo.hashCode());

        return result;
    }

    public boolean lookUp(Artigo artigo){
        return this.catalogo_artigos.containsKey(artigo.hashCode());
    }

    public String toString(){

        return this.catalogo_artigos
                    .entrySet()
                    .stream()
                    .map((x) -> x.getValue().toString())
                    .reduce("Catalogo Artigos:", (a,b) -> a + "\n" + b);
    }
}