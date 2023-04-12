package gestores;

import artigos.*;
import utilizadores.*;
import transportadoras.*;
import encomendas.*;

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
                this.gestor_utilizadores.addUtilizadorArtigoAVenda(artigo.getVendedor(),artigo);
                return;
            }
        }

        System.out.println("Não foi possivel inserir o artigo " + artigo.getCodigo());
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
                this.gestor_utilizadores.removeUtilizadorArtigoAVenda(result.getVendedor(),result.getCodigo());
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

    public void finalizarEncomenda(int codigo_encomenda){

        try {
            
            if (this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda).equals(Encomenda.PENDENTE)){
                this.gestor_encomendas.finalizarEncomenda(codigo_encomenda);
            }

            else System.out.println("Não foi possivel finalizar a encomenda, estado atual: "
                                    + this.gestor_encomendas.getEstadoEncomenda(codigo_encomenda));
        }

        catch (Exception e) {System.out.println("Não foi possivel finalizar a encomenda: " + codigo_encomenda);}
    }


    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append(this.gestor_utilizadores.toString());
        buffer.append("\n---------------------------------\n");
        buffer.append(this.gestor_transportadoras.toString());
        buffer.append("\n---------------------------------\n");
        buffer.append(this.gestor_artigos.toString());
        buffer.append("\n---------------------------------\n");
        buffer.append(this.gestor_encomendas.toString());
        return buffer.toString();
    }
}