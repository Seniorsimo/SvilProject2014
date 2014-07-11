/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione.strumenti;

import java.util.List;
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
public class DizionarioTest {
    
    public DizionarioTest() {
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
     * Test of load method, of class Dizionario.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        int id = 1;
        Dizionario result = Dizionario.load(id);
        assertEquals(1, result.getIdLingua());
    }

    /**
     * Test of cerca method, of class Dizionario.
     */
    @Test
    public void testCerca() {
        System.out.println("cerca");
        String pattern = "CIAm";
        Dizionario instance = Dizionario.load(1);
        List result = instance.cerca(pattern);
        assertEquals("ciao", result.get(0));
    }

}