package gestores;

import artigos.*;
import encomendas.*;
import calendario.*;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.stream.*;
import java.util.ArrayList;
import java.time.LocalDate;


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

    public Artigo removeArtigoEncomenda(int codigo_encomenda, String codigo_artigo){
        return this.catalogo_encomendas.get(codigo_encomenda).removeArtigo(codigo_artigo);
    }

    public void finalizarEncomenda(int codigo_encomenda){
        this.catalogo_encomendas.get(codigo_encomenda).finalizarEncomenda();
    }

    public void expedirEncomenda(int codigo_encomenda){
        this.catalogo_encomendas.get(codigo_encomenda).expedirEncomenda();
    }

    public void removeEncomenda(int codigo_encomenda){
        this.catalogo_encomendas.remove(codigo_encomenda);
    }

    // Getters

    public List<Artigo> getArtigosEncomenda(int codigo_encomenda){
        return this.catalogo_encomendas.get(codigo_encomenda).getArtigos();
    }

    public String getEstadoEncomenda(int codigo_encomenda){
        return this.catalogo_encomendas.get(codigo_encomenda).getEstado();
    }

    public int getCompradorEncomenda(int codigo_encomenda){
        return this.catalogo_encomendas.get(codigo_encomenda).getComprador();
    }

    public LocalDate getDataEncomenda(int codigo_encomenda){
        return this.catalogo_encomendas.get(codigo_encomenda).getDataCriacao();
    }

    public List<Integer> getAllEncomendasProntas(){

        ArrayList<Integer> result = new ArrayList<Integer>();

        for (Map.Entry<Integer,Encomenda> aux : this.catalogo_encomendas.entrySet()){

            if (aux.getValue().getEstado().equals(Encomenda.FINALIZADA)
                && Calendario.getIntervaloDias(aux.getValue().getDataCriacao(),Calendario.getData()) > 1){

                result.add(aux.getKey());
            }
        }

        return result;
    } 

    // toString

    public String toString(){

        return this.catalogo_encomendas
                    .entrySet()
                    .stream()
                    .map((x) -> x.getValue().toString())
                    .reduce("Catalogo Encomendas:", (a,b) -> a + "\n" + b);
    }
}