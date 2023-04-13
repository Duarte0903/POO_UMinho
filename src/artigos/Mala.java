package artigos;

import java.time.LocalDate;

public class Mala extends Artigo{

    int tamanho;
    String material;
    LocalDate colecao;

    // Construtores

    public Mala(int vendedor, String transportadora, String nome, String codigo, String marca, double preco, double desconto, String estado, int avaliacao, int n_donos, int tamanho, String material, LocalDate colecao){
        super(nome,codigo,marca,preco,desconto,estado,avaliacao,n_donos,vendedor,transportadora);
        this.tamanho = tamanho;
        this.material = material;
        this.colecao = colecao;
    }

    public Mala(Mala mala){
        super(mala);
        this.tamanho = mala.getTamanho();
        this.material = mala.getMaterial();
        this.colecao = mala.getColecao();
    }

    public Mala clone(){
        return new Mala(this);
    }

    // Getter

    public int getTamanho(){
        return this.tamanho;
    }

    public String getMaterial(){
        return this.material;
    }

    public LocalDate getColecao(){
        return this.colecao;
    }

    // Metodos

    public double calculaPreco(){
        return super.getPreco() - super.getPreco()/this.tamanho;
    }

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append("\tTamanho: ").append(this.tamanho);
        buffer.append("\tMaterial: ").append(this.material);
        buffer.append("\tColecao: ").append(this.colecao);

        return buffer.toString();
    }
}