/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.utility;

import svilproject2014.DBManager;

/**
 * la classe inserisce nel database in ogni tabella un oggetto in modo da poterlo utilizzare nei test.
 * 
 * ATTENZIONE!!!
 * e' necessario che le tabelle siano vuote!
 * Eventualmente cancellarle prima di eseguire i test.
 * 
 * @author Simone
 */
public class DBTestObjects {
    public static void main(String[] args){
        String sql;
        
        //Studente/UserInfo
        //ID    nome        cognome
        //1     'Marco'     'Rossi'
        //2     'Giovanna'  'Verdi'
        //3     'Maria'     'Rosa'
        sql = "INSERT INTO STUDENTI (NOME,COGNOME)"
                        + " VALUES ('Marco','Rossi')"
                                + ",('Giovanna','Verdi')"
                                + ",('Maria','Rosa')";
        execute(sql);
        
        //SistemaDiCifratura
        //ID    chiave      metodo      id_creatore
        //1     '3'         'cesare'    1
        //2     'casa'      'chiave'    1
        //3     'bu'        'casuale'   2
        sql = "INSERT INTO SDC (CHIAVE,METODO,ID_CREATORE)"
                    + " VALUES ('3','cesare',1)"
                            + ",('casa','chiave',1)"
                            + ",('bu','casuale',2)";
        execute(sql);
        
        //Proposta
        //ID    stato       notificata  id_proponente   id_partner  id_sdc
        //1     'accepted'  0           1               2           2
        //2     'pending'   0           1               2           3
        sql = "INSERT INTO PROPOSTE (STATO,NOTIFICATA,ID_PROPONENTE,ID_PARTNER,ID_SDC)"
                            + " VALUES ('accepted',0,1,2,2)"
                                    + ",('pending',0,1,2,3)";
        execute(sql);
        
        //Messaggio/MessaggioReale/MessaggioProxy
        //ID    testo       testocifrato    lingua      titolo  bozza   letto   id_mittente id_destinatario id_sdc
        //1     'Ciao'      ''              'Italiano'  'Test'  1       0       1           2               2
        sql = "INSERT INTO MESSAGGI (TESTO,TESTO_CIFRATO,LINGUA,TITOLO,BOZZA,LETTO,ID_MITTENTE,ID_DESTINATARIO,ID_SDC)"
                        + " VALUES ('Ciao','','Italiano','Test',1,0,1,2,2)"
                                + ",('Ciao2','','Italiano','Test',0,0,1,2,2)"
                                + ",('Ciao3','','Italiano','Test',0,0,2,3,3)";
        execute(sql);
        
        //GestoreIpotesi
        //ID    avanti  indietro    stato   id_messaggio    testo_originale testo_parziale  lista
        //1     0       0           0       1               'ciao'          'ciao'          '(  ,0,)'
        sql = "INSERT INTO GESTOREIPOTESI (AVANTI,INDIETRO,STATO,ID_MESSAGGIO,TESTO_ORIGINALE,TESTO_PARZIALE,LISTA)"
                                + " VALUES (0,0,0,1,'shcp','shcp','(  ,0,)')"
                                        + ",(1,1,1,1,'shcp','shAp','(  ,1,(ca,2,(pr,0,)(po,0,)))')";
        execute(sql);
        
        //SessioneDiLavoro
        //ID    id_messaggio    id_gestore  id_lingua   id_utente   stato
        //1     1               1           1           1           'aperto'
        sql = "INSERT INTO SESSIONEDILAVORO (ID_MESSAGGIO,ID_GESTORE,ID_LINGUA,ID_UTENTE,STATO)"
                + " VALUES (1,1,1,1,'aperto')";
        execute(sql);
        
        //Language
        //ID    nome
        //1     'Italiano'
        
        //usare InsertLanguage per aggiungerlo
        
        //Frequenze
        //ID    troppe....
        
        //Usare InsertLanguageFreq per aggiungerle.
        
    }
    
    private static void execute(String sql){
        DBManager.getDBManager().save(sql);
    }
}
