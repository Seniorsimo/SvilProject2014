/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import svilproject2014.DBManager;
import svilproject2014.Messaggio;
import svilproject2014.Studente;
import svilproject2014.UserInfo;

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
    private int id_utente;
    private String stato = "nuovo"; //stati possibili: nuovo (alla creazione), aperto (dopo aver selezionato un messaggio, indica il lavoro), terminato (indica completamento), abbandonato(rinuncia)
    
    public SessioneDiLavoro(Studente user){
        id_utente = Integer.parseInt(user.getId());
        id_lingua = 1;
    }
    
    public SessioneDiLavoro(ResultSet info){
        try {
                id = info.getInt("ID");
                id_messaggio = info.getInt("ID_MESSAGGIO");
                id_gestore = info.getInt("ID_GESTORE");
                id_lingua = info.getInt("ID_LINGUA");
                id_utente = info.getInt("ID_UTENTE");
                stato = info.getString("STATO");
                
//                gestoreIpotesi = GestoreIpotesi.load(id_gestore);
//                messaggio = gestoreIpotesi.getMessaggio();
                //strumentiSupporto = new StrumentiDiSupporto(id_lingua);
//                
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<SessioneDiLavoro> caricaSalvate(UserInfo stud){
        String idStud = stud.getId();
        
        String sql = "SELECT * FROM SESSIONEDILAVORO WHERE ID_UTENTE=" + idStud + "";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<SessioneDiLavoro> list = new ArrayList<>();
        while(createNextSessioneDiLavoro(rs, list));
        return list;
    }
    
    private static boolean createNextSessioneDiLavoro(ResultSet rs, List<SessioneDiLavoro> list){
        SessioneDiLavoro m = SessioneDiLavoro.creaSessioneDiLavoro(rs);
        if(m!=null){
            list.add(m);
            return true;
        }
        return false;
    }

    public GestoreIpotesi getGestoreIpotesi() {
        if(gestoreIpotesi==null){
            gestoreIpotesi = GestoreIpotesi.load(id_gestore);
            messaggio = gestoreIpotesi.getMessaggio();
        }
        return gestoreIpotesi;
    }

    public StrumentiDiSupporto getStrumentiSupporto() {
        if(strumentiSupporto==null) strumentiSupporto = new StrumentiDiSupporto(id_lingua);
        return strumentiSupporto;
    }
    
    public static SessioneDiLavoro load(int id){
        if(id<1) return null;
        
        String sql = "SELECT * FROM SESSIONEDILAVORO WHERE ID=" + id;
        DBManager db = DBManager.getDBManager();
        ResultSet rs = db.execute(sql);
        if(rs==null){
            Logger.getLogger(SessioneDiLavoro.class.getName()).log(Level.WARNING,"Errore: Impossibile caricare il gestoreipotesi con id: " + id);
            return null;
        }
        return SessioneDiLavoro.creaSessioneDiLavoro(rs);
               
    }
    
    private static SessioneDiLavoro creaSessioneDiLavoro(ResultSet rs){
        try {
            if(rs.next()) return new SessioneDiLavoro(rs);
        } catch (SQLException ex) {
            Logger.getLogger(SessioneDiLavoro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean salva(){
        if(!gestoreIpotesi.salva()) return false;
        id_gestore = gestoreIpotesi.getId();
        
        if(strumentiSupporto!=null) id_lingua = strumentiSupporto.getLingua();
        
        //salvo su db
        String sql;
        if(id!=0){
            sql = "UPDATE SESSIONEDILAVORO SET "
                    + "ID_MESSAGGIO=" + id_messaggio + ","
                    + "ID_GESTORE=" + id_gestore + ","
                    + "ID_LINGUA=" + id_lingua + ","
                    + "ID_UTENTE=" + id_utente + ","
                    + "STATO='" + stato + "'";
            sql += " WHERE ID=" + id;
        }
        else{
            sql = "INSERT INTO SESSIONEDILAVORO (ID_MESSAGGIO, ID_GESTORE, ID_LINGUA, ID_UTENTE, STATO) VALUES ("
                    + id_messaggio + ","
                    + id_gestore + ","
                    + id_lingua + ","
                    + id_utente + ","
                    + "'" + stato + "'";
            sql += ")";
        }
        int i = DBManager.getDBManager().save(sql);
        if(id==0){
            sql = "SELECT ID FROM SESSIONEDILAVORO WHERE "
                    + "ID_MESSAGGIO=" + id_messaggio + " AND "
                    + "ID_GESTORE=" + id_gestore + " AND "
                    + "ID_LINGUA=" + id_lingua + " AND "
                    + "ID_UTENTE=" + id_utente + " AND "
                    + "STATO='" + stato + "'";
            
            ResultSet rs = DBManager.getDBManager().execute(sql);
            try {
                if(rs.next()){ 
                    id = rs.getInt("ID"); 
                }
            } catch (SQLException ex) {
                Logger.getLogger(SessioneDiLavoro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return i>0 ? true : false;
        
    }
    
    public boolean abbandona(){
        stato = "abbandonato";
        return salva();
        
    }

    public Messaggio getMessaggio() {
        if(messaggio==null) messaggio = Messaggio.load(""+id_messaggio);
        return messaggio;
    }

    public int getId() {
        return id;
    }

    public int getId_lingua() {
        return id_lingua;
    }

    public int getId_utente() {
        return id_utente;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
    
    public void setMessaggio(Messaggio m){
        messaggio = m;
        id_messaggio = Integer.parseInt(messaggio.getId());
        gestoreIpotesi = new GestoreIpotesi(messaggio);
        strumentiSupporto = new StrumentiDiSupporto(id_lingua);
    }
    
    
}
