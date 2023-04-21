package utilizadores;

import artigos.Artigo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Collections;


public class Utilizador implements Serializable{

    private static final long serialVersionUID = 5L;
    private static int AUTO_INCREMENT = 0;

    private int codigo;
    private String email;
    private String nome;
    private int nif;
    private String morada;

    private List<Artigo> artigos_a_venda;
    private List<Artigo> artigos_vendidos;
    private List<Artigo> artigos_adquiridos;

    // Construtor

    public Utilizador(String email, String nome, int nif, String morada){
        this.codigo = Utilizador.AUTO_INCREMENT++;
        this.email = email;
        this.nome = nome;
        this.nif = nif;
        this.morada = morada;
        this.artigos_a_venda = new ArrayList<Artigo>();
        this.artigos_vendidos = new ArrayList<Artigo>();
        this.artigos_adquiridos = new ArrayList<Artigo>();
    }

    // Clone

    public Utilizador clone(){
        
        Utilizador.AUTO_INCREMENT--;

        Utilizador utilizador = new Utilizador(
            this.email,
            this.nome,
            this.nif,
            this.morada);

        utilizador.setCodigo(this.codigo);
        utilizador.artigos_a_venda = this.cloneArtigos(this.artigos_a_venda);
        utilizador.artigos_vendidos = this.cloneArtigos(this.artigos_vendidos);
        utilizador.artigos_adquiridos = this.cloneArtigos(this.artigos_adquiridos);

        return utilizador;
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

    public String getMorada(){
        return this.morada;
    }

    // Setters

    private void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setNif(int nif){
        this.nif = nif;
    }

    public void setMorada(String morada){
        this.morada = morada;
    }

    private List<Artigo> cloneArtigos(List<Artigo> lista){
        return lista.stream().map((x) -> x.clone()).collect(Collectors.toList());
    }

    // Metodos

    public void addArtigoAVenda(Artigo artigo){
        this.artigos_a_venda.add(artigo.clone());
    }

    public void removeArtigoAVenda(Artigo artigo){
        this.artigos_a_venda.removeIf((x) -> x.getCodigo().equals(artigo.getCodigo()));
    }

    public void addArtigoVendido(Artigo artigo){
        this.artigos_vendidos.add(artigo.clone());
    }

    public void removeArtigoVendido(Artigo artigo){
        this.artigos_vendidos.removeIf((x) -> x.getCodigo().equals(artigo.getCodigo()));
    }

    public void addArtigoAdquirido(Artigo artigo){
        this.artigos_adquiridos.add(artigo.clone());
    }

    public void removeArtigoAdquirido(Artigo artigo){
        this.artigos_adquiridos.removeIf((x) -> x.getCodigo().equals(artigo.getCodigo()));
    }

    public double userProfit(LocalDate d1, LocalDate d2) {
        double user_profit = 0;

        for (Artigo a : this.artigos_vendidos) {
            LocalDate saleDate = a.getData();

            if ((d1 == null || saleDate.isAfter(d1)) && (d2 == null || saleDate.isBefore(d2))) {
                user_profit += a.getPreco();
            }
        }

        return user_profit;
    }

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("\nCodigo: ").append(this.codigo);
        buffer.append("\tEmail: ").append(this.email);
        buffer.append("\tNome: ").append(this.nome);
        buffer.append("\tNif: ").append(this.nif);
        buffer.append("\tMorada: ").append(this.morada);
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