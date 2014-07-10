/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione;

import java.util.List;
import svilproject2014.sessione.strumenti.*;

/**
 *
 * @author User
 */
public class StrumentiDiSupporto {
    
    private Dizionario dizionario;
    private Frequenze frequenze;
    
    public StrumentiDiSupporto(int idLingua){
        aggiornaLingua(idLingua);
    }
    
    public boolean aggiornaLingua(int idLingua){
        if(idLingua<1) return false;
        dizionario = Dizionario.load(idLingua);
        frequenze = Frequenze.load(idLingua);
        return true;
    }
    
    public List<String> cercaPatternSulDizionario(String pattern){
        return dizionario.cerca(pattern);
    }
    
    public double getFrequenza(char carattere){
        return frequenze.getFrequenza(carattere);
    }
    
    public int getLingua(){
        return dizionario.getIdLingua();
    }
    
}
