package modulos;

import modulos.artigos.Artigo;
import modulos.Fatura.TIPO;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.*;
import java.io.Serializable;
import java.util.function.Predicate;


public class Utilizador implements Serializable{

    private static final long serialVersionUID = 5L;
    private static int AUTO_INCREMENT = 0;

    private int codigo;
    private String email;
    private String password;
    private String nome;
    private int nif;
    private String morada;
    private double dinheiro_faturacao;

    private List<Artigo> artigos_a_venda;
    private List<Artigo> artigos_vendidos;
    private List<Artigo> artigos_adquiridos;
    private Map<Integer,Fatura> faturas;

    // Construtor

    public Utilizador(String email, String password, String nome, int nif, String morada){
        this.codigo = Utilizador.AUTO_INCREMENT++;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.nif = nif;
        this.morada = morada;
        this.dinheiro_faturacao = 0;
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
        utilizador.setDinheiroFaturacao(this.dinheiro_faturacao);
        utilizador.artigos_a_venda = this.cloneArtigos(this.artigos_a_venda);
        utilizador.artigos_vendidos = this.cloneArtigos(this.artigos_vendidos);
        utilizador.artigos_adquiridos = this.cloneArtigos(this.artigos_adquiridos);
        utilizador.faturas = this.faturas.values().stream().map(Fatura::clone).collect(Collectors.toMap((x) -> x.getCodigoEncomenda(),(x) -> x));

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

    public double getDinheiroFaturacao(){
        return this.dinheiro_faturacao;
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

    public void setDinheiroFaturacao(double dinheiro_faturacao){
        this.dinheiro_faturacao = dinheiro_faturacao;
    }

    private List<Artigo> cloneArtigos(List<Artigo> lista){
        return lista.stream().map((x) -> x.clone()).collect(Collectors.toList());
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

    public void alterarPreco(String codigo_artigo, double preco){
        this.artigos_a_venda.forEach((x) -> {
            if (x.getCodigo().equals(codigo_artigo)) x.setPreco(preco);
        });
    }

    public void addArtigoFatura(Artigo artigo, int codigo, double comissao){
        TIPO tipo = TIPO.VENDA;
        if (Double.compare(comissao,0) == 0) tipo = TIPO.COMPRA;
        if (!this.faturas.containsKey(codigo+tipo.hashCode())) this.faturas.put(codigo+tipo.hashCode(),new Fatura(codigo,0,tipo));
        this.faturas.get(codigo+tipo.hashCode()).addPreco(artigo.calculaPreco()*(1-comissao));
    }

    public void removeFatura(int codigo){
        this.faturas.remove(codigo+TIPO.COMPRA.hashCode());
        this.faturas.remove(codigo+TIPO.VENDA.hashCode());
    }

    public void calculaFaturacao(Predicate<Fatura> filtro){
        this.setDinheiroFaturacao(this.faturas.values().stream().filter((x) -> filtro.test(x)).mapToDouble((x) -> x.getPreco()).sum());
    }

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("\nCodigo: ").append(this.codigo);
        buffer.append("\tEmail: ").append(this.email);
        buffer.append("\tNome: ").append(this.nome);
        buffer.append("\tNif: ").append(this.nif);
        buffer.append("\tMorada: ").append(this.morada);
        buffer.append("\nFaturas: ").append(this.faturas.values().toString());
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