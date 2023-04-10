package transportadoras;

import artigos.Artigo;
import java.util.ArrayList;

public class Transportadora{

    private String nome;
    private double base_enc_pequena;
    private double base_enc_media;
    private double base_enc_grande;
    private double mult_imposto;
    private ArrayList<Artigo> artigos_expedidos;

    // Construtor;

    public Transportadora(String nome, double base_enc_pequena, double base_enc_media, double base_enc_grande, double mult_imposto){
        this.nome = nome;
        this.base_enc_pequena = base_enc_pequena;
        this.base_enc_media = base_enc_media;
        this.base_enc_grande = base_enc_grande;
        this.mult_imposto = mult_imposto;
        this.artigos_expedidos = new ArrayList<Artigo>();
    }

    // Getters

    public String getNome(){
        return this.nome;
    }

    // Metodos

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("\nNome: ").append(this.nome);
        buffer.append("\tBase_enc_pequena: ").append(this.base_enc_pequena);
        buffer.append("\tBase_enc_media: ").append(this.base_enc_media);
        buffer.append("\tBase_enc_grande: ").append(this.base_enc_grande);
        buffer.append("\tMult_importo: ").append(this.mult_imposto);
        buffer.append("\nArtigos expedidos: ").append(this.artigos_expedidos.toString());

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