import gestores.*;
import parsing.*;
import artigos.*;
import encomendas.Encomenda;
import calendario.Calendario;
import utilizadores.Utilizador;
import transportadoras.Transportadora;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Main{

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    private static final String OBJC = "object.obj";

    public static void main(String[] args){

        Gestor gestor = new Gestor();
        Scanner input = new Scanner(System.in);

        Coletor.fillTabela();
        
        if (args.length != 0){
            
            if (args[0].contains(".txt")){

                try{
                    Scanner text_input = new Scanner(new File(args[0]));

                    while (text_input.hasNextLine()){
                
                        Coletor.collectDadosLine(text_input.nextLine(),gestor);
                    }

                    text_input.close();
                    System.out.println(Main.YELLOW + "Ficheiro lido com sucesso" + Main.RESET);
                }

                catch (Exception e){
                    System.out.println(Main.RED + "Não foi possivel abrir o ficheiro: " + args[0] + Main.RESET);}
                }

            else{

                try{

                    FileInputStream file_input = new FileInputStream(args[0]);
                    ObjectInputStream object_input = new ObjectInputStream(file_input);
                    
                    Calendario.setData((LocalDate) object_input.readObject());
                    gestor = (Gestor) object_input.readObject();
                    
                    file_input.close();
                    object_input.close();

                    System.out.println(Main.YELLOW + "Ficheiro lido com sucesso" + Main.RESET);
                }

                catch (Exception e) {System.out.println(Main.RED + "Não foi possivel ler o ficheiro" + Main.RESET);}
            }
        }

        System.out.printf(Main.GREEN + "%s", ">>> ");

        while (input.hasNextLine()){

            String line = input.nextLine();

            System.out.printf("%s", Main.RESET);
            Coletor.collectDadosLine(line,gestor);

            System.out.printf(Main.GREEN + "%s", ">>> ");
        }

        input.close();
        System.out.println("Bye!");

   //     System.out.println(gestor.toString());

        try{

            FileOutputStream file_output = new FileOutputStream(Main.OBJC);
            ObjectOutputStream object_output = new ObjectOutputStream(file_output);

            object_output.writeObject(Calendario.getData());
            object_output.writeObject(gestor);

            file_output.close();
            object_output.close();

            System.out.println(Main.YELLOW + "Ficheiro guardado com sucesso" + Main.RESET);
        }

        catch (Exception e) {System.out.println(Main.RED + "O ficheiro não foi guardado" + Main.RESET);}

    }
}
