package modulos.artigos;

import modulos.Calendario;
import java.time.LocalDate;
import java.io.Serializable;


public class Sapatilha extends Artigo{

    private static final long serialVersionUID = 3L;

    private String cor;
    private int tamanho;
    private boolean atacadores;
    private LocalDate colecao;

    // Construtores

    public Sapatilha(int vendedor, String transportadora, String nome, String codigo, String marca, double preco, double desconto, String estado, int avaliacao, int n_donos, boolean premium, String cor, int tamanho, boolean atacadores, LocalDate colecao){
        super(nome,codigo,marca,preco,desconto,estado,avaliacao,n_donos,vendedor,transportadora,premium);
        this.cor = cor;
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.colecao = colecao;
    }

    public Sapatilha(Sapatilha sapatilha){
        super(sapatilha);
        this.cor = sapatilha.getCor();
        this.tamanho = sapatilha.getTamanho();
        this.atacadores = sapatilha.getAtacadores();
        this.colecao = sapatilha.getColecao();
    }

    // Clone

    public Sapatilha clone(){
        return new Sapatilha(this);
    }

    // Getters

    public String getCor(){
        return this.cor;
    }

    public int getTamanho(){
        return this.tamanho;
    }

    public boolean getAtacadores(){
        return this.atacadores;
    }

    public LocalDate getColecao(){
        return this.colecao;
    }

    // Metodos

    public double calculaPreco(){

        double preco = super.getPreco();

        if (super.getPremium()){
            
            preco += Calendario.getIntervaloAnos(this.colecao,Calendario.getData());
        }

        else if (super.getEstado().equals(Artigo.USADO)){

            if (super.getNDonos() != 0 && super.getAvaliacao() != 0){
            
                preco -= preco/(super.getNDonos()*super.getAvaliacao());
            }
        }

        else if (this.tamanho > 45) preco -= super.getDesconto()*preco;

        return preco;
    }


    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append("\tCor: ").append(this.cor);
        buffer.append("\tTamanho: ").append(this.tamanho);
        buffer.append("\tAtacadores: ").append(this.atacadores);
        buffer.append("\tColecao: ").append(this.colecao);

        return buffer.toString();
    }
}
