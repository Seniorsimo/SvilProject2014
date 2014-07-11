/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class SessioneDiLavoroTest {
    
    public SessioneDiLavoroTest() {
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
     * Test of load method, of class SessioneDiLavoro.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        int id = 0;
        SessioneDiLavoro expResult = null;
        SessioneDiLavoro result = SessioneDiLavoro.load(1);
        assertEquals(1, result.getId());
        assertEquals(1, result.getId_lingua());
        assertEquals(1, result.getId_utente());
        assertEquals("aperto", result.getStato());
        assertEquals("1", result.getMessaggio().getId());
        assertEquals(1, result.getGestoreIpotesi().getId());
    }

    /**
     * Test of salva method, of class SessioneDiLavoro.
     */
    @Test
    public void testSalva() {
        System.out.println("salva");
        SessioneDiLavoro instance = SessioneDiLavoro.load(1);
        boolean expResult = true;
        boolean result = instance.salva();
        assertEquals(expResult, result);
    }

    /**
     * Test of abbandona method, of class SessioneDiLavoro.
     */
    @Test
    public void testAbbandona() {
        System.out.println("abbandona");
        SessioneDiLavoro instance = SessioneDiLavoro.load(1);
        boolean expResult = true;
        boolean result = instance.abbandona();
        assertEquals(expResult, result);
        assertEquals("abbandonato", instance.getStato());
        //ripristino stato precedente
        instance.setStato("aperto");
        assertEquals("aperto", instance.getStato());
        instance.salva();
    }
}