package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import modulos.artigos.Mala;
import modulos.artigos.Tshirt;
import modulos.artigos.Sapatilha;


public class ArtigoTest{

    public ArtigoTest(){}

    @BeforeEach
    public void setUp(){}

    @AfterEach
    public void tearDown(){}

    @Test
    public void testConstrutorMala(){
        
        Mala mala = new Mala(1,"Transportes Resulima","Mala da Gucci","ABCD","GUCCI",25.55,0.23,"novo",0,0,true,30,"couro",LocalDate.parse("1967-12-02"));
        Mala mala2 = new Mala(2,"Transportes Alegria","Mala preta","WXYZ","LV",26.88,0.23,"nova",0,0,false,40,"cortiça",LocalDate.parse("1950-04-08"));
        Mala malaclone = mala.clone();
        
        assertTrue(mala != null);
        assertTrue(malaclone != null);
        assertTrue(mala != malaclone);
        assertTrue(mala.getCodigo().equals("ABCD"));
        assertTrue(mala.getTransportadora().equals("Transportes Resulima"));
        assertTrue(mala.getMarca().equals("GUCCI"));
        assertTrue(mala2.getVendedor() == 2);
        assertTrue(mala.getPremium());
        assertTrue(mala.getEstado().equals(malaclone.getEstado()));
        assertTrue(Double.compare(mala.calculaPreco(),26.01) == 0);
        assertTrue(Double.compare(mala2.calculaPreco(),26.208) == 0);
    }

    @Test
    public void testConstrutorSapatilha(){

        Sapatilha sapatilha = new Sapatilha(1,"Transportes Alegria","Sapatilha Azul","CKDW","Nike",26.88,0.23,"usado",5,10,false,"Azul",36,false,LocalDate.parse("1940-10-19"));
        Sapatilha sapatilha2 = new Sapatilha(2,"Transportes Resulima","Sapatilha Amarela","CKEW","Puma",25.00,0.23,"novo",0,0,true,"Amarela",40,false,LocalDate.parse("1960-11-21"));
        Sapatilha sapatilhaclone = sapatilha.clone();
        Sapatilha sapatilhacloneclone = new Sapatilha(sapatilhaclone);

        assertTrue(sapatilha != null);
        assertTrue(sapatilhaclone != null);
        assertTrue(sapatilha.getTamanho() == 36);
        assertTrue(sapatilha.getVendedor() == 1);
        assertTrue(sapatilha2.getVendedor() == 2);
        assertTrue(sapatilha.getColecao().isEqual(LocalDate.parse("1940-10-19")));
        assertFalse(sapatilha.getAtacadores());
        assertFalse(sapatilha.getPremium());
        assertTrue(sapatilha2.getPremium());
        assertTrue(Double.compare(sapatilha.calculaPreco(),26.342399999999998) == 0);
        assertTrue(Double.compare(sapatilhaclone.calculaPreco(),26.342399999999998) == 0);
        assertTrue(Double.compare(sapatilhacloneclone.calculaPreco(),26.342399999999998) == 0);
        assertTrue(Double.compare(sapatilha2.calculaPreco(),34) == 0);
    }

    @Test
    public void testConstrutorTshirt(){

        Tshirt tshirt = new Tshirt(5,"Transportes Nogueira","Tshirt Amarela","FKTO","ZARA",26.00,0.23,"novo",0,0,false,"S","liso");
        Tshirt tshirt2 = new Tshirt(2,"Transportes Resulima","Tshirt fixe","FQTO","LV",26.90,0.23,"usado",0,0,true,"XL","riscas");
        Tshirt tshirtclone = tshirt.clone();
        Tshirt tshirtcloneclone = new Tshirt(tshirtclone);

        assertTrue(tshirt != null);
        assertTrue(tshirtcloneclone != null);
        assertTrue(tshirt.getTamanho().equals("S"));
        assertTrue(tshirt2.getTamanho().equals("XL"));
        assertTrue(tshirt.getVendedor() == 5);
        assertTrue(tshirt2.getVendedor() == 2);
        assertTrue(tshirt.getMarca().equals("ZARA"));
        assertTrue(tshirt2.getMarca().equals("LV"));
        assertFalse(tshirt.getPremium());
        assertTrue(tshirt2.getPremium());
        assertFalse(tshirtcloneclone.getPremium());
        assertTrue(Double.compare(tshirt.calculaPreco(),26.00) == 0);
        assertTrue(Double.compare(tshirtclone.calculaPreco(),26.00) == 0);
        assertTrue(Double.compare(tshirtcloneclone.calculaPreco(),26.00) == 0);
        assertTrue(Double.compare(tshirt2.calculaPreco(),13.45) == 0);
    }
}