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
     * Test of getFigli method, of class Coppia.
     */
    @Test
    public void testGetFigli() {
        System.out.println("getFigli");
        Coppia instance = new Coppia('c','a',null);
        List<Coppia> expResult = new ArrayList<>();
        List<Coppia> result = instance.getFigli();
        assertEquals(expResult, result);
        
        Coppia c = new Coppia('d','e',instance);
        expResult.add(c);
        result = instance.getFigli();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPadre method, of class Coppia.
     */
    @Test
    public void testGetPadre() {
        System.out.println("getPadre");
        Coppia instance = null;
        Coppia expResult = null;
        Coppia result = instance.getPadre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFiglio method, of class Coppia.
     */
    @Test
    public void testAddFiglio() {
        System.out.println("addFiglio");
        Coppia c = null;
        Coppia instance = null;
        boolean expResult = false;
        boolean result = instance.addFiglio(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salva method, of class Coppia.
     */
    @Test
    public void testSalva() {
        System.out.println("salva");
        Coppia instance = null;
        String expResult = "";
        String result = instance.salva();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class Coppia.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        List<Character> ref = null;
        Coppia instance = null;
        instance.load(ref);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVecchiaL method, of class Coppia.
     */
    @Test
    public void testGetVecchiaL() {
        System.out.println("getVecchiaL");
        Coppia instance = null;
        char expResult = ' ';
        char result = instance.getVecchiaL();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNuovaL method, of class Coppia.
     */
    @Test
    public void testGetNuovaL() {
        System.out.println("getNuovaL");
        Coppia instance = null;
        char expResult = ' ';
        char result = instance.getNuovaL();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Coppia.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Coppia instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
