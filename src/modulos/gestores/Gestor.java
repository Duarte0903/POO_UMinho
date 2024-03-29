package modulos.gestores;

import escritor.Escritor;
import modulos.Calendario;
import modulos.artigos.Artigo;
import modulos.Encomenda;
import modulos.Utilizador;
import modulos.Visivel;
import modulos.Fatura.TIPO;
import modulos.Transportadora;
import modulos.Tratador;
import modulos.Fatura;
import java.util.Map;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.function.Predicate;


public class Gestor implements Serializable, Visivel{

    private static final long serialVersionUID = 12L;
    private static double vintageComissao = 0.1;

    private GestorArtigos gestor_artigos;
    private GestorEncomendas gestor_encomendas;
    private GestorUtilizadores gestor_utilizadores;
    private GestorTransportadoras gestor_transportadoras;

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
        if (comissao >= 1) Tratador.trataException(new Exception("Comissão inválida"));
        else Gestor.vintageComissao = Math.abs(comissao);
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
        try {this.gestor_utilizadores.addUtilizador(utilizador);}
        catch (Exception e) {Tratador.trataException(e);}
    }

    public void insertTransportadora(Transportadora transportadora){
        try {this.gestor_transportadoras.addTransportadora(transportadora);}
        catch (Exception e) {Tratador.trataException(e);}
    }

    public void insertArtigo(Artigo artigo){

        try{
            this.gestor_transportadoras.lookUpTransportadoraArtigo(artigo.getTransportadora(),artigo.getPremium());
            this.gestor_utilizadores.lookUpUtilizador(artigo.getVendedor());
            this.gestor_artigos.addArtigo(artigo);
            this.gestor_utilizadores.addUtilizadorArtigoAVenda(artigo);
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void removeArtigo(String codigo_artigo, int utilizador){

        try{
            this.gestor_utilizadores.removeUtilizadorArtigoAVenda(
                this.gestor_artigos.getArtigo(codigo_artigo,utilizador));
            this.gestor_artigos.removeArtigo(codigo_artigo);
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void insertEncomenda(Encomenda encomenda){

        try{
            this.gestor_utilizadores.lookUpUtilizador(encomenda.getComprador());
            this.gestor_encomendas.addEncomenda(encomenda);
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void insertArtigoEncomenda(int codigo_encomenda, String codigo_artigo, int utilizador){

        try{
            this.gestor_encomendas.lookUpEncomenda(codigo_encomenda,utilizador,Encomenda.PENDENTE);
            Artigo result = this.gestor_artigos.removeArtigo(codigo_artigo);
            this.gestor_utilizadores.removeUtilizadorArtigoAVenda(result);
            this.gestor_encomendas.addArtigoEncomenda(codigo_encomenda,result);
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void removeArtigoEncomenda(int codigo_encomenda, String codigo_artigo, int utilizador){

        try{
            this.gestor_encomendas.lookUpEncomenda(codigo_encomenda,utilizador,Encomenda.PENDENTE);
            this.insertArtigo(this.gestor_encomendas.removeArtigoEncomenda(codigo_encomenda,codigo_artigo));
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void finalizarEncomenda(int codigo_encomenda, int utilizador){

        try{
            this.gestor_encomendas.lookUpEncomenda(codigo_encomenda,utilizador,Encomenda.PENDENTE);
            this.gestor_encomendas.finalizarEncomenda(codigo_encomenda);
            this.gestor_encomendas.getArtigosEncomenda(codigo_encomenda).forEach((x) -> {
                this.gestor_utilizadores.addUtilizadorArtigoVendido(x,new Fatura(codigo_encomenda,x,TIPO.VENDA));
                this.gestor_utilizadores.addUtilizadorArtigoAdquirido(
                    this.gestor_encomendas.getCompradorEncomenda(codigo_encomenda),x,new Fatura(codigo_encomenda,x,TIPO.COMPRA));
            });
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    private void expedirEncomenda(int codigo_encomenda){

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
            this.gestor_encomendas.getAllEncomendasProntas().forEach((x) -> this.expedirEncomenda(x));
        }

        catch(Exception e) {Tratador.trataException(e);}
    }

    public void devolverEncomenda(int codigo_encomenda, int utilizador){

        try{
            this.gestor_encomendas.lookUpEncomenda(codigo_encomenda,utilizador,Encomenda.EXPEDIDA);

            if (!Calendario.checkPrazoDevolucao(this.gestor_encomendas.getDataEncomenda(codigo_encomenda))){
                throw new Exception("Devolução fora de prazo");
            }

            this.gestor_encomendas.getArtigosEncomenda(codigo_encomenda).forEach((x) -> {
                this.gestor_utilizadores.removeUtilizadorArtigoVendido(x,new Fatura(codigo_encomenda,x,TIPO.VENDA));
                this.gestor_utilizadores.removeUtilizadorArtigoAdquirido(utilizador,x,new Fatura(codigo_encomenda,x,TIPO.COMPRA));
                x.setData(Calendario.getData());
                this.insertArtigo(x);
            });

            this.gestor_encomendas.removeEncomenda(codigo_encomenda);
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void alterarPrecoArtigo(String codigo_artigo, double preco, int utilizador){

        try{
            Artigo artigo = this.gestor_artigos.getArtigo(codigo_artigo,utilizador);
            this.gestor_artigos.alterarPrecoArtigo(artigo.getCodigo(),preco);
            this.gestor_utilizadores.alterarPrecoArtigo(artigo.getVendedor(),artigo.getCodigo(),preco);
        }

        catch (Exception e) {Tratador.trataException(e);}
    }

    public void alterarPrecosTransportadora(String transportadora, Double base_enc_pequena, Double base_enc_media, Double base_enc_grande, Double mult_imposto){
        try {this.gestor_transportadoras.alterarPrecosTransportadora(transportadora,base_enc_pequena,base_enc_media,base_enc_grande,mult_imposto);}
        catch (Exception e) {Tratador.trataException(e);}
    }

    public void getMelhoresUtilizadores(Predicate<Fatura> filtro){
        Escritor.escreveEstatisticasVisisvel(this.gestor_utilizadores.getMelhoresUtilizadores(filtro));
    }

    public void getMelhoresTransportadoras(){
        Escritor.escreveEstatisticasVisisvel(this.gestor_transportadoras.getMelhoresTransportadoras());
    }

    public void getEncomendasEmitidasVendedor(int utilizador){
        Escritor.escreveEstatisticasVisisvel(this.gestor_encomendas.getEncomendasEmitidasVendedor(utilizador));
    }

    public void getLucroVintage(){
        Escritor.escreve(this.gestor_utilizadores.getLucroVintage());
    }

    public Map.Entry<Integer,String> loginUtilizador(String email, String password){
        try {return this.gestor_utilizadores.login(email,password);}
        catch (Exception e) {Tratador.trataException(e); return null;}
    }

    public String visualiza(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("\n\033[38;5;214m\u001B[1mUTILIZADORES\u001B[0m\n\n");
        buffer.append(this.gestor_utilizadores.visualiza());
        buffer.append("\n\n\033[38;5;214m\u001B[1mTRANSPORTADORAS\u001B[0m\n\n");
        buffer.append(this.gestor_transportadoras.visualiza());
        buffer.append("\n\033[38;5;214m\u001B[1mSTOCK DE ARTIGOS\u001B[0m\n\n");
        buffer.append(this.gestor_artigos.visualiza());
        buffer.append("\n\n\033[38;5;214m\u001B[1mENCOMENDAS\u001B[0m\n\n");
        buffer.append(this.gestor_encomendas.visualiza());

        return buffer.toString();
    }
}