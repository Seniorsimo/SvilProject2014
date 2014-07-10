/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import svilproject2014.messaggio.*;

/**
 *
 * @author Simone
 */
public abstract class Messaggio {
    
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
        Messaggio m = Messaggio.creaMessaggio(rs);
        return m;
    }
    
    private static Messaggio creaMessaggio(ResultSet rs){
        try {
            if(rs.next()) return new MessaggioProxy(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<Messaggio> caricaBozze(Studente stud){
        String idStud = stud.getId();
        
        String sql = "SELECT * FROM MESSAGGI WHERE ID_MITTENTE='" + idStud + "' AND BOZZA=1";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<Messaggio> list = new ArrayList<>();
        while(createNextMessage(rs, list));
        return list;
    }
    
    public static List<Messaggio> caricaInviati(Studente stud){
        String idStud = stud.getId();
        
        String sql = "SELECT * FROM MESSAGGI WHERE ID_MITTENTE='" + idStud + "' AND BOZZA=0";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<Messaggio> list = new ArrayList<>();
        while(createNextMessage(rs, list));
        return list;
    }
    
    public static List<Messaggio> caricaRicevuti(Studente stud){
        String idStud = stud.getId();
        
        String sql = "SELECT * FROM MESSAGGI WHERE ID_DESTINATARIO='" + idStud + "'";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<Messaggio> list = new ArrayList<>();
        while(createNextMessage(rs, list));
        return list;
    }
    
    public static List<Messaggio> caricaSpiabili(Studente stud){
        String idStud = stud.getId();
        
        String sql = "SELECT * FROM MESSAGGI WHERE ID_DESTINATARIO<>'" + idStud + "' AND ID_MITTENTE<>'" + idStud + "'";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<Messaggio> list = new ArrayList<>();
        while(createNextMessage(rs, list));
        return list;
    }
    
    private static boolean createNextMessage(ResultSet rs, List<Messaggio> list){
        Messaggio m = Messaggio.creaMessaggio(rs);
        if(m!=null){
            list.add(m);
            return true;
        }
        return false;
    }
    
    public boolean elimina(){
        String sql = "DELETE FROM MESSAGGI WHERE ID=" + Integer.parseInt(getId());
        int i = DBManager.getDBManager().save(sql);
        return i>0 ? true : false;
    }
    
    public abstract boolean salva();
    
    public abstract String cifra();
    
    public abstract String decifra();
    
    public abstract boolean send();
    
    public abstract void setLetto(boolean l);
    
    public abstract String getId();

    public abstract String getTesto();

    public abstract String getTestoCifrato();

    public abstract String getTitolo();

    public abstract void setTesto(String testo);

    public abstract void setTitolo(String titolo);

    public abstract void setSisCif(SistemaDiCifratura sisCif);
    
    public abstract String getLingua();

    public abstract boolean isBozza();

    public abstract UserInfo getMittente();

    public abstract UserInfo getDestinatario();
}
