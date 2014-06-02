/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simone
 */
public class SistemaDiCifratura {
    
    private String id;
    private String chiave;
    private String metodo;
    
    private String idCreatore;
    
    private CalcolatoreMappe calcMap;
    private Mappatura map;
    private UserInfo creatore;
    
    public SistemaDiCifratura(String key, String metodo){
        chiave = key;
        this.metodo = metodo;
        //da implementare
    }
    
    public SistemaDiCifratura(ResultSet info){
        try {
            info.next();
            id = "" + info.getInt("ID");
            chiave = info.getString("CHIAVE");
            metodo = info.getString("METODO");
            idCreatore = "" + info.getInt("ID_CREATORE");
        } catch (SQLException ex) {
            Logger.getLogger(Proposta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<SistemaDiCifratura> caricaSistemiCifratura(Studente stud){
        //da implementare
        
        return null;
    }
    
    public static SistemaDiCifratura load(String id){
        if(Integer.parseInt(id)<1) return null;
        String sql = "SELECT * FROM SDC WHERE ID=" + Integer.parseInt(id);
        ResultSet rs = DBManager.getDBManager().execute(sql);
        return new SistemaDiCifratura(rs);
    }
    
    public void calcolaMappatura(){
        //da implementare
    }
    
    public String prova(String testo){
        //nel caso map sia nullo termino immediatamente e rirono null
        if(map==null) return null;
        
        String testoCifrato = Cifratore.cifra(map, testo);
        return testoCifrato;
    }
    
    public boolean salva(){
        //da implementare
        
        return false;
    }
    
    public boolean elimina(){
        //da implementare
        
        return false;
    }
    
    public Mappatura getMappatura(){
        return map;
    }
    
}
