/*
 * To change this template, choose Tools | Templates
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
 * @author User
 */
public class StrumentiDiSupportoTest {
    
    public StrumentiDiSupportoTest() {
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
     * Test of aggiornaLingua method, of class StrumentiDiSupporto.
     */
    @Test
    public void testAggiornaLingua() {
        System.out.println("aggiornaLingua");
        int idLingua = 1;
        StrumentiDiSupporto instance = new StrumentiDiSupporto(1);
        boolean expResult = true;
        boolean result = instance.aggiornaLingua(idLingua);
        assertEquals(expResult, result);
        assertEquals(1, instance.getLingua());
    }

    /**
     * Test of cercaPatternSulDizionario method, of class StrumentiDiSupporto.
     */
    @Test
    public void testCercaPatternSulDizionario() {
        System.out.println("cercaPatternSulDizionario");
        String pattern = "CIAn";
        StrumentiDiSupporto instance = new StrumentiDiSupporto(1);
        List result = instance.cercaPatternSulDizionario(pattern);
        assertEquals("ciao", result.get(0));
    }

    
}