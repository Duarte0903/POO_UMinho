package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import modulos.gestores.GestorArtigos;
import modulos.artigos.Tshirt;


public class GestorArtigosTest{

    public GestorArtigosTest(){}

    @BeforeEach
    public void setUp(){}

    @AfterEach
    public void tearDown(){}

    @Test
    public void testConstrutorGestorArtigos(){

        GestorArtigos gestorArtigos = new GestorArtigos();

        assertTrue(gestorArtigos != null);
    }

    @Test
    public void testArtigosConstrutorGestorArtigos(){

        GestorArtigos gestorArtigos = new GestorArtigos();
        Exception thrown;

        Tshirt tshirt = new Tshirt(5,"Transportes Nogueira","Tshirt Amarela","FKTO","ZARA",26.00,0.23,"novo",0,0,false,"S","liso");
        Tshirt tshirt2 = new Tshirt(2,"Transportes Resulima","Tshirt fixe","FQTO","LV",26.90,0.23,"usado",0,0,true,"XL","riscas");
    
        assertDoesNotThrow(() ->gestorArtigos.addArtigo(tshirt));
        assertDoesNotThrow(() -> gestorArtigos.addArtigo(tshirt2));

        thrown = assertThrows(Exception.class, () -> gestorArtigos.addArtigo(tshirt));
        assertTrue(thrown.getMessage().equals("Artigo já inserido"));

        thrown = assertThrows(Exception.class, () -> gestorArtigos.getArtigo(tshirt.getCodigo(),0));
        assertTrue(thrown.getMessage().equals("Não possui o artigo"));

        thrown = assertThrows(Exception.class, () -> gestorArtigos.getArtigo("SSSS",0));
        assertTrue(thrown.getMessage().equals("Artigo inexistente"));

        thrown = assertThrows(Exception.class, () -> gestorArtigos.getArtigo(tshirt2.getCodigo(),10));
        assertTrue(thrown.getMessage().equals("Não possui o artigo"));

        assertDoesNotThrow(() -> assertTrue(gestorArtigos.getArtigo(tshirt.getCodigo(),5).equals(tshirt)));
        assertDoesNotThrow(() -> assertFalse(gestorArtigos.getArtigo(tshirt.getCodigo(),5).equals(tshirt2)));
        assertDoesNotThrow(() -> assertTrue(gestorArtigos.getArtigo(tshirt2.getCodigo(),2).equals(tshirt2)));
        assertDoesNotThrow(() -> assertFalse(gestorArtigos.getArtigo(tshirt2.getCodigo(),2).equals(tshirt)));

        thrown = assertThrows(Exception.class, () -> gestorArtigos.removeArtigo("SSSS"));
        assertTrue(thrown.getMessage().equals("Artigo inexistente"));

        assertDoesNotThrow(() -> assertTrue(gestorArtigos.removeArtigo(tshirt.getCodigo()).equals(tshirt)));
        assertDoesNotThrow(() -> assertTrue(gestorArtigos.removeArtigo(tshirt2.getCodigo()).equals(tshirt2)));

        thrown = assertThrows(Exception.class, () -> gestorArtigos.removeArtigo(tshirt.getCodigo()));
        assertTrue(thrown.getMessage().equals("Artigo inexistente"));

        thrown = assertThrows(Exception.class, () -> gestorArtigos.getArtigo(tshirt2.getCodigo(),5));
        assertTrue(thrown.getMessage().equals(("Artigo inexistente")));

        assertDoesNotThrow(() -> gestorArtigos.addArtigo(tshirt2));
        assertDoesNotThrow(() -> assertFalse(gestorArtigos.getArtigo(tshirt2.getCodigo(),2).equals(tshirt)));
        assertDoesNotThrow(() -> assertTrue(gestorArtigos.getArtigo(tshirt2.getCodigo(),2).equals(tshirt2)));

        thrown = assertThrows(Exception.class, () -> gestorArtigos.removeArtigo(tshirt.getCodigo()));
        assertTrue(thrown.getMessage().equals("Artigo inexistente"));

        assertDoesNotThrow(() -> assertTrue(gestorArtigos.removeArtigo(tshirt2.getCodigo()).equals(tshirt2)));
    }
}