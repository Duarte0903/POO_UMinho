package modulos.gestores;

import modulos.artigos.Artigo;
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

    public void addArtigo(Artigo artigo) throws Exception{
        if (this.catalogo_artigos.containsKey(artigo.hashCode())){
            throw new Exception("Artigo já inserido");
        }
        this.catalogo_artigos.put(artigo.hashCode(),artigo.clone());
    }

    public Artigo removeArtigo(String codigo_artigo){

        Artigo result = this.catalogo_artigos.get(codigo_artigo.hashCode()).clone();
        this.catalogo_artigos.remove(codigo_artigo.hashCode());

        return result;
    }

    public Artigo getArtigo(String codigo_artigo){
        return this.catalogo_artigos.get(codigo_artigo.hashCode()).clone();
    }

    public boolean lookUp(Artigo artigo){
        return this.catalogo_artigos.containsKey(artigo.hashCode());
    }

    public void alterarPrecoArtigo(String codigo_artigo, double preco){
        this.catalogo_artigos.get(codigo_artigo.hashCode()).setPreco(preco);
    }

    public String toString(){

        return this.catalogo_artigos
                    .entrySet()
                    .stream()
                    .map((x) -> x.getValue().toString())
                    .reduce("Catalogo Artigos:", (a,b) -> a + "\n" + b);
    }
}