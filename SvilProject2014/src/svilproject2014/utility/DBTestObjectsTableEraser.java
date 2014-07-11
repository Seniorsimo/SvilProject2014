/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.utility;

import svilproject2014.DBManager;

/**
 *
 * @author User
 */
public class DBTestObjectsTableEraser {
    
    public static void main(String[] args){
        String sql;
        
        sql = "DROP TABLE SESSIONEDILAVORO";
        execute(sql);
        
        sql = "DROP TABLE GESTOREIPOTESI";
        execute(sql);
        
        sql = "DROP TABLE MESSAGGI";
        execute(sql);
        
        sql = "DROP TABLE PROPOSTE";
        execute(sql);
        
        sql = "DROP TABLE SDC";
        execute(sql);
        
        sql = "DROP TABLE STUDENTI";
        execute(sql);
        
    }
    
    private static void execute(String sql){
        DBManager.getDBManager().save(sql);
    }
}
