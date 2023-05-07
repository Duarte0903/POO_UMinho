import controlador.Controlador;
import leitor.Leitor;
import modulos.gestores.Gestor;


public class Main{

    public static void main(String[] args){

        Gestor gestor = new Gestor();
        Controlador controlador = new Controlador(gestor);
        Leitor leitor = new Leitor(controlador);

        if (args.length == 0) leitor.load(null);

        else leitor.load(args[0]);

        leitor.run();
        leitor.save();
    }
}