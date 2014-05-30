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
public class SistemaDiCifratura {
    
    private String id;
    private String chiave;
    private String metodo;
    
    private CalcolatoreMappe calcMap;
    private Mappatura map;
    private UserInfo creatore;
    
    public SistemaDiCifratura(String key, String metodo){
        chiave = key;
        this.metodo = metodo;
        //da implementare
    }
    
    public SistemaDiCifratura(/*queryResult info*/){
        //da implementare
    }
    
    public static List<SistemaDiCifratura> caricaSistemiCifratura(Studente stud){
        //da implementare
        
        return null;
    }
    
    public static SistemaDiCifratura load(String id){
        //da implementare
        
        return null;
    }
    
    public void calcolaMappatura(){
        //da implementare
    }
    
    public String prova(String testo){
        //da implementare
        
        return null;
    }
    
    public boolean salva(){
        //da implementare
        
        return false;
    }
    
    public boolean elimina(){
        //da implementare
        
        return false;
    }
    
}
