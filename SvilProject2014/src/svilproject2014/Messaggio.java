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
public class Messaggio {
    
    private String id;
    private String testo;
    private String testoCifrato;
    private String lingua;
    private String titolo;
    private boolean bozza;
    private boolean letto;
    
    private String idSdc;
    private String idDest;
    private String idMitt;
    
    private SistemaDiCifratura sisCif;
    private UserInfo mittente;
    private UserInfo destinatario;
    
    public Messaggio(ResultSet info){
        try {
            info.next();
            id = "" + info.getInt("ID");
            testo = info.getString("TESTO");
            testoCifrato = info.getString("TESTO_CIFRATO");
            lingua = info.getString("LINGUA");
            titolo = info.getString("TITOLO");
            int temp = info.getInt("BOZZA");
            if(temp==0) bozza = false;
            else bozza = true;
            temp = info.getInt("LETTO");
            if(temp==0) letto = false;
            else letto = true;
            idSdc = info.getString("ID_SDC");
            idDest = info.getString("ID_DESTINATARIO");
            idMitt = info.getString("ID_MITTENTE");
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Messaggio load(String id){
        String sql = "SELECT * FROM MESSAGGI WHERE ID=" + id;
        DBManager db = DBManager.getDBManager();
        ResultSet rs = db.execute(sql);
        if(rs==null){
            Logger.getLogger(Messaggio.class.getName()).log(Level.WARNING,"Errore: Impossibile caricare il messaggio con id: " + id);
            return null;
        }
        Messaggio m = new Messaggio(rs);
        return m;
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
    
    public void setLetto(boolean l){
        letto = l;
    }
    
}
