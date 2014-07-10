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
public class UserInfo {
    
    private String id;
    private String nome;
    private String cognome;
    
    public UserInfo(ResultSet info){
        try {
                id = "" + info.getInt("ID");
                nome = info.getString("NOME");
                cognome = info.getString("COGNOME");
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static UserInfo load(String id){
        //id deve contenere un numero e questo deve essere strettamente positivo, altrimenti errore.      
        if(Integer.parseInt(id)<1) return null;
        
        String sql = "SELECT * FROM STUDENTI WHERE ID=" + id;
        DBManager db = DBManager.getDBManager();
        ResultSet rs = db.execute(sql);
        if(rs==null){
            Logger.getLogger(UserInfo.class.getName()).log(Level.WARNING,"Errore: Impossibile caricare l'utente con id: " + id);
            return null;
        }
        UserInfo m = UserInfo.creaUserInfo(rs);
        return m;
    }
    
    protected static UserInfo creaUserInfo(ResultSet rs){
        try {
            if(rs.next()) return new UserInfo(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //get
    public String getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
    
    
    
}
