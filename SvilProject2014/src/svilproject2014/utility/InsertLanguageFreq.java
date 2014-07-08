/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import svilproject2014.DBManager;

/**
 *
 * @author Simone
 */
public class InsertLanguageFreq {
    
    public static void main(String[] args){
        
        //nome della lingua di cui si vogliono aggiungere le frequenze
        String name = "Italiano";
        
        //Frequenze
        double[] freq = new double[26];
        freq[0] = 11.74;    // a
        freq[1] = 0.92;     // b
        freq[2] = 4.50;     // c
        freq[3] = 3.73;     // d
        freq[4] = 11.79;    // e
        freq[5] = 0.95;     // f
        freq[6] = 1.64;     // g
        freq[7] = 1.54;     // h
        freq[8] = 11.28;    // i
        freq[9] = 0;        // j
        freq[10] = 0;       // k
        freq[11]= 6.51;     // l
        freq[12] = 2.51;    // m
        freq[13] = 6.88;    // n
        freq[14] = 9.83;    // o
        freq[15] = 3.05;    // p
        freq[16] = 0.51;    // q
        freq[17] = 6.37;    // r
        freq[18] = 4.98;    // s
        freq[19] = 5.62;    // t
        freq[20] = 3.01;    // u
        freq[21] = 2.10;    // v
        freq[22] = 0;       // w
        freq[23] = 0;       // x
        freq[24] = 0;       // y
        freq[25] = 0.49;    // z
        
        ResultSet rs = DBManager.getDBManager().execute("SELECT * FROM LANGUAGE WHERE NOME='" + name + "'");
        try {
            if(rs.next()){
                int id = rs.getInt("ID");
                
                String sql = "INSERT INTO FREQUENZE(ID";
                for(int i=0; i<26; i++){
                    sql += ",L" + i;
                }
                sql += ") VALUES(" + id;
                for(int i=0; i<26; i++){
                    sql += "," + freq[i];
                }
                sql += ")";
                
                DBManager.getDBManager().save(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertLanguageFreq.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
