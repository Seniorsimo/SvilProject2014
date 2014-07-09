/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package svilproject2014.sessione;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stefania
 */
public class GestoreIpotesiTest {
    
    public GestoreIpotesiTest() {
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
     * Test of load method, of class GestoreIpotesi.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        int id = 0;
        GestoreIpotesi expResult = null;
        GestoreIpotesi result = GestoreIpotesi.load(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of visualizzaAssociazioni method, of class GestoreIpotesi.
     */
    @Test
    public void testVisualizzaAssociazioni() {
        System.out.println("visualizzaAssociazioni");
        GestoreIpotesi instance = null;
        List<Coppia> expResult = null;
        List<Coppia> result = instance.visualizzaAssociazioni();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aggiungiIpotesi method, of class GestoreIpotesi.
     */
    @Test
    public void testAggiungiIpotesi() {
        System.out.println("aggiungiIpotesi");
        char vecchiaLettera = ' ';
        char nuovaLettera = ' ';
        GestoreIpotesi instance = null;
        boolean expResult = false;
        boolean result = instance.aggiungiIpotesi(vecchiaLettera, nuovaLettera);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of visualizzaStoria method, of class GestoreIpotesi.
     */
    @Test
    public void testVisualizzaStoria() {
        System.out.println("visualizzaStoria");
        GestoreIpotesi instance = null;
        Coppia expResult = null;
        Coppia result = instance.visualizzaStoria();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of avanti method, of class GestoreIpotesi.
     */
    @Test
    public void testAvanti() {
        System.out.println("avanti");
        int n = 0;
        GestoreIpotesi instance = null;
        boolean expResult = false;
        boolean result = instance.avanti(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indietro method, of class GestoreIpotesi.
     */
    @Test
    public void testIndietro() {
        System.out.println("indietro");
        int n = 0;
        GestoreIpotesi instance = null;
        boolean expResult = false;
        boolean result = instance.indietro(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verificaRisoluzione method, of class GestoreIpotesi.
     */
    @Test
    public void testVerificaRisoluzione() {
        System.out.println("verificaRisoluzione");
        GestoreIpotesi instance = null;
        boolean expResult = false;
        boolean result = instance.verificaRisoluzione();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salva method, of class GestoreIpotesi.
     */
    @Test
    public void testSalva() {
        System.out.println("salva");
        GestoreIpotesi instance = null;
        boolean expResult = false;
        boolean result = instance.salva();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
