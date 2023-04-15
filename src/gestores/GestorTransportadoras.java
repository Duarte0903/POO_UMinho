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

    public String toString(){

        return this.catalogo_transportadoras
                    .entrySet()
                    .stream()
                    .map((x) -> x.getValue().toString())
                    .reduce("Catalogo Transportadoras:", (a,b) -> a + "\n" + b);
    }
}