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
/*public class prova{
	public static void main(String[]args){
			new Frame();
  }
}*/
public class Frame extends JFrame{
    loginJPanel loginPanel;
    sceltaJPanel sceltaPanel;
    scriviJPanel scriviPanel;
    riceviJPanel riceviPanel;
    JPanel spiaPanel;
    
    public Frame(){
        super();
        loginPanel=new loginJPanel();
        visualizzaLogin();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void visualizzaLogin(){
        if(sceltaPanel!=null)
            remove(sceltaPanel);
        if(scriviPanel!=null)
            remove(scriviPanel);
        if(riceviPanel!=null)
            remove(riceviPanel);	
        if(spiaPanel!=null)
            remove(spiaPanel);	

        setTitle("Login");
        add(loginPanel);
        pack();
    }
    public void visualizzaScelta(){
        if(loginPanel!=null)
            remove(loginPanel);
        if(scriviPanel!=null)
            remove(scriviPanel);
        if(riceviPanel!=null)
            remove(riceviPanel);
        if(spiaPanel!=null)
            remove(spiaPanel);				

        setTitle("Scelta");
        add(sceltaPanel);
        pack();
    }
    public void visualizzaScrivi(){
        if(loginPanel!=null)
            remove(loginPanel);
        if(sceltaPanel!=null)
            remove(sceltaPanel);
        if(riceviPanel!=null)
            remove(riceviPanel);
        if(spiaPanel!=null)
            remove(spiaPanel);
        setTitle("Scrivi");
        add(scriviPanel);
        pack();
    }
    public void visualizzaRicevi(){
        if(loginPanel!=null)
            remove(loginPanel);
        if(sceltaPanel!=null)
            remove(sceltaPanel);
        if(scriviPanel!=null)
            remove(scriviPanel);
        if(spiaPanel!=null)
            remove(spiaPanel);

        setTitle("Ricevi");
        add(riceviPanel);
        pack();
    }
    public class loginJPanel extends JPanel{
        JButton button=new JButton("Login");
        public loginJPanel(){
            super();
            add(button);
            button.addActionListener(new Evento("goScegli"));
        }
    }
    public class sceltaJPanel extends JPanel{
        JButton button1=new JButton("Scrivi");
        JButton button2=new JButton("Ricevi");
        JButton button3=new JButton("Spia");
        public sceltaJPanel(){
            super();
            button1.addActionListener(new Evento("goScrivi"));
            button2.addActionListener(new Evento("goRicevi"));
            button3.addActionListener(new Evento("goSpia"));
            add(button1);
            add(button2);
            add(button3);
        }
    }
    public class scriviJPanel extends JPanel{
        JPanel northPanel=new JPanel();
        JPanel centerPanel=new JPanel();
        JTextField messaggio=new JTextField("Contenuto");
        JPanel southPanel=new JPanel();
        JButton home=new JButton("Home");
        JButton invia=new JButton("Invia");
        public scriviJPanel(){
            super();
            setLayout(new BorderLayout());
            northPanel.add(new JLabel("Destinatario"));
            messaggio.setPreferredSize(new Dimension(300,200));
            centerPanel.add(messaggio);
            home.addActionListener(new Evento("goScegli"));
            southPanel.add(invia);
            southPanel.add(home);
            add(northPanel,BorderLayout.NORTH);
            add(centerPanel,BorderLayout.CENTER);					
            add(southPanel,BorderLayout.SOUTH);
        }
    }
    public class riceviJPanel extends JPanel{
        JPanel northPanel=new JPanel();
        JPanel centerPanel=new JPanel();
        JTextField messaggio=new JTextField("Contenuto");
        JPanel eastPanel=new JPanel();
        JLabel lista=new JLabel("Lista Messaggi");
        JPanel southPanel=new JPanel();
        JButton home=new JButton("Home");
        public riceviJPanel(){
            super();
            setLayout(new BorderLayout());
            northPanel.add(new JLabel("Mittente:"));
            messaggio.setPreferredSize(new Dimension(300,200));
            messaggio.setEditable(false);
            messaggio.setBackground(new Color(255,255,255));
            centerPanel.add(messaggio);
            eastPanel.add(lista);
            home.addActionListener(new Evento("goScegli"));
            southPanel.add(home);
            add(northPanel,BorderLayout.NORTH);
            add(centerPanel,BorderLayout.CENTER);
            add(eastPanel,BorderLayout.EAST);					
            add(southPanel,BorderLayout.SOUTH);
        }
    }
    public class Evento implements ActionListener{
        String action;
        public Evento(String actionI){
            action=actionI;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (action){
                case "goScegli":
                    if(sceltaPanel==null)
											sceltaPanel=new sceltaJPanel();
										visualizzaScelta();											
                break;
                case "goScrivi":
                    if(scriviPanel==null)
											scriviPanel=new scriviJPanel();
										visualizzaScrivi();
                break;
                case "goRicevi":
                    if(riceviPanel==null)
											riceviPanel=new riceviJPanel();
										visualizzaRicevi();
                break;
                default:
                    System.out.println(action + " Not Found");
                break;

            }
        }
    }
    
}
