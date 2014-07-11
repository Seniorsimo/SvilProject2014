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
public class CalcolatorePseudoTest {
    
    public CalcolatorePseudoTest() {
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
     * Test of calcola method, of class CalcolatorePseudo.
     */
    @Test
    public void testCalcola() {
        System.out.println("calcola");
        String chiave = "";
        CalcolatorePseudo instance = new CalcolatorePseudo();
        Mappatura result = instance.calcola(chiave);
        assertEquals('a', result.inverseMap(result.map('a')));
        assertEquals('f', result.inverseMap(result.map('f')));
        assertEquals('g', result.inverseMap(result.map('g')));
        assertEquals('z', result.inverseMap(result.map('z')));
        
    }
}