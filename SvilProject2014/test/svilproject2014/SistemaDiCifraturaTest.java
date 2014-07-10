/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.util.List;
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
public class SistemaDiCifraturaTest {
    
    public SistemaDiCifraturaTest() {
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
     * Test of caricaSistemiCifratura method, of class SistemaDiCifratura.
     */
    @Test
    public void testCaricaSistemiCifratura() {
        System.out.println("caricaSistemiCifratura");
        Studente stud = Studente.gelListaStudenti().get(0);
        List result = SistemaDiCifratura.caricaSistemiCifratura(stud);
        assertEquals("1", ((SistemaDiCifratura)result.get(0)).getId());
        assertEquals("3", ((SistemaDiCifratura)result.get(0)).getChiave());
        assertEquals("cesare", ((SistemaDiCifratura)result.get(0)).getMetodo());
    }

    /**
     * Test of load method, of class SistemaDiCifratura.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        String id = "1";
        SistemaDiCifratura result = SistemaDiCifratura.load(id);
        assertEquals("1", result.getId());
        assertEquals("3", result.getChiave());
        assertEquals("cesare", result.getMetodo());
    }

    

    /**
     * Test of prova method, of class SistemaDiCifratura.
     */
    @Test
    public void testProva() {
        System.out.println("prova");
        String testo = "Ciao";
        SistemaDiCifratura instance = SistemaDiCifratura.load("1");
        String expResult = "fldr";
        String result = instance.prova(testo);
        assertEquals(expResult, result);
    }
    
}