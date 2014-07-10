/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    //metodi non registrato(necessario per emulazione login)
    public Studente(ResultSet info){
        try {
                id = "" + info.getInt("ID");
                nome = info.getString("NOME");
                cognome = info.getString("COGNOME");
        } catch (SQLException ex) {
            Logger.getLogger(Studente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Studente> gelListaStudenti(){
        String sql = "SELECT * FROM STUDENTI";
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<Studente> list = new ArrayList<>();
        while(createNextStudente(rs, list));
        return list;
    }
    
    private static boolean createNextStudente(ResultSet rs, List<Studente> list){
        Studente m = Studente.creaStudente(rs);
        if(m!=null){
            list.add(m);
            return true;
        }
        return false;
    }
    
    private static Studente creaStudente(ResultSet rs){
        try {
            if(rs.next()) return new Studente(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Studente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //FINE PARTE NON registrata
    
}
