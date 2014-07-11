/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.messaggio;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import svilproject2014.Messaggio;
import svilproject2014.SistemaDiCifratura;
import svilproject2014.UserInfo;

/**
 *
 * @author User
 */
public class MessaggioRealeTest {
    
    public MessaggioRealeTest() {
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
     * Test of cifra method, of class MessaggioReale.
     */
    @Test
    public void testCifra() {
        System.out.println("cifra");
        Messaggio instance = Messaggio.load("1");
        String expResult = "shcn";
        String result = instance.cifra();
        assertEquals(expResult, result);
    }

    /**
     * Test of decifra method, of class MessaggioReale.
     */
    @Test
    public void testDecifra() {
        System.out.println("decifra");
        Messaggio instance = Messaggio.load("1");
        String expResult = "ciao";
        instance.cifra();
        String result = instance.decifra();
        assertEquals(expResult, result);
    }
}