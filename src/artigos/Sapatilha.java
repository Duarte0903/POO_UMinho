package artigos;

import calendario.Calendario;
import java.time.LocalDate;

public class Sapatilha extends Artigo{

    private String cor;
    private int tamanho;
    private boolean atacadores;
    private LocalDate colecao;

    // Construtores

    public Sapatilha(){
        super();

        this.cor = "";
        this.tamanho = 0;
        this.atacadores = false;
        this.colecao = LocalDate.now();

    }

    public Sapatilha(String nome, String codigo, String marca, double preco, double desconto, String estado, int avaliacao, int n_donos, String cor, int tamanho, boolean atacadores, LocalDate colecao){
        super(nome,codigo,marca,preco,desconto,estado,avaliacao,n_donos);

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

/*    public double calculaPreco(){

        return this
    }
*/

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append("\nCor: ").append(this.cor);
        buffer.append("\nTamanho: ").append(this.tamanho);
        buffer.append("\nAtacadores: ").append(this.atacadores);
        buffer.append("\nColecao: ").append(this.colecao);

        return this.toString();
    }
}
