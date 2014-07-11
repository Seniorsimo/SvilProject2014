/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

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
public class MessaggioTest {
    
    public MessaggioTest() {
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
     * Test of load method, of class Messaggio.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        String id = "1";
        Messaggio result = Messaggio.load(id);
        assertEquals("1", result.getId());
        assertEquals("Ciao", result.getTesto());
        assertEquals("", result.getTestoCifrato());
        assertEquals("Italiano", result.getLingua());
        assertEquals("Test", result.getTitolo());
        assertEquals("1", result.getMittente().getId());
        assertEquals("2", result.getDestinatario().getId());
        assertEquals(true, result.isBozza());
        assertEquals(false, result.isLetto());
    }

    /**
     * Test of caricaBozze method, of class Messaggio.
     */
    @Test
    public void testCaricaBozze() {
        System.out.println("caricaBozze");
        Studente stud = Studente.getListaStudenti().get(0);
        List result = Messaggio.caricaBozze(stud);
        assertEquals("1", ((Messaggio)result.get(0)).getId());
        assertEquals("Ciao", ((Messaggio)result.get(0)).getTesto());
        assertEquals("", ((Messaggio)result.get(0)).getTestoCifrato());
        assertEquals("Italiano", ((Messaggio)result.get(0)).getLingua());
        assertEquals("Test", ((Messaggio)result.get(0)).getTitolo());
        assertEquals("1", ((Messaggio)result.get(0)).getMittente().getId());
        assertEquals("2", ((Messaggio)result.get(0)).getDestinatario().getId());
        assertEquals(true, ((Messaggio)result.get(0)).isBozza());
        assertEquals(false, ((Messaggio)result.get(0)).isLetto());
    }

    /**
     * Test of caricaInviati method, of class Messaggio.
     */
    @Test
    public void testCaricaInviati() {
        System.out.println("caricaInviati");
        Studente stud = Studente.getListaStudenti().get(0);
        List result = Messaggio.caricaInviati(stud);
        assertEquals("2", ((Messaggio)result.get(0)).getId());
        assertEquals("Ciao2", ((Messaggio)result.get(0)).getTesto());
        assertEquals("", ((Messaggio)result.get(0)).getTestoCifrato());
        assertEquals("Italiano", ((Messaggio)result.get(0)).getLingua());
        assertEquals("Test", ((Messaggio)result.get(0)).getTitolo());
        assertEquals("1", ((Messaggio)result.get(0)).getMittente().getId());
        assertEquals("2", ((Messaggio)result.get(0)).getDestinatario().getId());
        assertEquals(false, ((Messaggio)result.get(0)).isBozza());
        assertEquals(false, ((Messaggio)result.get(0)).isLetto());
    }

    /**
     * Test of caricaRicevuti method, of class Messaggio.
     */
    @Test
    public void testCaricaRicevuti() {
        System.out.println("caricaRicevuti");
        Studente stud = Studente.getListaStudenti().get(1);
        List result = Messaggio.caricaRicevuti(stud);
        assertEquals("2", ((Messaggio)result.get(0)).getId());
        assertEquals("Ciao2", ((Messaggio)result.get(0)).getTesto());
        assertEquals("", ((Messaggio)result.get(0)).getTestoCifrato());
        assertEquals("Italiano", ((Messaggio)result.get(0)).getLingua());
        assertEquals("Test", ((Messaggio)result.get(0)).getTitolo());
        assertEquals("1", ((Messaggio)result.get(0)).getMittente().getId());
        assertEquals("2", ((Messaggio)result.get(0)).getDestinatario().getId());
        assertEquals(false, ((Messaggio)result.get(0)).isBozza());
        assertEquals(false, ((Messaggio)result.get(0)).isLetto());
    }

    /**
     * Test of caricaSpiabili method, of class Messaggio.
     */
    @Test
    public void testCaricaSpiabili() {
        System.out.println("caricaSpiabili");
        Studente stud = Studente.getListaStudenti().get(0);
        List result = Messaggio.caricaSpiabili(stud);
        assertEquals("3", ((Messaggio)result.get(0)).getId());
        assertEquals("Ciao3", ((Messaggio)result.get(0)).getTesto());
        assertEquals("", ((Messaggio)result.get(0)).getTestoCifrato());
        assertEquals("Italiano", ((Messaggio)result.get(0)).getLingua());
        assertEquals("Test", ((Messaggio)result.get(0)).getTitolo());
        assertEquals("2", ((Messaggio)result.get(0)).getMittente().getId());
        assertEquals("3", ((Messaggio)result.get(0)).getDestinatario().getId());
        assertEquals(false, ((Messaggio)result.get(0)).isBozza());
        assertEquals(false, ((Messaggio)result.get(0)).isLetto());
    }

}