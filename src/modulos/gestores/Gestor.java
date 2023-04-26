package modulos.gestores;

import modulos.Calendario;
import modulos.artigos.Artigo;
import modulos.Encomenda;
import modulos.Utilizador;
import modulos.Transportadora;
import modulos.Tratador;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.function.Predicate;


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

    public void insertUtilizador(Utilizador utilizador){
        this.gestor_utilizadores.addUtilizador(utilizador);
    }

    public void insertTransportadora(Transportadora transportadora){
        try {this.gestor_transportadoras.addTransportadora(transportadora);}
        catch (Exception e) {Tratador.trataException(e);}
    }

    public void insertArtigo(Artigo artigo){

        try{
            Transportadora transportadora = this.gestor_transportadoras.getTransportadora(artigo.getTransportadora());

            if (transportadora.getPremium() != artigo.getPremium()){
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

            if (this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda).equals(Encomenda.PENDENTE)){

                Artigo result = this.gestor_artigos.removeArtigo(codigo_artigo);
                this.gestor_utilizadores.removeUtilizadorArtigoAVenda(result);
                this.gestor_encomendas.addArtigoEncomenda(codigo_encomenda,result);
            }

            else System.out.println("Não foi possivel inserir o artigo " + codigo_artigo + ", a encomenda está no estado: "
                                    + this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda));
        }

        catch (Exception e){
            System.out.println("Não foi possivel inserir o artigo " + codigo_artigo
                                + " à encomenda " + codigo_encomenda);
        }
    }

    public void removeArtigoEncomenda(int codigo_encomenda, String codigo_artigo){

        try{

            if (this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda).equals(Encomenda.PENDENTE)){

                Artigo result = this.gestor_encomendas.removeArtigoEncomenda(codigo_encomenda,codigo_artigo);
                this.insertArtigo(result);
            }

            else System.out.println("Não foi possivel remover o artigo " + codigo_artigo + ", a encomenda está no estado: "
                                    + this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda));
        }

        catch (Exception e){
            System.out.println("Não foi possivel remove o artigo " + codigo_artigo
                                + " da encomenda " + codigo_encomenda);
        }
    }

    public void finalizarEncomenda(int codigo_encomenda){

        try {
            
            if (this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda).equals(Encomenda.PENDENTE)){
                
                this.gestor_encomendas.finalizarEncomenda(codigo_encomenda);
                List<Artigo> artigos_encomenda = this.gestor_encomendas.getArtigosEncomenda(codigo_encomenda);

                artigos_encomenda.forEach(
                    (x) -> this.gestor_utilizadores.addUtilizadorArtigoVendido(x));

                artigos_encomenda.forEach(
                    (x) -> this.gestor_utilizadores.addUtilizadorArtigoAdquirido(
                        this.gestor_encomendas.getCompradorEncomenda(codigo_encomenda),x));

            }

            else System.out.println("Não foi possivel finalizar a encomenda, estado atual: "
                                    + this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda));
        }

        catch (Exception e) {System.out.println("Não foi possivel finalizar a encomenda: " + codigo_encomenda);}
    }

    public void expedirEncomenda(int codigo_encomenda){

        try{

            this.gestor_encomendas.expedirEncomenda(codigo_encomenda);
            List<Artigo> artigos_encomenda = this.gestor_encomendas.getArtigosEncomenda(codigo_encomenda);

            artigos_encomenda.forEach((x) -> {
                
                x.setData(x.getData().plusDays(2));
                this.gestor_transportadoras.addArtigoTransportadora(
                                                x.getTransportadora(),
                                                codigo_encomenda,
                                                x);
            });
        }

        catch (Exception e) {System.out.println("Não foi possivel expedir a encomenda: " + codigo_encomenda);}
    }

    public void updateData(LocalDate data){

        if (Calendario.getIntervaloDias(Calendario.getData(),data) >= 0){

            Calendario.setData(data);
            List<Integer> encomendas_prontas = this.gestor_encomendas.getAllEncomendasProntas();
            encomendas_prontas.forEach((x) -> this.expedirEncomenda(x));
            encomendas_prontas.forEach((x) -> this.gestor_transportadoras.updatePrecoEncomenda(x));
        }

        else{
            System.out.println("Não é possível retroceder no tempo");
        }
    }

    public void devolverEncomenda(int codigo_encomenda){

        try{

            if (this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda).equals(Encomenda.EXPEDIDA)){

                long dias = Calendario.getIntervaloDias(this.gestor_encomendas.getDataEncomenda(codigo_encomenda),Calendario.getData());

                if (dias > 1 && dias < 4){

                    int comprador = this.gestor_encomendas.getCompradorEncomenda(codigo_encomenda);
                    List<Artigo> artigos_encomenda = this.gestor_encomendas.getArtigosEncomenda(codigo_encomenda);

                    this.gestor_encomendas.removeEncomenda(codigo_encomenda);

                    artigos_encomenda.forEach((x) -> this.gestor_utilizadores.removeUtilizadorArtigoVendido(x));
                    artigos_encomenda.forEach((x) -> this.gestor_utilizadores.removeUtilizadorArtigoAdquirido(comprador,x));
                    artigos_encomenda.forEach((x) -> {
                        x.setData(Calendario.getData());
                        this.insertArtigo(x);
                    });
                }

                else{
                    System.out.println("Não foi possivel devolver a encomenda, já passaram " + (dias-2) + " dias");
                }
            }

            else{
                System.out.println("Não foi possivel devolver a encomenda, estado atual: "
                                    + this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda));
            }
        }

        catch (Exception e) {System.out.println("Não foi possivel devolver a encomenda: " + codigo_encomenda);}
    }

    public void alterarPrecoArtigo(String codigo_artigo, double preco){

        try{
            Artigo artigo = this.gestor_artigos.getArtigo(codigo_artigo);

            this.gestor_artigos.alterarPrecoArtigo(artigo.getCodigo(),preco);
            this.gestor_utilizadores.alterarPrecoArtigo(artigo.getVendedor(),artigo.getCodigo(),preco);
        }

        catch (Exception e){
            System.out.println("Não foi possivel identificar o artigo: " + codigo_artigo);
        }
    }

    public void alterarPrecosTransportadora(String transportadora, Double base_enc_pequena, Double base_enc_media, Double base_enc_grande, Double mult_imposto){
        try {this.gestor_transportadoras.alterarPrecosTransportadora(transportadora,base_enc_pequena,base_enc_media,base_enc_grande,mult_imposto);}

        catch (Exception e) {System.out.println("Não foi possivel identificar a transportadora: " + transportadora);}
    }

    public void dizMelhoresVendedores(Predicate<Artigo> filtro){
        this.gestor_utilizadores.dizMelhoresVendedores(filtro);
    }

    public void dizMelhoresCompradores(Predicate<Artigo> filtro){
        this.gestor_utilizadores.dizMelhoresCompradores(filtro);
    }

    public void dizMelhoresTransportadoras(){
        this.gestor_transportadoras.dizMelhoresTransportadoras();
    }

    public void getEncomendasEmitidasVendedor(int utilizador){
        this.gestor_encomendas.getEncomendasEmitidasVendedor(utilizador);
    }

    public double getLucroVintage(){
        return this.gestor_encomendas.getLucroVintage(Gestor.vintageComissao);
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