package estatisticas;

import comparadores.*;
import artigos.Artigo;
import utilizadores.Utilizador;
import java.util.List;
import java.util.stream.*;
import java.util.function.Predicate;


public class Estatisticas{

    public static List<Utilizador> getMelhoresVendedores(List<Utilizador> utilizadores, Predicate<Artigo> filtro){

        List<Utilizador> result = utilizadores.stream().map((x) -> x.clone()).collect(Collectors.toList());

        result.forEach((x) -> x.setArtigosVendidos(filtro.negate()));
        return result.stream().sorted(new OrdenaVendedor().reversed()).collect(Collectors.toList());
    }

    public static List<Utilizador> getMelhoresCompradores(List<Utilizador> utilizadores, Predicate<Artigo> filtro){

        List<Utilizador> result = utilizadores.stream().map((x) -> x.clone()).collect(Collectors.toList());

        result.forEach((x) -> x.setArtigosAdquiridos(filtro.negate()));
        return result.stream().sorted(new OrdenaComprador().reversed()).collect(Collectors.toList());
    }
}