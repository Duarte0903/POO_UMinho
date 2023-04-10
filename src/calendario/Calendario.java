package calendario;

import java.time.LocalDate;

public class Calendario{

    private static LocalDate data;

    public static LocalDate getData(){
        return Calendario.data;
    }

    public static void setData(LocalDate data){
        Calendario.data = data;
    }
}