package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import modulos.Encomenda;
import modulos.gestores.GestorEncomendas;


public class GestorEncomendasTest{

    public GestorEncomendasTest(){}

    @BeforeEach
    public void setUp(){}

    @AfterEach
    public void tearDown(){}

    @Test
    public void testConstrutorGestorEncomendas(){

        GestorEncomendas gestorEncomendas = new GestorEncomendas();

        assertTrue(gestorEncomendas != null);
    }

    @Test
    public void testCompradorGestorEncomendas(){

        GestorEncomendas gestorEncomendas = new GestorEncomendas();
        Encomenda.setAutoIncrement(0);

        assertDoesNotThrow(() -> gestorEncomendas.addEncomenda(new Encomenda(0)));
        assertDoesNotThrow(() -> gestorEncomendas.addEncomenda(new Encomenda(1)));
        assertDoesNotThrow(() -> gestorEncomendas.addEncomenda(new Encomenda(1)));

        assertDoesNotThrow(() -> assertTrue(gestorEncomendas.getCompradorEncomenda(0) == 0));
        assertDoesNotThrow(() -> assertTrue(gestorEncomendas.getCompradorEncomenda(1) == 1));
        assertDoesNotThrow(() -> assertTrue(gestorEncomendas.getCompradorEncomenda(2) == 1));
    }

    @Test
    public void testEncomendasGestorEncomdendas(){

        GestorEncomendas gestorEncomendas = new GestorEncomendas();
        Exception thrown;

        Encomenda.setAutoIncrement(0);

        assertDoesNotThrow(() -> gestorEncomendas.addEncomenda(new Encomenda(0)));
        assertDoesNotThrow(() -> gestorEncomendas.addEncomenda(new Encomenda(1)));
        assertDoesNotThrow(() -> gestorEncomendas.addEncomenda(new Encomenda(1)));

        thrown = assertThrows(Exception.class, () -> gestorEncomendas.lookUpEncomenda(0,1,"pendente"));
        assertTrue(thrown.getMessage().equals("Não possui a encomenda"));

        thrown = assertThrows(Exception.class, () -> gestorEncomendas.lookUpEncomenda(10,1,"pendente"));
        assertTrue(thrown.getMessage().equals("Encomenda inexistente"));

        thrown = assertThrows(Exception.class, () -> gestorEncomendas.lookUpEncomenda(0,0,"finalizada"));
        assertTrue(thrown.getMessage().equals("A encomenda não está finalizada"));

        assertDoesNotThrow(() -> gestorEncomendas.lookUpEncomenda(0,0,"pendente"));
        assertDoesNotThrow(() -> gestorEncomendas.removeEncomenda(0));

        thrown = assertThrows(Exception.class,() -> gestorEncomendas.lookUpEncomenda(0,0,"pendente"));
        assertTrue(thrown.getMessage().equals("Encomenda inexistente"));

        assertDoesNotThrow(() -> gestorEncomendas.lookUpEncomenda(1,1,"pendente"));
        assertDoesNotThrow(() -> gestorEncomendas.lookUpEncomenda(2,1,"pendente"));
        assertDoesNotThrow(() -> gestorEncomendas.addEncomenda(new Encomenda(0)));
        assertDoesNotThrow(() -> gestorEncomendas.lookUpEncomenda(3,0,"pendente"));

        assertTrue(gestorEncomendas.getDataEncomenda(1) == null);
        gestorEncomendas.finalizarEncomenda(1);
        assertTrue(gestorEncomendas.getDataEncomenda(1).isEqual(LocalDate.parse("1970-01-01")));
        assertDoesNotThrow(() -> gestorEncomendas.lookUpEncomenda(1,1,"finalizada"));
        assertDoesNotThrow(() -> gestorEncomendas.lookUpEncomenda(2,1,"pendente"));
        assertDoesNotThrow(() -> gestorEncomendas.lookUpEncomenda(3,0,"pendente"));

        thrown = assertThrows(Exception.class, () -> gestorEncomendas.lookUpEncomenda(1,1,"pendente"));
        assertTrue(thrown.getMessage().equals("A encomenda não está pendente"));

        thrown = assertThrows(Exception.class, () -> gestorEncomendas.lookUpEncomenda(1,1,"expedida"));
        assertTrue(thrown.getMessage().equals("A encomenda não está expedida"));

        gestorEncomendas.expedirEncomenda(1);
        assertDoesNotThrow(() -> gestorEncomendas.lookUpEncomenda(1,1,"expedida"));

        thrown = assertThrows(Exception.class, () -> gestorEncomendas.lookUpEncomenda(1,0,"expedida"));
        assertTrue(thrown.getMessage().equals("Não possui a encomenda"));

        thrown = assertThrows(Exception.class, () -> gestorEncomendas.lookUpEncomenda(10,0,"expedida"));
        assertTrue(thrown.getMessage().equals("Encomenda inexistente"));
    }
}