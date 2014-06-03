/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.calcolatori;

import svilproject2014.CalcolatoreMappe;
import svilproject2014.Mappatura;

/**
 *
 * @author User
 */
public class CalcolatoreCesare extends CalcolatoreMappe{

    @Override
    public Mappatura calcola(String chiave) {
        
        //La chiave utilizzata deve essere un numero da 0 a 25
        int i = Integer.parseInt(chiave);
        if(i<0||i>25){//nel caso non lo sia genero un numero a caso
            i = (int)Math.random() * 26; //il 26 è corretto.
        }
        //alfabeto di partenza
        char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        
        //map e inverse map
        char[] map = new char[26];
        char[] inverseMap = new char[26];
        
        //genero la Mappatura
        for(int j=0; j<alfabeto.length; j++){
            
            //calcolo gli indici
            int indexMap = (j + i) % alfabeto.length;
            int indexInverseMap = (j + alfabeto.length - i) % alfabeto.length; //il + nella parentesi garantisce che la sottrazione sia positiva e quindi il modulo corretto.
            
            //scivo gli array
            map[j] = alfabeto[indexMap];
            inverseMap[j] = alfabeto[indexInverseMap];
        }
        Mappatura m = new Mappatura(map, inverseMap);
        return m;
    }
    
}
