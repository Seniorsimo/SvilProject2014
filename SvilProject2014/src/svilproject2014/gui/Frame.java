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
/*public class prova{
	public static void main(String[]args){
			new Frame();
  }
}*/
class Frame extends JFrame{
    sceltaJPanel sceltaPanel;
    scriviJPanel scriviPanel;
    gestisciJPanel gestisciPanel;
    spiaJPanel spiaPanel;
    proposteJPanel propostePanel;

    public Frame(){
        super();
        sceltaPanel=new sceltaJPanel();
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
    public JPanel editLeft(JLabel lbl,Component comp,JTextField titolo,JTextField mex,ArrayList <JButton> btns){
        JPanel mexPanel=new JPanel();
        JPanel buttonPanel=new JPanel();
        JButton home=new JButton("Home");
        mex.setPreferredSize(new Dimension(300,200));
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sceltaPanel==null)
                    sceltaPanel=new sceltaJPanel();
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
    public class proposteJPanel extends JPanel{
        JRadioButton opt1 = new JRadioButton("Cifrario di Cesare");
        JRadioButton opt2 = new JRadioButton("Cifrario a Chiave");
        JRadioButton opt3 = new JRadioButton("Cifrario Casuale");
        JButton rnd=new JButton("Random");
        JTextField shift=new JTextField();
        JTextField key=new JTextField();
        JTextField anteprima=new JTextField();
        /**************************************/
        JButton btn2=new JButton("Aggiorna");
        public proposteJPanel(){
            Choice user=new Choice();
            user.add("Max210491");
            user.add("SeniornSimo");
            user.add("NoPuffi");
            ButtonGroup group = new ButtonGroup();
            group.add(opt1);
            group.add(opt2);
            group.add(opt3);
            anteprima.setText("Cesare "+shift.getText());
            opt1.setSelected(true);
            opt1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                                anteprima.setText("Cesare "+shift.getText());
                }});
            opt2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                                anteprima.setText("Chiave "+key.getText());
                }});
            opt3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                                anteprima.setText("Casuale");
                }});
            shift.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                                anteprima.setText("Cesare "+shift.getText());
                                opt1.setSelected(true);
                }});
            key.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                                anteprima.setText("Chiave "+key.getText());
                                opt2.setSelected(true);
                }});
            rnd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                                anteprima.setText("New Random");
                                opt3.setSelected(true);
                }});
            Component[]comp1={editCifrario(opt1,shift),editCifrario(opt2,key),editCifrario(opt3,rnd)};//vert
            JPanel panel=new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(new JLabel("Anteprima"),BorderLayout.NORTH);
            panel.add(anteprima,BorderLayout.CENTER);
            Component[]leftCenter={user,gridVert(comp1),panel,};
            JPanel left=new JPanel();
            left.setLayout(new  BorderLayout());
            left.add(user,BorderLayout.NORTH);
            left.add(gridVert(leftCenter),BorderLayout.CENTER);
            JButton home=new JButton("Home");
            home.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(sceltaPanel==null)
                        sceltaPanel=new sceltaJPanel();
                    visualizzaScelta();
                }});
            Component[]comp={new Button("Invia"),home};
            left.add(gridOrizz(comp),BorderLayout.SOUTH);
            /*************************************/
            Object [][]testo={{"max210491","tilooo1"},{"seniorsimo","tilooo2"}};
            Object []nomi={"ID","Titolo"};
            JTable table1=new JTable(testo,nomi);
            JPanel panel1=new JPanel();
            JPanel panel2=new JPanel();
            panel2.setLayout(new GridLayout(1,1));
            panel2.add(editTabella("Proposte",table1));
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
    }
    public class scriviJPanel extends JPanel{
        JLabel userlbl=new JLabel("Destinatario");
        Choice user=new Choice();
        JTextField messaggio=new JTextField("Contenuto");
        JTextField titolo=new JTextField("Titolo");
        JButton btn=new JButton("Invia");
        /*************************************/
        Object [][]testo={{"max210491","tilooo1"},{"seniorsimo","tilooo2"}};
        Object []nomi={"ID","Titolo"};
        JTable table1=new JTable(testo,nomi);
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        JButton btn2=new JButton("Cancella");
        public scriviJPanel(){
            super();
            /*************************************/
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Inviato a "+user.getSelectedItem()+" Mex: "+messaggio.getText());
                }});
            ArrayList<JButton> temp=new ArrayList<JButton>();
            temp.add(btn);
            user.add("Max210491");
            user.add("SeniornSimo");
            user.add("NoPuffi");
            /*************************************/
            panel2.setLayout(new GridLayout(1,1));
            panel2.add(editTabella("BOZZE",table1));
            panel1.setLayout(new BorderLayout());
            panel1.add(panel2,BorderLayout.NORTH);
            panel1.add(btn2,BorderLayout.SOUTH);   
            /*************************************/
            setLayout(new GridLayout(1,2));
            add(editLeft(userlbl,user,titolo,messaggio,temp));
            add(panel1);
        }
    }
    public class gestisciJPanel extends JPanel{
        JLabel userlbl=new JLabel("Mittente");
        JTextField user=new JTextField("");
        JTextField messaggio=new JTextField("Contenuto");
        JTextField titolo=new JTextField("Titolo");
        JButton btn=new JButton("Cancella");
        /*************************************/
        JButton btn2=new JButton("Aggiorna");
        public gestisciJPanel(){
            super();
            messaggio.setEditable(false);
            user.setEditable(false);
            ArrayList<JButton> temp=new ArrayList<JButton>();
            temp.add(btn);
            /*************************************/
            Object [][]testo={{"max210491","tilooo1"},{"seniorsimo","tilooo2"}};
            Object []nomi={"ID","Titolo"};
            JTable table1=new JTable(testo,nomi);
            JTable table2=new JTable(testo,nomi);
            JPanel extraPanel=new JPanel();
            JPanel panel=new JPanel();
            panel.setLayout(new GridLayout(2,1));
            panel.add(editTabella("Inviati",table1));
            panel.add(editTabella("Ricevuti",table2));
            extraPanel.setLayout(new BorderLayout());
            extraPanel.add(panel,BorderLayout.NORTH);
            extraPanel.add(btn2,BorderLayout.SOUTH);
            
            /*************************************/
            setLayout(new GridLayout(1,2));
            add(editLeft(userlbl,user,titolo,messaggio,temp));
            add(extraPanel);
        }
    }
    public class spiaJPanel extends JPanel{
        JLabel userlbl=new JLabel("Mittente");
        JTextField user=new JTextField("");
        JTextField messaggio=new JTextField("Contenuto");
        JTextField titolo=new JTextField("Titolo");
        JButton btnUndo=new JButton("<-");
        JButton btnRetry=new JButton("->");
        JButton btnAdd=new JButton("+");
        
        JButton btn2=new JButton("Aggiorna");
        /*************************************/
        public spiaJPanel(){
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
    public class sceltaJPanel extends JPanel{
        JButton button1=new JButton("Scrivi");
        JButton button2=new JButton("Gestisci");
        JButton button3=new JButton("Spia");
        JButton button4=new JButton("Proposte");
        public sceltaJPanel(){
            super();
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(scriviPanel==null)
                        scriviPanel=new scriviJPanel();
                    visualizzaScrivi();
                }});
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(gestisciPanel==null)
                        gestisciPanel=new gestisciJPanel();
                    visualizzaGestisci();
                }});
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(spiaPanel==null)
                        spiaPanel=new spiaJPanel();
                    visualizzaSpia();
                }});
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(propostePanel==null)
                                    propostePanel=new proposteJPanel();
                    visualizzaProposte();
                }});
            Component[]temp={button1,button4,button2,button3};
            add(gridOrizz(temp));
        }
    }
}

