/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package svilproject2014.sessione;

import java.util.ArrayList;
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
     * Test del metodo load della classe GestoreIpotesi.
     * Verifiche: id non valido restituisce null.
     *            id valido (coerente con il database) restituisce un oggetto non vuoto.
     */
    @Test
    public void testLoad() {
        System.out.println("load - id < 1");
        int id = 0;
//        GestoreIpotesi expResult = null;
        GestoreIpotesi result = GestoreIpotesi.load(id);
        assertNull(result);
        
 /*       System.out.println("load - id = 1 - id non presente nel database");
        id = 1;
        result = GestoreIpotesi.load(id);
        System.out.println("load result null? " + (result == null));
        assertNull(result);
   */         
        
        System.out.println("load - id = 8 - id presente nel db");
        id = 8;
        result = GestoreIpotesi.load(id);
        assertNotNull(result);
        
 /*       System.out.println("load - id = 9 - id non presente nel db");
        id = 9;
        result = GestoreIpotesi.load(id);
        assertNull(result);
*/
    }

    /**
     * Test del metodo visualizzaAssociazioni della classe GestoreIpotesi.
     * La root non viene mai stampata, quindi si considera la listaAssociazioni a partire dal nodo 1.
     */
    @Test
    public void testVisualizzaAssociazioni() {
        System.out.println("visualizzaAssociazioni");
        GestoreIpotesi instance = GestoreIpotesi.load(8);
        System.out.println("visualizzaAssociazioni - instance " + instance.toString());
        List<Coppia> expResult = new ArrayList<>();
        expResult.add(new Coppia('x','o', new Coppia(' ',' ', null))); //creo un figlio della radice
        System.out.println("visualizzaAssociazioni - expResult " + expResult.toString());      
        List<Coppia> result = instance.visualizzaAssociazioni();
        System.out.println("visualizzaAssociazioni - result vuoto " + result.isEmpty()); 
        System.out.println("visualizzaAssociazioni - result vuoto " + result.get(0).toString()); //dà 1 null
        assertEquals(expResult, result);
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

    /**
     * Test of toString method, of class GestoreIpotesi.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        GestoreIpotesi instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
