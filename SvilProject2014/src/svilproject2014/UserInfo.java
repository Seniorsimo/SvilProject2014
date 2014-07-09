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
    
}
