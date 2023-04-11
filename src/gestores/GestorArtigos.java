package gestores;

import artigos.*;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.*;


public class GestorArtigos{

    private Map<Integer,Artigo> catalogo_artigos;

    public GestorArtigos(){
        this.catalogo_artigos = new HashMap<Integer,Artigo>();
    }

    public void addArtigo(Artigo artigo){
        this.catalogo_artigos.put(artigo.hashCode(),artigo.clone());
    }

    public String toString(){

        return this.catalogo_artigos
                    .entrySet()
                    .stream()
                    .map((x) -> x.getValue().toString())
                    .reduce("Catalogo Artigos:", (a,b) -> a + "\n" + b);
    }
}