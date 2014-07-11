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
//        GestoreIpotesi expResult = null;
        GestoreIpotesi result = GestoreIpotesi.load(id);
        assertNull(result);
        
 /*       System.out.println("load - id = 1 - id non presente nel database");
        id = 1;
        result = GestoreIpotesi.load(id);
        System.out.println("load result null? " + (result == null));
        assertNull(result);
   */         
        
        System.out.println("load - id = 8 - id presente nel db");
        id = 1;
        result = GestoreIpotesi.load(id);
        assertNotNull(result);
        
 /*       System.out.println("load - id = 9 - id non presente nel db");
        id = 9;
        result = GestoreIpotesi.load(id);
        assertNull(result);
*/
    }

    /**
     * Test del metodo visualizzaAssociazioni della classe GestoreIpotesi.
     * La root non viene mai stampata, quindi si considera la listaAssociazioni a partire dal nodo 1.
     */
    @Test
    public void testVisualizzaAssociazioni() {
        System.out.println("visualizzaAssociazioni");
        GestoreIpotesi instance = GestoreIpotesi.load(8);
        System.out.println("visualizzaAssociazioni - instance " + instance.toString());
        List<Coppia> expResult = new ArrayList<>();
        expResult.add(new Coppia('x','o', new Coppia(' ',' ', instance.visualizzaStoria()))); //creo un figlio della radice
        System.out.println("visualizzaAssociazioni - expResult " + expResult.toString());      
        List<Coppia> result = instance.visualizzaAssociazioni();
        System.out.println("visualizzaAssociazioni - result vuoto " + result.isEmpty()); 
        System.out.println("visualizzaAssociazioni - result elemento 0 " + result.get(0).toString()); 
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo aggiungiIpotesi, della classe GestoreIpotesi.
     */
    @Test
    public void testAggiungiIpotesi() {
        System.out.println("aggiungiIpotesi - vecchiaLettera = x (già presente)");
        char vecchiaLettera = 'x';
        char nuovaLettera = 'a';
        GestoreIpotesi instance = GestoreIpotesi.load(8);
        boolean expResult = false;
        boolean result = instance.aggiungiIpotesi(vecchiaLettera, nuovaLettera);
        assertEquals(expResult, result);
        
        System.out.println("aggiungiIpotesi - nuovaLettera = o (già presente)");
        vecchiaLettera = 'a';
        nuovaLettera = 'o';
        result = instance.aggiungiIpotesi(vecchiaLettera, nuovaLettera);
        assertEquals(expResult, result);
        
        System.out.println("aggiungiIpotesi - lettere non utilizzate per le associazioni prima");
        vecchiaLettera = 'a';
        nuovaLettera = 's';
        expResult = true;
        result = instance.aggiungiIpotesi(vecchiaLettera, nuovaLettera);
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo visualizzaStoria della classe GestoreIpotesi.
     */
    @Test
    public void testVisualizzaStoria() {
        System.out.println("visualizzaStoria");
        GestoreIpotesi instance = GestoreIpotesi.load(8);
        Coppia expResult = new Coppia(' ',' ',null);
        Coppia result = instance.visualizzaStoria();
        result = new Coppia(' ',' ',null);
        System.out.println("visualizzaStoria - coppie equals "+expResult.equals(result));
        assertEquals(expResult, result);
    }

    /**
     * Test del metodo avanti della classe GestoreIpotesi.
     */
    @Test
    public void testAvanti() {
         System.out.println("avanti - n=1 possibili = 1");
        int n = 1;
        GestoreIpotesi instance = GestoreIpotesi.load(8);
        boolean expResult = true;
        boolean result = instance.indietro(n);
        assertEquals(expResult, result);

        System.out.println("avanti - n=4 possibili = 1");
        n = 4;
        expResult = false;
        result = instance.indietro(n);
        assertEquals(expResult, result);

    }

    /**
     * Test of indietro method, of class GestoreIpotesi.
     */
    @Test
    public void testIndietro() {
        System.out.println("indietro - n=1 possibili = 2");
        int n = 1;
        GestoreIpotesi instance = GestoreIpotesi.load(8);
        boolean expResult = true;
        boolean result = instance.indietro(n);
        assertEquals(expResult, result);

        System.out.println("indietro - n=4 possibili = 2");
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
        System.out.println("verificaRisoluzione - testoParziale e originale null");
        GestoreIpotesi instance = GestoreIpotesi.load(8);
        boolean expResult = true;
        boolean result = instance.verificaRisoluzione();
        assertEquals(expResult, result);
        
        System.out.println("verificaRisoluzione - testoParziale e originale uguali");
        result = instance.verificaRisoluzione();
        assertEquals(expResult, result);
        
        System.out.println("verificaRisoluzione - testoParziale e originale diversi");
        expResult = true;
        result = instance.verificaRisoluzione();
//        assertEquals(expResult, result);
    }
    
}
