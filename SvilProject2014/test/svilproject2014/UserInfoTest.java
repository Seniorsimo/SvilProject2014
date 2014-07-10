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
public class UserInfoTest {
    
    public UserInfoTest() {
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
     * Test of load method, of class UserInfo.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        
        String id = "1";
        UserInfo result = UserInfo.load(id);
        assertEquals("1", result.getId());
        assertEquals("Marco", result.getNome());
        assertEquals("Rossi", result.getCognome());
        
        id = "0";
        result = UserInfo.load(id);
        assertNull(result);
        
    }

}