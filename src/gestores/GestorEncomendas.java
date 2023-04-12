package gestores;

import artigos.*;
import encomendas.*;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.*;


public class GestorEncomendas{

    private Map<Integer,Encomenda> catalogo_encomendas;

    public GestorEncomendas(){
        this.catalogo_encomendas = new HashMap<Integer,Encomenda>();
    }

    public void addEncomenda(Encomenda encomenda){
        this.catalogo_encomendas.put(encomenda.hashCode(),encomenda.clone());
    }

    public void addArtigoEncomenda(int codigo_encomenda, Artigo artigo){
        this.catalogo_encomendas.get(codigo_encomenda).addArtigo(artigo);
    }

    public void finalizarEncomenda(int codigo_encomenda){
        this.catalogo_encomendas.get(codigo_encomenda).finalizarEncomenda();
    }

    public String getEstadoEncomenda(int codigo_encomenda){
        return this.catalogo_encomendas.get(codigo_encomenda).getEstado();
    }

    public String toString(){

        return this.catalogo_encomendas
                    .entrySet()
                    .stream()
                    .map((x) -> x.getValue().toString())
                    .reduce("Catalogo Encomendas:", (a,b) -> a + "\n" + b);
    }
}