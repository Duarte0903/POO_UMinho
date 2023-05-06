package escritor;

import java.util.List;
import modulos.EstatisticasVisivel;
import modulos.Visivel;


public class Escritor{

    public static void escreve(Object objeto){
        System.out.println(objeto);
    }

    public static void escreveVisivel(Visivel objeto){
        System.out.println(objeto.visualiza());
    }

    public static void escreveEstatisticasVisisvel(List<EstatisticasVisivel> objeto){
        objeto.forEach((x) -> System.out.println(x.visualizaEstatistica()));
    }
}