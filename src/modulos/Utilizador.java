package modulos;

import modulos.artigos.Artigo;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.*;
import java.io.Serializable;
import java.util.function.Predicate;


public class Utilizador implements Serializable, EstatisticasVisivel{

    private static final long serialVersionUID = 5L;
    private static int AUTO_INCREMENT = 0;

    private int codigo;
    private String email;
    private String password;
    private String nome;
    private int nif;
    private String morada;

    private List<Artigo> artigos_a_venda;
    private List<Artigo> artigos_vendidos;
    private List<Artigo> artigos_adquiridos;
    private Map<Integer,Fatura> faturas;

    private double ultimo_calculo;

    // Construtor

    public Utilizador(String email, String password, String nome, int nif, String morada){
        this.codigo = Utilizador.AUTO_INCREMENT++;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.nif = nif;
        this.morada = morada;
        this.ultimo_calculo = 0;
        this.artigos_a_venda = new ArrayList<Artigo>();
        this.artigos_vendidos = new ArrayList<Artigo>();
        this.artigos_adquiridos = new ArrayList<Artigo>();
        this.faturas = new HashMap<Integer,Fatura>();
    }

    // Clone

    public Utilizador clone(){

        Utilizador.AUTO_INCREMENT--;

        Utilizador utilizador = new Utilizador(
            this.email,
            this.password,
            this.nome,
            this.nif,
            this.morada);

        utilizador.setCodigo(this.codigo);
        utilizador.artigos_a_venda = this.artigos_a_venda.stream().map((x) -> x.clone()).collect(Collectors.toList());
        utilizador.artigos_vendidos = this.artigos_vendidos.stream().map((x) -> x.clone()).collect(Collectors.toList());
        utilizador.artigos_adquiridos = this.artigos_adquiridos.stream().map((x) -> x.clone()).collect(Collectors.toList());
        utilizador.faturas = this.faturas.values().stream().map((x) -> x.clone()).collect(Collectors.toMap((x) -> x.getCodigoEncomenda(),(x) -> x));

        return utilizador;
    }

    // Getters

    public int getCodigo(){
        return this.codigo;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
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

    public static int getAutoIncrement(){
        return Utilizador.AUTO_INCREMENT;
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

    public static void setAutoIncrement(int x){
        Utilizador.AUTO_INCREMENT = x;
    }

    // Metodos

    public void addArtigoAVenda(Artigo artigo){
        this.artigos_a_venda.add(artigo.clone());
    }

    public void removeArtigoAVenda(Artigo artigo){
        this.artigos_a_venda.removeIf((x) -> x.getCodigo().equals(artigo.getCodigo()));
    }

    public void addArtigoVendido(Artigo artigo, Fatura fatura){
        this.artigos_vendidos.add(artigo.clone());
        this.addFatura(fatura.clone());
        this.addFatura(fatura.duplicado());
    }

    public void removeArtigoVendido(Artigo artigo, Fatura fatura){
        this.artigos_vendidos.removeIf((x) -> x.getCodigo().equals(artigo.getCodigo()));
        this.faturas.remove(fatura.hashCode());
        this.faturas.remove(fatura.duplicado().hashCode());
    }

    public void addArtigoAdquirido(Artigo artigo, Fatura fatura){
        this.artigos_adquiridos.add(artigo.clone());
        this.addFatura(fatura.clone());
    }

    public void removeArtigoAdquirido(Artigo artigo, Fatura fatura){
        this.artigos_adquiridos.removeIf((x) -> x.getCodigo().equals(artigo.getCodigo()));
        this.faturas.remove(fatura.hashCode());
    }

    public void alterarPreco(String codigo_artigo, double preco){
        this.artigos_a_venda.forEach((x) -> {
            if (x.getCodigo().equals(codigo_artigo)) x.setPreco(preco);
        });
    }

    private void addFatura(Fatura fatura){
        if (this.faturas.putIfAbsent(fatura.hashCode(),fatura) != null){
            this.faturas.get(fatura.hashCode()).addPreco(fatura.getPreco());
        }
    }

    public double getFaturacao(Predicate<Fatura> filtro){
        this.ultimo_calculo = this.faturas.values().stream().filter((x) -> filtro.test(x)).mapToDouble((x) -> x.getPreco()).sum();
        return ultimo_calculo;
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

    public String visualiza(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("\033[48;5;240mNome: ").append(this.nome);
        buffer.append("\tEmail: ").append(this.email);
        buffer.append("\tCodigo: ").append(this.codigo);
        buffer.append("\u001B[0m\n\033[38;5;226m\u001B[1mARTIGOS À VENDA\u001B[0m");
        if (this.artigos_a_venda.size() > 0) buffer.append(this.artigos_a_venda.stream().map(Visivel::visualiza).collect(Collectors.joining("\n","\n","")));
        buffer.append("\n\033[38;5;226m\u001B[1mARTIGOS VENDIDOS\u001B[0m");
        if (this.artigos_vendidos.size() > 0) buffer.append(this.artigos_vendidos.stream().map(Visivel::visualiza).collect(Collectors.joining("\n","\n","")));
        buffer.append("\n\033[38;5;226m\u001B[1mARTIGO ADQUIRIDOS\u001B[0m");
        if (this.artigos_adquiridos.size() > 0) buffer.append(this.artigos_adquiridos.stream().map(Visivel::visualiza).collect(Collectors.joining("\n","\n","")));

        return buffer.toString();
    }

    public String visualizaEstatistica(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("Nome: ").append(this.nome);
        buffer.append("\tCodigo: ").append(this.codigo);
        buffer.append("\tDinheiro: ").append(this.ultimo_calculo);

        return buffer.toString();
    }
}