package gestores;

import artigos.*;
import utilizadores.*;
import transportadoras.*;
import encomendas.*;
import calendario.*;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Gestor{

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

    public void insertUtilizador(Utilizador utilizador){
        this.gestor_utilizadores.addUtilizador(utilizador);
    }

    public void insertTransportadora(Transportadora transportadora){
        this.gestor_transportadoras.addTransportadora(transportadora);
    }

    public void insertArtigo(Artigo artigo){

        if (artigo.getVendedor() >= 0 && artigo.getVendedor() < gestor_utilizadores.getSize()){

            if (gestor_transportadoras.lookUp(artigo.getTransportadora())){

                this.gestor_artigos.addArtigo(artigo);
                this.gestor_utilizadores.addUtilizadorArtigoAVenda(artigo);
            }
        }

        else {System.out.println("Não foi possivel inserir o artigo " + artigo.getCodigo());}
    }

    public void insertEncomenda(Encomenda encomenda){

        if (encomenda.getComprador() >= 0 && encomenda.getComprador() < gestor_utilizadores.getSize()){

            this.gestor_encomendas.addEncomenda(encomenda);
        }

        else System.out.println("Não foi possivel criar a encomenda do comprador: " + encomenda.getComprador());
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
        }

        else{
            System.out.println("Não é possível retroceder no tempo");
        }
    }

    public void devolverEncomenda(int codigo_encomenda){

        try{

            if (this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda).equals(Encomenda.EXPEDIDA)){

                long dias = Calendario.getIntervaloDias(this.gestor_encomendas.getDataEncomenda(codigo_encomenda),Calendario.getData());

                if (dias > 3){

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
                    System.out.println("Não foi possivel devolver a encomenda, falta(m) " + (4-dias) + " dia(s)");
                }
            }

            else{
                System.out.println("Não foi possivel devolver a encomenda, estado atual: "
                                    + this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda));
            }
        }

        catch (Exception e) {System.out.println("Não foi possivel devolver a encomenda: " + codigo_encomenda);}
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