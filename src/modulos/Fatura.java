package modulos;

import java.time.LocalDate;
import modulos.gestores.Gestor;
import modulos.artigos.Artigo;
import java.io.Serializable;;


public class Fatura implements Serializable{

    private static final long serialVersionUID = 13L;
    public enum TIPO {COMPRA,VENDA,COMISSAO};

    private int codigo_encomenda;
    private double preco;
    private double comissao;
    private LocalDate data;
    private TIPO tipo;

    // Construtor

    public Fatura(int codigo_encomenda, double preco, TIPO tipo){
        this.codigo_encomenda = codigo_encomenda;
        this.preco = preco;
        this.tipo = tipo;
        this.data = Calendario.getData();
        this.comissao = Gestor.getComissao();
        if (tipo == TIPO.VENDA) this.preco *= (1-this.comissao);
        else if (tipo == TIPO.COMISSAO) this.preco *= this.comissao;
    }

    public Fatura(int codigo_encomenda, Artigo artigo, TIPO tipo){
        this(codigo_encomenda,artigo.calculaPreco(),tipo);
    }

    public Fatura(Fatura fatura){
        this.codigo_encomenda = fatura.getCodigoEncomenda();
        this.preco = fatura.getPreco();
        this.comissao = fatura.getComissao();
        this.data = fatura.getData();
        this.tipo = fatura.getTipo();
    }

    // Clone

    public Fatura clone(){
        return new Fatura(this);
    }

    // Getters

    public double getPreco(){
        return this.preco;
    }

    public double getComissao(){
        return this.comissao;
    }

    public int getCodigoEncomenda(){
        return codigo_encomenda;
    }

    public LocalDate getData(){
        return this.data;
    }

    public TIPO getTipo(){
        return this.tipo;
    }

    // Metodos

    public void addPreco(double preco){
        this.preco += preco;
    }

    public Fatura duplicado(){
        return new Fatura(
            this.codigo_encomenda,
            this.preco/(1-this.comissao),
            TIPO.COMISSAO
        );
    }

    public int hashCode(){

        int result = this.codigo_encomenda;

        switch (this.tipo){

            case COMPRA:
                result += 1000000;
                break;

            case COMISSAO:
                result += 2000000;
                break;

            case VENDA:
                result += 3000000;
                break;
        }

        return result;
    }
}