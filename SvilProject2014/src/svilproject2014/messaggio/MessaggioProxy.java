/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.messaggio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import svilproject2014.DBManager;
import svilproject2014.Messaggio;
import svilproject2014.SistemaDiCifratura;
import svilproject2014.UserInfo;

/**
 *
 * @author User
 */
public class MessaggioProxy extends Messaggio{
    
    Messaggio messaggio;
    
    private String id;
    private String lingua;
    private String titolo;
    private boolean bozza = true;
    private boolean letto = false;
    private String idDest;
    private String idMitt;
    
    public MessaggioProxy(ResultSet info){
        try {
                id = "" + info.getInt("ID");
                lingua = info.getString("LINGUA");
                titolo = info.getString("TITOLO");
                int temp = info.getInt("BOZZA");
                if(temp==0) bozza = false;
                else bozza = true;
                temp = info.getInt("LETTO");
                if(temp==0) letto = false;
                else letto = true;
                idDest = "" + info.getInt("ID_DESTINATARIO");
                idMitt = "" + info.getInt("ID_MITTENTE");
        } catch (SQLException ex) {
            Logger.getLogger(MessaggioProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MessaggioProxy(){
        //non deve fare nulla...credo.
    }
    
    @Override
    public boolean salva(){
        if(messaggio==null) caricaReale();
        return messaggio.salva();
    }

    @Override
    public String cifra() {
        if(messaggio==null) caricaReale();
        return messaggio.cifra();
    }

    @Override
    public String decifra() {
        if(messaggio==null) caricaReale();
        return messaggio.decifra();
    }

    @Override
    public boolean send() {
        if(messaggio==null) caricaReale();
        return messaggio.send();
    }

    @Override
    public void setLetto(boolean l) {
        if(messaggio==null) caricaReale();
        messaggio.setLetto(l);
    }

    @Override
    public String getId() {
        if(messaggio==null) return id;
        return messaggio.getId();
    }

    @Override
    public String getTesto() {
        if(messaggio==null) caricaReale();
        return messaggio.getTesto();
    }

    @Override
    public String getTestoCifrato() {
        if(messaggio==null) caricaReale();
        return messaggio.getTestoCifrato();
    }

    @Override
    public String getTitolo() {
        if(messaggio==null) return titolo;
        return messaggio.getTitolo();
    }

    @Override
    public void setTesto(String testo) {
        if(messaggio==null) caricaReale();
        messaggio.setTesto(testo);
    }

    @Override
    public void setTitolo(String titolo) {
        if(messaggio==null) caricaReale();
        messaggio.setTitolo(titolo);
    }

    @Override
    public void setSisCif(SistemaDiCifratura sisCif) {
        if(messaggio==null) caricaReale();
        messaggio.setSisCif(sisCif);
    }

    @Override
    public String getLingua() {
        if(messaggio==null) return lingua;
        return messaggio.getLingua();
    }

    @Override
    public boolean isBozza() {
        if(messaggio==null) return bozza;
        return messaggio.isBozza();
    }

    @Override
    public UserInfo getMittente() {
        if(messaggio==null) return UserInfo.load(idMitt);
        return messaggio.getMittente();
    }

    @Override
    public UserInfo getDestinatario() {
        if(messaggio==null) return UserInfo.load(idMitt);
        return messaggio.getDestinatario();
    }
    
    private void caricaReale(){
        String sql = "SELECT * FROM MESSAGGI WHERE ID=" + id;
        DBManager db = DBManager.getDBManager();
        ResultSet rs = db.execute(sql);
        if(rs==null){
            Logger.getLogger(Messaggio.class.getName()).log(Level.WARNING,"Errore: Impossibile caricare il messaggio con id: " + id);
        }
        messaggio = MessaggioProxy.creaMessaggio(rs);
    }
    
    private static Messaggio creaMessaggio(ResultSet rs){
        if(rs==null)return null;
        try {
            if(rs.next()) return new MessaggioReale(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean isLetto() {
        if(messaggio==null) return letto;
        return messaggio.isLetto();
    }

    @Override
    public void setLingua(String l) {
        if(messaggio==null) caricaReale();
        messaggio.setLingua(l);
    }

    @Override
    public void setDestinatario(UserInfo dest) {
        if(messaggio==null) caricaReale();
        messaggio.setDestinatario(dest);
    }

    @Override
    public void setTestoCifrato(String testo) {
        if(messaggio==null) caricaReale();
        messaggio.setTestoCifrato(testo);
    }
    
}
