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
public class CoppiaTest {
    
    public CoppiaTest() {
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
     * Test of addFiglio method, of class Coppia.
     */
    @Test
    public void testAddFiglio() {
        System.out.println("addFiglio");
        Coppia instance = new Coppia('f','n',null);
        boolean expResult = true;
        boolean result = instance.addFiglio(new Coppia('a','f',null));
        assertEquals(expResult, result);
        assertEquals(1, instance.getFigli().size());
        assertEquals(new Coppia('a','f',null), instance.getFigli().get(0));
    }

    /**
     * Test of salva method, of class Coppia.
     */
    @Test
    public void testSalva() {
        System.out.println("salva");
        Coppia instance = new Coppia('f','n',null);
        String expResult = "(fn,0,)";
        String result = instance.salva();
        assertEquals(expResult, result);
    }

    /**
     * Test of load method, of class Coppia.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        List<Character> ref = new ArrayList<>();
        ref.add('(');
        ref.add('f');
        ref.add('n');
        ref.add(',');
        ref.add('0');
        ref.add(',');
        ref.add(')');
        Coppia instance = new Coppia(' ',' ',null);
        instance.load(ref);
        assertEquals(new Coppia('f','n',null),instance);
    }

    /**
     * Test of toString method, of class Coppia.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Coppia instance = new Coppia('f','n',null);
        String expResult = "(fn,)";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
