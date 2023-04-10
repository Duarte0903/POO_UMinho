package artigos;

public class Mala extends Artigo{

    int tamanho;

    // Construtores

    public Mala(String nome, String codigo, String marca, double preco, double desconto, String estado, int avaliacao, int n_donos, int vendedor, String transportadora, int tamanho){
        super(nome,codigo,marca,preco,desconto,estado,avaliacao,n_donos,vendedor,transportadora);
        this.tamanho = tamanho;
    }

    public Mala(Mala mala){
        super(mala);
        this.tamanho = mala.getTamanho();
    }

    public Mala clone(){
        return new Mala(this);
    }

    // Getter

    public int getTamanho(){
        return this.tamanho;
    }

    // Metodos

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append("\tTamanho: ").append(this.tamanho);

        return buffer.toString();
    }
}