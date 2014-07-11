/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.calcolatori;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import svilproject2014.Mappatura;

/**
 *
 * @author User
 */
public class CalcolatoreCesareTest {
    
    public CalcolatoreCesareTest() {
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
     * Test of calcola method, of class CalcolatoreCesare.
     */
    @Test
    public void testCalcola() {
        System.out.println("calcola");
        String chiave = "2";
        CalcolatoreCesare instance = new CalcolatoreCesare();
        Mappatura result = instance.calcola(chiave);
        assertEquals('c', result.map('a'));
        assertEquals('h', result.map('f'));
        assertEquals('l', result.inverseMap('n'));
        assertEquals('x', result.inverseMap('z'));
    }
}