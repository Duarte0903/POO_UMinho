package gestores;

import artigos.Artigo;
import encomendas.Encomenda;
import calendario.Calendario;
import estatisticas.Estatisticas;
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

    public double getLucroVintage(double comissao){
        return Estatisticas.getLucroVintage(this.catalogo_encomendas,comissao);
    }

    public void getEncomendasEmitidasVendedor(int utilizador){
        Estatisticas.getEncomendasEmitidasVendedor(utilizador,this.catalogo_encomendas)
                    .forEach((x) -> System.out.println(x.toString()));
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