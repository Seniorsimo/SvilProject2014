/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

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
 * @author Simone
 */
public class StudenteTest {
    
    public StudenteTest() {
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
     * Test of getUserInfo method, of class Studente.
     */
    @Test
    public void testGetUserInfo() {
        System.out.println("getUserInfo");
        Studente instance = Studente.gelListaStudenti().get(0);
        UserInfo result = instance.getUserInfo();
        assertEquals("1", result.getId());
        assertEquals("Marco", result.getNome());
        assertEquals("Rossi", result.getCognome());
    }

    /**
     * Test of gelListaStudenti method, of class Studente.
     */
    @Test
    public void testGelListaStudenti() {
        System.out.println("gelListaStudenti");
        List result = Studente.gelListaStudenti();
        assertEquals("1", ((Studente)result.get(0)).getId());
        assertEquals("Marco", ((Studente)result.get(0)).getNome());
        assertEquals("Rossi", ((Studente)result.get(0)).getCognome());
        assertEquals("2", ((Studente)result.get(1)).getId());
        assertEquals("Giovanna", ((Studente)result.get(1)).getNome());
        assertEquals("Verdi", ((Studente)result.get(1)).getCognome());
    }
}