/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package svilproject2014.gui;

import java.util.List;
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
    
    
    public GUIController(){
        //vuoto
    }
    
    //UC1
    
    public void apriMessaggoBozza(String id){
        Messaggio m = Messaggio.load(id);
        //visualizza qualcosa
        
    }
    
    public void apriMessaggioRicevuto(String id){
        Messaggio m = CommunicationController.apriMessaggioRicevuto(id);
        //visualizza
        
    }
    
    public void cifra(String testo, SistemaDiCifratura sdc){
        String s = sdc.prova(testo);
        //visualizza
        
    }
    
    public void cifraMessaggio(Messaggio m){
        String s = m.cifra();
        //visualizza
        
    }
    
    public void comunicaDecisione(Proposta p, String decisione){
        boolean success = CommunicationController.inviaDecisione(p, decisione);
        //visualizza
        
    }
    
    public void decifraMessaggio(Messaggio m){
        String s = m.decifra();
        //visualizza
        
    }
    
    public void elencaDestinatari(){
        List<UserInfo> l = CommunicationController.getDestinatari(user);
        //visualizza
        
    }
    
    public void elencaMessaggiBozza(){
        List<Messaggio> l = Messaggio.caricaBozze(user);
        //visualizza
        
    }
    
    public void elencaMessaggiInviati(){
        List<Messaggio> l = Messaggio.caricaInviati(user);
        //visualizza
        
    }
    
    public void elencaMessaggiRicevuti(){
        List<Messaggio> l = Messaggio.caricaRicevuti(user);
        //visualizza
        
    }
    
    public void eliminaSistemaCifratura(SistemaDiCifratura sdc){
        boolean success = sdc.elimina();
        //visualizza
        
    }
    
    public void generaMappatura(String chiave, String metodo){
        key = chiave;
        sdc = new SistemaDiCifratura(chiave, metodo);
        map = sdc.getMappatura();
        //visualizza
        
    }
    
    public void mostraSceltaChiave(String metodo){
        //visualizza istruzioni di scelta chiave??
        
    }
    
    public void ottieniMetodiCifratura(){
        //i metodi sono
        //cesare chave pseudocasuale
    }
    
    public void proponiSistemaCifratura(SistemaDiCifratura sdc, UserInfo partner){
        boolean success = CommunicationController.inviaProposta(user, partner, sdc);
        //visualizza
        
    }
    
    public void salvaMessaggioBozza(Messaggio m){
        boolean success = m.salva();
        //visualizza
        
    }
    
    public void salvaSistemaCifratura(SistemaDiCifratura sdc){
        boolean success = sdc.salva();
        //visualizza
        
    }
    
    public void spedisciMessaggio(Messaggio m){
        boolean success = CommunicationController.send(m);
        //visualizza
        
    }
    
    public void vediNotificheAccettazioneProposte(){
        List<Proposta>  list = CommunicationController.getAccettazioneProposte(user);
        //visualizza
        
    }
    
    public void vediProposteSistemaCifratura(){
        List<Proposta>  list = CommunicationController.getProposte(user);
        //visualizza
        
    }
    
    public void visualizzaMessaggioInviato(String id){
        Messaggio m = Messaggio.load(id);
        //visualizza
        
    }
    
    public void visualizzaSistemaCifratura(String id){
        SistemaDiCifratura s = SistemaDiCifratura.load(id);
        //visualizza
        
    }
    
    //UC2
    
    public void aggiornaLingua(int id){
        boolean success = sos.aggiornaLingua(id);
        //visualizza
        
    }
    
    public void aggiungiIpotesi(char old, char nuovo){
        boolean success = g.aggiungiIpotesi(old, nuovo);
        //visualizza
        
    }
    
    public void caricaSessioneDiLavoro(int id){
        sdl = SessioneDiLavoro.load(id);
        //visualizza
        
    }
    
    public void cercaPatternSulDizionario(String pattern){
        List<String> list = sos.cercaPatternSulDizionario(pattern);
        //visualizza
        
    }
    
    public void salvaSessioneDiLavoro(){
        boolean success = sdl.salva();
        //visualizza
        
    }
    
    public void vaiAvantiNelleIpotesi(int n){
        boolean success = g.avanti(n);
        //visualizza
        
    }
    
    public void vaiIndietroNelleIpotesi(int n){
        boolean success = g.indietro(n);
        //visualizza
        
    }
    
    public void visualizzaAssociazioni(){
        List<Coppia> list = g.visualizzaAssociazioni();
        //visualizza
        
    }
    
    public void visualizzaIpotesi(){
        Coppia c = g.visualizzaStoria();
        //visualizza
        
    }
    
    //METODI DI EMULAZIONE
    
    public void getListaStudenti(){
        List<Studente> list = Studente.gelListaStudenti();
        //visualizza
        
    }
    
}
