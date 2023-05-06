package tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import modulos.Utilizador;
import modulos.gestores.GestorUtilizadores;


public class GestorUtilizadoresTest{

    public GestorUtilizadoresTest(){}

    @BeforeEach
    public void setUp(){}

    @AfterEach
    public void tearDown(){}

    @Test
    public void testConstrutorGestorUtilizadores(){

        GestorUtilizadores gestorUtilizadores = new GestorUtilizadores();

        assertTrue(gestorUtilizadores != null);
    }

    @Test
    public void testUtilizadoresGestorUtilizadores(){

        GestorUtilizadores gestorUtilizadores = new GestorUtilizadores();
        Exception thrown;

        Utilizador.setAutoIncrement(0);
        Utilizador utilizador = new Utilizador("al19813@aebarcelos.pt","1234","Diogo Marques",123456789,"Rua das Flores");
        Utilizador utilizador2 = new Utilizador("maximino.marques@outlook.pt","4896","Maximino Marques",123789456,"Rua Feliz");

        assertTrue(gestorUtilizadores.getSize() == 0);

        assertDoesNotThrow(() -> gestorUtilizadores.addUtilizador(utilizador));
        assertTrue(gestorUtilizadores.getSize() == 1);
        assertDoesNotThrow(() -> gestorUtilizadores.addUtilizador(utilizador2));
        assertTrue(gestorUtilizadores.getSize() == 2);

        thrown = assertThrows(Exception.class, () -> gestorUtilizadores.lookUpUtilizador(10));
        assertTrue(thrown.getMessage().equals("Utilizador inexistente"));

        thrown = assertThrows(Exception.class, () -> gestorUtilizadores.lookupUtilizadorByEmail("xxxx"));
        assertTrue(thrown.getMessage().equals("Utilizador inexistente"));

        assertDoesNotThrow(() -> gestorUtilizadores.lookUpUtilizador(0));
        assertDoesNotThrow(() -> gestorUtilizadores.lookUpUtilizador(1));
        assertDoesNotThrow(() -> gestorUtilizadores.lookupUtilizadorByEmail("al19813@aebarcelos.pt"));
        assertDoesNotThrow(() -> gestorUtilizadores.lookupUtilizadorByEmail("maximino.marques@outlook.pt"));
    }

    @Test
    public void testLoginGestorUtilizadores(){

        GestorUtilizadores gestorUtilizadores = new GestorUtilizadores();
        Exception thrown;

        Utilizador.setAutoIncrement(0);
        Utilizador utilizador = new Utilizador("al19813@aebarcelos.pt","1234","Diogo Marques",123456789,"Rua das Flores");
        Utilizador utilizador2 = new Utilizador("maximino.marques@outlook.pt","4896","Maximino Marques",123789456,"Rua Feliz");

        assertDoesNotThrow(() -> gestorUtilizadores.addUtilizador(utilizador));
        assertDoesNotThrow(() -> gestorUtilizadores.addUtilizador(utilizador2));

        assertDoesNotThrow(() -> assertTrue(gestorUtilizadores.login("al19813@aebarcelos.pt","1234") != null));
        assertDoesNotThrow(() -> assertTrue(gestorUtilizadores.login("maximino.marques@outlook.pt","4896") != null));

        thrown = assertThrows(Exception.class, () -> gestorUtilizadores.login("xxxx","1234"));
        assertTrue(thrown.getMessage().equals("Utilizador inexistente"));

        thrown = assertThrows(Exception.class, () -> gestorUtilizadores.login("al19813@aebarcelos.pt","1235"));
        assertTrue(thrown.getMessage().equals("Password inválida"));

        thrown = assertThrows(Exception.class, () -> gestorUtilizadores.login("maximino.marques@outlook.pt","1235"));
        assertTrue(thrown.getMessage().equals("Password inválida"));
    }
}