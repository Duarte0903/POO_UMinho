package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import modulos.Encomenda;
import modulos.artigos.Tshirt;


public class EncomendaTest{

    public EncomendaTest(){}

    @BeforeEach
    public void setUp(){}

    @AfterEach
    public void tearDown(){}

    @Test
    public void testConstrutorEncomenda(){

        Encomenda.setAutoIncrement(0);
        Encomenda encomenda = new Encomenda(0);
        Encomenda encomendaclone = encomenda.clone();
        Encomenda encomenda2 = new Encomenda(2);

        assertTrue(encomenda.getEmbalagem().equals("pequena"));
        assertTrue(encomenda.getEstado().equals("pendente"));
        assertTrue(encomenda.getComprador() == 0);
        assertTrue(encomenda2.getComprador() == 2);
        assertTrue(encomenda.getCodigo() == 0);
        assertTrue(encomendaclone.getCodigo() == 0);
        assertTrue(encomenda2.getCodigo() == 1);
    }

    @Test
    public void testArtigoEncomenda() throws Exception{

        Encomenda.setAutoIncrement(0);
        Encomenda encomenda = new Encomenda(0);

        Tshirt tshirt = new Tshirt(5,"Transportes Nogueira","Tshirt Amarela","FKTO","ZARA",26.00,0.23,"novo",0,0,false,"S","liso");
        Tshirt tshirt2 = new Tshirt(2,"Transportes Resulima","Tshirt fixe","FQTO","LV",26.90,0.23,"usado",0,0,true,"XL","riscas");

        assertTrue(encomenda.size() == 0);

        encomenda.addArtigo(tshirt);
        assertTrue(encomenda.size() == 1); 
        assertTrue(encomenda.getEmbalagem().equals("pequena"));

        encomenda.addArtigo(tshirt2);
        assertTrue(encomenda.size() == 2);
        assertTrue(encomenda.getEmbalagem().equals("media"));

        assertTrue(encomenda.removeArtigo("FKTO").equals(tshirt));
        assertTrue(encomenda.getEmbalagem().equals("pequena"));
        assertTrue(encomenda.size() == 1);

        assertTrue(encomenda.removeArtigo("FQTO").equals(tshirt2));
        assertTrue(encomenda.getEmbalagem().equals("pequena"));
        assertTrue(encomenda.size() == 0);
    }

    @Test
    public void testComportamentoEncomenda(){

        Encomenda.setAutoIncrement(0);
        Encomenda encomenda = new Encomenda(0);

        Tshirt tshirt = new Tshirt(5,"Transportes Nogueira","Tshirt Amarela","FKTO","ZARA",26.00,0.23,"novo",0,0,false,"S","liso");

        assertTrue(encomenda.getDataCriacao() == null);
        assertTrue(encomenda.getEstado().equals("pendente"));
        
        encomenda.finalizarEncomenda();

        assertTrue(encomenda.getEstado().equals("finalizada"));
        assertTrue(encomenda.getDataCriacao().isEqual(LocalDate.parse("1970-01-01")));

        encomenda.expedirEncomenda();
        assertTrue(encomenda.getEstado().equals("expedida"));
    }
}