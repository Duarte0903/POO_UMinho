package gestores;

import artigos.Artigo;
import utilizadores.Utilizador;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
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

    public Utilizador getUserWithHighestProfit() {
        Utilizador highest_profit_user = null;
        double max_profit = 0;

        for (Utilizador u : this.catalogo_utilizadores) {
            double user_profit = u.totalUserProfit();
            if (user_profit > max_profit) {
                max_profit = user_profit;
                highest_profit_user = u;
            }
        }

        return highest_profit_user;
    }

    public Utilizador getUserWithHighestProfitBetweenDates(LocalDate d1, LocalDate d2) {
        Utilizador highest_profit_user = null;
        double max_profit = 0;

        for (Utilizador u : this.catalogo_utilizadores) {
            double user_profit = u.userProfitBetweenDates(d1, d2);
            if (user_profit > max_profit) {
                max_profit = user_profit;
                highest_profit_user = u;
            }
        }

        return highest_profit_user;
    }

    public int getSize(){
        return this.catalogo_utilizadores.size();
    }

    public String toString(){

        return this.catalogo_utilizadores
                    .stream()
                    .map((x) -> x.toString())
                    .reduce("Catalogos Utilizadores:", (a,b) -> a + "\n" + b);
    }
}