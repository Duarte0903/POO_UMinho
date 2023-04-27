package modulos.gestores;

import modulos.artigos.Artigo;
import modulos.Encomenda;
import modulos.Calendario;
import modulos.Estatisticas;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.stream.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.Serializable;


public class GestorEncomendas implements Serializable{

    private static final long serialVersionUID = 10L;

    private Map<Integer,Encomenda> catalogo_encomendas;

    public GestorEncomendas(){
        this.catalogo_encomendas = new HashMap<Integer,Encomenda>();
    }

    public static int getAutoIncrement(){
        return Encomenda.getAutoIncrement();
    }

    public static void setAutoIncrement(int x){
        Encomenda.setAutoIncrement(x);
    }

    private void lookUpEncomenda(int codigo_encomenda) throws Exception{
        if (!this.catalogo_encomendas.containsKey(codigo_encomenda)){
            throw new Exception("Encomenda inexistente");
        }
    }

    public void addEncomenda(Encomenda encomenda){
        this.catalogo_encomendas.put(encomenda.hashCode(),encomenda.clone());
    }

    public void addArtigoEncomenda(int codigo_encomenda, Artigo artigo) throws Exception{
        this.lookUpEncomenda(codigo_encomenda);
        this.catalogo_encomendas.get(codigo_encomenda).addArtigo(artigo);
    }

    public Artigo removeArtigoEncomenda(int codigo_encomenda, String codigo_artigo) throws Exception{
        this.lookUpEncomenda(codigo_encomenda);
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

    public String getEstadoEncomenda(int codigo_encomenda) throws Exception{
        this.lookUpEncomenda(codigo_encomenda);
        return this.catalogo_encomendas.get(codigo_encomenda).getEstado();
    }

    public int getCompradorEncomenda(int codigo_encomenda){
        return this.catalogo_encomendas.get(codigo_encomenda).getComprador();
    }

    public LocalDate getDataEncomenda(int codigo_encomenda){
        return this.catalogo_encomendas.get(codigo_encomenda).getDataCriacao();
    }

    public double getLucroVintage(){
        return Estatisticas.getLucroVintage(this.catalogo_encomendas);
    }

    public List<Encomenda> getEncomendasEmitidasVendedor(int utilizador){
        return Estatisticas.getEncomendasEmitidasVendedor(utilizador,this.catalogo_encomendas);
    }

    public List<Integer> getAllEncomendasProntas(){

        return this.catalogo_encomendas
                    .entrySet()
                    .stream()
                    .filter((x) -> x.getValue().getEstado().equals(Encomenda.FINALIZADA)
                                && Calendario.getIntervaloDias(x.getValue().getDataCriacao(),Calendario.getData()) > 1)
                    .map((x) -> x.getKey())
                    .collect(Collectors.toList());
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