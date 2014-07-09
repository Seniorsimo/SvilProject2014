/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione;

import java.util.List;

/**
 *
 * @author User
 */
public class Coppia {
    
    private Coppia padre;
    private List<Coppia> figli;
    
    private char vecchiaL;
    private char nuovaL;
    
    public Coppia(char nuovaL, char vecchiaL, Coppia padre){
        this.nuovaL = nuovaL;
        this.vecchiaL = vecchiaL;
        this.padre = padre;
        padre.addFiglio(this);
    }
    
    public List<Coppia> getFigli(){
        return figli;
    }
    
    public Coppia getPadre(){
        return padre;
    }
    
    public boolean addFiglio(Coppia c){
        return figli.add(c);
    }

    public char getVecchiaL() {
        return vecchiaL;
    }

    public char getNuovaL() {
        return nuovaL;
    }
    
}
