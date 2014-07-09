/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simone
 */
public class CommunicationController {
    
    public static boolean send(Messaggio msg){
        return msg.send();
    }
    
    public static List<UserInfo> getDestinatari(Studente usr){
        String idStud = usr.getId();
        
        String sql = "SELECT ID,NOME,COGNOME FROM STUDENTI WHERE ID IN " +
            "(SELECT ID_PARTNER FROM PROPOSTE " +
            "WHERE ID_PROPONENTE=" + Integer.parseInt(idStud) + " AND STATO='accepted')";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<UserInfo> listaDest = new ArrayList<>();
        
        while(createNextUserInfo(rs, listaDest));
        
        return listaDest;
    }
    
    private static boolean createNextUserInfo(ResultSet rs, List<UserInfo> list){
        UserInfo u = UserInfo.creaUserInfo(rs);
        String idU = u.getId();
        if(idU!=null){
            if(Integer.parseInt(idU)>0){
                list.add(u);
                return true;
            }
        }
        return false;
    }
    
    public static boolean inviaProposta(Studente usr, UserInfo partner, SistemaDiCifratura sdc){
        Proposta prop = new Proposta(usr.getUserInfo(), partner, sdc);
        return prop.salva();
    }
    
    public static List<Proposta> getAccettazioneProposte(Studente usr){
        String sql = "SELECT * FROM PROPOSTE WHERE ID_PARTNER=" + usr.getId() + " AND NOTIFICATA=0 AND (STATO='accepted' OR STATO='refused')";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<Proposta> listaProp = new ArrayList<>();
        
        while(createNextProposta(rs, listaProp));
        
        return listaProp;
    }
    
    private static boolean createNextProposta(ResultSet rs, List<Proposta> list){
        Proposta p = Proposta.creaProposta(rs);
        String idP = p.getId();
        if(idP!=null){
            if(Integer.parseInt(idP)>0){
                list.add(p);
                return true;
            }
        }
        return false;
    }
    
    public static List<Proposta> getProposte(Studente usr){
        String sql = "SELECT * FROM PROPOSTE WHERE ID_PARTNER=" + usr.getId() + " AND STATO='pending'";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<Proposta> listaProp = new ArrayList<>();
        
        while(createNextProposta(rs, listaProp));
        
        return listaProp;
    }
    
    public static boolean inviaDecisione(Proposta prop, String dec){
        
        if(dec.equals("accettata")){
            Proposta old = Proposta.caricaAttiva(prop.getIdProponente(), prop.getIdPartner());
            
            if(old!=null){
                old.setStato("expired");
                old.salva(); 
            }
        }
        
        prop.setStato(dec);
        return prop.salva();
    }
    
    public static Messaggio apriMessaggioRicevuto(String id){
        Messaggio m = Messaggio.load(id);
        m.setLetto(true);
        m.salva();
        return m;
    }
    
}
