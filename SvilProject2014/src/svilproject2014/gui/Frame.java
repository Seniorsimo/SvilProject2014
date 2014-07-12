/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import svilproject2014.sessione.StrumentiDiSupporto;

    
import java.awt.event.*;
import java.awt.*;
import java.awt.Color.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import javax.swing.event.*;
import svilproject2014.messaggio.MessaggioReale;
/*public class prova{
	public static void main(String[]args){
			new Frame();
  }
}*/
class Frame extends JFrame{
    SceltaJPanel sceltaPanel;
    ScriviJPanel scriviPanel;
    GestisciJPanel gestisciPanel;
    SpiaJPanel spiaPanel;
    ProposteJPanel propostePanel;
    int idPanelVisualizzato = 0;
    GUIController gc;
    
    public void setGC(GUIController g){
        gc = g;
    }

    public Frame(){
        super();
        sceltaPanel=new SceltaJPanel();
        visualizzaScelta();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void visualizzaScelta(){
        if(scriviPanel!=null)
            remove(scriviPanel);
        if(gestisciPanel!=null)
            remove(gestisciPanel);
        if(spiaPanel!=null)
            remove(spiaPanel);
        if(propostePanel!=null)
            remove(propostePanel);
        setTitle("Scelta");
        add(sceltaPanel);
        idPanelVisualizzato = 0;
        pack();
    }
    public void visualizzaScrivi(){
        if(sceltaPanel!=null)
            remove(sceltaPanel);
        if(gestisciPanel!=null)
            remove(gestisciPanel);
        if(spiaPanel!=null)
            remove(spiaPanel);
        if(propostePanel!=null)
            remove(propostePanel);
        setTitle("Scrivi");
        add(scriviPanel);
        idPanelVisualizzato = 1;
        pack();
    }
    public void visualizzaGestisci(){
        if(sceltaPanel!=null)
            remove(sceltaPanel);
        if(scriviPanel!=null)
            remove(scriviPanel);
        if(spiaPanel!=null)
            remove(spiaPanel);
        if(propostePanel!=null)
            remove(propostePanel);

        setTitle("Gestisci");
        add(gestisciPanel);
        idPanelVisualizzato = 2;
        pack();
    }
    public void visualizzaSpia(){
        if(sceltaPanel!=null)
            remove(sceltaPanel);
        if(scriviPanel!=null)
            remove(scriviPanel);
        if(gestisciPanel!=null)
            remove(gestisciPanel);
        if(propostePanel!=null)
            remove(propostePanel);

        setTitle("Spia");
        add(spiaPanel);
        idPanelVisualizzato = 3;
        pack();
    }
    public void visualizzaProposte(){
        if(sceltaPanel!=null)
            remove(sceltaPanel);
        if(scriviPanel!=null)
            remove(scriviPanel);
        if(gestisciPanel!=null)
            remove(gestisciPanel);
        if(spiaPanel!=null)
            remove(spiaPanel);

        setTitle("Proposte");
        add(propostePanel);
        idPanelVisualizzato = 4;
        pack();
    }
    public JPanel gridOrizz(Component[]comp){
            JPanel panel=new JPanel();
            panel.setLayout(new GridLayout(1,comp.length));
            for(int i=0;i<comp.length;i++)
                    panel.add(comp[i]);
            return panel;
    }
    public JPanel gridVert(Component[]comp){
            JPanel panel=new JPanel();
            panel.setLayout(new GridLayout(comp.length,1));
            for(int i=0;i<comp.length;i++)
                    panel.add(comp[i]);
            return panel;
    }
    public JPanel borderVert(Component north,Component center,Component south){
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        if(north!=null)
            panel.add(north,BorderLayout.NORTH);
        if(center!=null)
            panel.add(center,BorderLayout.CENTER);
        if(south!=null)
            panel.add(south,BorderLayout.SOUTH);
        return panel;
    }
    public JPanel editLeft(JLabel lbl,Component comp,JTextField titolo,JTextArea mex,ArrayList <JButton> btns){
        JPanel mexPanel=new JPanel();
        JPanel buttonPanel=new JPanel();
        JButton home=new JButton("Home");
        mex.setPreferredSize(new Dimension(300,200));
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //se ti trovi in send o spia chiedi salvataggio
                if(idPanelVisualizzato==1&&scriviPanel.modificato){//sto uscendo da send
                        int risp = DialogMessage.popupYesNo("Vuoi salvare una bozza?", null);
                        if(risp==JOptionPane.YES_OPTION){
                            scriviPanel.salva();
                            scriviPanel.nuovo();
                        }
                        else if(risp==JOptionPane.CANCEL_OPTION||risp==JOptionPane.CLOSED_OPTION){
                            return;
                        }
                }
                else if(idPanelVisualizzato==3){//sto uscendo da spia
                    
                }
                
                
                if(sceltaPanel==null)
                    sceltaPanel=new SceltaJPanel();
                visualizzaScelta();
            }});
        Component[]comp1={lbl,comp};//orizz
        Component[]comp2={new JLabel("Titolo"),titolo};//orizz
        Component[]comp3={gridOrizz(comp1),gridOrizz(comp2)};//vert
                
        buttonPanel.setLayout(new GridLayout(1,btns.size()+1));
        for(int i=0;i<btns.size();i++)
            buttonPanel.add(btns.get(i));
        buttonPanel.add(home);
        mexPanel.setLayout(new BorderLayout());
        mexPanel.add(gridVert(comp3),BorderLayout.NORTH);
        mexPanel.add(mex,BorderLayout.CENTER);
        mexPanel.add(buttonPanel,BorderLayout.SOUTH);
        return mexPanel;
    }
    public JPanel editTabella(String txt,JTable table){
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel(txt),BorderLayout.NORTH);
        panel.add(table,BorderLayout.CENTER);
        return panel;
    }
    public class ProposteJPanel extends JPanel{
        JRadioButton opt1 = new JRadioButton("Cifrario di Cesare");
        JRadioButton opt2 = new JRadioButton("Cifrario a Chiave");
        JRadioButton opt3 = new JRadioButton("Cifrario Casuale");
        JButton rnd=new JButton("Random");
        JTextField shift=new JTextField();
        JTextField key=new JTextField();
        JTextArea anteprima=new JTextArea();
        final Choice user=new Choice();
        
        List<Studente> studenti;
        List<Proposta> proposte;
        JTable table1;
        Object []nomi={"ID","Metodo","Chiave"};
        JPanel panel2=new JPanel();
        /**************************************/
        JButton btn2=new JButton("Aggiorna");
        //!!! Aggiungere refresh proposte: forse è più faciel richreare il panel, dato che bisogna ricreare la tabella.
        
        public ProposteJPanel(){
            
//            user.add("Max210491");
//            user.add("SeniornSimo");
//            user.add("NoPuffi");
            studenti = gc.getListaStudenti();
            for(Studente s:studenti){
                user.add(s.getNome()+" "+s.getCognome());
            }
            
            ButtonGroup group = new ButtonGroup();
            group.add(opt1);
            group.add(opt2);
            group.add(opt3);
            //anteprima.setText("Cesare "+shift.getText());
            Mappatura map = gc.generaMappatura(shift.getText(), "cesare");
            anteprima.setText("Metodo: cesare\nChiave: " + shift.getText() + "\n\nMappatura:\n" + String.copyValueOf(map.getMappa()) + "\n\nMappatura inversa:\n" + String.copyValueOf(map.getMappaInversa()));
            
            opt1.setSelected(true);
            opt1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Mappatura map = gc.generaMappatura(shift.getText(), "cesare");
                    anteprima.setText("Metodo: cesare\nChiave: " + shift.getText() + "\n\nMappatura:\n" + String.copyValueOf(map.getMappa()) + "\n\nMappatura inversa:\n" + String.copyValueOf(map.getMappaInversa()));
                }});
            opt2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Mappatura map = gc.generaMappatura(key.getText(), "chiave");
                    anteprima.setText("Metodo: chiave\nChiave: " + key.getText() + "\n\nMappatura:\n" + String.copyValueOf(map.getMappa()) + "\n\nMappatura inversa:\n" + String.copyValueOf(map.getMappaInversa()));
                }});
            opt3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String k = (int)(Math.random()*100000) + "";
                    Mappatura map = gc.generaMappatura(k, "casuale");
                    anteprima.setText("Metodo: casuale\nChiave: ???\n\nMappatura:\n" + String.copyValueOf(map.getMappa()) + "\n\nMappatura inversa:\n" + String.copyValueOf(map.getMappaInversa()));
                }});
            shift.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Mappatura map = gc.generaMappatura(shift.getText(), "cesare");
                    anteprima.setText("Metodo: cesare\nChiave: " + shift.getText() + "\n\nMappatura:\n" + String.copyValueOf(map.getMappa()) + "\n\nMappatura inversa:\n" + String.copyValueOf(map.getMappaInversa()));
                    opt1.setSelected(true);
                }});
            key.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Mappatura map = gc.generaMappatura(key.getText(), "chiave");
                    anteprima.setText("Metodo: chiave\nChiave: " + key.getText() + "\n\nMappatura:\n" + String.copyValueOf(map.getMappa()) + "\n\nMappatura inversa:\n" + String.copyValueOf(map.getMappaInversa()));
                    opt2.setSelected(true);
                }});
            rnd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String k = (int)(Math.random()*100000) + "";
                    Mappatura map = gc.generaMappatura(k, "casuale");
                    anteprima.setText("Metodo: casuale\nChiave: ???\n\nMappatura:\n" + String.copyValueOf(map.getMappa()) + "\n\nMappatura inversa:\n" + String.copyValueOf(map.getMappaInversa()));
                    opt3.setSelected(true);
                }});
            Component[]comp1={editCifrario(opt1,shift),editCifrario(opt2,key),editCifrario(opt3,rnd)};//vert
            JPanel panel=new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(new JLabel("Anteprima"),BorderLayout.NORTH);
            panel.add(anteprima,BorderLayout.CENTER);
            Component[]leftCenter={/*user,*/gridVert(comp1),panel,};
            JPanel left=new JPanel();
            left.setLayout(new  BorderLayout());
            left.add(user,BorderLayout.NORTH);
            left.add(gridVert(leftCenter),BorderLayout.CENTER);
            JButton home=new JButton("Home");
            home.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(sceltaPanel==null)
                        sceltaPanel=new SceltaJPanel();
                    visualizzaScelta();
                }});
            JButton invia = new JButton("Invia");
            invia.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean success = gc.proponiSistemaCifratura(studenti.get(user.getSelectedIndex()).getUserInfo());
                    String out;
                    if(success) out="Proposta di un sistema di cifratura inviata correttamente.";
                    else out="Si è verificato un errore. Impossibile inviare la proposta.";
                    DialogMessage.popupTesto(out);
                }});
            btn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean success = carica();
                }});
            Component[]comp={invia,home};
            left.add(gridOrizz(comp),BorderLayout.SOUTH);
            /*************************************/
            
            
            //table1 = new JTable(testo,nomi);
            panel2.setLayout(new GridLayout(1,1));
            carica();
            JPanel panel1=new JPanel();
            
            //panel2.add(editTabella("Proposte",table1));
            panel1.setLayout(new BorderLayout());
            panel1.add(panel2,BorderLayout.NORTH);
            panel1.add(btn2,BorderLayout.SOUTH);
            /*************************************/
            setLayout(new GridLayout(1,2));
            add(left);
            add(panel1);
        }
         public JPanel editCifrario(JRadioButton opt,Component obj){
            Component[]comp={opt,obj};
            return gridVert(comp);
        }
         
         public boolean carica(){
            proposte = gc.vediProposteSistemaCifratura();
            List<Object[]> pListTemp = new ArrayList<Object[]>();
            for(Proposta p:proposte){
                UserInfo u = UserInfo.load(p.getIdProponente());
                Object[] pTemp = {u.getNome()+" "+u.getCognome(),p.getSdc().getMetodo(),p.getSdc().getChiave()};
                pListTemp.add(pTemp);
            }
            
            //Object [][]testo={{"max210491","tilooo1"},{"seniorsimo","tilooo2"}};
            Object[][] testo = new Object[pListTemp.size()][];
            int index = 0;
            for(Object[] o:pListTemp){
                testo[index++] = o;
            }
            
            
            table1 = new JTable(testo,nomi);
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //if (e.getClickCount() == 2) {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow();
                        //int column = target.getSelectedColumn();
                        // do some action if appropriate column
                        carica();
                        int r = DialogMessage.popupYesNo("Vuoi accettare questa proposta?", "Proposta");
                        if(r==JOptionPane.YES_OPTION){
//                            proposte.get(row).setStato("accepted");
//                            proposte.get(row).salva();
                            gc.comunicaDecisione(proposte.get(row), "accepted");
                        }
                        else if(r==JOptionPane.NO_OPTION){
//                            proposte.get(row).setStato("refused");
//                            proposte.get(row).salva();
                            gc.comunicaDecisione(proposte.get(row), "refused");
                        }
//                        opt1.setSelected(true);
//                        if(user.getItemCount()!=0){
//                            user.select(0);
//                        }
                    //}
                }
            });
            
            panel2.removeAll();
            panel2.add(editTabella("PROPOSTE RICEVUTE",table1));
            panel2.repaint();
            panel2.revalidate();
            
            return true;
        }
    }
    public class ScriviJPanel extends JPanel{
        JLabel userlbl=new JLabel("Destinatario");
        Choice sceltaDestinatario=new Choice();
        JTextArea messaggio=new JTextArea("Testo");
        JTextField titolo=new JTextField("Titolo");
        JButton btn=new JButton("Invia");
        JButton nuovo=new JButton("Nuovo");
        List<UserInfo> destinatari;
        Messaggio msgScrivi;
        boolean modificato = false;
        JPanel tabella;
        JTable table1;
        /*************************************/
        List<Messaggio> bozze;
        
        //Object [][]testo={{"max210491","tilooo1"},{"seniorsimo","tilooo2"}};
        Object []nomi={"Destinatario","Titolo"};
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        JButton btn2=new JButton("Cancella");
        
        public ScriviJPanel(){
            super();
            /*************************************/
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean success = send();
                    visualizzaScrivi();
                }});
            nuovo();
            ArrayList<JButton> temp=new ArrayList<JButton>();
            temp.add(btn);
//            user.add("Max210491");
//            user.add("SeniornSimo");
//            user.add("NoPuffi");
            nuovo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salva();
                    boolean success = nuovo();
                    visualizzaScrivi();
                }});
            temp.add(nuovo);
            sceltaDestinatario.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e) {
                    modificato = true;
                }
            });
            titolo.addCaretListener(new CaretListener(){
                @Override
                public void caretUpdate(CaretEvent e) {
                    modificato = true;
                }
            });
            messaggio.addCaretListener(new CaretListener(){
                @Override
                public void caretUpdate(CaretEvent e) {
                    modificato = true;
                }
            });
            
            /*************************************/
            panel2.setLayout(new GridLayout(1,1));
            carica();
            btn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean success = cancella();
                    //visualizzaScrivi();
                }});
            
            //=new JTable(testo,nomi);
            //panel2.removeAll();
            //panel2.add(editTabella("BOZZE",table1));
            
            
            //panel2.add(tabella);
            panel1.setLayout(new BorderLayout());
            panel1.add(panel2,BorderLayout.NORTH);
            panel1.add(btn2,BorderLayout.SOUTH);   
            /*************************************/
            setLayout(new GridLayout(1,2));
            add(editLeft(userlbl,sceltaDestinatario,titolo,messaggio,temp));
            add(panel1);
        }
        
        public boolean nuovo(){
            destinatari = gc.elencaDestinatari();
            sceltaDestinatario.removeAll();
            for(UserInfo s:destinatari){
                sceltaDestinatario.add(s.getNome()+" "+s.getCognome());
            }
            msgScrivi = null;
            titolo.setText("Titolo");
            messaggio.setText("Testo");
            if(sceltaDestinatario.getItemCount()!=0){
                sceltaDestinatario.select(0);
            }
            modificato = false;
            carica();
            return true;
        }
        
        public boolean salva(){
            if(!modificato) return false;
            if(sceltaDestinatario.getItemCount()==0) return false;
            
            if(msgScrivi==null){
                msgScrivi = new MessaggioReale(gc.getUser());
            }
            msgScrivi.setTitolo(titolo.getText());
            msgScrivi.setTesto(messaggio.getText());
            
            msgScrivi.setSisCif(gc.getSdcAttivo(destinatari.get(sceltaDestinatario.getSelectedIndex())));
            msgScrivi.setDestinatario(destinatari.get(sceltaDestinatario.getSelectedIndex()));
            
            msgScrivi.setLingua("Italiano");
            modificato = false;
            return gc.salvaMessaggioBozza(msgScrivi);
        }
        
        public void carica(){
            bozze = gc.elencaMessaggiBozza();
            List<Object[]> sListTemp = new ArrayList<Object[]>();
            for(Messaggio m:bozze){
                Object[] mTemp = {m.getDestinatario().getNome() + " " + m.getDestinatario().getCognome(),m.getTitolo()};
                sListTemp.add(mTemp);
            }
            Object[][] testo = new Object[sListTemp.size()][];
            int index = 0;
            for(Object[] o:sListTemp){
                testo[index++] = o;
            }
            
            
            table1 = new JTable(testo,nomi);
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //if (e.getClickCount() == 2) {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow();
                        //int column = target.getSelectedColumn();
                        // do some action if appropriate column
                        salva();
                        carica();
                        msgScrivi = bozze.get(row);
                        msgScrivi = gc.apriMessaggoBozza(msgScrivi.getId());
                        titolo.setText(msgScrivi.getTitolo());
                        messaggio.setText(msgScrivi.getTesto());
                        String id = msgScrivi.getDestinatario().getId();
                        for(UserInfo u : destinatari){
                            if(u.getId().equals(id)) sceltaDestinatario.select(destinatari.indexOf(u));
                        }
                        modificato = false;
                        
                    //}
                }
            });
            
            panel2.removeAll();
            panel2.add(editTabella("BOZZE",table1));
            panel2.repaint();
            panel2.revalidate();
        }
        
        public boolean send(){
            if(!salva()&&msgScrivi==null){
                DialogMessage.popupTesto("Impossibile inviare un messaggio vuoto.");
                return false;
            }
            if(!gc.spedisciMessaggio(msgScrivi)){
                DialogMessage.popupTesto("Impossibile inviare il messaggio");
                return false;
            }
            carica();
            return true;
        }
        
        public boolean cancella(){
            if(msgScrivi==null){
                DialogMessage.popupTesto("Selezionare un messaggio");
                return false;
            }
            //chiede conferma
            int out = DialogMessage.popupYesNo("Cancellare "+titolo.getText()+"?", "Cancella");
            boolean success = false;
            if(out==JOptionPane.YES_OPTION){
                success = msgScrivi.elimina();
                if(!success) DialogMessage.popupTesto("Impossibile eliminare il messaggio");
            }
            carica();
            nuovo();
            return success;
        }
    }
    public class GestisciJPanel extends JPanel{
        JLabel userlbl=new JLabel("");
        JTextField user=new JTextField("");
        JTextArea messaggio=new JTextArea("Contenuto");
        JTextField titolo=new JTextField("Titolo");
        JButton btn=new JButton("Cancella");
        Messaggio msgLeggi;
        /*************************************/
        JButton btn2=new JButton("Aggiorna");
        List<Messaggio> inviati,ricevuti;
        JTable table1,table2;
        Object [][]testo={{"max210491","tilooo1"},{"seniorsimo","tilooo2"}};
        Object []nomi={"ID","Titolo"};
        JPanel panel=new JPanel();
        public GestisciJPanel(){
            super();
            messaggio.setEditable(false);
            user.setEditable(false);
            titolo.setEditable(false);
            ArrayList<JButton> temp=new ArrayList<JButton>();
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean success = cancella();
                    //visualizzaScrivi();
                }});
            btn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    carica();
                    //visualizzaScrivi();
                }});
            temp.add(btn);
            /*************************************/
            
            table1=new JTable(testo,nomi);
            table2=new JTable(testo,nomi);
            JPanel extraPanel=new JPanel();
            
            panel.setLayout(new GridLayout(2,1));
            carica();
            //panel.add(editTabella("Inviati",table1));
            //panel.add(editTabella("Ricevuti",table2));
            extraPanel.setLayout(new BorderLayout());
            extraPanel.add(panel,BorderLayout.NORTH);
            extraPanel.add(btn2,BorderLayout.SOUTH);
            
            /*************************************/
            setLayout(new GridLayout(1,2));
            add(editLeft(userlbl,user,titolo,messaggio,temp));
            add(extraPanel);
        }
        
        public void carica(){
            inviati = gc.elencaMessaggiInviati();
            List<Object[]> sListTemp = new ArrayList<Object[]>();
            for(Messaggio m:inviati){
                Object[] mTemp = {m.getDestinatario().getNome() + " " + m.getDestinatario().getCognome(),m.getTitolo()};
                sListTemp.add(mTemp);
            }
            Object[][] testo = new Object[sListTemp.size()][];
            int index = 0;
            for(Object[] o:sListTemp){
                testo[index++] = o;
            }
            
            
            table1 = new JTable(testo,nomi);
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //if (e.getClickCount() == 2) {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow();
                        //int column = target.getSelectedColumn();
                        // do some action if appropriate column
                        msgLeggi = inviati.get(row);
                        msgLeggi = gc.visualizzaMessaggioInviato(msgLeggi.getId());
                        titolo.setText(msgLeggi.getTitolo());
                        messaggio.setText(msgLeggi.getTesto());
                        String id = msgLeggi.getDestinatario().getId();
                        UserInfo mi = UserInfo.load(id);
                        user.setText(mi.getNome() + " " + mi.getCognome());
                        userlbl.setText("Destinatario");
                        
                    //}
                }
            });
            
            ricevuti = gc.elencaMessaggiRicevuti();
            List<Object[]> sListTemp2 = new ArrayList<Object[]>();
            for(Messaggio m:ricevuti){
                Object[] mTemp = {m.getDestinatario().getNome() + " " + m.getDestinatario().getCognome(),m.getTitolo()};
                sListTemp2.add(mTemp);
            }
            Object[][] testo2 = new Object[sListTemp2.size()][];
            int index2 = 0;
            for(Object[] o:sListTemp2){
                testo2[index2++] = o;
            }
            
            
            table2 = new JTable(testo2,nomi);
            table2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //if (e.getClickCount() == 2) {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow();
                        //int column = target.getSelectedColumn();
                        // do some action if appropriate column
                        msgLeggi = ricevuti.get(row);
                        msgLeggi = gc.apriMessaggioRicevuto(msgLeggi.getId());
                        titolo.setText(msgLeggi.getTitolo());
                        messaggio.setText(msgLeggi.getTesto());
                        String id = msgLeggi.getMittente().getId();
                        UserInfo mi = UserInfo.load(id);
                        user.setText(mi.getNome() + " " + mi.getCognome());
                        userlbl.setText("Mittente");
                    //}
                }
            });
            
            panel.removeAll();
            panel.add(editTabella("Inviati",table1));
            panel.add(editTabella("Ricevuti",table2));
            panel.repaint();
            panel.revalidate();
        }
        
        public boolean cancella(){
            if(msgLeggi==null){
                DialogMessage.popupTesto("Selezionare un messaggio");
                return false;
            }
            //chiede conferma
            int out = DialogMessage.popupYesNo("Cancellare "+titolo.getText()+"?", "Cancella");
            boolean success = false;
            if(out==JOptionPane.YES_OPTION){
                success = msgLeggi.elimina();
                if(!success) DialogMessage.popupTesto("Impossibile eliminare il messaggio");
            }
            carica();
            return success;
        }
    }
    public class SpiaJPanel extends JPanel{
        JLabel userlbl=new JLabel("Mittente");
        JTextField user=new JTextField("");
        JTextArea messaggio=new JTextArea("Contenuto");
        JTextField titolo=new JTextField("Titolo");
        JButton btnUndo=new JButton("<-");
        JButton btnRetry=new JButton("->");
        JButton btnAdd=new JButton("+");
        
        JButton btn2=new JButton("Aggiorna");
        /*************************************/
        public SpiaJPanel(){
            super();
            messaggio.setEditable(false);
            user.setEditable(false);
            ArrayList<JButton> temp=new ArrayList<JButton>();
            temp.add(btnUndo);
            temp.add(btnRetry);
            temp.add(btnAdd);
            /*************************************/
            Object [][]testo={{"max210491"},{"seniorsimo"}};
            Object []nomi={"ID"};
            JTextField pattern=new JTextField();
            JButton find=new JButton("Cerca");
            JTable table=new JTable(testo,nomi);
            table.setPreferredSize(new Dimension(300,200));
            Component[]comp={pattern,find};
            JPanel patternPanel=borderVert(gridOrizz(comp),table,null);
            
            JTextField mapping=new JTextField("a|B B|c");
            
            JButton freq=new JButton("Frequenze");
            freq.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DialogMessage.popupTesto("Help Frequenze");
                    System.out.println(DialogMessage.popupYesNo("Help Frequenze","YESNO"));
                    System.out.println(DialogMessage.popupString("Help Frequenze","Input"));
                    String[]opt={"palla","pollo"};
                    System.out.println(DialogMessage.popupChoice("Help Frequenze","Input",opt));
                }});
            JButton grafo=new JButton("Grafo");
            Component[] comp2={freq,grafo};

            /*************************************/
            setLayout(new GridLayout(1,2));
            add(editLeft(userlbl,user,titolo,messaggio,temp));
            add(borderVert(patternPanel,mapping,gridOrizz(comp2)));
        }
    }
    public class SceltaJPanel extends JPanel{
        JButton button1=new JButton("Scrivi");
        JButton button2=new JButton("Gestisci");
        JButton button3=new JButton("Spia");
        JButton button4=new JButton("Proposte");
        public SceltaJPanel(){
            super();
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(scriviPanel==null)
                        scriviPanel=new ScriviJPanel();
                    visualizzaScrivi();
                }});
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(gestisciPanel==null)
                        gestisciPanel=new GestisciJPanel();
                    visualizzaGestisci();
                }});
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(spiaPanel==null)
                        spiaPanel=new SpiaJPanel();
                    visualizzaSpia();
                }});
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(propostePanel==null)
                                    propostePanel=new ProposteJPanel();
                    visualizzaProposte();
                }});
            Component[]temp={button1,button4,button2,button3};
            add(gridOrizz(temp));
        }
    }
}

