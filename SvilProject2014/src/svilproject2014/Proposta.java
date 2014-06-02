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
            info.next();
            id = "" + info.getInt("ID");
            stato = info.getString("STATO");
            int temp = info.getInt("NOTIFICATA");
            if(temp==0) notificata = false;
            else notificata = true;
            idSdc = "" + info.getInt("ID_SDC");
            idProp = "" + info.getInt("ID_PROPONENTE");
            idPart = "" + info.getInt("ID_PARTNER");
            sisCif = SistemaDiCifratura.load(idSdc);
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
        return new Proposta(rs);
    }
    
    public void salva(){
        //da implementare
    }
    
    //aggiunto x cifra
    public SistemaDiCifratura getSdc(){
        return sisCif;
    }
    
}
