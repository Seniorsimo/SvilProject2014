/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.calcolatori;

import java.util.ArrayList;
import svilproject2014.CalcolatoreMappe;
import svilproject2014.Mappatura;

/**
 *
 * @author User
 */
public class CalcolatorePseudo extends CalcolatoreMappe{

    @Override
    public Mappatura calcola(String chiave) {
        //la chiave viene totalmente ignorata, e la generazione avviene in modo casuale usando il generatore Random di numeri di java.
        
        //alfabeto di partenza
        char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        
        //map e inverse map
        char[] map = new char[26];
        char[] inverseMap = new char[26];
        
        //inizializzo
        ArrayList<Character> daInserire = new ArrayList<>();
        ArrayList<Character> inseriti = new ArrayList<>();
        for(char c : alfabeto){
            daInserire.add(c);
        }
        
        //genero: estraggo a caso una lettera da insere per ogni lettera dell'alfabeto
        for(int i=0; i<alfabeto.length; i++){
            int target = (int)Math.random() * daInserire.size();
            char a = daInserire.remove(target);
            int indexA = position(a, alfabeto);
            
            map[i] = alfabeto[indexA];
            inverseMap[indexA] = alfabeto[i];
        }
        
        //Genero la Mappatura
        return new Mappatura(map, inverseMap);
    }
    
    private int position(char c, char[] list){
        for(int i=0; i<list.length; i++){
            if(list[i]==c) return i;
        }
        return -1;
    }
    
}
