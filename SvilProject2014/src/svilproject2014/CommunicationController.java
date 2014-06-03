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
            "WHERE ID_PROPONENTE=" + Integer.parseInt(idStud) + " AND STATO='accettata')";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<UserInfo> listaDest = new ArrayList<>();
        
        while(createNextUserInfo(rs, listaDest));
        
        return listaDest;
    }
    
    private static boolean createNextUserInfo(ResultSet rs, List<UserInfo> list){
        UserInfo u = new UserInfo(rs);
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
        
        
        return null;
    }
    
    public static List<Proposta> getProposte(Studente usr){
        //da implementare
        
        return null;
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
