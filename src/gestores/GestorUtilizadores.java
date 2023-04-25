package gestores;

import artigos.Artigo;
import utilizadores.Utilizador;
import estatisticas.Estatisticas;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.io.Serializable;


public class GestorUtilizadores implements Serializable{

    private static final long serialVersionUID = 9L;

    private List<Utilizador> catalogo_utilizadores;

    public GestorUtilizadores(){
        this.catalogo_utilizadores = new ArrayList<Utilizador>();
    }

    public void addUtilizador(Utilizador utilizador){
        this.catalogo_utilizadores.add(utilizador.clone());
    }

    public void addUtilizadorArtigoAVenda(Artigo artigo){
        this.catalogo_utilizadores.get(artigo.getVendedor()).addArtigoAVenda(artigo);
    }

    public void removeUtilizadorArtigoAVenda(Artigo artigo){
        this.catalogo_utilizadores.get(artigo.getVendedor()).removeArtigoAVenda(artigo);
    }

    public void addUtilizadorArtigoVendido(Artigo artigo){
        this.catalogo_utilizadores.get(artigo.getVendedor()).addArtigoVendido(artigo);
    }

    public void removeUtilizadorArtigoVendido(Artigo artigo){
        this.catalogo_utilizadores.get(artigo.getVendedor()).removeArtigoVendido(artigo);
    }

    public void addUtilizadorArtigoAdquirido(int utilizador, Artigo artigo){
        this.catalogo_utilizadores.get(utilizador).addArtigoAdquirido(artigo);
    }

    public void removeUtilizadorArtigoAdquirido(int utilizador, Artigo artigo){
        this.catalogo_utilizadores.get(utilizador).removeArtigoAdquirido(artigo);
    }

    public void alterarPrecoArtigo(int utilizador, String codigo_artigo, double preco){
        this.catalogo_utilizadores.get(utilizador).alterarPreco(codigo_artigo,preco);
    }

    public int getSize(){
        return this.catalogo_utilizadores.size();
    }

    public void dizMelhoresVendedores(Predicate<Artigo> filtro){
        List<Utilizador> ranking = Estatisticas.getMelhoresVendedores(this.catalogo_utilizadores,filtro);
        ranking.forEach((x) -> System.out.println("Nome: " + x.getNome()
                                                + "\tCodigo " + x.getCodigo()
                                                + "\tDinherio Ganho: " + x.getPrecoArtigosVendidos()));
    }

    public void dizMelhoresCompradores(Predicate<Artigo> filtro){
        List<Utilizador> ranking = Estatisticas.getMelhoresCompradores(this.catalogo_utilizadores,filtro);
        ranking.forEach((x) -> System.out.println("Nome: " + x.getNome()
                                                + "\tCodigo " + x.getCodigo()
                                                + "\tDinheiro Gasto: " + x.getPrecoArtigosAdquiridos()));
    }

    public void dizMelhorVendedor(Predicate<Artigo> filtro){
        
        List<Utilizador> ranking = Estatisticas.getMelhoresVendedores(this.catalogo_utilizadores,filtro);

        if (ranking.size() > 0){
            System.out.println("Nome: " + ranking.get(0).getNome()
                                + "\tCodigo: " + ranking.get(0).getCodigo()
                                + "\tDinheiro Ganho: " + ranking.get(0).getPrecoArtigosVendidos());
        }

        else System.out.println("Não foi possivel identificar o melhor vendedor");
    }

    public String toString(){

        return this.catalogo_utilizadores
                    .stream()
                    .map((x) -> x.toString())
                    .reduce("Catalogos Utilizadores:", (a,b) -> a + "\n" + b);
    }
}