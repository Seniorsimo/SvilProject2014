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
import svilproject2014.calcolatori.CalcolatoreCesare;
import svilproject2014.calcolatori.CalcolatoreChiave;
import svilproject2014.calcolatori.CalcolatorePseudo;

/**
 *
 * @author Simone
 */
public class CalcolatoreMappeTest {
    
    public CalcolatoreMappeTest() {
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
     * Test of create method, of class CalcolatoreMappe.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String met = "cesare";
        CalcolatoreMappe expResult = new CalcolatoreCesare();
        CalcolatoreMappe result = CalcolatoreMappe.create(met);
        assertEquals(expResult.getClass(), result.getClass());
        
        met = "chiave";
        expResult = new CalcolatoreChiave();
        result = CalcolatoreMappe.create(met);
        assertEquals(expResult.getClass(), result.getClass());
        
        met = "casuale";
        expResult = new CalcolatorePseudo();
        result = CalcolatoreMappe.create(met);
        assertEquals(expResult.getClass(), result.getClass());
    }

}