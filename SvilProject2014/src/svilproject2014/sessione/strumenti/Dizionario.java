/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione.strumenti;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import svilproject2014.DBManager;

/**
 *
 * @author User
 */
public class Dizionario {
    
    private int idLingua;
    private String nomeLingua;
    
    public Dizionario(ResultSet info){
        try {
                idLingua = info.getInt("ID");
                nomeLingua = info.getString("NOME");
        } catch (SQLException ex) {
            Logger.getLogger(Dizionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public static Dizionario load(int id){
        if(id<1) return null;
        String sql = "SELECT * FROM LANGUAGE WHERE ID=" + id;
        DBManager db = DBManager.getDBManager();
        ResultSet rs = db.execute(sql);
        if(rs==null){
            Logger.getLogger(Dizionario.class.getName()).log(Level.WARNING,"Errore: Impossibile caricare la lingua con id: " + id);
            return null;
        }
        Dizionario d = Dizionario.creaDizionario(rs);
        return d;
    }
    
    private static Dizionario creaDizionario(ResultSet rs){
        try {
            if(rs.next()) return new Dizionario(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Dizionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /*
     * Cerca effettua una ricerca nel DB usando un pattern del tipo sdTTd
     * - le lettere maiuscole rappresentano lettere conosciute (le due T)
     * - le minuscole sono lettere variabili
     * - se due minuscole sono uguali (come le d) per la ricerca ne viene tenuto conto
     */
    public List<String> cerca(String pattern){
        //memorizza lunghezza del pattern e inizializza la list
        int l = pattern.length();
        char[] word;
        word = pattern.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        
        //crea string sql
        String sql = "SELECT * FROM L" + idLingua + "_" + l;
        boolean inseritoWhere = false;
        
        //inserisci vincoli 
        for(int i=0; i<word.length; i++){
            
            //se uppercase allora aggiungo vincolo su lettera
            if(Character.isUpperCase(word[i])){
                if(inseritoWhere) sql += " AND";
                else{
                    sql += " WHERE";
                    inseritoWhere = true;
                }
                sql += " C" + i + "='" + Character.toLowerCase(word[i]) + "'";
            }
            
            //altrimenti controllo se ci sono caratteri uguali dopo e se ne trovo inserisco vincolo su uguaglianza
            else{
                int j = i+1;
                boolean found = false;
                while(!found && j<word.length){
                    if(word[i]==word[j]){
                        if(inseritoWhere) sql += " AND";
                        else{
                            sql += " WHERE";
                            inseritoWhere = true;
                        }
                        sql += " C" + i + "=C" + j;
                        found = true;
                    }
                    j++;
                }
            }
        }
        
        //eseguo la query, formatto e restituisco il risultato
        ResultSet rs = DBManager.getDBManager().execute(sql);
        try {
            while(rs.next()){
                String temp = "";
                for(int i=0; i<word.length; i++){
                    temp += rs.getString("C"+i);
                }
                list.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dizionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;  
    }       

    public int getIdLingua() {
        return idLingua;
    }
    
    
}
