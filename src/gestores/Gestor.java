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

    public void insertArtigo(Artigo artigo){

        if (artigo.getVendedor() >= 0 && gestor_utilizadores.getSize() > artigo.getVendedor()){

            if (gestor_transportadoras.lookUp(artigo.getTransportadora())){

                this.gestor_artigos.addArtigo(artigo);
                this.gestor_utilizadores.addUtilizadorArtigoAVenda(artigo.getVendedor(),artigo);
                return;
            }
        }

        System.out.println("Não foi possivel inserir o artigo " + artigo.getCodigo());
    }

    public void insertUtilizador(Utilizador utilizador){
        this.gestor_utilizadores.addUtilizador(utilizador);
    }

    public void insertTransportadora(Transportadora transportadora){
        this.gestor_transportadoras.addTransportadora(transportadora);
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