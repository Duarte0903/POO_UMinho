package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;

import modulos.Fatura;
import modulos.Utilizador;
import modulos.Fatura.TIPO;
import modulos.artigos.Mala;
import modulos.artigos.Tshirt;
import modulos.gestores.Gestor;
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

        Gestor.setComissao(0.1);
        Utilizador.setAutoIncrement(0);
        Utilizador utilizador = new Utilizador("al19813@aebarcelos.pt","1234","Diogo Marques",123456789,"Rua das Flores");

        Mala mala = new Mala(1,"Transportes Resulima","Mala da Gucci","ABCD","GUCCI",25.55,0.23,"novo",0,0,true,30,"couro",LocalDate.parse("1967-12-02"));
        Mala mala2 = new Mala(2,"Transportes Alegria","Mala preta","WXYZ","LV",26.88,0.23,"nova",0,0,false,40,"cortiça",LocalDate.parse("1950-04-08"));

        Tshirt tshirt = new Tshirt(5,"Transportes Nogueira","Tshirt Amarela","FKTO","ZARA",26.00,0.23,"novo",0,0,false,"S","liso");
        Tshirt tshirt2 = new Tshirt(2,"Transportes Resulima","Tshirt fixe","FQTO","LV",26.90,0.23,"usado",0,0,true,"XL","riscas");

        Sapatilha sapatilha = new Sapatilha(1,"Transportes Alegria","Sapatilha Azul","CKDW","Nike",26.88,0.23,"usado",5,10,false,"Azul",36,false,LocalDate.parse("1940-10-19"));
        Sapatilha sapatilha2 = new Sapatilha(2,"Transportes Resulima","Sapatilha Amarela","CKEW","Puma",25.00,0.23,"novo",0,0,true,"Amarela",40,false,LocalDate.parse("1960-11-21"));

        utilizador.addArtigoVendido(mala,new Fatura(1,mala,TIPO.VENDA));
        utilizador.addArtigoVendido(mala2,new Fatura(1,mala2,TIPO.VENDA));
        assertTrue(Double.compare(utilizador.getFaturacao((x) -> x.getTipo() == TIPO.VENDA),46.9962) == 0);
        
        utilizador.removeArtigoVendido(mala,new Fatura(1,mala,TIPO.VENDA));
        assertTrue(Double.compare(utilizador.getFaturacao((x) -> x.getTipo() == TIPO.VENDA),0) == 0);

        Gestor.setComissao(0.2);
        utilizador.addArtigoVendido(tshirt,new Fatura(0,tshirt,TIPO.VENDA));
        Gestor.setComissao(0.3);
        utilizador.addArtigoVendido(tshirt2,new Fatura(1,tshirt2,TIPO.VENDA));
        assertTrue(Double.compare(utilizador.getFaturacao((x) -> x.getTipo() == TIPO.COMPRA),0) == 0);
        assertTrue(Double.compare(utilizador.getFaturacao((x) -> x.getTipo() == TIPO.VENDA),30.215) == 0); 
        
        utilizador.removeArtigoVendido(tshirt,new Fatura(0,tshirt,TIPO.VENDA));
        assertTrue(Double.compare(utilizador.getFaturacao((x) -> x.getTipo() == TIPO.VENDA),9.415) == 0);
        
        utilizador.removeArtigoVendido(tshirt2,new Fatura(1,tshirt2,TIPO.VENDA));
        assertTrue(Double.compare(utilizador.getFaturacao((x) -> x.getTipo() == TIPO.VENDA),0) == 0);

        Gestor.setComissao(0.5);
        utilizador.addArtigoVendido(sapatilha,new Fatura(0,sapatilha,TIPO.VENDA));
        Gestor.setComissao(0.2);
        utilizador.addArtigoVendido(sapatilha2,new Fatura(2,sapatilha2,TIPO.VENDA));
        assertTrue(Double.compare(utilizador.getFaturacao((x) -> x.getTipo() == TIPO.VENDA),40.3712) == 0);
        assertTrue(Double.compare(utilizador.getFaturacao((x) -> x.getTipo() == TIPO.COMPRA),0) == 0);

        utilizador.removeArtigoVendido(sapatilha2,new Fatura(2,sapatilha2,TIPO.VENDA));
        assertTrue(Double.compare(utilizador.getFaturacao((x) -> x.getTipo() == TIPO.VENDA),13.171199999999999) == 0);

        utilizador.removeArtigoVendido(sapatilha,new Fatura(0,sapatilha,TIPO.VENDA));
        assertTrue(Double.compare(utilizador.getFaturacao((x) -> true),0) == 0);
    }
}