package modulos.artigos;


public class Tshirt extends Artigo{

    private static final long serialVersionUID = 4L;

    private static final String LISO = "liso";

    private String tamanho; /* S M L XL */
    private String padrao; /* liso riscas palmeiras */

    // Construtor

    public Tshirt(int vendedor, String transportadora, String nome, String codigo, String marca, double preco, double desconto, String estado, int avaliacao, int n_donos, boolean premium, String tamanho, String padrao){
        super(nome,codigo,marca,preco,desconto,estado,avaliacao,n_donos,vendedor,transportadora,premium);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    public Tshirt(Tshirt tshirt){
        super(tshirt);
        this.tamanho = tshirt.getTamanho();
        this.padrao = tshirt.getPadrao();
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

        if (!this.padrao.equals(Tshirt.LISO)){

            if (super.getEstado().equals(Artigo.USADO)) preco /= 2;

            else preco -= super.getDesconto()*preco;
        }

        return preco;
    }

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append("\tTamanho: ").append(this.tamanho);
        buffer.append("\tPadrao: ").append(this.padrao);

        return buffer.toString();
    }
}