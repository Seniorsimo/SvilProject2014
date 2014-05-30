/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import java.sql.*;

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
    
    static String url = "jdbc:derby://localhost:1527/dbsvil";
    static String user = "adminsvil";
    static String pwd = "adminsvil";
    
    public ResultSet execute(Statement q){
        //da implementare
        
        return null;
    }
    
}
