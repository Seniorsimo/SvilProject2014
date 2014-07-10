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
public class MappaturaTest {
    
    public MappaturaTest() {
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
     * Test of map method, of class Mappatura.
     */
    @Test
    public void testMap() {
        System.out.println("map");
        char c = 'a';
        char[] m1 = {'b','a','c'};
        char[] m2 = {'c','a','b'};
        Mappatura instance = new Mappatura(m1,m2);
        char expResult = 'b';
        char result = instance.map(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of inverseMap method, of class Mappatura.
     */
    @Test
    public void testInverseMap() {
        System.out.println("inverseMap");
        char c = 'a';
        char[] m1 = {'a','b','c'};
        char[] m2 = {'c','a','b'};
        Mappatura instance = new Mappatura(m1,m2);
        char expResult = 'c';
        char result = instance.inverseMap(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Mappatura.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        char[] m1 = {'a','b','c'};
        char[] m2 = {'c','a','b'};
        Mappatura instance = new Mappatura(m1,m2);
        String expResult = "map: abc\ninversa: cab";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}