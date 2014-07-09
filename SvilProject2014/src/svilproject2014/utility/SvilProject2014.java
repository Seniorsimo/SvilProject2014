/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package svilproject2014.utility;

import java.util.List;
import svilproject2014.CalcolatoreMappe;
import svilproject2014.Cifratore;
import svilproject2014.Mappatura;
import svilproject2014.Messaggio;
import svilproject2014.sessione.GestoreIpotesi;
import svilproject2014.sessione.StrumentiDiSupporto;
import svilproject2014.sessione.strumenti.Dizionario;
import svilproject2014.sessione.strumenti.Frequenze;

public class SvilProject2014 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //utilizzo veloce come testing
        
        //TESTING SAVE/LOAD MESSAGGIO
        
//        Messaggio m = new Messaggio();
//        Messaggio m = Messaggio.load("1");
//        m.setLetto(false);
//        m.salva();
        //Messaggio m = CommunicationController.apriMessaggioRicevuto("1");
        
        //TESTING DIZIONARIO
        
//        Dizionario d = Dizionario.load(1);
//        List<String> list = d.cerca("gfTncvTpO");
//        while(!list.isEmpty()){
//            System.out.println(list.remove(0));
//        }
        
        //TESTING FREQUENZE
//        Frequenze f = Frequenze.load(1);
//        System.out.println(f.getFrequenza('z'));
        
        //TESTING STRUMENTI SUPPORTO
//        StrumentiDiSupporto s = new StrumentiDiSupporto(1);
//        List<String> list = s.cercaPatternSulDizionario("gfTncvTpO");
//        while(!list.isEmpty()){
//            System.out.println(list.remove(0));
//        }
//        System.out.println(s.getFrequenza('z'));
        
          //TESTING GESTORE IPOTESI
        Messaggio m = Messaggio.load("1");
//        m = Messaggio.load("1");
//        Mappatura map = CalcolatoreMappe.create("").calcola("");
//        System.out.println(Cifratore.cifra(map, m.getTesto()));
        GestoreIpotesi g = new GestoreIpotesi(m);
        System.out.println(g);
//        g.aggiungiIpotesi('x', 'o');
//        g.aggiungiIpotesi('r', 'g');
//        g.indietro(1);
//        g.aggiungiIpotesi('z', 'i');
//        g.indietro(1);
//        g.avanti(1);
//        g.salva();
        
//        GestoreIpotesi g = GestoreIpotesi.load(1);
////        System.out.println(g);
        g.aggiungiIpotesi('x', 'o');
////        g.aggiungiIpotesi('r', 'g');
////        g.indietro(1);
//        g.salva();
        System.out.println(g);
    }
    
}
