import leitor.Leitor;
import controlador.Coletor;
import modulos.gestores.Gestor;


public class Main{

    public static void main(String[] args){

        Gestor gestor = new Gestor();
        Coletor.fillTabela();

        if (args.length != 0) Leitor.run(args[0],gestor);

        else Leitor.run(null,gestor);
    }
}