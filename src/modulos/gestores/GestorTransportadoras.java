package modulos.gestores;

import modulos.artigos.Artigo;
import modulos.Transportadora;
import modulos.Estatisticas;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.*;
import java.io.Serializable;


public class GestorTransportadoras implements Serializable{

    private static final long serialVersionUID = 11L;

    private Map<Integer,Transportadora> catalogo_transportadoras;

    public GestorTransportadoras(){
        this.catalogo_transportadoras = new HashMap<Integer,Transportadora>();
    }

    private void lookUpTransportadora(int codigo) throws Exception{
        if (!this.catalogo_transportadoras.containsKey(codigo)){
            throw new Exception("Transportadora inexistente");
        }
    }

    public void addTransportadora(Transportadora transportadora) throws Exception{
        if (this.catalogo_transportadoras.containsKey(transportadora.hashCode())){
            throw new Exception("Transportadora já inserida");
        }
        this.catalogo_transportadoras.put(transportadora.hashCode(),transportadora.clone());
    }

    public void addArtigoTransportadora(String transportadora, int codigo_encomenda, Artigo artigo){
        this.catalogo_transportadoras.get(transportadora.hashCode()).addArtigo(codigo_encomenda,artigo);
    }

    public Transportadora getTransportadora(String transportadora) throws Exception{
        this.lookUpTransportadora(transportadora.hashCode());
        return this.catalogo_transportadoras.get(transportadora.hashCode()).clone();
    }

    public void alterarPrecosTransportadora(String transportadora, Double base_enc_pequena, Double base_enc_media, Double base_enc_grande, Double mult_imposto) throws Exception{
        this.lookUpTransportadora(transportadora.hashCode());
        this.catalogo_transportadoras.get(transportadora.hashCode()).setBaseEncPequena(base_enc_pequena);
        this.catalogo_transportadoras.get(transportadora.hashCode()).setBaseEncMedia(base_enc_media);
        this.catalogo_transportadoras.get(transportadora.hashCode()).setBaseEncGrande(base_enc_grande);
        this.catalogo_transportadoras.get(transportadora.hashCode()).setMultImposto(mult_imposto);
    }

    public void updatePrecoEncomenda(int codigo_encomenda){
        
        this.catalogo_transportadoras
            .entrySet()
            .stream()
            .filter((x) -> x.getValue().containsEncomenda(codigo_encomenda))
            .forEach((x) -> x.getValue().updatePrecoEncomenda(codigo_encomenda));
    }

    public void dizMelhoresTransportadoras(){
        Estatisticas.getMelhoresTransportadoras(this.catalogo_transportadoras)
                    .forEach((x) -> System.out.println("Nome: " + x.getNome()
                                                    + "\t\tDinheiro Ganho: " + x.getFaturacao()));
    }

    public String toString(){

        return this.catalogo_transportadoras
                    .entrySet()
                    .stream()
                    .map((x) -> x.getValue().toString())
                    .reduce("Catalogo Transportadoras:", (a,b) -> a + "\n" + b);
    }
}