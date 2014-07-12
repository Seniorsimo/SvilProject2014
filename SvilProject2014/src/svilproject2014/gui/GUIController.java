/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import svilproject2014.CommunicationController;
import svilproject2014.Mappatura;
import svilproject2014.Messaggio;
import svilproject2014.Proposta;
import svilproject2014.SistemaDiCifratura;
import svilproject2014.Studente;
import svilproject2014.UserInfo;
import svilproject2014.sessione.Coppia;
import svilproject2014.sessione.GestoreIpotesi;
import svilproject2014.sessione.SessioneDiLavoro;
import svilproject2014.sessione.StrumentiDiSupporto;

/**
 *
 * @author User
 */
public class GUIController {
    /*
     * idea: manteniamo un Controller con la funzione di mantenere al suo interno le variabili e le informazioni d'esecuzione.
     * creiamo varie viste (jpanel x la rappresentazione) e ai tasti facciamo in modo di assegnare funzioni del controller:
     * tipo login -> chiama metodo login del Controller -> il controller effettua il login (lo si collega alla fine) e aggiorna i dati interni di user -> aggiorno la vista (chiamo un qualche update sul jpanel)
     * per le viste direi, insseriamoci un metodo update che viene invocato dal controller dopo ogni modifica, oppure usiamo observer.
     */
    
    //variabili di sistema
    private Studente user;
    private SistemaDiCifratura sdc;
    private Mappatura map;
    private String key;
    private StrumentiDiSupporto sos;
    private GestoreIpotesi g;
    private SessioneDiLavoro sdl;
    private Messaggio msgScrivi, msgLeggi;
    
    public static void main(String[] args){
        Frame f = new Frame();
        f.setGC(new GUIController());
        
    }
    
    
    public GUIController(){

        try {
            user = Studente.getListaStudenti().get(2); //da spostare su una qualche richiesta
        } catch(IndexOutOfBoundsException e) {
            Logger.getLogger(GUIController.class.getName()).log(Level.WARNING,"Nel sistema non sono registrati studenti.");
            //TODO: exit
        }

    }
    
    //UC1
    
    public Messaggio apriMessaggoBozza(String id){
        Messaggio m = Messaggio.load(id);
        //visualizza qualcosa
        return m;
    }
    
    public Messaggio apriMessaggioRicevuto(String id){
        Messaggio m = CommunicationController.apriMessaggioRicevuto(id);
        //visualizza
        return m;
    }
    
    public void cifra(String testo, SistemaDiCifratura sdc){
        String s = sdc.prova(testo);
        //visualizza
        
    }
    
    public void cifraMessaggio(Messaggio m){
        String s = m.cifra();
        //visualizza
        
    }
    
    public boolean comunicaDecisione(Proposta p, String decisione){
        boolean success = CommunicationController.inviaDecisione(p, decisione);
        //visualizza
        return success;
    }
    
    public void decifraMessaggio(Messaggio m){
        String s = m.decifra();
        //visualizza
        
    }
    
    public List<UserInfo> elencaDestinatari(){
        List<UserInfo> l = CommunicationController.getDestinatari(user);
        //visualizza
        return l;
    }
    
    public List<Messaggio> elencaMessaggiBozza(){
        List<Messaggio> l = Messaggio.caricaBozze(user);
        //visualizza
        return l;
    }
    
    public List<Messaggio> elencaMessaggiInviati(){
        List<Messaggio> l = Messaggio.caricaInviati(user);
        //visualizza
        return l;
    }
    
    public List<Messaggio> elencaMessaggiRicevuti(){
        List<Messaggio> l = Messaggio.caricaRicevuti(user);
        //visualizza
        return l;
    }
    
    public void eliminaSistemaCifratura(SistemaDiCifratura sdc){
        boolean success = sdc.elimina();
        //visualizza
        
    }
    
    public Mappatura generaMappatura(String chiave, String metodo){
        key = chiave;
        sdc = new SistemaDiCifratura(chiave, metodo);
        if(sdc!=null){
            sdc.setCreatore(user.getUserInfo());
            map = sdc.getMappatura();
            //visualizza
            return map;
        }
        return null;
    }
    
    public void mostraSceltaChiave(String metodo){
        //visualizza istruzioni di scelta chiave??
        
    }
    
    public void ottieniMetodiCifratura(){
        //i metodi sono
        //cesare chave pseudocasuale
    }
    
    public boolean proponiSistemaCifratura(/*SistemaDiCifratura sdc,*/ UserInfo partner){
            if(!salvaSistemaCifratura(sdc)) return false;
            boolean success = CommunicationController.inviaProposta(user, partner, sdc);
            //visualizza
            return success;
    }
    
    public boolean salvaMessaggioBozza(Messaggio m){
        boolean success = m.salva();
        //visualizza
        return success;
    }
    
    public boolean salvaSistemaCifratura(SistemaDiCifratura sdc){
        boolean success = sdc.salva();
        //visualizza
        return success;
    }
    
    public boolean spedisciMessaggio(Messaggio m){
        String temp = m.cifra();
        if(temp!=null){
            m.setTestoCifrato(temp);
            if(!m.salva())return false;
            return CommunicationController.send(m);
        }
        return false;
    }
    
    public void vediNotificheAccettazioneProposte(){
        List<Proposta>  list = CommunicationController.getAccettazioneProposte(user);
        //visualizza
        
    }
    
    public List<Proposta> vediProposteSistemaCifratura(){
        List<Proposta>  list = CommunicationController.getProposte(user);
        //visualizza
        return list;
    }
    
    public Messaggio visualizzaMessaggioInviato(String id){
        Messaggio m = Messaggio.load(id);
        //visualizza
        return m;
    }
    
    public SistemaDiCifratura visualizzaSistemaCifratura(String id){
        SistemaDiCifratura s = SistemaDiCifratura.load(id);
        //visualizza
        return s;
    }
    
    //UC2
    
    public void aggiornaLingua(int id){
        boolean success = sos.aggiornaLingua(id);
        //visualizza
        
    }
    
    public boolean aggiungiIpotesi(char old, char nuovo){
        boolean success = sdl.getGestoreIpotesi().aggiungiIpotesi(old, nuovo);
        //visualizza
        return success;
    }
    
    public void avviaSessione(Messaggio m){
        sdl = new SessioneDiLavoro(user);
        sdl.setMessaggio(m);
    }
    
    public void caricaSessioneDiLavoro(int id){
        sdl = SessioneDiLavoro.load(id);
        //visualizza
        
    }
    
    public List<String> cercaPatternSulDizionario(String pattern){
        if(sdl==null)return new ArrayList<String>();
        List<String> list = sdl.getStrumentiSupporto().cercaPatternSulDizionario(pattern);
        //visualizza
        return list;
    }
    
    public List<Messaggio> mostraMessaggiSpiabili(){
        List<Messaggio> list = Messaggio.caricaSpiabili(user);
        //visualizza
        return list;
    }
    
    public void salvaSessioneDiLavoro(){
        boolean success = sdl.salva();
        //visualizza
        
    }
    
    public boolean vaiAvantiNelleIpotesi(int n){
        boolean success = sdl.getGestoreIpotesi().avanti(n);
        //visualizza
        return success;
    }
    
    public boolean vaiIndietroNelleIpotesi(int n){
        boolean success = sdl.getGestoreIpotesi().indietro(n);
        //visualizza
        return success;
    }
    
    public void visualizzaAssociazioni(){
        List<Coppia> list = sdl.getGestoreIpotesi().visualizzaAssociazioni();
        //visualizza
        
    }
    
    public String visualizzaIpotesi(){
        String s = sdl.getGestoreIpotesi().visualizzaStoria();
        //visualizza
        return s;
    }
    
    public double visualizzaFrequenza(char c){
        if(sdl==null) return 0;
        return sdl.getStrumentiSupporto().getFrequenza(c);
    }
    
    public Messaggio visualizzaMessaggioSessione(){
        if(sdl==null) return null;
        return sdl.getMessaggio();
    }
    
    public String visualizzaTestoParziale(){
        if(sdl==null) return null;
        return sdl.getGestoreIpotesi().getTestoParziale();
    }
    
    //METODI DI EMULAZIONE
    
    public List<Studente> getListaStudenti(){//esclude dalla lista se stessi
        List<Studente> list = Studente.getListaStudenti();
        //visualizza
        int i = 0;
        while(i<list.size()){
            if(list.get(i).getId().equals(user.getId())) list.remove(i);
            else i++;
        }
        return list;
    }
    
    public void login(Studente s){
        user = s;
        //visualizza
        
    }
    
    public UserInfo getUser(){
        return user.getUserInfo();
    }
    
    public SistemaDiCifratura getSdcAttivo(UserInfo partner){
        return Proposta.caricaAttiva(user.getId(), partner.getId()).getSdc();
    }
    
}
