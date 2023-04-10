package utilizadores;

import artigos.Artigo;
import java.util.List;
import java.util.ArrayList;

public class Utilizador{

    private static int AUTO_INCREMENT = 0;

    private int codigo;
    private String email;
    private String nome;
    private int nif;

    private List<Artigo> artigos_a_venda;
    private List<Artigo> artigos_vendidos;
    private List<Artigo> artigos_adquiridos;

    // Construtor

    public Utilizador(String email, String nome, int nif){
        this.codigo = Utilizador.AUTO_INCREMENT++;
        this.email = email;
        this.nome = nome;
        this.nif = nif;
        this.artigos_a_venda = new ArrayList<Artigo>();
        this.artigos_vendidos = new ArrayList<Artigo>();
        this.artigos_adquiridos = new ArrayList<Artigo>();
    }

    // Getters

    public int getCodigo(){
        return this.codigo;
    }

    public String getEmail(){
        return this.email;
    }

    public String getNome(){
        return this.nome;
    }

    public int getNif(){
        return this.nif;
    }

    // Metodos

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("\nCodigo: ").append(this.codigo);
        buffer.append("\nEmail: ").append(this.email);
        buffer.append("\nNome: ").append(this.nome);
        buffer.append("\nNif: ").append(this.nif);
        buffer.append("\nArtigos à venda: ").append(this.artigos_a_venda.toString());
        buffer.append("\nArtigos vendidos: ").append(this.artigos_vendidos.toString());
        buffer.append("\nArtigos adquiridos: ").append(this.artigos_adquiridos.toString());

        return buffer.toString();
    }

    public boolean equals(Object object){

        if (this == object) return true;

        if (object == null || this.getClass() != object.getClass()) return false;

        Utilizador that = (Utilizador) object;

        return this.codigo == that.getCodigo();
    }

    public int hashCode(){
        return this.codigo;
    }
}