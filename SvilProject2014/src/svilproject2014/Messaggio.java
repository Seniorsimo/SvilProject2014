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
        Messaggio m = Messaggio.creaMessaggio(rs);
        return m;
    }
    
    private static Messaggio creaMessaggio(ResultSet rs){
        try {
            if(rs.next()) return new Messaggio(rs);
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
        String sql = "DELETE FROM MESSAGGI WHERE ID=" + Integer.parseInt(id);
        int i = DBManager.getDBManager().save(sql);
        return i>0 ? true : false;
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
        if(id==null){
            sql = "SELECT ID FROM MESSAGGI WHERE "
                    + "TESTO='" + testo + "' AND "
                    + "TESTO_CIFRATO='" + testoCifrato + "' AND "
                    + "LINGUA='" + lingua + "' AND "
                    + "TITOLO='" + titolo + "' AND ";
            if(bozza) sql += "BOZZA=1 AND ";
            else sql += "BOZZA=0 AND ";
            if(letto) sql += "LETTO=1";
            else sql += "LETTO=0";
            
            if(idMitt!=null) sql += " AND ID_MITTENTE=" + Integer.parseInt(idMitt);
            if(idDest!=null) sql += " AND ID_DESTINATARIO=" + Integer.parseInt(idDest);
            if(idSdc!=null) sql += " AND ID_SDC=" + Integer.parseInt(idSdc);
            
            ResultSet rs = DBManager.getDBManager().execute(sql);
            try {
                if(rs.next()){ 
                    id = "" + rs.getInt("ID"); 
                }
            } catch (SQLException ex) {
                Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return i>0 ? true : false;
    }
    
    public String cifra(){
        //verifico ed eventualmente carico il sistema di cifratura
        if(sisCif==null){
            //nel caso ne abbia il riferimento ma non sia carico lo creo.
            if(idSdc!=null){
                sisCif = SistemaDiCifratura.load(idSdc);
            }
            //altrimenti lo genero cercando la prima proposta nel DB
            else{
                Proposta p = Proposta.caricaAttiva(idMitt, idDest);
                sisCif = p.getSdc();
            }
        }
        
        //nel caso di errori (sisCif == null termino
        if(sisCif==null){
            Logger.getLogger(Messaggio.class.getName()).log(Level.WARNING, "Impossibile cifrare il messaggio: nessun sistema di cifratura trovato.");
            return null;
        }
        
        //cifro
        Mappatura map = sisCif.getMappatura();
        testoCifrato = Cifratore.cifra(map, testo);
        return testoCifrato;
    }
    
    public String decifra(){
        //se non c'è un testo cifrato termino
        if(testoCifrato==null||testoCifrato.equals("")){
            Logger.getLogger(Messaggio.class.getName()).log(Level.WARNING, "Impossibile decifrare il messaggio: il messaggio non contiene testo cifrato.");
            return null;
        }
        
        //carico il sdc (non carico nulla di defaul come in cifra, ma carico solo se sisCif è null ma idSdc esiste.
        if(sisCif==null){
            if(idSdc==null){
                Logger.getLogger(Messaggio.class.getName()).log(Level.WARNING, "Impossibile decifrare il messaggio: Sistema di cifratura non presente");
                return null;
            }
            sisCif = SistemaDiCifratura.load(idSdc);
        }
        
        Mappatura map = sisCif.getMappatura();
        testo = Cifratore.decifra(map, testoCifrato);
        return testo;
    }
    
    public boolean send(){
        bozza = false;
        return salva();
    }
    
    public void setLetto(boolean l){
        letto = l;
    }
    
    public String getId(){
        return id;
    }

    public String getTesto() {
        return testo;
    }

    public String getTestoCifrato() {
        return testoCifrato;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setSisCif(SistemaDiCifratura sisCif) {
        this.sisCif = sisCif;
    }
    
    
}
