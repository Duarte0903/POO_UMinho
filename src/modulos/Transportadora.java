package modulos;

import modulos.artigos.Artigo;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.stream.*;
import java.io.Serializable;


public class Transportadora implements Serializable, EstatisticasVisivel{

    private static final long serialVersionUID = 7L;

    private String nome;
    private double base_enc_pequena;
    private double base_enc_media;
    private double base_enc_grande;
    private double mult_imposto;
    private boolean premium;
    private Map<Integer,Map.Entry<Double,List<Artigo>>> encomendas_expedidas;

    // Construtores

    public Transportadora(String nome, double base_enc_pequena, double base_enc_media, double base_enc_grande, double mult_imposto, boolean premium){
        this.nome = nome;
        this.base_enc_pequena = base_enc_pequena;
        this.base_enc_media = base_enc_media;
        this.base_enc_grande = base_enc_grande;
        this.mult_imposto = mult_imposto;
        this.premium = premium;
        this.encomendas_expedidas = new HashMap<Integer,Map.Entry<Double,List<Artigo>>>();
    }

    // Clone

    public Transportadora clone(){

        Transportadora result = new Transportadora(
            this.nome,
            this.base_enc_pequena,
            this.base_enc_media,
            this.base_enc_grande,
            this.mult_imposto,
            this.premium
        );

        result.encomendas_expedidas = this.encomendas_expedidas
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                (x) -> x.getKey(),
                (x) -> new AbstractMap.SimpleEntry<Double,List<Artigo>>(
                    x.getValue().getKey(),
                    x.getValue().getValue().stream().map((y) -> y.clone()).collect(Collectors.toList()))));

        return result;
    }

    // Getters

    public String getNome(){
        return this.nome;
    }

    public double getBaseEncPequena(){
        return this.base_enc_pequena;
    }

    public double getBaseEncMedia(){
        return this.base_enc_media;
    }

    public double getBaseEncGrande(){
        return this.base_enc_grande;
    }

    public double getMultImporto(){
        return this.mult_imposto;
    }

    public boolean getPremium(){
        return this.premium;
    }

    public double getFaturacao(){
        return this.encomendas_expedidas
                    .values()
                    .stream()
                    .mapToDouble((x) -> x.getKey())
                    .sum();
    }

    // Setters

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setBaseEncPequena(double base_enc_pequena){
        this.base_enc_pequena = base_enc_pequena;
    }

    public void setBaseEncMedia(double base_enc_media){
        this.base_enc_media = base_enc_media;
    }

    public void setBaseEncGrande(double base_enc_grande){
        this.base_enc_grande = base_enc_grande;
    }

    public void setMultImposto(double mult_imposto){
        this.mult_imposto = mult_imposto;
    }

    // Metodos

    public boolean containsEncomenda(int codigo_encomenda){
        return this.encomendas_expedidas.containsKey(codigo_encomenda);
    }

    private double calculaPreco(List<Artigo> artigos){

        double imposto = this.base_enc_pequena;

        if (artigos.size() > 5) imposto = this.base_enc_grande;

        else if (artigos.size() > 1) imposto = this.base_enc_media;

        return imposto*(1+mult_imposto)*0.9;
    }

    public void addArtigo(int codigo_encomenda, Artigo artigo){

        this.encomendas_expedidas.putIfAbsent(
            codigo_encomenda,
            new AbstractMap.SimpleEntry<Double,List<Artigo>>(0.0,new ArrayList<Artigo>())
        );

        this.encomendas_expedidas.get(codigo_encomenda).getValue().add(artigo);

        this.encomendas_expedidas.put(
            codigo_encomenda,
            new AbstractMap.SimpleEntry<Double,List<Artigo>>(
                this.calculaPreco(this.encomendas_expedidas.get(codigo_encomenda).getValue()),
                this.encomendas_expedidas.get(codigo_encomenda).getValue())
        );
    }

    public boolean equals(Object object){

        if (this == object) return true;

        if (object == null || this.getClass() != object.getClass()) return false;

        Transportadora that = (Transportadora) object;

        return this.nome.equals(that.getNome());
    }

    public int hashCode(){
        return this.nome.hashCode();
    }

    public String visualiza(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("\033[48;5;240mNome: ").append(this.nome);
        buffer.append("\tImposto Pequena: ").append(this.base_enc_pequena);
        buffer.append("\tImposto Media: ").append(this.base_enc_media);
        buffer.append("\tImposto Grande: ").append(this.base_enc_grande);
        buffer.append("\tImposto: ").append(this.mult_imposto);
        buffer.append("\tPremium: ").append(this.premium);
        buffer.append("\u001B[0m\n\033[38;5;226m\u001B[1mENCOMENDAS EXPEDIDAS\u001B[0m\n");
        if (this.encomendas_expedidas.size() > 0){
            buffer.append(this.encomendas_expedidas
                .values()
                .stream()
                .map((x) -> x.getValue().stream().map(Visivel::visualiza) .collect(Collectors.joining("\n")))
                .collect(Collectors.joining("\n","","\n")));
        }

        return buffer.toString();
    }

    public String visualizaEstatistica(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("Nome: ").append(this.nome);
        buffer.append("\tDinheiro Ganho: ").append(this.getFaturacao());

        return buffer.toString();
    }
}