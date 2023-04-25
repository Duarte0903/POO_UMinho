package comparadores;

import utilizadores.Utilizador;
import java.util.Comparator;

public class OrdenaMelhorComprador implements Comparator<Utilizador>{

    public int compare(Utilizador a, Utilizador b){

        return Double.compare(
                a.getPrecoArtigosAdquiridos(),
                b.getPrecoArtigosAdquiridos());
    }
}