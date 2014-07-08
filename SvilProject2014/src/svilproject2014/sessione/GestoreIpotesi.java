/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione;

import java.sql.ResultSet;
import java.util.List;
import svilproject2014.Messaggio;

/**
 *
 * @author User
 */
public class GestoreIpotesi {
    
    private Messaggio messaggio;
    private List<Coppia> listaAssociazioni;
    
    private int id;
    private int avantiPossibili = 0;
    private int indietroPossibili = 0;
    private int indiceStatoCorrente = 0;
    private char[] testoOriginale;
    private char[] testoParziale;
    
    public GestoreIpotesi(ResultSet info){
        //da implementare
        
    }
    
    public static GestoreIpotesi load(int id){
        //da implementare
        return null;
        
    }
    
    public List<Coppia> visualizzaAssociazioni(){
        //da implementare
        return null;
        
    }
    
    public boolean aggiungiIpotesi(char vecchiaLettera, char nuovaLettera){
        //da implementare
        return false;
        
    }
    
    public Coppia visualizzaStoria(){
        //da implementare
        return null;
    }
    
    public boolean avanti(int n){
        //da implementare
        return false;
        
    }
    
    public boolean indietro(int n){
        //da implementare
        return false;
        
    }
    
    public boolean verificaRisoluzione(){
        //da implementare
        return false;
        
    }
    
    public boolean salva(){
        //da implementare
        return false;
        
    }
    
    private String applicaIpotesi(Coppia c){
        //da implementare
        return "";
        
    }
    
    private String rollback(Coppia c){
        //da implementare
        return "";
        
    }
    
}
