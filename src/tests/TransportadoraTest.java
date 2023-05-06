package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import modulos.Transportadora;
import modulos.artigos.Tshirt;


public class TransportadoraTest{

    public TransportadoraTest(){}

    @BeforeEach
    public void setUp(){}

    @AfterEach
    public void tearDown(){}

    @Test
    public void testConstrutorTransportadora(){

        Transportadora transportadora = new Transportadora("Transportes Nogueira",2.5,5.0,8.4,0.5,false);
        Transportadora transportadora2 = transportadora.clone();

        assertTrue(transportadora.equals(transportadora2));
        assertTrue(transportadora.getNome().equals("Transportes Nogueira"));
        assertTrue(Double.compare(transportadora.getBaseEncPequena(),2.5) == 0);
        assertTrue(Double.compare(transportadora.getBaseEncMedia(),5) == 0);
        assertTrue(Double.compare(transportadora.getBaseEncGrande(),8.4) == 0);
        assertFalse(transportadora.getPremium());
        assertFalse(transportadora2.getPremium());

        transportadora.setMultImposto(10.2);

        assertTrue(Double.compare(transportadora.getMultImporto(),10.2) == 0);
        assertTrue(Double.compare(transportadora2.getMultImporto(),0.5) == 0);
    }

    @Test
    public void testExisteArtigoTransportadora(){

        Transportadora transportadora = new Transportadora("Transportes Nogueira",2.5,5.0,8.4,0.5,false);

        Tshirt tshirt = new Tshirt(5,"Transportes Nogueira","Tshirt Amarela","FKTO","ZARA",26.00,0.23,"novo",0,0,false,"S","liso");
        Tshirt tshirt2 = new Tshirt(2,"Transportes Resulima","Tshirt fixe","FQTO","LV",26.90,0.23,"usado",0,0,true,"XL","riscas");
        Tshirt tshirt3 = new Tshirt(2,"Transportes Resulima","Tshirt fixe","FQTO","LV",26.90,0.23,"usado",0,0,true,"XL","riscas");

        transportadora.addArtigo(0,tshirt);
        transportadora.addArtigo(1,tshirt2);

        assertTrue(transportadora.containsEncomenda(0));
        assertTrue(transportadora.containsEncomenda(1));
        assertFalse(transportadora.containsEncomenda(2));

        transportadora.addArtigo(2,tshirt3);
        assertTrue(transportadora.containsEncomenda(2));
    }

    @Test
    public void testCalculaArtigoTransportadora(){

        Transportadora transportadora = new Transportadora("Transportes Nogueira",2.5,5.0,8.4,0.5,false);

        Tshirt tshirt = new Tshirt(5,"Transportes Nogueira","Tshirt Amarela","FKTO","ZARA",26.00,0.23,"novo",0,0,false,"S","liso");
        Tshirt tshirt2 = new Tshirt(2,"Transportes Resulima","Tshirt fixe","FQTO","LV",26.90,0.23,"usado",0,0,true,"XL","riscas");
        Tshirt tshirt3 = new Tshirt(2,"Transportes Resulima","Tshirt fixe","FQTO","LV",26.90,0.23,"usado",0,0,true,"XL","riscas");

        transportadora.addArtigo(0,tshirt);
        transportadora.addArtigo(1,tshirt2);
        assertTrue(Double.compare(transportadora.getFaturacao(),6.75) == 0);

        transportadora.addArtigo(2,tshirt3);
        assertTrue(Double.compare(transportadora.getFaturacao(),10.125) == 0);

        transportadora.addArtigo(0,tshirt3);
        assertTrue((Double.compare(transportadora.getFaturacao(),13.5) == 0));

        transportadora.addArtigo(0,tshirt3);
        transportadora.addArtigo(0,tshirt3);
        transportadora.addArtigo(0,tshirt3);
        assertTrue((Double.compare(transportadora.getFaturacao(),13.5) == 0));

        transportadora.addArtigo(0,tshirt3);
        assertTrue((Double.compare(transportadora.getFaturacao(),18.090000000000003) == 0));
    }
}