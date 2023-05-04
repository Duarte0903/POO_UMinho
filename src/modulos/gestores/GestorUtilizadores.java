package modulos.gestores;

import modulos.artigos.Artigo;
import modulos.Utilizador;
import modulos.Estatisticas;
import modulos.Fatura;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.AbstractMap;
import java.io.Serializable;


public class GestorUtilizadores implements Serializable{

    private static final long serialVersionUID = 9L;

    private List<Utilizador> catalogo_utilizadores;

    public GestorUtilizadores(){
        this.catalogo_utilizadores = new ArrayList<Utilizador>();
    }

    public static int getAutoIncrement(){
        return Utilizador.getAutoIncrement();
    }

    public static void setAutoIncrement(int x){
        Utilizador.setAutoIncrement(x);
    }

    public int getSize(){
        return this.catalogo_utilizadores.size();
    }

    public void lookUpUtilizador(int codigo) throws Exception{
        if (codigo < 0 || codigo >= this.getSize()){
            throw new Exception("Utilizador inexistente");
        }
    }

    public void lookupUtilizadorByEmail(String email) throws Exception{
        if (this.catalogo_utilizadores.stream().filter((x) -> x.getEmail().equals(email)).count() != 1){
            throw new Exception("Utilizador inexistente");
        }
    }

    private int getCodigo(String email) throws Exception{
        this.lookupUtilizadorByEmail(email);
        return this.catalogo_utilizadores.stream().filter((x) -> x.getEmail().equals(email)).mapToInt((x) -> x.getCodigo()).sum();
    }

    public void addUtilizador(Utilizador utilizador){
        this.catalogo_utilizadores.add(utilizador.clone());
    }

    public void addUtilizadorArtigoAVenda(Artigo artigo) throws Exception{
        this.catalogo_utilizadores.get(artigo.getVendedor()).addArtigoAVenda(artigo);
    }

    public void removeUtilizadorArtigoAVenda(Artigo artigo){
        this.catalogo_utilizadores.get(artigo.getVendedor()).removeArtigoAVenda(artigo);
    }

    public void addUtilizadorArtigoVendido(Artigo artigo, Fatura fatura){
        this.catalogo_utilizadores.get(artigo.getVendedor()).addArtigoVendido(artigo,fatura);
    }

    public void removeUtilizadorArtigoVendido(Artigo artigo, Fatura fatura){
        this.catalogo_utilizadores.get(artigo.getVendedor()).removeArtigoVendido(artigo,fatura);
    }

    public void addUtilizadorArtigoAdquirido(int utilizador, Artigo artigo, Fatura fatura){
        this.catalogo_utilizadores.get(utilizador).addArtigoAdquirido(artigo,fatura);
    }

    public void removeUtilizadorArtigoAdquirido(int utilizador, Artigo artigo, Fatura fatura){
        this.catalogo_utilizadores.get(utilizador).removeArtigoAdquirido(artigo,fatura);
    }

    public void alterarPrecoArtigo(int utilizador, String codigo_artigo, double preco){
        this.catalogo_utilizadores.get(utilizador).alterarPreco(codigo_artigo,preco);
    }

    public List<Utilizador> getMelhoresUtilizadores(Predicate<Fatura> filtro){
        return Estatisticas.getMelhoresUtilizadores(this.catalogo_utilizadores,filtro);
    }

    public double getLucroVintage(){
        return Estatisticas.getLucroVintage(catalogo_utilizadores);
    }

    public Map.Entry<Integer,String> login(String email, String password) throws Exception{
        if (!this.catalogo_utilizadores.get(this.getCodigo(email)).getPassword().equals(password)){
            throw new Exception("Password inválida");
        }

        return new AbstractMap.SimpleEntry<Integer,String>(
            this.getCodigo(email),
            this.catalogo_utilizadores.get(this.getCodigo(email)).getNome());
    }

    public String toString(){
        return this.catalogo_utilizadores
                    .stream()
                    .map((x) -> x.toString())
                    .reduce("Catalogos Utilizadores:", (a,b) -> a + "\n" + b);
    }
}