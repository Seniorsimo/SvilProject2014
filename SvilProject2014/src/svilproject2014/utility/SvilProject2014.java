/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package svilproject2014.utility;

import java.util.List;
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
        
        //Messaggio m = new Messaggio();
        //Messaggio m = Messaggio.load("1");
        //m.setLetto(false);
        //m.salva();
        //Messaggio m = CommunicationController.apriMessaggioRicevuto("1");
        
        //TESTING DIZIONARIO
        
//        Dizionario d = Dizionario.load(1);
//        List<String> list = d.cerca("gfTncvTpO");
//        while(!list.isEmpty()){
//            System.out.println(list.remove(0));
//        }
        
        //TESTING FREQUENZE
        Frequenze f = Frequenze.load(1);
        System.out.println(f.getFrequenza('z'));
    }
    
}
