package encomendas;

import artigos.Artigo;
import calendario.Calendario;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.stream.*;
import java.util.Iterator;


public class Encomenda{

    private static int AUTO_INCREMENT = 0; 

    public static final String PENDENTE = "pendente";
    public static final String FINALIZADA = "finalizada";
    public static final String EXPEDIDA = "expedida";

    private static String PEQUENA = "pequena";
    private static String MEDIA = "media";
    private static String GRANDE = "grande";

    private static int MEDIA_SIZE = 1;
    private static int GRANDE_SIZE = 5;

    private LocalDate data_criacao;
    private List<Artigo> artigos;
    private String embalagem;
    private String estado;

    /* Informaçoes extra */

    private int codigo;
    private int comprador;

    public Encomenda(int comprador){
        this.data_criacao = null;
        this.artigos = new ArrayList<Artigo>();
        this.embalagem = Encomenda.PEQUENA;
        this.estado = Encomenda.PENDENTE;
        this.codigo = Encomenda.AUTO_INCREMENT++;
        this.comprador = comprador;
    }

    public Encomenda(Encomenda encomenda){
        this.data_criacao = encomenda.getDataCriacao();
        this.artigos = new ArrayList<Artigo>(encomenda.getArtigos());
        this.embalagem = encomenda.getEmbalagem();
        this.estado = encomenda.getEstado();
        this.codigo = encomenda.getCodigo();
        this.comprador = encomenda.getComprador();
    }

    // Clone

    public Encomenda clone(){
        return new Encomenda(this);
    }

    // Getters

    public LocalDate getDataCriacao(){
        return this.data_criacao;
    }

    public String getEmbalagem(){
        return this.embalagem;
    }

    public String getEstado(){
        return this.estado;
    }

    public int getCodigo(){
        return this.codigo;
    }

    public int getComprador(){
        return this.comprador;
    }

    public List<Artigo> getArtigos(){
        return this.artigos
                    .stream()
                    .map((x) -> x.clone())
                    .collect(Collectors.toList());
    }

    // Metodos

    public double calculaPreco(){

        return this.artigos.stream()
                        .mapToDouble((x) -> x.calculaPreco())
                        .sum();
    }

    private void updateEmbalagem(){

        if (this.artigos.size() > Encomenda.GRANDE_SIZE) this.embalagem = Encomenda.GRANDE;

        else if (this.artigos.size() > Encomenda.MEDIA_SIZE) this.embalagem = Encomenda.MEDIA;

        else this.embalagem = Encomenda.PEQUENA;
    }

    public void addArtigo(Artigo artigo){

        this.artigos.add(artigo.clone());
        this.updateEmbalagem();
    }

    public Artigo removeArtigo(String codigo){

        Iterator<Artigo> iterator = this.artigos.iterator();
        Artigo result = null;

        while (iterator.hasNext() && result == null){

            Artigo aux = iterator.next();

            if (aux.getCodigo().equals(codigo)){

                result = aux.clone();
                iterator.remove();
            }
        }

        this.updateEmbalagem();

        return result;
    }

    public void finalizarEncomenda(){
        this.estado = Encomenda.FINALIZADA;
        this.data_criacao = Calendario.getData();
        this.artigos.forEach((x) -> x.setDataVenda(Calendario.getData()));
    }

    public void expedirEncomenda(){
        this.estado = Encomenda.EXPEDIDA;
    }

    public String toString(){

        StringBuffer buffer = new StringBuffer();

        buffer.append("\nCodigo da encomenda: ").append(this.codigo);
        buffer.append("\tComprador: ").append(this.comprador);
        buffer.append("\tData da criação: ").append(this.data_criacao);
        buffer.append("\tEmbalagem: ").append(this.embalagem);
        buffer.append("\tEstado: ").append(this.estado);
        buffer.append("\nArtigos da encomenda:").append(this.artigos.toString());

        return buffer.toString();
    }

    public int hashCode(){
        return this.codigo;
    }
}