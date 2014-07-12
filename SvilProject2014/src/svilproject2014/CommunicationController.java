/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import svilproject2014.sessione.GestoreIpotesi;

/**
 *
 * @author Simone
 */
public class CommunicationController {

    public static boolean send(Messaggio msg) {
        if (msg == null) {
            return false;
        }
        return msg.send();
    }
    
    /**
     * 
     * @param usr
     * @return restituisce la lista dei destinatari. La lista è vuota se non ne esistono.
     */
    public static List<UserInfo> getDestinatari(Studente usr) {
        String idStud;
        ArrayList<UserInfo> listaDest = new ArrayList<>();
        try {
            idStud = usr.getId();

            String sql = "SELECT ID,NOME,COGNOME FROM STUDENTI WHERE ID IN "
                    + "(SELECT ID_PARTNER FROM PROPOSTE "
                    + "WHERE ID_PROPONENTE=" + Integer.parseInt(idStud) + " AND STATO='accepted')";
            ResultSet rs = DBManager.getDBManager().execute(sql);
            

            while (createNextUserInfo(rs, listaDest));

            return listaDest;

        } catch (NullPointerException e) {
            Logger.getLogger(CommunicationController.class.getName()).log(Level.INFO, "Non esistono studenti a cui destinare il messaggio.");
        }

        return listaDest; //la lista dei destinatari è vuota
    }

    private static boolean createNextUserInfo(ResultSet rs, List<UserInfo> list) {
        UserInfo u = UserInfo.creaUserInfo(rs);
        if (u != null) {
            if (Integer.parseInt(u.getId()) > 0) {
                list.add(u);
                return true;
            }
        }
        return false;
    }

    public static boolean inviaProposta(Studente usr, UserInfo partner, SistemaDiCifratura sdc) {
        if (usr == null || partner == null || sdc == null) {
            return false;
        }
        Proposta prop = new Proposta(usr.getUserInfo(), partner, sdc);
        return prop.salva();
    }

    public static List<Proposta> getAccettazioneProposte(Studente usr) {
        String sql = "SELECT * FROM PROPOSTE WHERE ID_PARTNER=" + usr.getId() + " AND NOTIFICATA=0 AND (STATO='accepted' OR STATO='refused')";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<Proposta> listaProp = new ArrayList<>();

        while (createNextProposta(rs, listaProp));

        return listaProp;
    }

    private static boolean createNextProposta(ResultSet rs, List<Proposta> list) {
        Proposta p = Proposta.creaProposta(rs);
        if (p != null) {
            if (Integer.parseInt(p.getId()) > 0) {
                list.add(p);
                return true;
            }
        }
        return false;
    }

    public static List<Proposta> getProposte(Studente usr) {
        String sql = "SELECT * FROM PROPOSTE WHERE ID_PARTNER=" + usr.getId() + " AND STATO='pending'";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<Proposta> listaProp = new ArrayList<>();

        while (createNextProposta(rs, listaProp));

        return listaProp;
    }

    public static boolean inviaDecisione(Proposta prop, String dec) {
        if (prop == null || dec == null) {
            return false;
        }
        if (!dec.equals("accepted") && !dec.equals("refused")) {
            return false;
        }
        if (dec.equals("accepted")) {
            Proposta old = Proposta.caricaAttiva(prop.getIdProponente(), prop.getIdPartner());

            if (old != null) {
                old.setStato("expired");
                old.salva();
            }
        }

        prop.setStato(dec);
        return prop.salva();
    }

    public static Messaggio apriMessaggioRicevuto(String id) {
        Messaggio m = Messaggio.load(id);
        if (m == null) {
            return null;
        }
        m.setLetto(true);
        m.salva();
        return m;
    }

}
