package leitor;

import controlador.Coletor;
import modulos.gestores.Gestor;
import modulos.Calendario;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Leitor{

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    private static final String OBJC = "object.obj";

    public static void run(String path, Gestor gestor){

        Scanner input = new Scanner(System.in);
        
        if (path != null){
            
            if (path.contains(".txt")){

                try{
                    Scanner text_input = new Scanner(new File(path));

                    while (text_input.hasNextLine()){
                
                        Coletor.collectDadosLine(text_input.nextLine().split(";",0),gestor);
                    }

                    text_input.close();
                    System.out.println(Leitor.YELLOW + "Ficheiro lido com sucesso" + Leitor.RESET);
                }

                catch (Exception e){
                    System.out.println(Leitor.RED + "Não foi possivel abrir o ficheiro: " + path + Leitor.RESET);}
                }

            else{

                try{

                    FileInputStream file_input = new FileInputStream(path);
                    ObjectInputStream object_input = new ObjectInputStream(file_input);
                    
                    Calendario.setData((LocalDate) object_input.readObject());
                    Gestor.setComissao((double) object_input.readObject());
                    Gestor.setAutoIncrementEncomeda((int) object_input.readObject());
                    gestor = (Gestor) object_input.readObject();
                    
                    file_input.close();
                    object_input.close();

                    System.out.println(Leitor.YELLOW + "Ficheiro lido com sucesso" + Leitor.RESET);
                }

                catch (Exception e) {System.out.println(Leitor.RED + "Não foi possivel ler o ficheiro" + Leitor.RESET);}
            }
        }

        System.out.printf(Leitor.GREEN + "%s", ">>> ");

        while (input.hasNextLine()){

            String line = input.nextLine();

            System.out.printf("%s", Leitor.RESET);
            Coletor.collectDadosLine(line.split(";",0),gestor);

            System.out.printf(Leitor.GREEN + "%s", ">>> ");
        }

        input.close();
        System.out.println("Bye!");

   //     System.out.println(gestor.toString());

        try{

            FileOutputStream file_output = new FileOutputStream(Leitor.OBJC);
            ObjectOutputStream object_output = new ObjectOutputStream(file_output);

            object_output.writeObject(Calendario.getData());
            object_output.writeObject(Gestor.getComissao());
            object_output.writeObject(Gestor.getAutoIncrementEncomenda());
            object_output.writeObject(gestor);

            file_output.close();
            object_output.close();

            System.out.println(Leitor.YELLOW + "Ficheiro guardado com sucesso" + Leitor.RESET);
        }

        catch (Exception e) {System.out.println(Leitor.RED + "O ficheiro não foi guardado" + Leitor.RESET);}
    }
}