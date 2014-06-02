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
    private boolean bozza = true;
    private boolean letto = false;
    
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
            idSdc = "" + info.getInt("ID_SDC");
            idDest = "" + info.getInt("ID_DESTINATARIO");
            idMitt = "" + info.getInt("ID_MITTENTE");
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //mancava il costruttore per i nuovi messaggi?
    public Messaggio(){
        //non deve fare nulla...credo.
    }
    
    public static Messaggio load(String id){
        //id deve contenere un numero e questo deve essere strettamente positivo, altrimenti errore.      
        if(Integer.parseInt(id)<1) return null;
        
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
        String sql;
        //ho già un id quindi il messaggio esiste già nel DB e deve essere aggiornato.
        if(id!=null){
            sql = "UPDATE MESSAGGI SET "
                    + "TESTO='" + testo + "',"
                    + "TESTO_CIFRATO='" + testoCifrato + "',"
                    + "LINGUA='" + lingua + "',"
                    + "TITOLO='" + titolo + "',";
            if(bozza) sql += "BOZZA=1,";
            else sql += "BOZZA=0,";
            if(letto) sql += "LETTO=1";
            else sql += "LETTO=0";
            
            if(idMitt!=null) sql += ",ID_MITTENTE=" + Integer.parseInt(idMitt);
            if(idDest!=null) sql += ",ID_DESTINATARIO=" + Integer.parseInt(idDest);
            if(idSdc!=null) sql += ",ID_SDC=" + Integer.parseInt(idSdc);
            
            sql += " WHERE ID=" + Integer.parseInt(id);
        }
        //non ho ancora un id, quindi devo inserire il messaggio in DB
        else{
            sql = "INSERT INTO MESSAGGI (TESTO, TESTO_CIFRATO, LINGUA, TITOLO, BOZZA, LETTO, ID_MITTENTE, ID_DESTINATARIO, ID_SDC) VALUES ("
                    + "'" + testo + "',"
                    + "'" + testoCifrato + "',"
                    + "'" + lingua + "',"
                    + "'" + titolo + "',";
            if(bozza) sql += "1,";
            else sql += "0,";
            if(letto) sql += "1,";
            else sql += "0,";
            if(idMitt!=null) sql += idMitt + ",";
            else sql += "0,";
            if(idDest!=null) sql += idDest + ",";
            else sql += "0,";
            if(idSdc!=null) sql += idSdc + "";
            else sql += "0";
            sql += ")";
        }
        int i = DBManager.getDBManager().save(sql);
        return i>0 ? true : false;
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
