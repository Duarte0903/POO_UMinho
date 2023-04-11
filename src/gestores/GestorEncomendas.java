package gestores;

import encomendas.*;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.*;


public class GestorEncomendas{

    private Map<Integer,Encomenda> catalogo_encomendas;

    public GestorEncomendas(){
        this.catalogo_encomendas = new HashMap<Integer,Encomenda>();
    }

    public void addEncomenda(Encomenda encomenda){
        this.catalogo_encomendas.put(encomenda.hashCode(),encomenda.clone());
    }

    public String toString(){

        return this.catalogo_encomendas
                    .entrySet()
                    .stream()
                    .map((x) -> x.getValue().toString())
                    .reduce("Catalogo Encomendas:", (a,b) -> a + "\n" + b);
    }
}