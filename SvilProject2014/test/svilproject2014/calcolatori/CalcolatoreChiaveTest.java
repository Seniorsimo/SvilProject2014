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
public class CalcolatoreChiaveTest {
    
    public CalcolatoreChiaveTest() {
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
     * Test of calcola method, of class CalcolatoreChiave.
     */
    @Test
    public void testCalcola() {
        System.out.println("calcola");
        String chiave = "ciao";
        CalcolatoreChiave instance = new CalcolatoreChiave();
        Mappatura result = instance.calcola(chiave);
        assertEquals('a', result.map('c'));
        assertEquals('c', result.map('a'));
        assertEquals('b', result.inverseMap('i'));
        assertEquals('d', result.inverseMap('o'));
    }
}