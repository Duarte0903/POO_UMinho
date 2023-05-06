package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import modulos.Transportadora;
import modulos.artigos.Tshirt;
import modulos.gestores.GestorTransportadoras;


public class GestorTransportadorasTest{

    public GestorTransportadorasTest(){}

    @BeforeEach
    public void setUp(){}

    @AfterEach
    public void tearDown(){}

    @Test
    public void testConstrutorGestorTransportadoras(){

        GestorTransportadoras gestorTransportadoras = new GestorTransportadoras();

        assertTrue(gestorTransportadoras != null);
    }

    @Test
    public void testTransportadorasGestorTransportadoras(){

        GestorTransportadoras gestorTransportadoras = new GestorTransportadoras();
        Exception thrown;

        Transportadora transportadora = new Transportadora("Transportes Nogueira",2.5,5.0,8.4,0.5,false);
        Transportadora transportadora2 = new Transportadora("Transportes Resulima",4.7,8.8,10.6,0.6,true);

        assertDoesNotThrow(() -> gestorTransportadoras.addTransportadora(transportadora));
        assertDoesNotThrow(() -> gestorTransportadoras.addTransportadora(transportadora2));
        assertDoesNotThrow(() -> gestorTransportadoras.alterarPrecosTransportadora("Transportes Nogueira",0.2,0.6,8.4,5.5));

        thrown = assertThrows(Exception.class, () -> gestorTransportadoras.addTransportadora(transportadora));
        assertTrue(thrown.getMessage().equals("Transportadora já inserida"));

        thrown = assertThrows(Exception.class, () -> gestorTransportadoras.addTransportadora(transportadora2));
        assertTrue(thrown.getMessage().equals("Transportadora já inserida"));

        thrown = assertThrows(Exception.class, () -> gestorTransportadoras.alterarPrecosTransportadora("Transportes",0.2,0.6,8.4,5.5));
        assertTrue(thrown.getMessage().equals("Transportadora inexistente"));
    }

    public void testArtigoGestorTransportadoras(){

        GestorTransportadoras gestorTransportadoras = new GestorTransportadoras();
        Exception thrown;

        Transportadora transportadora = new Transportadora("Transportes Nogueira",2.5,5.0,8.4,0.5,false);
        Transportadora transportadora2 = new Transportadora("Transportes Resulima",4.7,8.8,10.6,0.6,true);

        Tshirt tshirt = new Tshirt(5,"Transportes Nogueira","Tshirt Amarela","FKTO","ZARA",26.00,0.23,"novo",0,0,false,"S","liso");
        Tshirt tshirt2 = new Tshirt(2,"Transportes Resulima","Tshirt fixe","FQTO","LV",26.90,0.23,"usado",0,0,true,"XL","riscas");

        assertDoesNotThrow(() -> gestorTransportadoras.addTransportadora(transportadora));
        assertDoesNotThrow(() -> gestorTransportadoras.addTransportadora(transportadora2));
        assertDoesNotThrow(() -> gestorTransportadoras.lookUpTransportadoraArtigo("Transportes Nogueira",tshirt.getPremium()));
        assertDoesNotThrow(() -> gestorTransportadoras.lookUpTransportadoraArtigo("Transportes Resulima",tshirt2.getPremium()));

        thrown = assertThrows(Exception.class, () -> gestorTransportadoras.lookUpTransportadoraArtigo("Transportes Resulima",tshirt.getPremium()));
        assertTrue(thrown.getMessage().equals("Transportadora e artigo de diferentes tipos"));

        thrown = assertThrows(Exception.class, () -> gestorTransportadoras.lookUpTransportadoraArtigo("Transportes Nogueira",tshirt2.getPremium()));
        assertTrue(thrown.getMessage().equals("Transportadora e artigo de diferentes tipos"));

        thrown = assertThrows(Exception.class, () -> gestorTransportadoras.lookUpTransportadoraArtigo("Transportes Nogueir",tshirt2.getPremium()));
        assertTrue(thrown.getMessage().equals("Transportadora inexistente"));
    }
}