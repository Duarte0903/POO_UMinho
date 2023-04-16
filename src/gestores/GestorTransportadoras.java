package gestores;

import artigos.Artigo;
import transportadoras.Transportadora;
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

    public void addTransportadora(Transportadora transportadora){
        this.catalogo_transportadoras.put(transportadora.hashCode(),transportadora.clone());
    }

    public void addArtigoTransportadora(String transportadora, int codigo_encomenda, Artigo artigo){
        this.catalogo_transportadoras.get(transportadora.hashCode()).addArtigo(codigo_encomenda,artigo);
    }

    public boolean lookUp(String transportadora){
        return this.catalogo_transportadoras.containsKey(transportadora.hashCode());
    }

    public boolean getPremiumTransportadora(String transportadora){
        return this.catalogo_transportadoras.get(transportadora.hashCode()).getPremium();
    }

    public void alterarPrecosTransportadora(String transportadora, Double base_enc_pequena, Double base_enc_media, Double base_enc_grande, Double mult_imposto){
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

    public String toString(){

        return this.catalogo_transportadoras
                    .entrySet()
                    .stream()
                    .map((x) -> x.getValue().toString())
                    .reduce("Catalogo Transportadoras:", (a,b) -> a + "\n" + b);
    }
}