package transportadoras;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.*;


public class GestorTransportadoras{

    private static Map<Integer,Transportadora> catalogo_transportadoras = new HashMap<Integer,Transportadora>();

    public static void addTransportadora(Transportadora transportadora){
        GestorTransportadoras.catalogo_transportadoras.put(transportadora.hashCode(),transportadora);
    }

    public static String getString(){

        return GestorTransportadoras.catalogo_transportadoras
                                    .entrySet()
                                    .stream()
                                    .map((x) -> x.getValue().toString())
                                    .reduce("Catalogo Transportadoras:", (a,b) -> a + "\n" + b);
    }




}