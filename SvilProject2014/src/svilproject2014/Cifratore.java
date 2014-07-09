/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014;

/**
 *
 * @author Simone
 */
public class Cifratore {
    
    public static String cifra(Mappatura map, String testo){
        char[] txt = testo.toLowerCase().toCharArray();
        
        char[] out = new char[txt.length];
        
        for(int i=0; i<txt.length; i++){
            out[i] = map.map(txt[i]);
        }
        
        String converted = String.copyValueOf(out);
        
        return converted;
    }
    
    public static String decifra(Mappatura map, String testo){
        char[] txt = testo.toLowerCase().toCharArray();
        char[] out = new char[txt.length];
        
        for(int i=0; i<txt.length; i++){
            out[i] = map.inverseMap(txt[i]);
        }
        
        String converted = String.copyValueOf(out);
        
        return converted;
    }
    
}
