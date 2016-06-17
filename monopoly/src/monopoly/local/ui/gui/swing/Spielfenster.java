package monopoly.local.ui.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import monopoly.local.domain.Monopoly;
import monopoly.local.ui.gui.swing.Menue.Menuefenster;
import monopoly.local.valueobjects.Spieler;
import net.miginfocom.swing.MigLayout;

public class Spielfenster {
	//Variablen f�r das Spielfenster
	private JFrame spiel;
	private SButtonPanel sBP;
	private SpielPanel sP;
	private SInfoPanel sIP;
	private SInfoPanel sIP2;
	private Monopoly monopoly;
	private HausFenster haFenster;
	private HypothekFenster hyFenster; 
	private SpeichernFenster speFenster;
	
	/**
	 * Konstruktor der Klasse Spielfenster
	 * ein neuer JFrame wird erstellt und alle Komponenten hinzugefuegt
	 * enthaelt alle ActionListener f�r das Spielfenster 
	 * @param monopoly
	 */
	public Spielfenster(Monopoly monopoly){
		this.monopoly = monopoly;
	}

	public void sInit(){
		//Spielfeldfenster
		spiel = new JFrame("Monopoly");
		sP = new SpielPanel();
		sBP = new SButtonPanel();
		sIP = new SInfoPanel();
		sIP2 = new SInfoPanel();
		haFenster = new HausFenster(monopoly);
		hyFenster = new HypothekFenster(monopoly);
		speFenster = new SpeichernFenster(monopoly);
		spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spiel.setLayout(new MigLayout("", "[]10[]", "[]10[]"));
		
		spiel.add(sP, "cell 0 0,span 1 3, w 600, h 600");
		spiel.add(sBP, "cell 1 0, push, grow, shrink");
		spiel.add(sIP, "cell 1 1,h 150, push, grow, shrink");
		spiel.add(sIP2, "cell 1 2,h 150, push, grow, shrink");
		
		spiel.getContentPane().setBackground(new Color(197,251,255));
		
		spiel.setSize(1000, 725);
		spiel.setVisible(true);
		Vector<Spieler> spielerliste = monopoly.getAllSpieler();
		Spieler spieler = spielerliste.get(0);
		ImageIcon icon = new ImageIcon("images/spielfiguren/red.png");
		spieler.setSpielfigur(icon.getImage());
		spieler.getSpielerPosition().getNummer();
		sP.add(new Spielfigur(spieler.getSpielfigur()), "pos 0.91al 0.9al,h 50,w 30");
		//Info-TextAreas mit sich anpassenden Infos... wie macht man das
		
		//ActionListener fuer den wuerfeln-Button
		sBP.getButton1().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int zugweite = monopoly.wuerfel();
				Spieler spieler = monopoly.getTurn().getWerIstDran();
				monopoly.move(spieler, zugweite);
			}
		});
		sP.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent me) {
				//me.getX()/100;
				System.out.println("\"pos "+ Math.round((double)me.getX()/sP.getWidth()*100)/100.0+"al "
						+ Math.round((double)me.getY()/sP.getHeight()*100)/100.0+"al\"");
				
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//ActionListener fuer den Haus-bauen-Button
		sBP.getButton2().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				spiel.remove(sBP);
				spiel.add(haFenster, "cell 1 0, push, grow, shrink");
				spiel.repaint();
				spiel.revalidate();
			}
		});
		
		//ActionListener fuer den Hypothek-aufnehmen-Button
		sBP.getButton3().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				spiel.remove(sBP);
				spiel.add(hyFenster, "cell 1 0, push, grow, shrink");
				spiel.repaint();
				spiel.revalidate();
			}
		});
		
		//ActionListener fuer den speichern-Button
		sBP.getButton4().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//monopoly.saveAll();
				spiel.remove(sBP);
				spiel.add(speFenster, "cell 1 0, pushx, growx, shrinkx");
				spiel.repaint();
				spiel.revalidate();
				//JOptionPane.showMessageDialog(spiel, "Eggs are not supposed to be green.");
			}
		});
		
		
		//ActionListener für den bauen-Button
		haFenster.getHaButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Spieler spieler = monopoly.getTurn().getWerIstDran();
				int position = spieler.getSpielerPosition().getNummer();
				if(!haFenster.getHaListe().isSelectionEmpty()){
					//monopoly.bauHaus(position, spieler);
					JOptionPane.showMessageDialog(spiel, "Eggs are not supposed to be green.");
//					spiel.remove(haFenster);
//					spiel.add(sBP, "cell 1 0, push, grow, shrink");
//					spiel.repaint();
//					spiel.revalidate();
				}
			}
		});
		
		//ActionListener fuer den zurueck-Button
		haFenster.getHaButton2().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				spiel.remove(haFenster);
				spiel.add(sBP, "cell 1 0, push, grow, shrink");
				spiel.repaint();
				spiel.revalidate();
			}
		});
		
		//ActionListener fuer den aufnehmen-Button
		hyFenster.getHyButton1().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//if hypothek == false -->if(monopoly.getHypothek == false){
				//}
				Spieler spieler = monopoly.getTurn().getWerIstDran();
				int position = spieler.getSpielerPosition().getNummer();
				if(! hyFenster.getHyListe().isSelectionEmpty()){
					//if(){
						//monopoly.switchHypothek(position);
//						spiel.remove(hyFenster);
//						spiel.add(sBP, "cell 1 0, push, grow, shrink");
//						spiel.repaint();
//						spiel.revalidate();
						JOptionPane.showMessageDialog(spiel, "Eggs are not supposed to be green.");
					//}
				}
			}
		});
		
		//ActionListener fuer den abbezahlen-Button
		hyFenster.getHyButton2().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//if hypothek == true --> if(monopoly.getHypothek == true){
				//monopoly.switchHypothek(position);}
				if(! hyFenster.getHyListe().isSelectionEmpty()){
					//if(){
						Spieler spieler = monopoly.getTurn().getWerIstDran();
						int position = spieler.getSpielerPosition().getNummer();
						//monopoly.switchHypothek(position);
//						spiel.remove(hyFenster);
//						spiel.add(sBP, "cell 1 0, push, grow, shrink");
//						spiel.repaint();
//						spiel.revalidate();
						JOptionPane.showMessageDialog(spiel, "Eggs are not supposed to be green.");
					//}
				}
			}
		});
		
		//ActionListener fuer den zurueck-Button
		hyFenster.getHyButton3().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				spiel.remove(hyFenster);
				spiel.add(sBP, "cell 1 0, push, grow, shrink");
				spiel.repaint();
				spiel.revalidate();
			}
		});
		
		//ActionListener fuer den speichern-Button
		speFenster.getSfButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String datei = speFenster.getDatName().getText();
				monopoly.saveAll(datei);
				//zurueck zum Hauptmenue
				JOptionPane.showMessageDialog(spiel, "Speichern erfolgreich");
//				Spielfenster spFenster = new Spielfenster(monopoly);
//				spFenster.sInit();
//				menue.dispose();
				Menuefenster mFenster = new Menuefenster();
				mFenster.mSetVisible();
				spiel.dispose();
			}
		});
		
		//ActionListener fuer den zurueck-Button
		speFenster.getSfButton2().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					spiel.remove(speFenster);
					spiel.add(sBP, "cell 1 0, push, grow, shrink");
					spiel.repaint();
					spiel.revalidate();
				}
			});
	}
}




