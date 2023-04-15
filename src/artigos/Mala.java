package artigos;

import calendario.Calendario;
import java.time.LocalDate;
import java.io.Serializable;


public class Mala extends Artigo{

    private static final long serialVersionUID = 2L;

    private int tamanho;
    private String material;
    private LocalDate colecao;

    // Construtores

    public Mala(int vendedor, String transportadora, String nome, String codigo, String marca, double preco, double desconto, String estado, int avaliacao, int n_donos, boolean premium, int tamanho, String material, LocalDate colecao){
        super(nome,codigo,marca,preco,desconto,estado,avaliacao,n_donos,vendedor,transportadora, premium);
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

        double preco = super.getPreco();

        if (super.getPremium()){

            preco += super.getDesconto()*Calendario.getIntervaloAnos(this.colecao,Calendario.getData());
        }

        else preco -= preco/this.tamanho;

        return preco;
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