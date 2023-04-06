package entidades.artigo;

import entidades.Transportadora;

public class Artigo
{
    private String nome;

    private String codigo;

    private Transportadora transportadora;

    private String estado; // novo ou usado

    private double preco;

    private String marca;

    private String colecao;

    private boolean premium;

    private double desconto;

    public Artigo (String nome, String codigo, Transportadora transportadora, String estado, Double preco, String marca, String colecao, boolean premium, double desconto) {
        this.nome = nome;
        this.codigo = codigo;
        this.transportadora = transportadora;
        this.estado = estado;
        this.preco = preco;
        this.marca = marca;
        this.colecao = colecao;
        this.premium = premium;
        this.desconto = desconto;
    }

    public String get_nome () {
        return this.nome;
    }

    public void set_nome (String nome) {
        this.nome = nome;
    }

    public String get_codigo () {
        return this.codigo;
    }

    public void set_codigo (String codigo) {
        this.codigo = codigo;
    }

    public Transportadora get_transportadora () {
        return this.transportadora;
    }

    public void set_transportadora (Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public String get_estado () {
        return this.estado;
    }

    public void set_estado (String estado) {
        this.estado = estado;
    }

    public Double get_preco () {
        return this.preco;
    }

    public void set_preco (Double preco) {
        this.preco = preco;
    }

    public String get_marca () {
        return this.marca;
    }

    public void set_marca (String marca) {
        this.marca = marca;
    }

    public String get_colecao () {
        return this.colecao;
    }

    public void set_colecao () {
        this.colecao = colecao;
    }

    public boolean get_premium () {
        return this.premium;
    }

    public void set_premium (boolean premium) {
        this.premium = premium;
    }

    public double get_desconto () {
        return this.desconto;
    }

    public void set_desconto (double desconto) {
        this.desconto = desconto;
    }
}