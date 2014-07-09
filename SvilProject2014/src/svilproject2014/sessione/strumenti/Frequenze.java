/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione.strumenti;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import svilproject2014.DBManager;

/**
 *
 * @author User
 */
public class Frequenze {
    
    private int idLingua;
    private double[] listaFrequenze;
    
    public Frequenze(ResultSet info){
        try {
                    idLingua = info.getInt("ID");
                    listaFrequenze = new double[26];
                    for(int i=0; i<26; i++){
                        listaFrequenze[i] = info.getDouble("L"+i);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(Frequenze.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static Frequenze load(int id){
        if(id<1) return null;
        String sql = "SELECT * FROM FREQUENZE WHERE ID=" + id;
        ResultSet rs = DBManager.getDBManager().execute(sql);
        return Frequenze.creaFrequenze(rs);
        
    }
    
    private static Frequenze creaFrequenze(ResultSet rs){
        try {
            if(rs.next()) return new Frequenze(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Frequenze.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public double getFrequenza(char carattere){
        int target = -1;
        switch(carattere){
            case 'a': target = 0; break;
            case 'b': target = 1; break;
            case 'c': target = 2; break;
            case 'd': target = 3; break;
            case 'e': target = 4; break;
            case 'f': target = 5; break;
            case 'g': target = 6; break;
            case 'h': target = 7; break;
            case 'i': target = 8; break;
            case 'j': target = 9; break;
            case 'k': target = 10; break;
            case 'l': target = 11; break;
            case 'm': target = 12; break;
            case 'n': target = 13; break;
            case 'o': target = 14; break;
            case 'p': target = 15; break;
            case 'q': target = 16; break;
            case 'r': target = 17; break;
            case 's': target = 18; break;
            case 't': target = 19; break;
            case 'u': target = 20; break;
            case 'v': target = 21; break;
            case 'w': target = 22; break;
            case 'x': target = 23; break;
            case 'y': target = 24; break;
            case 'z': target = 25; break; 
        }
        if(target>-1) return listaFrequenze[target];
        return -1;
        
    }
    
}
