package modulos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Calendario{

    private static LocalDate data = LocalDate.parse("1970-01-01");

    public static LocalDate getData(){
        return Calendario.data;
    }

    public static void setData(LocalDate data){
        Calendario.data = data;
    }

    public static long getIntervaloDias(LocalDate data1, LocalDate data2){
        return ChronoUnit.DAYS.between(data1,data2);
    }

    public static long getIntervaloAnos(LocalDate data1, LocalDate data2){
        return ChronoUnit.YEARS.between(data1,data2);
    }

    public static boolean isBetween(LocalDate data1, LocalDate data2, LocalDate data3){
        return (data2.isAfter(data1) && data2.isBefore(data3));
    }

    public static boolean checkPrazoDevolucao(LocalDate data){
        return Calendario.isBetween(data.plusDays(1),Calendario.data,data.plusDays(4));

    }
}