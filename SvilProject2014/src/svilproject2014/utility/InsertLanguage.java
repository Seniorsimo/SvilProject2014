/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import svilproject2014.DBManager;

/**
 *
 * @author Simone
 */
public class InsertLanguage {
    public static void main(String[] args){
        
        //Nome lingua e file da cui importare le parole
        //NB La lingua non deve esistere ne tanto meno le tabelle relative ad essa
        String name = "Italiano";
        
        DBManager db = DBManager.getDBManager();
        
        //Inserisco la lingua nell'elenco e me ne procuro l'id
        String sql = "INSERT INTO LANGUAGE(NOME) VALUES('" + name + "')";
        db.save(sql);
        sql = "SELECT * FROM LANGUAGE WHERE NOME='" + name + "'";
        ResultSet rs = db.execute(sql);
        try {
            if(rs.next()){
                int id = rs.getInt("ID");
                String prefix = id + "_";
                addWords(name, prefix);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private static void addWords(String name, String prefix){
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader("src/svilproject2014/utility/"+name+".txt"));
            //creo una lista x ogni lunghezza da 2 a 22
            List tables[] = new List[20];
            for(int i=0; i<tables.length; i++) tables[i] = new ArrayList<String>();
            DBManager db = DBManager.getDBManager();
            
            //scansiono le parole e le inserisco nelle rispettive liste
            String line = null;
            while((line = reader.readLine())!=null){
                if(line.length()>1&&line.length()<22){
                    tables[line.length()-2].add(line);
                }
            }
            
            //creo le tabelle se le liste non sono vuote e inserisco le relative parole
            for(int i=0; i<tables.length; i++){
                if(!tables[i].isEmpty()){
                    int l = i+2;
                    String tableName = "L" + prefix + l;
                    System.out.println("Creazione tabella per parole di lunghezza "+l + "; " + tables[i].size() + " parole");
                    
                    String sql = "CREATE TABLE " + tableName + "("
                            + "ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)";
                    for(int j=0; j<l; j++){
                        sql += ", C" + j + " VARCHAR(1) NOT NULL";
                    }
                    sql += ")";
                    db.save(sql);
                    
                    String word = null;
                    while(!tables[i].isEmpty()){
                        word = (String)tables[i].remove(0);
                        sql = "INSERT INTO " + tableName + "(C0";
                        for(int j=1; j<l; j++){
                            sql += ", C" + j;
                        }
                        sql += ") VALUES('" + word.charAt(0);
                        for(int j=1; j<l; j++){
                            sql += "', '" + word.charAt(j);
                        }
                        sql += "')";
                        db.save(sql);
                    }
                            
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(InsertLanguage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InsertLanguage.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Terminato");
    }
}
