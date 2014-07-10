/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

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
public class CifratoreTest {
    
    public CifratoreTest() {
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
     * Test of cifra method, of class Cifratore.
     */
    @Test
    public void testCifra() {
        System.out.println("cifra");
        Mappatura map = CalcolatoreMappe.create("cesare").calcola("1");
        String testo = "ciao";
        String expResult = "djbp";
        String result = Cifratore.cifra(map, testo);
        assertEquals(expResult, result);
    }

    /**
     * Test of decifra method, of class Cifratore.
     */
    @Test
    public void testDecifra() {
        System.out.println("decifra");
        Mappatura map = CalcolatoreMappe.create("cesare").calcola("1");
        String testo = "djbp";
        String expResult = "ciao";
        String result = Cifratore.decifra(map, testo);
        assertEquals(expResult, result);
    }
}