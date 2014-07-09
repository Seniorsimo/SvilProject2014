/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.sessione;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import svilproject2014.Messaggio;

/**
 *
 * @author User
 */
public class GestoreIpotesi {
    
    private Messaggio messaggio;
    private Coppia[] listaAssociazioni;
    
    private int id;
    private int avantiPossibili = 0;
    private int indietroPossibili = 0;
    private int indiceStatoCorrente = 0;
    private char[] testoOriginale;
    //testo parziale è un array in cui ogni carattere se minuscolo rappresenta il testo originale, se maiuscolo  testo decifrato
    private char[] testoParziale;
    
    public GestoreIpotesi(ResultSet info){
        //da implementare
        
        //carico id, avanti, indietro, stato e testi da db
        
        //tramite id messaggio lo carico da db
        
    }
    
    public GestoreIpotesi(Messaggio m){
        messaggio = m;
        listaAssociazioni = new Coppia[27];
        listaAssociazioni[0] = new Coppia(' ', ' ', null);
        
        String txt = m.getTestoCifrato();
        txt = txt.toLowerCase();
        testoOriginale = txt.toCharArray();
        testoParziale = testoOriginale;
    }
    
    public static GestoreIpotesi load(int id){
        //da implementare
        //uso il costruttore con con query
        
        //ripristino stato listaAssociazioni.
        
        return null;
        
    }
    
    public List<Coppia> visualizzaAssociazioni(){
        ArrayList<Coppia> list = new ArrayList<>();
        for(int i=0; i<indiceStatoCorrente; i++){   //[0, indice-1] ma 0=root=ipotesivuota --> non serve! && indice=ultima ipotesi --> da stampare
            list.add(listaAssociazioni[i+1]);       //shift di uno a destra: salto 0 e includo indiceCorrente
        }
        return list;
    }
    
    public boolean aggiungiIpotesi(char vecchiaLettera, char nuovaLettera){
        for(int i=indiceStatoCorrente+1; i<27; i++){
            listaAssociazioni[i] = null;
        }
        avantiPossibili = 0;
        
        int i = 1;
        boolean exist = false;
        while(!exist && i<indiceStatoCorrente+1){
            if(listaAssociazioni[i].getVecchiaL()==vecchiaLettera) exist=true;
        }
        if(exist) return false;
        
        Coppia c = new Coppia(nuovaLettera, vecchiaLettera, listaAssociazioni[indiceStatoCorrente]);
        indiceStatoCorrente++;
        listaAssociazioni[indiceStatoCorrente] = c;
        boolean temp = applicaIpotesi(c);
        
        indietroPossibili++;
        return temp;
    }
    
    public Coppia visualizzaStoria(){
        return listaAssociazioni[0];
    }
    
    public boolean avanti(int n){
        if(avantiPossibili>=n){
            for(int i=0; i<n; i++){
                indiceStatoCorrente++;
                boolean temp = applicaIpotesi(listaAssociazioni[indiceStatoCorrente]);
                indietroPossibili++;
                avantiPossibili--;
                if(!temp) return false;
            }
            return true;
        }
        return false;
    }
    
    public boolean indietro(int n){
        if(indietroPossibili>=n){
            for(int i=0; i<n ; i++){
                boolean temp = rollback(listaAssociazioni[indiceStatoCorrente]);
                indiceStatoCorrente--;
                indietroPossibili--;
                avantiPossibili++;
                if(!temp) return false;
            }
            return true;
        }
        return false;
    }
    
    public boolean verificaRisoluzione(){
        String original = messaggio.getTesto();
        String solution = testoParziale.toString();
        
        return solution.equalsIgnoreCase(original);
        
    }
    
    public boolean salva(){
        //da implementare
        //calcolo una stringa x listaAssociazioni e getID messaggio
        
        //salvo su db
        
        return false;
        
    }
    
    private boolean applicaIpotesi(Coppia c){
        char target = c.getVecchiaL();
        char test = Character.toUpperCase(c.getNuovaL());
        for(int i=0; i<testoOriginale.length; i++){
            if(testoOriginale[i]==target){
                testoParziale[i] = test;
            }
        }
        return true;
        
    }
    
    private boolean rollback(Coppia c){
        char target = Character.toUpperCase(c.getNuovaL());
        for(int i=0; i<testoOriginale.length; i++){
            if(testoParziale[i]==target){
                testoParziale[i] = testoOriginale[i];
            }
        }
        return true;
        
    }
    
}
