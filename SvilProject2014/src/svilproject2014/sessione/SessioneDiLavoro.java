/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione;

import svilproject2014.Messaggio;

/**
 *
 * @author User
 */
public class SessioneDiLavoro {
    
    private Messaggio messaggio;
    private GestoreIpotesi gestoreIpotesi;
    private StrumentiDiSupporto strumentiSupporto;
    
    private int id;
    private int id_messaggio;
    private int id_gestore;
    private int id_lingua;
    
    public SessioneDiLavoro(){
        //da implementare
    }

    public GestoreIpotesi getGestoreIpotesi() {
        return gestoreIpotesi;
    }

    public StrumentiDiSupporto getStrumentiSupporto() {
        return strumentiSupporto;
    }
    
    public static SessioneDiLavoro load(int id){
        //da implementare
        return null;
               
    }
    
    public boolean salva(){
        //da implementare
        return false;
        
    }
    
    public void abbandona(){
        //da implementare
        
    }
    
}
