/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simone
 */
public class PropostaTest {
    
    public PropostaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

        /**
     * Test of caricaAttiva method, of class Proposta.
     */
    @Test
    public void testCaricaAttiva() {
        System.out.println("caricaAttiva");
        String idProp = "1";
        String idPart = "2";
        Proposta result = Proposta.caricaAttiva(idProp, idPart);
        assertEquals("1", result.getId());
        assertEquals("2", result.getIdPartner());
        assertEquals("1", result.getIdProponente());
        assertEquals("accepted", result.getStato());
    }

        /**
     * Test of getSdc method, of class Proposta.
     */
    @Test
    public void testGetSdc() {
        System.out.println("getSdc");
        Proposta instance = Proposta.caricaAttiva("1", "2");
        SistemaDiCifratura expResult = SistemaDiCifratura.load("2");
        SistemaDiCifratura result = instance.getSdc();
        assertEquals(expResult.getClass(), result.getClass());
        assertEquals(expResult.getId(), result.getId());
        for(int i=0; i<26; i++){
        assertEquals(expResult.getMappatura().getMappa()[i], result.getMappatura().getMappa()[i]);
        assertEquals(expResult.getMappatura().getMappaInversa()[i], result.getMappatura().getMappaInversa()[i]);
        }
    }

}