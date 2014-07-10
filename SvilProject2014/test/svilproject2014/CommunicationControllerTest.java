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
public class CommunicationControllerTest {
    
    public CommunicationControllerTest() {
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
     * Test of send method, of class CommunicationController.
     * Testa solamente il caso del parametro null. il resto deve essere testato in Messaggio.send()
     */
    @Test
    public void testSend() {
        System.out.println("send");
        Messaggio msg = null;
        boolean expResult = false;
        boolean result = CommunicationController.send(msg);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDestinatari method, of class CommunicationController.
     */
    @Test
    public void testGetDestinatari() {
        System.out.println("getDestinatari");
        Studente usr = Studente.gelListaStudenti().get(0);
        List result = CommunicationController.getDestinatari(usr);
        assertEquals("2", ((UserInfo)result.get(0)).getId());
        assertEquals("Giovanna", ((UserInfo)result.get(0)).getNome());
        assertEquals("Verdi", ((UserInfo)result.get(0)).getCognome());
    }

    /**
     * Test of inviaProposta method, of class CommunicationController.
     * Testo solo il caso in cui uno dei parametri sia null. il resto è testato in proposta.salva()
     */
    @Test
    public void testInviaProposta() {
        System.out.println("inviaProposta");
        Studente usr = Studente.gelListaStudenti().get(0);
        UserInfo partner = Studente.gelListaStudenti().get(1).getUserInfo();
        SistemaDiCifratura sdc = SistemaDiCifratura.load("2");
        boolean expResult = false;
        boolean result = CommunicationController.inviaProposta(usr, partner, null);
        assertEquals(expResult, result);
        result = CommunicationController.inviaProposta(usr, null, sdc);
        assertEquals(expResult, result);
        result = CommunicationController.inviaProposta(null, partner, sdc);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccettazioneProposte method, of class CommunicationController.
     */
    @Test
    public void testGetAccettazioneProposte() {
        System.out.println("getAccettazioneProposte");
        Studente usr = Studente.gelListaStudenti().get(1);
        List result = CommunicationController.getAccettazioneProposte(usr);
        assertEquals("1", ((Proposta)result.get(0)).getId());
        assertEquals("2", ((Proposta)result.get(0)).getIdPartner());
        assertEquals("1", ((Proposta)result.get(0)).getIdProponente());
        assertEquals("accepted", ((Proposta)result.get(0)).getStato());
        assertEquals(false, ((Proposta)result.get(0)).isNotificata());
    }

    /**
     * Test of getProposte method, of class CommunicationController.
     */
    @Test
    public void testGetProposte() {
        System.out.println("getProposte");
        Studente usr = Studente.gelListaStudenti().get(1);
        List result = CommunicationController.getProposte(usr);
        assertEquals("2", ((Proposta)result.get(0)).getId());
        assertEquals("2", ((Proposta)result.get(0)).getIdPartner());
        assertEquals("1", ((Proposta)result.get(0)).getIdProponente());
        assertEquals("pending", ((Proposta)result.get(0)).getStato());
    }

    /**
     * Test of inviaDecisione method, of class CommunicationController.
     * Testa solo i casi di null, per ulteriori test vedere Proposta
     */
    @Test
    public void testInviaDecisione() {
        System.out.println("inviaDecisione");
        Proposta prop = Proposta.caricaAttiva("1", "2");
        String dec = "ciao";
        boolean result = CommunicationController.inviaDecisione(prop, dec);
        assertEquals(false, result);
        result = CommunicationController.inviaDecisione(prop, null);
        assertEquals(false, result);
        result = CommunicationController.inviaDecisione(null, dec);
        assertEquals(false, result);
    }

    /**
     * Test of apriMessaggioRicevuto method, of class CommunicationController.
     * Testa solo il caso null. Vedere Messaggio.load() e Messaggio.salva() per ulteriori test
     */
    @Test
    public void testApriMessaggioRicevuto() {
        System.out.println("apriMessaggioRicevuto");
        String id = "0";
        Messaggio expResult = null;
        Messaggio result = CommunicationController.apriMessaggioRicevuto(id);
        assertEquals(expResult, result);
    }
}