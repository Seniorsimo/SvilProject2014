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

/**
 *
 * @author User
 */
public class GestoreIpotesi {
    
    private Messaggio messaggio;
    private Coppia[] listaAssociazioni;
    
    private int id = 0;
    private int avantiPossibili = 0;
    private int indietroPossibili = 0;
    private int indiceStatoCorrente = 0;
    private char[] testoOriginale;
    //testo parziale è un array in cui ogni carattere se minuscolo rappresenta il testo originale, se maiuscolo  testo decifrato
    private char[] testoParziale;
    
    public GestoreIpotesi(ResultSet info){
        int messageId = 0;
        
        //carico id, avanti, indietro, stato e testi da db
        try {
            if(info.next()){
                id = info.getInt("ID");
                avantiPossibili = info.getInt("AVANTI");
                indietroPossibili = info.getInt("INDIETRO");
                indiceStatoCorrente = info.getInt("STATO");
                messageId = info.getInt("ID_MESSAGGIO");
                testoOriginale = info.getString("TESTO_ORIGINALE").toCharArray();
                testoParziale = info.getString("TESTO_PARZIALE").toCharArray();
                char[] list = info.getString("LISTA").toCharArray();
                ArrayList<Character> ref = new ArrayList<>();
                for(char c:list){
                    ref.add(c);
                }
                listaAssociazioni = new Coppia[27];
                listaAssociazioni[0] = new Coppia(' ', ' ', null);
                
                listaAssociazioni[0].load(ref);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //tramite id messaggio lo carico da db
        messaggio = Messaggio.load(""+messageId);
        
    }
    
    public GestoreIpotesi(Messaggio m){
        messaggio = m;
        listaAssociazioni = new Coppia[27];
        listaAssociazioni[0] = new Coppia(' ', ' ', null);
        
        String txt = m.getTestoCifrato();
        txt = txt.toLowerCase();
        testoOriginale = txt.toCharArray();
        testoParziale = txt.toCharArray();
    }
    
    public static GestoreIpotesi load(int id){
        if(id<1) return null;
        
        String sql = "SELECT * FROM GESTOREIPOTESI WHERE ID=" + id;
        DBManager db = DBManager.getDBManager();
        ResultSet rs = db.execute(sql);
        if(rs==null){
            Logger.getLogger(Messaggio.class.getName()).log(Level.WARNING,"Errore: Impossibile caricare il gestoreipotesi con id: " + id);
            return null;
        }
        return new GestoreIpotesi(rs);
        
    }
    
    public List<Coppia> visualizzaAssociazioni(){
        ArrayList<Coppia> list = new ArrayList<>();
        for(int i=0; i<=indiceStatoCorrente; i++){   //indice=ultima ipotesi --> da stampare //TODO dovrebbe partire da 1 per non visualizzare la root
            list.add(listaAssociazioni[i]);       
        }
        return list;
    }
    
    public boolean aggiungiIpotesi(char vecchiaLettera, char nuovaLettera){
        for(int i=indiceStatoCorrente+1; i<27; i++){
            listaAssociazioni[i] = null;
        }
        avantiPossibili = 0;
        
        int i = 1;
        boolean exist = false;
        while(!exist && i<indiceStatoCorrente+1){
            if(listaAssociazioni[i].getVecchiaL()==vecchiaLettera) exist=true;
            i++;
        }
        if(exist) return false;
        
        Coppia c = new Coppia(nuovaLettera, vecchiaLettera, listaAssociazioni[indiceStatoCorrente]);
        indiceStatoCorrente++;
        listaAssociazioni[indiceStatoCorrente] = c;
        boolean temp = applicaIpotesi(c);
        
        indietroPossibili++;
        return temp;
    }
    
    public Coppia visualizzaStoria(){
        return listaAssociazioni[0];
    }
    
    public boolean avanti(int n){
        if(avantiPossibili>=n){
            for(int i=0; i<n; i++){
                indiceStatoCorrente++;
                boolean temp = applicaIpotesi(listaAssociazioni[indiceStatoCorrente]);
                indietroPossibili++;
                avantiPossibili--;
                if(!temp) return false;
            }
            return true;
        }
        return false;
    }
    
    public boolean indietro(int n){
        if(indietroPossibili>=n){
            for(int i=0; i<n ; i++){
                boolean temp = rollback(listaAssociazioni[indiceStatoCorrente]);
                indiceStatoCorrente--;
                indietroPossibili--;
                avantiPossibili++;
                if(!temp) return false;
            }
            return true;
        }
        return false;
    }
    
    public boolean verificaRisoluzione(){
        String original = messaggio.getTesto();
        String solution = String.copyValueOf(testoParziale);
        
        return solution.equalsIgnoreCase(original);
        
    }
    
    public boolean salva(){
        //calcolo una stringa x listaAssociazioni e getID messaggio
        String lista = listaAssociazioni[0].salva();
        int messageId = Integer.parseInt(messaggio.getId());
        
        //salvo su db
        String sql;
        if(id!=0){
            sql = "UPDATE GESTOREIPOTESI SET "
                    + "AVANTI=" + avantiPossibili + ","
                    + "INDIETRO=" + indietroPossibili + ","
                    + "STATO=" + indiceStatoCorrente + ","
                    + "ID_MESSAGGIO=" + messageId + ","
                    + "TESTO_ORIGINALE='" + String.copyValueOf(testoOriginale) + "',"
                    + "TESTO_PARZIALE='" + String.copyValueOf(testoParziale) + "',"
                    + "LISTA='" + lista + "'";
            sql += " WHERE ID=" + id;
        }
        else{
            sql = "INSERT INTO GESTOREIPOTESI (AVANTI, INDIETRO, STATO, ID_MESSAGGIO, TESTO_ORIGINALE, TESTO_PARZIALE, LISTA) VALUES ("
                    + avantiPossibili + ","
                    + indietroPossibili + ","
                    + indiceStatoCorrente + ","
                    + messageId + ","
                    + "'" + String.copyValueOf(testoOriginale) + "',"
                    + "'" + String.copyValueOf(testoParziale) + "',"
                    + "'" + lista + "'";
            sql += ")";
        }
        int i = DBManager.getDBManager().save(sql);
        return i>0 ? true : false;
        
    }
    
    private boolean applicaIpotesi(Coppia c){
        char target = c.getVecchiaL();
        char test = Character.toUpperCase(c.getNuovaL());
        for(int i=0; i<testoOriginale.length; i++){
            if(testoOriginale[i]==target){
                testoParziale[i] = test;
            }
        }
        return true;
        
    }
    
    private boolean rollback(Coppia c){
        char target = Character.toUpperCase(c.getNuovaL());
        for(int i=0; i<testoOriginale.length; i++){
            if(testoParziale[i]==target){
                testoParziale[i] = testoOriginale[i];
            }
        }
        return true;
        
    }
    
    @Override
    public String toString(){
        String out = "";
        out += "id: "+ id;
        out += "\navanti: "+ avantiPossibili;
        out += "\nindietro: "+ indietroPossibili;
        out += "\nstato: "+ indiceStatoCorrente;
        out += "\ntesto originale: "+ String.copyValueOf(testoOriginale);
        out += "\ntesto parziale: "+ String.copyValueOf(testoParziale);
        out += "\n" + listaAssociazioni[0].toString();
        return out;
    }
    
}
