package artigos;

public class Artigo{

    private String nome;
    private String codigo;
    private String marca;
    private double preco;
    private double desconto;
    private String estado;

    /* caso seja um artigo usado*/

    private int avaliacao; /* de 1 a 5 */
    private int n_donos;

    // Construtores

    public Artigo(){
        this.nome = "";
        this.codigo = "";
        this.preco = 0.0;
        this.desconto = 0.0;
        this.marca = "";
        this.estado = "";
        this.avaliacao = 0;
        this.n_donos = 0;        
    }

    public Artigo(String nome, String codigo, String marca, double preco, double desconto, String estado, int avaliacao, int n_donos){
        this.nome = nome;
        this.codigo = codigo;
        this.marca = marca;
        this.preco = preco;
        this.desconto = desconto;
        this.estado = estado;
        this.avaliacao = avaliacao;
        this.n_donos = n_donos;
    }

    public Artigo(Artigo artigo){
        this(artigo.getNome(),
            artigo.getCodigo(),
            artigo.getMarca(),
            artigo.getPreco(),
            artigo.getDesconto(),
            artigo.getEstado(),
            artigo.getAvaliacao(),
            artigo.getNDonos());
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