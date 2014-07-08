/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.utility;

import svilproject2014.DBManager;

/**
 * Classe utilizzata per la creazione delle tabelle del DB con indici (chiavi primarie) auto incrementanti.
 * 
 * ATTENZIONE
 * La presente classe non si occupa di inserire nel DB lingue o frequenze.
 * Nel caso sia necessario l'uso degli strumenti di supporto è necessario eseguire nell'ordine:
 * - InsertLanguage.java: per l'inserimento di un dizionario
 * - InsertLanguageFreq.java: per l'inserimento delle frequenze
 * 
 * Entrambi vanno eseguiti una sola volta!
 * L'esecuzione degli stessi più volte sullo stesso DB (senza aver prima rimosso tutte le tabelle relative alle lingue - vedi nota 1) può portare ad uno stato di inconsistenza il DB
 * 
 * note:
 * 1 - Le tabelle sono organizzate per ID della lingua. Affidarsi alla tabella LANGUAGE per identificare le lingue. Le frequenze sono collegate tramite lo stesso valore di ID e memorizzate nella tabella FREQUENZE.
 *  Le parole vengono salvate in tabelle non nomi che seguono la seguente sintassi: 'L' + ID_lingua + '_' + lunghezza delle parole.
 * 
 * @author User
 */
public class DBCreator {
    public static void main(String[] args){
        DBManager  db = DBManager.getDBManager();
        
    }
}
