package transportadoras;

import artigos.Artigo;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.*;
import java.io.Serializable;


public class Transportadora implements Serializable{

    private static final long serialVersionUID = 7L;

    private String nome;
    private double base_enc_pequena;
    private double base_enc_media;
    private double base_enc_grande;
    private double mult_imposto;
    private Map<Integer,List<Artigo>> encomendas_expedidas;

    // Construtor;

    public Transportadora(String nome, double base_enc_pequena, double base_enc_media, double base_enc_grande, double mult_imposto){
        this.nome = nome;
        this.base_enc_pequena = base_enc_pequena;
        this.base_enc_media = base_enc_media;
        this.base_enc_grande = base_enc_grande;
        this.mult_imposto = mult_imposto;
        this.encomendas_expedidas = new HashMap<Integer,List<Artigo>>();
    }

    // Clone

    public Transportadora clone(){

        Transportadora result = new Transportadora(
            this.nome,
            this.base_enc_pequena,
            this.base_enc_media,
            this.base_enc_grande,
            this.mult_imposto);

        result.encomendas_expedidas = this.encomendas_expedidas
                                        .entrySet()
                                        .stream()
                                        .collect(Collectors.toMap(
                                            (x) -> x.getKey(),
                                            (x) -> x.getValue()
                                                    .stream()
                                                    .map((y) -> y.clone())
                                                    .collect(Collectors.toList())));

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

    // Setter

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

    public void addArtigo(int codigo_encomenda, Artigo artigo){

        if (!this.encomendas_expedidas.containsKey(codigo_encomenda)){

            this.encomendas_expedidas.put(codigo_encomenda, new ArrayList<Artigo>());
        }

        this.encomendas_expedidas.get(codigo_encomenda).add(artigo.clone());
    }

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("\nNome: ").append(this.nome);
        buffer.append("\tBase_enc_pequena: ").append(this.base_enc_pequena);
        buffer.append("\tBase_enc_media: ").append(this.base_enc_media);
        buffer.append("\tBase_enc_grande: ").append(this.base_enc_grande);
        buffer.append("\tMult_importo: ").append(this.mult_imposto);
        buffer.append("\nEncomendas expedidas: ");
        buffer.append(this.encomendas_expedidas
                            .entrySet()
                            .stream()
                            .map((x) -> x.getKey().toString() + x.getValue().toString())
                            .reduce("", (a,b) -> a + "\n" + b));

        return buffer.toString();
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
}