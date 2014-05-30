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
public class Messaggio {
    
    private String id;
    private String testo;
    private String testoCifrato;
    private String lingua;
    private String titolo;
    private boolean bozza;
    private boolean letto;
    
    private SistemaDiCifratura sisCif;
    private UserInfo mittente;
    private UserInfo destinatario;
    
    public Messaggio(/*queryResult info*/){
        //da implementare
    }
    
    public static Messaggio load(String id){
        //da implementare
        
        return null;
    }
    
    public static List<Messaggio> caricaBozze(Studente stud){
        //da implementare
        
        return null;
    }
    
    public static List<Messaggio> caricaInviati(Studente stud){
        //da implementare
        
        return null;
    }
    
    public static List<Messaggio> caricaRicevuti(Studente stud){
        //da implementare
        
        return null;
    }
    
    public boolean elimina(){
        //da implementare
        
        return false;
    }
    
    public boolean salva(){
        //da implemenatare
        
        return false;
    }
    
    public void cifra(){
        //da implementare
    }
    
    public void send(){
        //da implementare
    }
    
}
