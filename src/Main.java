import leitor.Leitor;
import modulos.gestores.Gestor;


public class Main{

    public static void main(String[] args){

        Gestor gestor = new Gestor();

        if (args.length != 0) Leitor.run(args[0],gestor);

        else Leitor.run(null,gestor);
    }
}