package modulos.gestores;

import modulos.artigos.Artigo;
import modulos.Utilizador;
import modulos.Estatisticas;
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

    public void addUtilizadorArtigoAVenda(Artigo artigo) throws Exception{
        if (artigo.getVendedor() < 0 || artigo.getVendedor() >= this.catalogo_utilizadores.size()){
            throw new Exception("Utilizador inexistente");
        }
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
                                                + "\tDinherio Ganho: " + (1-Gestor.getComissao())*x.getPrecoArtigosVendidos()));
    }

    public void dizMelhoresCompradores(Predicate<Artigo> filtro){
        List<Utilizador> ranking = Estatisticas.getMelhoresCompradores(this.catalogo_utilizadores,filtro);
        ranking.forEach((x) -> System.out.println("Nome: " + x.getNome()
                                                + "\tCodigo " + x.getCodigo()
                                                + "\tDinheiro Gasto: " + x.getPrecoArtigosAdquiridos()));
    }

    public String toString(){

        return this.catalogo_utilizadores
                    .stream()
                    .map((x) -> x.toString())
                    .reduce("Catalogos Utilizadores:", (a,b) -> a + "\n" + b);
    }
}