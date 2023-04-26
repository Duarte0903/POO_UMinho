package modulos;


public class Tratador{

    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static void trataException(Exception e){

        System.out.println(Tratador.RED + e.getMessage() + Tratador.RESET);
    }
}