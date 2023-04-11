package gestores;

import transportadoras.Transportadora;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.*;


public class GestorTransportadoras{

    private Map<Integer,Transportadora> catalogo_transportadoras;

    public GestorTransportadoras(){
        this.catalogo_transportadoras = new HashMap<Integer,Transportadora>();
    }

    public void addTransportadora(Transportadora transportadora){
        this.catalogo_transportadoras.put(transportadora.hashCode(),transportadora);
    }

    public String toString(){

        return this.catalogo_transportadoras
                    .entrySet()
                    .stream()
                    .map((x) -> x.getValue().toString())
                    .reduce("Catalogo Transportadoras:", (a,b) -> a + "\n" + b);
    }
}