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
        Coppia instance = null;
        List<Coppia> expResult = null;
        List<Coppia> result = instance.getFigli();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
//DEFA:        Coppia c = null;
        Coppia c = new Coppia('a','b',null);
//DEFA:        Coppia instance = null;
        Coppia instance = new Coppia('c','b', null);
        
        boolean expResult = false;
        boolean result = instance.addFiglio(c);
        assertEquals(expResult, result);
        System.out.println("addFiglio - exp false effettuato");
//NOTA: al primo assert sbagliato smette di eseguire il metodo e dà fallimento        
        expResult = true;
        result = instance.addFiglio(c);
        assertEquals(expResult, result);
        System.out.println("addFiglio exp true - effettuato");
        
        // TODO review the generated test code and remove the default call to fail.
//DEFA:        fail("The test case is a prototype.");
    }
    
}
