package comparadores;

import transportadoras.Transportadora;
import java.util.Comparator;

public class OrdenaTransportadora implements Comparator<Transportadora>{

    public int compare(Transportadora a, Transportadora b){

        return Double.compare(
                a.getFaturacao(),
                b.getFaturacao());
    }
}