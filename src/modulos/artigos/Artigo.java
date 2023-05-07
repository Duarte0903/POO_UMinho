package modulos.artigos;
import modulos.Calendario;
import modulos.Visivel;
import java.time.LocalDate;
import java.io.Serializable;


public abstract class Artigo implements Serializable, Visivel{

    private static final long serialVersionUID = 1L;

    public static String USADO = "usado";
    public static String NOVO = "novo";

    private String nome;
    private String codigo;
    private String marca;
    private double preco;
    private double desconto;
    private String estado;

    /* caso seja um artigo usado*/

    private int avaliacao; /* de 1 a 5 */
    private int n_donos;

    /* Informações extra */

    private int vendedor;
    private String transportadora;
    private LocalDate data;
    private boolean premium;

    // Construtores

    public Artigo(String nome, String codigo, String marca, double preco, double desconto, String estado, int avaliacao, int n_donos, int vendedor, String transportadora, boolean premium){
        this.nome = nome;
        this.codigo = codigo;
        this.marca = marca;
        this.preco = preco;
        this.desconto = desconto;
        this.estado = estado;
        this.avaliacao = avaliacao;
        this.n_donos = n_donos;
        this.vendedor = vendedor;
        this.transportadora = transportadora;
        this.data = Calendario.getData();
        this.premium = premium;
    }

    public Artigo(Artigo artigo){
        this(artigo.getNome(),
            artigo.getCodigo(),
            artigo.getMarca(),
            artigo.getPreco(),
            artigo.getDesconto(),
            artigo.getEstado(),
            artigo.getAvaliacao(),
            artigo.getNDonos(),
            artigo.getVendedor(),
            artigo.getTransportadora(),
            artigo.getPremium());

        this.data = artigo.getData();
    }

    // Clone

    public abstract Artigo clone();

    // Getters

    public String getNome(){
        return this.nome;
    }

    public String getCodigo(){
        return this.codigo;
    }

    public String getEstado(){
        return this.estado;
    }

    public Double getPreco(){
        return this.preco;
    }

    public String getMarca(){
        return this.marca;
    }

    public double getDesconto(){
        return this.desconto;
    }

    public int getAvaliacao(){
        return this.avaliacao;
    }

    public int getNDonos(){
        return this.n_donos;
    }

    public int getVendedor(){
        return this.vendedor;
    }

    public String getTransportadora(){
        return this.transportadora;
    }

    public LocalDate getData(){
        return this.data;
    }

    public boolean getPremium(){
        return this.premium;
    }

    // Setters

    public void setPreco(double preco){
        this.preco = preco;
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    // Metodos

    public abstract double calculaPreco();

    public boolean equals(Object object){

        if (this == object) return true;

        if (object == null || this.getClass() != object.getClass()) return false;

        Artigo that = (Artigo) object;

        return this.codigo.equals(that.getCodigo());
    }

    public int hashCode(){
        return this.codigo.hashCode();
    }

    public String visualiza(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("Vendedor: ").append(this.vendedor);
        buffer.append("\tTranspostadora: ").append(this.transportadora);
        buffer.append("\tCodigo: ").append(this.codigo);
        buffer.append("\tPreco: ").append(this.preco);
        buffer.append("\tDesconto: ").append(this.desconto);
        buffer.append("\tEstado: ").append(this.estado);
        buffer.append("\tPremium: ").append(this.premium);

        return buffer.toString();
    }
}