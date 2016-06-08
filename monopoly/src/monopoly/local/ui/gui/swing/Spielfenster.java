package monopoly.local.ui.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import monopoly.local.domain.Monopoly;
import monopoly.local.valueobjects.Spieler;
import net.miginfocom.swing.MigLayout;

public class Spielfenster {
	//Variablen f�r das Spielfenster
	private JFrame spiel;
	private SButtonPanel sBP;
	private SpielPanel sP;
	private SInfoPanel sIP;
	private Monopoly monopoly;
	private HausFenster haFenster;
	private HypothekFenster hyFenster; 
	
	public Spielfenster(Monopoly monopoly){
		this.monopoly = monopoly;
	}

	public void sInit(){
		//Spielfeldfenster
		spiel = new JFrame("Monopoly");
		sP = new SpielPanel();
		sBP = new SButtonPanel();
		sIP = new SInfoPanel();
		haFenster = new HausFenster(monopoly);
		hyFenster = new HypothekFenster(monopoly);
		spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spiel.setLayout(new MigLayout("", "[]10[]", "[]10[]"));
		
		spiel.add(sP, "cell 0 0, w 600, h 600, push, grow, shrink ");
		spiel.add(sBP, "cell 1 0, push, grow, shrink");
		spiel.add(sIP, "cell 0 1, span 2, pushx, growx, shrinkx");
		
		spiel.getContentPane().setBackground(new Color(0,0,0));
		
		spiel.setSize(600, 600);
		spiel.setVisible(true);
		
		//Info-TextArea mit sich anpassenden Infos... wie macht man das
		
		sBP.getButton1().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int zugweite = monopoly.wuerfel();
				Spieler spieler = monopoly.getTurn().getWerIstDran();
				monopoly.move(spieler, zugweite);
				JOptionPane.showMessageDialog(spiel, "Eggs are not supposed to be green.");
			}
		});
		
		sBP.getButton2().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				spiel.remove(sBP);
				spiel.add(haFenster, "cell 1 0, push, grow, shrink");
				spiel.repaint();
				spiel.revalidate();
			}
		});
		sBP.getButton3().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				spiel.remove(sBP);
				spiel.add(hyFenster, "cell 1 0, push, grow, shrink");
				spiel.repaint();
				spiel.revalidate();
			}
		});
		
		sBP.getButton4().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//monopoly.saveAll();
				JOptionPane.showMessageDialog(spiel, "Eggs are not supposed to be green.");
			}
		});
		
		haFenster.getHaButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Spieler spieler = monopoly.getTurn().getWerIstDran();
				int position = spieler.getSpielerPosition().getNummer();
				if(!haFenster.getHaListe().isSelectionEmpty()){
					monopoly.bauHaus(position, spieler);
					JOptionPane.showMessageDialog(spiel, "Eggs are not supposed to be green.");
					spiel.remove(haFenster);
					spiel.add(sBP, "cell 1 0, push, grow, shrink");
					spiel.repaint();
					spiel.revalidate();
				}
				else{
					spiel.remove(haFenster);
					spiel.add(sBP, "cell 1 0, push, grow, shrink");
					spiel.repaint();
					spiel.revalidate();
				}
			}
		});
		
		hyFenster.getHyButton1().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//if hypothek == false -->if(monopoly.getHypothek == false){
				//}
				Spieler spieler = monopoly.getTurn().getWerIstDran();
				int position = spieler.getSpielerPosition().getNummer();
				if(! hyFenster.getHyListe().isSelectionEmpty()){
					monopoly.switchHypothek(position);
					spiel.remove(hyFenster);
					spiel.add(sBP, "cell 1 0, push, grow, shrink");
					spiel.repaint();
					spiel.revalidate();
					JOptionPane.showMessageDialog(spiel, "Eggs are not supposed to be green.");
				}
				else{
					spiel.remove(hyFenster);
					spiel.add(sBP, "cell 1 0, push, grow, shrink");
					spiel.repaint();
					spiel.revalidate();
				}
			}
		});
		
		hyFenster.getHyButton2().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//if hypothek == true --> if(monopoly.getHypothek == true){
				//monopoly.switchHypothek(position);}
				spiel.remove(hyFenster);
				spiel.add(sBP, "cell 1 0, push, grow, shrink");
				spiel.repaint();
				spiel.revalidate();
				JOptionPane.showMessageDialog(spiel, "Eggs are not supposed to be green.");
			}
		});
	}
}




