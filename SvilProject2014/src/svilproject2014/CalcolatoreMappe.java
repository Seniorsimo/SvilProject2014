/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

import svilproject2014.calcolatori.*;

/**
 *
 * @author Simone
 */
public abstract class CalcolatoreMappe {
    
    public static CalcolatoreMappe create(String met){
        
        if(met.equals("cesare")){
            return new CalcolatoreCesare();
        }
        else if(met.equals("chiave")){
            return new CalcolatoreChiave();
        }
        else return new CalcolatorePseudo();
    }
    
    abstract public Mappatura calcola(String chiave);
}
