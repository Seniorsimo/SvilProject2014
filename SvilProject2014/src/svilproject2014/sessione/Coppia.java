/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione;

import java.util.ArrayList;
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
    
    public Coppia(char vecchiaL, char nuovaL, Coppia padre){
        this.vecchiaL = vecchiaL;
        this.nuovaL = nuovaL;        
        this.padre = padre;
        figli = new ArrayList<>();
        if(padre!=null)padre.addFiglio(this);
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
    
    public String salva(){
        String out = "("+vecchiaL+nuovaL+","+figli.size()+",";
        for(Coppia c:figli){
            out += c.salva();
        }
        out += ")";
        return out;
    }
    
    public Coppia load(List<Character> ref) {
        Coppia last = this;
        ref.remove(0); //toglie la parentesi inisiale
        vecchiaL = ref.remove(0); //legge il primo char
        nuovaL = ref.remove(0);   //legge il secondo char
        ref.remove(0);  //toglie la prima virgola
        String n = "" + ref.remove(0);  //legge la prima cifra del numero
        char test = ref.remove(0);      //o numero a 2 cifre o ','
        int q = 0;
        if(test==','){
            q = Integer.parseInt(n);
        }
        else{
            n += test;
            q = Integer.parseInt(n);
            ref.remove(0);      //nel caso devo rimuovere la seconda virgola
        }
        for(int i=0; i<q; i++){
            Coppia c = new Coppia(' ', ' ', this);
            last = c.load(ref);
        }
        ref.remove(0);  //tolgo l'ulima parentesi
        return last;
    }

    public char getVecchiaL() {
        return vecchiaL;
    }

    public char getNuovaL() {
        return nuovaL;
    }
    
    @Override
    public String toString(){
        String out = "";
        out += "("+vecchiaL+nuovaL+",";
        for(Coppia c:figli){
            out += c.toString();
        }
        out += ")";
        return out;
    }
    
    public boolean equals(Coppia c) {
        if(c==null) return false;
        if(vecchiaL != c.getVecchiaL()) return false;
        if(nuovaL != c.getNuovaL()) return false;
        if((padre!=null && c.getPadre()==null)||(padre==null && c.getPadre()!=null)) return false;
        if(padre==null && c.getPadre()==null) return true;
        if(!padre.equals(c.getPadre())) return false;
        return true;
    }
    
    @Override
    public boolean equals(Object o){
        if(o==null) return false;
        Coppia c = (Coppia) o;
        if(vecchiaL != c.getVecchiaL()) return false;
        if(nuovaL != c.getNuovaL()) return false;
        if(padre==null && c.getPadre()!=null) return false;
        if(padre!=null&&(!padre.equals(c.getPadre()))) return false;
        if(!getFigli().equals(c.getFigli()))return false;
        return true;
    }

}
