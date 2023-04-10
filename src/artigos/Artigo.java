package artigos;

public class Artigo{

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

    /* outra informacoes */

    private int vendedor;
    private String transportadora;

    // Construtores

    public Artigo(String nome, String codigo, String marca, double preco, double desconto, String estado, int avaliacao, int n_donos, int vendedor, String transportadora){
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
            artigo.getTransportadora());
    }

    // Clone

    public Artigo clone(){
        return new Artigo(this);
    }

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

    // Metodos

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("\nNome: ").append(this.nome);
        buffer.append("\tCodigo: ").append(this.codigo);
        buffer.append("\tMarca: ").append(this.marca);
        buffer.append("\tPreco: ").append(this.preco);
        buffer.append("\tDesconto: ").append(this.desconto);
        buffer.append("\tEstado: ").append(this.estado);
        buffer.append("\tAvaliacao: ").append(this.avaliacao);
        buffer.append("\tNDonos: ").append(this.n_donos);
        buffer.append("\tVendedor: ").append(this.vendedor);
        buffer.append("\tTranspostadora: ").append(this.transportadora);

        return buffer.toString();
    }

    public boolean equals(Object object){
        
        if (this == object) return true;

        if (object == null || this.getClass() != object.getClass()) return false;

        Artigo that = (Artigo) object;

        return this.codigo.equals(that.getCodigo());
    }

    public int hashCode(){
        return this.codigo.hashCode();
    }
}