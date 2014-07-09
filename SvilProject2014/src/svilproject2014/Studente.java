/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.sql.ResultSet;

/**
 *
 * @author Simone
 */
public class Studente {
    
    private String nome;
    private String cognome;
    private String id;
    private String login;
    private String pwd;
    
    public String getId(){
        return id;
    }
    
    //get
    public UserInfo getUserInfo(){
        String sql = "SELECT ID,NOME,COGNOME FROM STUDENTI WHERE ID=" + Integer.parseInt(id);
        ResultSet rs = DBManager.getDBManager().execute(sql);
        return UserInfo.creaUserInfo(rs);
    }
    
}
