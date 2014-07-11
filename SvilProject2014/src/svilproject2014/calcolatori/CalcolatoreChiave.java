/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.calcolatori;

import java.util.logging.Level;
import java.util.logging.Logger;
import svilproject2014.CalcolatoreMappe;
import svilproject2014.Mappatura;

/**
 *
 * @author User
 */
public class CalcolatoreChiave extends CalcolatoreMappe{

    @Override
    public Mappatura calcola(String chiave) {
        //alfabeto di partenza
        char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        //la chiave deve essere una parola di non più di 26 lettere
        //nel caso sia presente una lettera non dell'alfabeto l'esecuzione termina e restituisce null
        //le doppie vengono saltate
        if(chiave.length()>26){
            Logger.getLogger(CalcolatoreChiave.class.getName()).log(Level.SEVERE, "Errore: la chiave deve essere inferiore ai 26 caratteri");
            chiave = chiave.substring(0, 26);
        }
        if(chiave.length()==0) chiave = "chiavenulla";
        chiave = chiave.toLowerCase();
        boolean correct = true;
        for(char c:chiave.toCharArray()) {
            boolean found = false;
            for(char cc:alfabeto){
                if(c==cc) found = true;
            }
            if(!found) correct = false;
        }
        if(!correct) chiave = "chiavenulla";
        
        
        //es:               c   i   a   o   b   d   e   f   g   h   j   k  ...
        
        //map e inverse map
        char[] map = new char[26];
        char[] inverseMap = new char[26];
        
        //genero la chiave
        char[] key = chiave.toCharArray();
        char[] realKey = new char[key.length];//usato x evitare le doppie
        
        int index = 0;
        for(char c : key){
            if(!inserito(c, realKey)){
                boolean found = false;
                int i = 0;
                while(!found&&i<alfabeto.length){
                    if(alfabeto[i]==c) found = true;
                    else i++;
                }
                if(!found){
                    Logger.getLogger(CalcolatoreChiave.class.getName()).log(Level.SEVERE, "Errore: la chiave può essere composta soltanto da combinazioni delle 26 lettere dell'alfabeto");
                    return null;
                }
                map[index] = alfabeto[i];
                inverseMap[i] = alfabeto[index];
                realKey[index] = c;
                index++;
            }
        }
        
        //completo con le lettere che mancano
        int indexAlfabeto = 0;
        while(index < 26){
            if(!inserito(alfabeto[indexAlfabeto], map)){
                map[index] = alfabeto[indexAlfabeto];
                inverseMap[indexAlfabeto] = alfabeto[index];
                index++;
            }
            indexAlfabeto++;
        }
        
        //genero la Mappatura
        return new Mappatura(map, inverseMap);
    }
    
    private boolean inserito(char c, char[] list){
        for(char a : list){
            if(a==c) return true;
        }
        return false;
    }
    
}
