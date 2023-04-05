package entidades.user;

public class Morada
{
    private String rua;

    private int n_porta;

    private String zip;

    private String cidade;

    public Morada (String rua, int n_porta, String zip, String cidade) {
        this.rua = rua;
        this.n_porta = n_porta;
        this.zip = zip;
        this.cidade = cidade;
    }

    public String get_rua () {
        return this.rua;
    }

    public void set_rua (String rua) {
        this.rua = rua;
    }

    public int get_n_porta () {
        return this.n_porta;
    }

    public void set_n_porta (int n_porta) {
        this.n_porta = n_porta;
    }

    public String get_zip () {
        return this.zip;
    }

    public void set_zip (String zip) {
        this.zip = zip;
    }

    public String get_cidade () {
        return this.cidade;
    }

    public void set_cidade (String cidade) {
        this.cidade = cidade;
    }

    public boolean equals (Object o) {
        if (this == o)
            return true;

        if (this.getClass() != o.getClass())
            return false;

        Morada morada = (Morada) o;

        return super.equals(morada) &&
                this.rua == morada.rua &&
                this.n_porta == morada.n_porta &&
                this.zip == morada.zip &&
                this.cidade == morada.cidade;
    }

    public String toString () {
        return super.toString() +
                "Rua: " + this.rua + "\n" +
                "Número de Porta: " + this.n_porta + "\n" +
                "ZIP: " + this.zip + "\n" +
                "Cidade: " + this.cidade + "\n";
    }
}