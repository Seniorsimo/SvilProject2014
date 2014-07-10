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
/*public class prova{
	public static void main(String[]args){
			new Frame();
  }
}*/
class Frame extends JFrame{
    sceltaJPanel sceltaPanel;
    scriviJPanel scriviPanel;
    gestisciJPanel gestisciPanel;
    JPanel spiaPanel;
    
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
								
					setTitle("Gestisci");
					add(gestisciPanel);
					pack();
			}
    
    public JPanel editLeft(JLabel userlbl,JTextField user,JTextField mex,ArrayList <JButton> btns){
			JPanel mexPanel=new JPanel();
			JPanel panel1=new JPanel();
			JPanel panel2=new JPanel();
			JButton home=new JButton("Home");
			mex.setPreferredSize(new Dimension(300,200));
			home.addActionListener(new Evento("goHome"));
			
			panel1.setLayout(new GridLayout(1,2));
			panel1.add(userlbl);
			panel1.add(user);
			panel2.setLayout(new GridLayout(1,btns.size()+1));
			for(int i=0;i<btns.size();i++)
				panel2.add(btns.get(i));
			panel2.add(home);
			mexPanel.setLayout(new BorderLayout());
			mexPanel.add(panel1,BorderLayout.NORTH);
			mexPanel.add(mex,BorderLayout.CENTER);
			mexPanel.add(panel2,BorderLayout.SOUTH);
			return mexPanel;
		}
    public class scriviJPanel extends JPanel{
				JLabel userlbl=new JLabel("Mittente");
				JTextField user=new JTextField("");
				JTextField messaggio=new JTextField("Contenuto");
				JButton btn=new JButton("Invia");
				/*************************************/
				JButton rnd=new JButton("Random");
				public scriviJPanel(){
						super();
						/*************************************/
						btn.addActionListener(new Evento("InviaMex"));
						ArrayList<JButton> temp=new ArrayList<JButton>();
						temp.add(btn);
						/*************************************/
						JPanel extraPanel=new JPanel();
						extraPanel.setLayout(new GridLayout(2,1));
						extraPanel.add(editCesare());
						rnd.addActionListener(new Evento("RandomPermutazione"));
						extraPanel.add(editPermutazione(rnd));
						/*************************************/
						setLayout(new GridLayout(1,2));
						add(editLeft(userlbl,user,messaggio,temp));
						add(extraPanel);
				}
				public JPanel editCesare(){
					JPanel panel=new JPanel();
					JLabel lbl=new JLabel("Cesare");
					panel.add(lbl);
					return panel;
				}
				public JPanel editPermutazione(JButton rnd){
					JPanel panel=new JPanel();
					JLabel lbl=new JLabel("Permutazione");
					panel.setLayout(new BorderLayout());
					panel.add(lbl,BorderLayout.NORTH);
					panel.add(rnd,BorderLayout.SOUTH);
					return panel;
				}
		}
    public class gestisciJPanel extends JPanel{
				JLabel userlbl=new JLabel("Mittente");
				JTextField user=new JTextField("");
				JTextField messaggio=new JTextField("Contenuto");
				JButton btn=new JButton("Cancella");
				JButton btn2=new JButton("Aggiorna");
				/*************************************/
				public gestisciJPanel(){
						super();
						btn.addActionListener(new Evento("CancellaMex"));
						messaggio.setEditable(false);
						user.setEditable(false);
						ArrayList<JButton> temp=new ArrayList<JButton>();
						temp.add(btn);
						/*************************************/
						JPanel extraPanel=new JPanel();
						extraPanel.setLayout(new BorderLayout());
						extraPanel.add(editInviati(),BorderLayout.NORTH);
						extraPanel.add(editRicevuti(),BorderLayout.CENTER);
						extraPanel.add(btn2,BorderLayout.SOUTH);
						/*************************************/
						setLayout(new GridLayout(1,2));
						add(editLeft(userlbl,user,messaggio,temp));
						add(extraPanel);
				}
				public JPanel editInviati(){
					JPanel panel=new JPanel();
					JLabel lbl=new JLabel("Inviati");
					panel.add(lbl);
					return panel;
				}
				public JPanel editRicevuti(){
					JPanel panel=new JPanel();
					JLabel lbl=new JLabel("Ricevuti");
					panel.add(lbl);
					return panel;
				}
		}
		public class sceltaJPanel extends JPanel{
			JButton button1=new JButton("Scrivi");
			JButton button2=new JButton("Gestisci");
			JButton button3=new JButton("Spia");
			public sceltaJPanel(){
				super();
				button1.addActionListener(new Evento("goScrivi"));
				button2.addActionListener(new Evento("goGestisci"));
				button3.addActionListener(new Evento("goSpia"));
				add(button1);
				add(button2);
				add(button3);
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
								case "goHome":
										if(sceltaPanel==null)
											sceltaPanel=new sceltaJPanel();
										visualizzaScelta();											
								break;
								case "goScrivi":
										if(scriviPanel==null)
											scriviPanel=new scriviJPanel();
										visualizzaScrivi();
								break;
								case "goGestisci":
										if(gestisciPanel==null)
											gestisciPanel=new gestisciJPanel();
										visualizzaGestisci();
								break;
								default:
										System.out.println(action + " Not Found");
								break;

						}
				}
		}
    
}