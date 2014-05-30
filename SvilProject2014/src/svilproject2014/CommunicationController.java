/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.util.List;

/**
 *
 * @author Simone
 */
public class CommunicationController {
    
    public static void send(Messaggio msg){
        //da implementare
    }
    
    public static List<UserInfo> getDestinatari(Studente usr){
        //da implementare
        
        return null;
    }
    
    public static void inviaProposta(Studente usr, UserInfo partner, SistemaDiCifratura sdc){
        //da implementare
    }
    
    public static List<Proposta> getAccettazioneProposte(Studente usr){
        //da implementare
        
        return null;
    }
    
    public static List<Proposta> getProposte(Studente usr){
        //da implementare
        
        return null;
    }
    
    public static void inviaDecisione(Proposta prop, String dec){
        //da implementare
    }
    
    public static Messaggio apriMessaggioRicevuto(String id){
        //da implementare
        
        return null;
    }
    
}
