package artigos;

public class Tshirt extends Artigo{

    public static String LISO = "liso";

    private String tamanho; /* S M L XL */
    private String padrao; /* liso riscas palmeniras */

    // Construtor

    public Tshirt(String nome, String codigo, String marca, double preco, double desconto, String estado, int avaliacao, int n_donos, String tamanho, String padrao){
        super(nome,codigo,marca,preco,desconto,estado,avaliacao,n_donos);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    public Tshirt(Tshirt tshirt){
        super(tshirt);
        this.tamanho = tshirt.getTamanho();
        this.padrao = this.getPadrao();
    }

    // Clone

    public Tshirt clone(){
        return new Tshirt(this);
    }

    // Getters

    public String getTamanho(){
        return this.tamanho;
    }

    public String getPadrao(){
        return this.padrao;
    }

    // Metodos

    public double calculaPreco(){

        double preco = super.getPreco();

        if (!this.padrao.equals(Tshirt.LISO)) preco /= 2;

        return preco;
    }

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append("\tTamanho: ").append(this.tamanho);
        buffer.append("\tPadrao: ").append(this.tamanho);

        return buffer.toString();
    }
}