/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

/**
 *
 * @author Simone
 */
public class Mappatura {
    
    private char[] mappa;
    private char[] mappaInversa;
    //alfabeto di partenza
    private char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    public char map(char c){
        int position = getPosition(c, alfabeto);
        
        //nel caso il char non sia presente restituisco il char iniziale
        if(position<0) return c;
        
        //altrimenti restituisco il char associato
        return mappa[position];
    }
    
    public char inverseMap(char c){
        int position = getPosition(c, alfabeto);
        
        //nel caso il char non sia presente restituisco il char iniziale
        if(c<0) return c;
        
        //altrimenti restituisco il char associato
        return mappaInversa[position];
    }
    
    public Mappatura(char[] map, char[] inverseMap){
        mappa = map;
        mappaInversa = inverseMap;
    }
    
    private int getPosition(char c, char[] list){
        for(int i=0; i<list.length; i++){
            if(list[i]==c) return i;
        }
        return -1;
    }
    
    @Override
    public String toString(){
        return "map: "+ String.copyValueOf(mappa) + "\ninversa: " + String.copyValueOf(mappaInversa);
    }

    public char[] getMappa() {
        return mappa;
    }

    public char[] getMappaInversa() {
        return mappaInversa;
    }
    
}
