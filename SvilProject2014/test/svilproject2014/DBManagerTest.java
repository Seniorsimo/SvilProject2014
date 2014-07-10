/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.sql.ResultSet;
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
public class DBManagerTest {
    
    public DBManagerTest() {
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
     * Test of getDBManager method, of class DBManager.
     * Testa il singleton. non si fanno test su query.
     */
    @Test
    public void testGetDBManager() {
        System.out.println("getDBManager");
        DBManager expResult = DBManager.getDBManager();
        DBManager result = DBManager.getDBManager();
        assertEquals(expResult, result);
    }

    
}