package modulos;

import java.time.LocalDate;
import java.io.Serializable;;


public class Fatura implements Serializable{

    private static final long serialVersionUID = 13L;
    public enum TIPO {COMPRA,VENDA};

    private int codigo_encomenda;
    private double preco;
    private LocalDate data;
    private TIPO tipo;

    public Fatura(int codigo_encomenda, double preco, TIPO tipo){
        this.codigo_encomenda = codigo_encomenda;
        this.preco = preco;
        this.data = Calendario.getData();
        this.tipo = tipo;
    }

    public Fatura(Fatura fatura){
        this.codigo_encomenda = fatura.getCodigoEncomenda();
        this.preco = fatura.getPreco();
    }

    public Fatura clone(){
        return new Fatura(this);
    }

    public double getPreco(){
        return this.preco;
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

    public void setPreco(double preco){
        this.preco = preco;
    }

    public void setCodigoEncomenda(int codigo_encomenda){
        this.codigo_encomenda = codigo_encomenda;
    }

    public void addPreco(double preco){
        this.setPreco(this.getPreco()+preco);
    }

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("Encomenda: ").append(this.codigo_encomenda);
        buffer.append("\tPreco: ").append(this.preco);
        buffer.append("\tData: ").append(this.data);
        buffer.append("\tTipo: ").append(this.tipo);

        return buffer.toString();
    }

    public int hashCode(){
        return this.getCodigoEncomenda() + tipo.hashCode();
    }
}