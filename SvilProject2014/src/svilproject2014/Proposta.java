/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

/**
 *
 * @author Simone
 */
public class Proposta {
    
    private String id;
    private String stato;
    private boolean notificata;
    
    private SistemaDiCifratura sisCif;
    private UserInfo proponente;
    private UserInfo partner;
    
    public Proposta(/*queryresult*/){
        //da implementare
    }
    
    public Proposta(UserInfo prop, UserInfo part, SistemaDiCifratura sdc){
        proponente = prop;
        partner = part;
        sisCif = sdc;
        //da implementare
    }
    
    public static Proposta caricaAttiva(String idProp, String idPart){
        //da implementare
        
        return null;
    }
    
    public void salva(){
        //da implementare
    }
    
}
