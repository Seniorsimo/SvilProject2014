/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simone
 * 
 * Informazioni Imporanti per il database.
 * Il db si trova nella cartella del progetto con il nome "dbsvil".
 * purtroppo git non lo sincronizza e ora che ci penso ha senso, ma la cosa crea problemi.
 * come già fatto x tweb ripeto: la cartella del progetto è presente non solo su git,
 * ma anche in drive: SvilProject2014 o come si chiama, al suo interno trovate il db.
 * 
 * Per installarlo semplicemente salvatevelo sul pc (non credo importi dove) e in netbeans andate
 * su servizi > database. Clic destro su Java DB > Proprietà. Modificate la location alla
 * cartella del db scaricata oppure inserite in quella location il db scaricato. il risultato
 * è lo stesso.
 * 
 * IMPORTANTE: Se fate modifiche al DB ricaricatelo poi su drive e informate gli altri che così
 * si scaricano la versione aggiornata.
 * 
 */
public class DBManager {
    
    private static String url = "jdbc:derby://localhost:1527/dbsvil";
    private static String user = "adminsvil";
    private static String pwd = "adminsvil";
    
    //inizializzazione Statement per query e eventuale creazione di tabelle.
    private static Connection conn;
    private static Statement st = init(); //in questo modo il metodo viene invocato al caricamento della classe garantendo la creazione di una connessione
    
    //Perchè più DBManager? Creo un metodo statico che ne cra uno e restituisce sempre lo stesso.
    private static DBManager dbm;
    
    public static DBManager getDBManager(){
        if(dbm==null){
            dbm = new DBManager();
        }
        return dbm;
    }
    
    /*IMPORTANTE
     * non vengono effettuate close() ne sullo Statement ne sulla Connection.
     * Queste connessioni vengono create a runtime durante il caricamento della classe.
     * Verificare o controllare che effettivamente a terminazione del programma queste vengano hiuse, in caso contrario sarà
     * necessario provvedere noi a chiuderle in qualche modo
     */
    
    
    public ResultSet execute(String q){
        
        //utilizzando lo statemente già creato eseguo la query e restituisco il resultSet risultato
        try {
            ResultSet rs = st.executeQuery(q);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.WARNING,"Errore nell'esecuzione della query: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    
    public int save(String q){
        
        try {
            return st.executeUpdate(q);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.WARNING,"Errore nell'esecuzione della query: " + ex.getMessage());
            ex.printStackTrace();
        }
        return -1;
    }
    
    //metodo scritto esclusivamente a fini pratici, in quanto da GUI non è possibile creare tabelle con chiavi auto incrementate.
    private static Statement init(){
        
        //creo la connessione al DB
        try {
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //creo lo statement
        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //tabella STUDENTI
        try {
            String sql = "CREATE TABLE STUDENTI ("
                    + "ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "NOME VARCHAR(20) NOT NULL,"
                    + "COGNOME VARCHAR(20) NOT NULL"
                    + ")";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO,"Tabella studenti già esistente.");
        }
        
        //tabella SDC
        try {
            String sql = "CREATE TABLE SDC ("
                    + "ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "CHIAVE VARCHAR(20) NOT NULL,"
                    + "METODO VARCHAR(20) NOT NULL,"
                    + "ID_CREATORE INT NOT NULL"
                    + ")";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO,"Tabella sdc già esistente.");
        }
        
        //tabella PROPOSTE
        try {
            String sql = "CREATE TABLE PROPOSTE ("
                    + "ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "STATO VARCHAR(20) NOT NULL,"
                    + "NOTIFICATA INT NOT NULL DEFAULT 0,"
                    + "ID_PROPONENTE INT NOT NULL,"
                    + "ID_PARTNER INT NOT NULL,"
                    + "ID_SDC INT NOT NULL"
                    + ")";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO,"Tabella proposte già esistente.");
        }
        
        //tabella MESSAGGI
        try {
            String sql = "CREATE TABLE MESSAGGI ("
                    + "ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "TESTO VARCHAR(10000) NOT NULL,"
                    + "TESTO_CIFRATO VARCHAR(10000) NOT NULL,"
                    + "LINGUA VARCHAR(20) NOT NULL,"
                    + "TITOLO VARCHAR(20) NOT NULL,"
                    + "BOZZA INT NOT NULL DEFAULT 1,"
                    + "LETTO INT NOT NULL DEFAULT 0,"
                    + "ID_MITTENTE INT NOT NULL,"
                    + "ID_DESTINATARIO INT NOT NULL,"
                    + "ID_SDC INT NOT NULL"
                    + ")";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO,"Tabella messaggi già esistente.");
        }
        
        //tabella LINGUAGE
        try {
            String sql = "CREATE TABLE LANGUAGE ("
                    + "ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "NOME VARCHAR(20) NOT NULL"
                    + ")";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.INFO,"Tabella messaggi già esistente.");
        }
        
        return st;
    }
    
}
