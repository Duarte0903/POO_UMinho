package utilizadores;

import java.util.*;

public class GestorUtilizadores{

    private static List<Utilizador> catalogo_utilizadores = new ArrayList<Utilizador>();

    public static void addUtilizador(Utilizador utilizador){
        GestorUtilizadores.catalogo_utilizadores.add(utilizador);
    }

    public static String getString(){

        return GestorUtilizadores.catalogo_utilizadores
                    .stream()
                    .map((x) -> x.toString())
                    .reduce("Catalogo Utilizadores:", (a,b) -> a + "\n" + b);
    }
}