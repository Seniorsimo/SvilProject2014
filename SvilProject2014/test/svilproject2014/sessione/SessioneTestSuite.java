/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author User
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({svilproject2014.sessione.GestoreIpotesiTest.class, svilproject2014.sessione.SessioneDiLavoroTest.class, svilproject2014.sessione.CoppiaTest.class, svilproject2014.sessione.StrumentiDiSupportoTest.class,
            svilproject2014.sessione.strumenti.StrumentiTestSuite.class})
public class SessioneTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}