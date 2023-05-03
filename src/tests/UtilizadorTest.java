package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import modulos.Utilizador;
import modulos.artigos.Mala;
import modulos.artigos.Tshirt;
import modulos.artigos.Sapatilha;


public class UtilizadorTest{

    public UtilizadorTest(){}

    @BeforeEach
    public void setUp(){}

    @AfterEach
    public void tearDown(){}

    @Test
    public void testConstrutorUtilizador(){

        Utilizador.setAutoIncrement(0);
        Utilizador utilizador = new Utilizador("al19813@aebarcelos.pt","1234","Diogo Marques",123456789,"Rua das Flores");
        Utilizador utilizador2 = new Utilizador("maximino.marques@outlook.pt","4896","Maximino Marques",123789456,"Rua Feliz");
        Utilizador utilizadorclone = utilizador.clone();

        assertTrue(utilizador != utilizador.clone());
        assertTrue(utilizador.equals(utilizadorclone));
        assertFalse(utilizador.equals(utilizador2));
        assertTrue(utilizador.getNome().equals("Diogo Marques"));
        assertTrue(utilizador2.getNome().equals("Maximino Marques"));
        assertTrue(utilizador.getEmail().equals("al19813@aebarcelos.pt"));
        assertTrue(utilizador2.getEmail().equals("maximino.marques@outlook.pt"));
        assertTrue(utilizador.getPassword().equals("1234"));
        
        utilizador.setNome("Marco");
        utilizador.setEmail("gmail.com");
        utilizador.setMorada("Rua de Abril");

        assertTrue(utilizador.getNome().equals("Marco"));
        assertTrue(utilizador.getEmail().equals("gmail.com"));
        assertTrue(utilizador.getMorada().equals("Rua de Abril"));
    }

    @Test
    public void testArtigoUtilizador(){

        Utilizador.setAutoIncrement(0);
        Utilizador utilizador = new Utilizador("al19813@aebarcelos.pt","1234","Diogo Marques",123456789,"Rua das Flores");

        Mala mala = new Mala(1,"Transportes Resulima","Mala da Gucci","ABCD","GUCCI",25.55,0.23,"novo",0,0,true,30,"couro",LocalDate.parse("1967-12-02"));
        Mala mala2 = new Mala(2,"Transportes Alegria","Mala preta","WXYZ","LV",26.88,0.23,"nova",0,0,false,40,"cortiça",LocalDate.parse("1950-04-08"));

        Tshirt tshirt = new Tshirt(5,"Transportes Nogueira","Tshirt Amarela","FKTO","ZARA",26.00,0.23,"novo",0,0,false,"S","liso");
        Tshirt tshirt2 = new Tshirt(2,"Transportes Resulima","Tshirt fixe","FQTO","LV",26.90,0.23,"usado",0,0,true,"XL","riscas");

        Sapatilha sapatilha = new Sapatilha(1,"Transportes Alegria","Sapatilha Azul","CKDW","Nike",26.88,0.23,"usado",5,10,false,"Azul",36,false,LocalDate.parse("1940-10-19"));
        Sapatilha sapatilha2 = new Sapatilha(2,"Transportes Resulima","Sapatilha Amarela","CKEW","Puma",25.00,0.23,"novo",0,0,true,"Amarela",40,false,LocalDate.parse("1960-11-21"));

        utilizador.addArtigoFatura(mala,1,0.1);
        utilizador.addArtigoFatura(mala2,1,0.1);

        utilizador.calculaFaturacao((x) -> true);
        assertTrue(Double.compare(utilizador.getDinheiroFaturacao(),46.9962) == 0);
        
        utilizador.removeFatura(1);
        utilizador.calculaFaturacao((x) -> true);
        assertTrue(Double.compare(utilizador.getDinheiroFaturacao(),0) == 0);

        utilizador.addArtigoFatura(tshirt,0,0.2);
        utilizador.addArtigoFatura(tshirt2,1,0.3);
        utilizador.calculaFaturacao((x) -> false);
        assertTrue(Double.compare(utilizador.getDinheiroFaturacao(),0) == 0);

        utilizador.calculaFaturacao((x) -> true);
        assertTrue(Double.compare(utilizador.getDinheiroFaturacao(),30.215) == 0); 
        utilizador.removeFatura(0);
        utilizador.calculaFaturacao((x) -> true);
        assertTrue(Double.compare(utilizador.getDinheiroFaturacao(),9.415) == 0);
        
        utilizador.removeFatura(1);
        utilizador.calculaFaturacao((x) -> true);
        assertTrue(Double.compare(utilizador.getDinheiroFaturacao(),0) == 0);

        utilizador.addArtigoFatura(sapatilha,0,0.5);
        utilizador.addArtigoFatura(sapatilha2,2,0.2);
        utilizador.calculaFaturacao((x) -> true);
        assertTrue(Double.compare(utilizador.getDinheiroFaturacao(),40.3712) == 0);

        utilizador.calculaFaturacao((x) -> false);
        assertTrue(Double.compare(utilizador.getDinheiroFaturacao(),0) == 0);

        utilizador.removeFatura(2);
        utilizador.calculaFaturacao((x) -> true);
        assertTrue(Double.compare(utilizador.getDinheiroFaturacao(),13.171199999999999) == 0);

        utilizador.removeFatura(0);
        utilizador.calculaFaturacao((x) -> true);
        assertTrue(Double.compare(utilizador.getDinheiroFaturacao(),0) == 0);
    }
}