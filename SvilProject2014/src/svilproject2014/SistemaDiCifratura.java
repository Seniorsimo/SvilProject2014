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
public class SistemaDiCifratura {
    
    private String id;
    private String chiave;
    private String metodo;
    
    private String idCreatore;
    
    private CalcolatoreMappe calcMap;
    private Mappatura map;
    private UserInfo creatore;
    
    public SistemaDiCifratura(String key, String metodo){
        chiave = key;
        this.metodo = metodo;
        
        calcolaMappatura();
    }
    
    public SistemaDiCifratura(ResultSet info){
        try {
            id = "" + info.getInt("ID");
            chiave = info.getString("CHIAVE");
            metodo = info.getString("METODO");
            idCreatore = "" + info.getInt("ID_CREATORE");
        } catch (SQLException ex) {
            Logger.getLogger(Proposta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<SistemaDiCifratura> caricaSistemiCifratura(Studente stud){
        String idStud = stud.getId();
        
        String sql = "SELECT * FROM SDC WHERE ID_CREATORE=" + Integer.parseInt(idStud);
        ResultSet rs = DBManager.getDBManager().execute(sql);
        ArrayList<SistemaDiCifratura> listaSdc = new ArrayList<>();
        
        while(createNextSistemaDiCifratura(rs, listaSdc));
        
        return listaSdc;
    }
    
    private static boolean createNextSistemaDiCifratura(ResultSet rs, List<SistemaDiCifratura> list){
        SistemaDiCifratura sdc = SistemaDiCifratura.creaSistemaDiCifratura(rs);
        String idSdc = sdc.getId();
        if(idSdc!=null){
            if(Integer.parseInt(idSdc)>0){
                list.add(sdc);
                return true;
            }
        }
        return false;
    }
    
    public static SistemaDiCifratura load(String id){
        if(Integer.parseInt(id)<1) return null;
        String sql = "SELECT * FROM SDC WHERE ID=" + Integer.parseInt(id);
        ResultSet rs = DBManager.getDBManager().execute(sql);
        return SistemaDiCifratura.creaSistemaDiCifratura(rs);
    }
    
    private static SistemaDiCifratura creaSistemaDiCifratura(ResultSet rs){
        try {
            if(rs.next()) return new SistemaDiCifratura(rs);
        } catch (SQLException ex) {
            Logger.getLogger(SistemaDiCifratura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void calcolaMappatura(){
        calcMap = CalcolatoreMappe.create(metodo);
        map = calcMap.calcola(chiave);
    }
    
    public String prova(String testo){
        //nel caso map sia nullo termino immediatamente e rirono null
        if(map==null) return null;
        
        String testoCifrato = Cifratore.cifra(map, testo);
        return testoCifrato;
    }
    
    public boolean salva(){
        String sql;
        //ho già un id quindi il SDC esiste già nel DB e deve essere aggiornato.
        if(id!=null){
            sql = "UPDATE SDC SET "
                    + "CHIAVE='" + chiave + "',"
                    + "METODO='" + metodo + "',";
            if(idCreatore!=null) sql += ",ID_CREATORE=" + Integer.parseInt(idCreatore);
            
            sql += " WHERE ID=" + Integer.parseInt(id);
        }
        //non ho ancora un id, quindi devo inserire il Sdc in DB
        else{
            sql = "INSERT INTO SDC (CHIAVE, METODO, ID_CREATORE) VALUES ("
                    + "'" + chiave + "',"
                    + "'" + metodo + "',";
            if(idCreatore!=null) sql += idCreatore;
            else sql += "0";
        }
        int i = DBManager.getDBManager().save(sql);
        return i>0 ? true : false;
    }
    
    public boolean elimina(){
        String sql = "DELETE FROM SDC WHERE ID=" + Integer.parseInt(id);
        int i = DBManager.getDBManager().save(sql);
        return i>0 ? true : false;
    }
    
    public Mappatura getMappatura(){
        return map;
    }
    
    public String getId(){
        return id;
    }
    
}
