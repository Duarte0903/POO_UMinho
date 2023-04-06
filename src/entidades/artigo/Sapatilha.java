package entidades.artigo;

import entidades.Transportadora;

public class Sapatilha extends Artigo
{
    private int tamanho;

    private String cor;


    public Sapatilha (String nome, String codigo, Transportadora transportadora, String estado, Double preco, String marca, boolean premium, double desconto, int tamanho, String cor, String colecao) {
        super(nome, codigo, transportadora, estado, preco, marca, colecao, premium, desconto);
        this.tamanho = tamanho;
        this.cor = cor;
    }

    public int get_tamanho () {
        return this.tamanho;
    }

    public void set_tamanho (int tamanho) {
        this.tamanho = tamanho;
    }

    public String get_cor () {
        return this.cor;
    }

    public void set_cor (String cor) {
        this.cor = cor;
    }
}
