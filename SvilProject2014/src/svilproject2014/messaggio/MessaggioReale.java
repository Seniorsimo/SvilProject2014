/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.messaggio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import svilproject2014.Cifratore;
import svilproject2014.DBManager;
import svilproject2014.Mappatura;
import svilproject2014.Messaggio;
import svilproject2014.Proposta;
import svilproject2014.SistemaDiCifratura;
import svilproject2014.UserInfo;

/**
 *
 * @author User
 */
public class MessaggioReale extends Messaggio {
    
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
    
    public MessaggioReale(ResultSet info){
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
            Logger.getLogger(MessaggioReale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MessaggioReale(UserInfo mit){
        //non deve fare nulla...credo.
        mittente = mit;
        idMitt = mittente.getId();
    }
    
    @Override
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
    
    @Override
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
    
    @Override
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
    
    @Override
    public boolean send(){
        bozza = false;
        return salva();
    }
    
    @Override
    public void setLetto(boolean l){
        letto = l;
    }
    
    @Override
    public void setTesto(String testo) {
        this.testo = testo;
    }
    
    @Override
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    
    @Override
    public void setSisCif(SistemaDiCifratura sisCif) {
        this.sisCif = sisCif;
        idSdc = sisCif.getId();
    }
    
    @Override
    public void setDestinatario(UserInfo dest){
        destinatario = dest;
        idDest = destinatario.getId();
    }
    
    @Override
    public String getId(){
        return id;
    }

    @Override
    public String getTesto() {
        return testo;
    }

    @Override
    public String getTestoCifrato() {
        return testoCifrato;
    }

    @Override
    public String getTitolo() {
        return titolo;
    }

    @Override
    public String getLingua() {
        return lingua;
    }

    @Override
    public boolean isBozza() {
        return bozza;
    }
    
    @Override
    public boolean isLetto() {
        return letto;
    }

    @Override
    public UserInfo getMittente() {
        if(mittente==null) mittente = UserInfo.load(idMitt);
        return mittente;
    }

    @Override
    public UserInfo getDestinatario() {
        if(destinatario==null) destinatario = UserInfo.load(idDest);
        return destinatario;
    }
    
    public void setLingua(String l){
        lingua = l;
    }

    @Override
    public void setTestoCifrato(String testo) {
        testoCifrato = testo;
    }

}
