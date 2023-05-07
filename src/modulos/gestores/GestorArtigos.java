package modulos.gestores;

import modulos.Visivel;
import modulos.artigos.Artigo;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.io.Serializable;


public class GestorArtigos implements Serializable, Visivel{

    private static final long serialVersionUID = 8L;

    private Map<Integer,Artigo> catalogo_artigos;

    public GestorArtigos(){
        this.catalogo_artigos = new HashMap<Integer,Artigo>();
    }

    private void lookUpArtigo(int codigo_artigo) throws Exception{
        if (!this.catalogo_artigos.containsKey(codigo_artigo)){
            throw new Exception("Artigo inexistente");
        }
    }

    private void lookUpArtigoUtilizador(int codigo_artigo, int utilizador) throws Exception{
        this.lookUpArtigo(codigo_artigo);
        if (this.catalogo_artigos.get(codigo_artigo).getVendedor() != utilizador){
            throw new Exception("Não possui o artigo");
        }
    }

    public void addArtigo(Artigo artigo) throws Exception{
        if (this.catalogo_artigos.containsKey(artigo.hashCode())){
            throw new Exception("Artigo já inserido");
        }
        this.catalogo_artigos.put(artigo.hashCode(),artigo.clone());
    }

    public Artigo removeArtigo(String codigo_artigo) throws Exception{
        this.lookUpArtigo(codigo_artigo.hashCode());
        return this.catalogo_artigos.remove(codigo_artigo.hashCode()).clone();
    }

    public Artigo getArtigo(String codigo_artigo, int utilizador) throws Exception{
        this.lookUpArtigoUtilizador(codigo_artigo.hashCode(),utilizador);
        return this.catalogo_artigos.get(codigo_artigo.hashCode()).clone();
    }

    public void alterarPrecoArtigo(String codigo_artigo, double preco){
        this.catalogo_artigos.get(codigo_artigo.hashCode()).setPreco(preco);
    }

    public String visualiza(){
        return this.catalogo_artigos
                    .values()
                    .stream()
                    .map(Visivel::visualiza)
                    .collect(Collectors.joining("\n"));
    }
}