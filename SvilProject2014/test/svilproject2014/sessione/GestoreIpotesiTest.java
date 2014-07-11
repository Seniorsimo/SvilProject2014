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
public class GestoreIpotesiTest {
    
    public GestoreIpotesiTest() {
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
     * Test del metodo load della classe GestoreIpotesi.
     * Verifiche: id non valido restituisce null.
     *            id valido (coerente con il database) restituisce un oggetto non vuoto.
     */
    @Test
    public void testLoad() {
        System.out.println("load - id < 1");
        int id = 0;
        GestoreIpotesi result = GestoreIpotesi.load(id);
        assertNull(result);
        
        System.out.println("load - id = 10 - id non presente nel database");
        id = 10;
        result = GestoreIpotesi.load(id);
        //System.out.println("load result null? " + (result == null));
        assertNull(result);
        
        System.out.println("load - id = 1 - id presente nel db");
        id = 1;
        result = GestoreIpotesi.load(id);
        assertNotNull(result);
        assertEquals(1,result.getId());
        assertEquals(0,result.getAvantiPossibili());
        assertEquals(0,result.getIndietroPossibili());
        assertEquals("1",result.getMessaggio().getId());
    }

    /**
     * Test del metodo visualizzaAssociazioni della classe GestoreIpotesi.
     * La root non viene mai stampata, quindi si considera la listaAssociazioni a partire dal nodo 1.
     */
    @Test
    public void testVisualizzaAssociazioni() {
        System.out.println("visualizzaAssociazioni");
        GestoreIpotesi instance = GestoreIpotesi.load(2);
        //System.out.println("visualizzaAssociazioni - instance " + instance.toString());
        List<Coppia> expResult = new ArrayList<>();
        Coppia c1 = new Coppia('c','a', new Coppia(' ',' ',null)); //creo un figlio della radice
        expResult.add(c1);
        //System.out.println("visualizzaAssociazioni - expResult " + expResult.toString());      
        List<Coppia> result = instance.visualizzaAssociazioni();
        //System.out.println("visualizzaAssociazioni - result vuoto " + result.isEmpty()); 
        //System.out.println("visualizzaAssociazioni - result elemento 0 " + result.get(0).toString()); 
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo aggiungiIpotesi, della classe GestoreIpotesi.
     */
    @Test
    public void testAggiungiIpotesi() {
        System.out.println("aggiungiIpotesi - vecchiaLettera = c (già presente)");
        char vecchiaLettera = 'c';
        char nuovaLettera = 'r';
        GestoreIpotesi instance = GestoreIpotesi.load(2);
        boolean expResult = false;
        boolean result = instance.aggiungiIpotesi(vecchiaLettera, nuovaLettera);
        assertEquals(expResult, result);
        
        System.out.println("aggiungiIpotesi - nuovaLettera = a (già presente)");
        vecchiaLettera = 'h';
        nuovaLettera = 'a';
        result = instance.aggiungiIpotesi(vecchiaLettera, nuovaLettera);
        assertEquals(expResult, result);
        
        System.out.println("aggiungiIpotesi - lettere non utilizzate per le associazioni prima");
        vecchiaLettera = 'h';
        nuovaLettera = 'i';
        expResult = true;
        result = instance.aggiungiIpotesi(vecchiaLettera, nuovaLettera);
        assertEquals(expResult, result);
        List<Coppia> list = instance.visualizzaAssociazioni();
        assertEquals('h',list.get(list.size()-1).getVecchiaL());
        assertEquals('i',list.get(list.size()-1).getNuovaL());
    }

    /**
     * Test del metodo visualizzaStoria della classe GestoreIpotesi.
     */
    @Test
    public void testVisualizzaStoria() {
        System.out.println("visualizzaStoria");
        GestoreIpotesi instance = GestoreIpotesi.load(2);
        Coppia expResult = new Coppia(' ',' ',null);
        Coppia result = instance.visualizzaStoria();
        result = new Coppia(' ',' ',null);
        Coppia c1 = new Coppia('c','a',result);
        Coppia c2_1 = new Coppia('p','r',c1);
        Coppia c2_2 = new Coppia('p','o',c1);
        assertEquals(expResult, result); //testo la radice
        //e testo i sui nodi figli
        assertEquals(c1, result.getFigli().get(0));
        assertEquals(c2_1, result.getFigli().get(0).getFigli().get(0));
        assertEquals(c2_2, result.getFigli().get(0).getFigli().get(1));
    }

    /**
     * Test del metodo avanti della classe GestoreIpotesi.
     */
    @Test
    public void testAvanti() {
         System.out.println("avanti - n=1 possibili = 1");
        int n = 1;
        GestoreIpotesi instance = GestoreIpotesi.load(2);
        boolean expResult = true;
        boolean result = instance.avanti(n);
        assertEquals(expResult, result);

        System.out.println("avanti - n=4 possibili = 1");
        n = 4;
        expResult = false;
        result = instance.avanti(n);
        assertEquals(expResult, result);

    }

    /**
     * Test of indietro method, of class GestoreIpotesi.
     */
    @Test
    public void testIndietro() {
        System.out.println("indietro - n=1 possibili = 1");
        int n = 1;
        GestoreIpotesi instance = GestoreIpotesi.load(2);
        boolean expResult = true;
        boolean result = instance.indietro(n);
        assertEquals(expResult, result);

        System.out.println("indietro - n=4 possibili = 1");
        n = 4;
        expResult = false;
        result = instance.indietro(n);
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo verificaRisoluzione della classe GestoreIpotesi.
     */
    @Test
    public void testVerificaRisoluzione() {
        //System.out.println("verificaRisoluzione - testoParziale e originale null"); non posso fare un new Gestore() con i testi nullquesti vengono inizializzati,al peggio come char[] vuoti, ma cmq istanziati
        GestoreIpotesi instance = GestoreIpotesi.load(2);
        boolean expResult = true;
        /*boolean result = instance.verificaRisoluzione();
        assertEquals(expResult, result);
        */
        /*System.out.println("verificaRisoluzione - testoParziale e originale uguali");
        boolean result = instance.verificaRisoluzione();
        assertEquals(expResult, result);*/
        
        System.out.println("verificaRisoluzione - testoParziale e originale diversi");
        expResult = false;
        //instance = GestoreIpotesi.load(2);
        boolean result = instance.verificaRisoluzione();
        assertEquals(expResult, result);
        
        System.out.println("verificaRisoluzione - testoParziale e originale uguali");
        expResult = true;
        instance.aggiungiIpotesi('s','c');
        instance.aggiungiIpotesi('h','i');
        instance.aggiungiIpotesi('p','o');
        //instance = GestoreIpotesi.load(2);
        result = instance.verificaRisoluzione();
        assertEquals(expResult, result);
    }
    
}
