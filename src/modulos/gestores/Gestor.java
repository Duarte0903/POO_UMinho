package modulos.gestores;

import escritor.Escritor;
import modulos.Calendario;
import modulos.artigos.Artigo;
import modulos.Encomenda;
import modulos.Utilizador;
import modulos.Transportadora;
import modulos.Tratador;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.function.Predicate;
import java.util.AbstractMap.SimpleEntry;


public class Gestor implements Serializable{

    private static final long serialVersionUID = 12L;
    private static double vintageComissao = 0.1;

    public GestorArtigos gestor_artigos;
    public GestorEncomendas gestor_encomendas;
    public GestorUtilizadores gestor_utilizadores;
    public GestorTransportadoras gestor_transportadoras;

    public Gestor(){
        this.gestor_artigos = new GestorArtigos();
        this.gestor_encomendas = new GestorEncomendas();
        this.gestor_utilizadores = new GestorUtilizadores();
        this.gestor_transportadoras = new GestorTransportadoras();
    }

    public static double getComissao(){
        return Gestor.vintageComissao;
    }

    public static void setComissao(double comissao){
        Gestor.vintageComissao = comissao;
    }

    public static int getAutoIncrementUtilizador(){
        return GestorUtilizadores.getAutoIncrement();
    }

    public static int getAutoIncrementEncomenda(){
        return GestorEncomendas.getAutoIncrement();
    }

    public static void setAutoIncrementUtilizador(int x){
        GestorUtilizadores.setAutoIncrement(x);
    }

    public static void setAutoIncrementEncomeda(int x){
        GestorEncomendas.setAutoIncrement(x);
    }

    public void insertUtilizador(Utilizador utilizador){
        this.gestor_utilizadores.addUtilizador(utilizador);
    }

    public void insertTransportadora(Transportadora transportadora){
        try {this.gestor_transportadoras.addTransportadora(transportadora);}
        catch (Exception e) {Tratador.trataException(e);}
    }

    public void insertArtigo(Artigo artigo){

        try{
            if (this.gestor_transportadoras.getTransportadora(artigo.getTransportadora()).getPremium() != artigo.getPremium()){
                throw new Exception("Artigo e Transportadora de tipos diferentes");
            }

            this.gestor_utilizadores.addUtilizadorArtigoAVenda(artigo);
            this.gestor_artigos.addArtigo(artigo);
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void insertEncomenda(Encomenda encomenda){

        try{
            if (encomenda.getComprador() < 0 || encomenda.getComprador() >= gestor_utilizadores.getSize()){
                throw new Exception("Comprador não identificado");
            }

            this.gestor_encomendas.addEncomenda(encomenda);
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void insertArtigoEncomenda(int codigo_encomenda, String codigo_artigo){

        try{
            if (!this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda).equals(Encomenda.PENDENTE)){
                throw new Exception("Encomenda não pendente");
            }

            Artigo result = this.gestor_artigos.removeArtigo(codigo_artigo);
            this.gestor_utilizadores.removeUtilizadorArtigoAVenda(result);
            this.gestor_encomendas.addArtigoEncomenda(codigo_encomenda,result);
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void removeArtigoEncomenda(int codigo_encomenda, String codigo_artigo){

        try{
            if (!this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda).equals(Encomenda.PENDENTE)){
                throw new Exception("Encomenda não pendente");
            }

            this.insertArtigo(this.gestor_encomendas.removeArtigoEncomenda(codigo_encomenda,codigo_artigo));
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void finalizarEncomenda(int codigo_encomenda){

        try{
            if (!this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda).equals(Encomenda.PENDENTE)){
                throw new Exception("Encomenda não pendente");
            }

            this.gestor_encomendas.finalizarEncomenda(codigo_encomenda);
            this.gestor_encomendas.getArtigosEncomenda(codigo_encomenda).forEach((x) -> {
                this.gestor_utilizadores.addUtilizadorArtigoVendido(x);
                this.gestor_utilizadores.addUtilizadorArtigoAdquirido(
                    this.gestor_encomendas.getCompradorEncomenda(codigo_encomenda),x);
            });
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void expedirEncomenda(int codigo_encomenda){

        this.gestor_encomendas.expedirEncomenda(codigo_encomenda);
        this.gestor_encomendas.getArtigosEncomenda(codigo_encomenda).forEach((x) -> {
            x.setData(x.getData().plusDays(2));
            this.gestor_transportadoras.addArtigoTransportadora(
                x.getTransportadora(),
                codigo_encomenda,
                x);
        });
    }

    public void updateData(LocalDate data){

        try{
            if (Calendario.getIntervaloDias(Calendario.getData(),data) < 0){
                throw new Exception("Não é possivel retroceder");
            }

            Calendario.setData(data);
            this.gestor_encomendas.getAllEncomendasProntas().forEach((x) -> {
                this.expedirEncomenda(x);
                this.gestor_transportadoras.updatePrecoEncomenda(x);
            });
        }

        catch(Exception e) {Tratador.trataException(e);}
    }

    public void devolverEncomenda(int codigo_encomenda){

        try{
            if (!this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda).equals(Encomenda.EXPEDIDA)){
                throw new Exception("Encomenda não expedida");
            }

            if (!Calendario.checkPrazoDevolucao(this.gestor_encomendas.getDataEncomenda(codigo_encomenda))){
                throw new Exception("Devolução fora de prazo");
            }

            int comprador = this.gestor_encomendas.getCompradorEncomenda(codigo_encomenda);

            this.gestor_encomendas.getArtigosEncomenda(codigo_encomenda).forEach((x) -> {
                this.gestor_utilizadores.removeUtilizadorArtigoVendido(x);
                this.gestor_utilizadores.removeUtilizadorArtigoAdquirido(comprador,x);
                x.setData(Calendario.getData());
                this.insertArtigo(x);
            });

            this.gestor_encomendas.removeEncomenda(codigo_encomenda);
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void alterarPrecoArtigo(String codigo_artigo, double preco){

        try{
            Artigo artigo = this.gestor_artigos.getArtigo(codigo_artigo);
            this.gestor_artigos.alterarPrecoArtigo(artigo.getCodigo(),preco);
            this.gestor_utilizadores.alterarPrecoArtigo(artigo.getVendedor(),artigo.getCodigo(),preco);
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void alterarPrecosTransportadora(String transportadora, Double base_enc_pequena, Double base_enc_media, Double base_enc_grande, Double mult_imposto){
        try {this.gestor_transportadoras.alterarPrecosTransportadora(transportadora,base_enc_pequena,base_enc_media,base_enc_grande,mult_imposto);}
        catch (Exception e) {Tratador.trataException(e);}
    }

    public void getMelhoresVendedores(Predicate<Artigo> filtro){
        Escritor.printMelhoresVendedores(this.gestor_utilizadores.getMelhoresVendedores(filtro));
    }

    public void getMelhoresCompradores(Predicate<Artigo> filtro){
        Escritor.printMelhoresCopmradores(this.gestor_utilizadores.getMelhoresCompradores(filtro));
    }

    public void getMelhoresTransportadoras(){
        Escritor.printMelhoresTransportadoras(this.gestor_transportadoras.getMelhoresTransportadoras());
    }

    public void getEncomendasEmitidasVendedor(int utilizador){
        Escritor.printEncomendasEmitidasVendedor(this.gestor_encomendas.getEncomendasEmitidasVendedor(utilizador));
    }

    public void getLucroVintage(){
        Escritor.escreve(this.gestor_encomendas.getLucroVintage());
    }

    public Map.Entry<Integer,String> loginUtilizador(String email, String password){
        try {return this.gestor_utilizadores.login(email,password);}
        catch (Exception e) {Tratador.trataException(e); return null;}
    }

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append(this.gestor_utilizadores.toString());
        buffer.append("\n------------------------------------\n");
        buffer.append(this.gestor_transportadoras.toString());
        buffer.append("\n------------------------------------\n");
        buffer.append(this.gestor_artigos.toString());
        buffer.append("\n------------------------------------\n");
        buffer.append(this.gestor_encomendas.toString());

        return buffer.toString();
    }
}