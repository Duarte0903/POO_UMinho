package modulos.comparadores;

import modulos.Utilizador;
import java.util.Comparator;

public class OrdenaVendedor implements Comparator<Utilizador>{

    public int compare(Utilizador a, Utilizador b){

        return Double.compare(
                a.getPrecoArtigosVendidos(),
                b.getPrecoArtigosVendidos());
    }
}