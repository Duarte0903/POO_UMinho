package modulos.comparadores;

import modulos.Utilizador;
import modulos.Fatura;
import java.util.Comparator;
import java.util.function.Predicate;

public class OrdenaUtilizador implements Comparator<Utilizador>{

    Predicate<Fatura> filtro;

    public OrdenaUtilizador(Predicate<Fatura> filtro){
        this.filtro = filtro;
    }

    public int compare(Utilizador a, Utilizador b){

        return Double.compare(
                a.getFaturacao(this.filtro),
                b.getFaturacao(this.filtro));
    }
}