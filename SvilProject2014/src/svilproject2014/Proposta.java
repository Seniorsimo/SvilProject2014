/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simone
 */
public class Proposta {
    
    private String id;
    private String stato;
    private boolean notificata;
    
    private String idSdc;
    private String idProp;
    private String idPart;
    
    private SistemaDiCifratura sisCif;
    private UserInfo proponente;
    private UserInfo partner;
    
    public Proposta(ResultSet info){
        try {
            if(info.next()){
                id = "" + info.getInt("ID");
                stato = info.getString("STATO");
                int temp = info.getInt("NOTIFICATA");
                if(temp==0) notificata = false;
                else notificata = true;
                idSdc = "" + info.getInt("ID_SDC");
                idProp = "" + info.getInt("ID_PROPONENTE");
                idPart = "" + info.getInt("ID_PARTNER");
                sisCif = SistemaDiCifratura.load(idSdc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Proposta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Proposta(UserInfo prop, UserInfo part, SistemaDiCifratura sdc){
        proponente = prop;
        partner = part;
        sisCif = sdc;
        //da implementare
    }
    
    public static Proposta caricaAttiva(String idProp, String idPart){
        if(Integer.parseInt(idProp)<1||Integer.parseInt(idPart)<1){
            Logger.getLogger(Proposta.class.getName()).log(Level.WARNING, "Impossibile caricare una Proposta: Uno degli id non è corretto");
            return null;
        }
        String sql = "SELECT + FROM PROPOSTE WHERE ID_PROPONENTE='" + idProp + "' AND ID_PARTNER='" + idPart + "'";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        Proposta p = new Proposta(rs);
        if(p.getId()!=null) return p;
        return null;
    }
    
    public boolean salva(){
        String sql;
        //ho già un id quindi la Proposta esiste già nel DB e deve essere aggiornato.
        if(id!=null){
            sql = "UPDATE PROPOSTE SET "
                    + "STATO='" + stato + "',";
            if(notificata) sql += "NOTIFICATA=1,";
            else sql += "NOTIFICATA=0,";
            
            if(idProp!=null) sql += ",ID_PROPONENTE=" + Integer.parseInt(idProp);
            if(idPart!=null) sql += ",ID_PARTNER=" + Integer.parseInt(idPart);
            if(idSdc!=null) sql += ",ID_SDC=" + Integer.parseInt(idSdc);
            
            sql += " WHERE ID=" + Integer.parseInt(id);
        }
        //non ho ancora un id, quindi devo inserire la Proposta in DB
        else{
            sql = "INSERT INTO PROPOSTE (STATO, NOTIFICATA, ID_PROPONENTE, ID_PARTNER, ID_SDC) VALUES ("
                    + "'" + stato + "',";
            if(notificata) sql += "1,";
            else sql += "0,";
            if(idProp!=null) sql += idProp + ",";
            else sql += "0,";
            if(idPart!=null) sql += idPart + ",";
            else sql += "0,";
            if(idSdc!=null) sql += idSdc + "";
            else sql += "0";
            sql += ")";
        }
        int i = DBManager.getDBManager().save(sql);
        return i>0 ? true : false;
    }
    
    //get
    public SistemaDiCifratura getSdc(){
        return sisCif;
    }
    
    public String getIdProponente(){
        return idProp;
    }
    
    public String getIdPartner(){
        return idPart;
    }
    
    public String getId(){
        return id;
    }
    
    public String getStato(){
        return stato;
    }
    
    public void setStato(String stato){
        this.stato = stato;
    }
    
}
