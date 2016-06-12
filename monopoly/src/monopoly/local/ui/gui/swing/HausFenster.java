package monopoly.local.ui.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import monopoly.local.domain.Monopoly;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;

import monopoly.local.domain.exceptions.HausbauException;

import net.miginfocom.swing.MigLayout;

public class HausFenster extends JPanel {
	//Variablen fï¿½r das "Haus bauen"-Fenster
	private JPanel haBauen;
	private JPanel haPanel1;
	private JPanel haPanel2;
	private JPanel haPanel3;
	private JLabel haLabel;
	private JTextField haHausAnz;
	private JButton haButton;
	private JButton haButton2;
	private JList<String> liste;
	private JScrollPane haSP;
	private Vector<String> spalten;
	private Monopoly monopoly;
	private int position;
	
	/**
	 * Konstruktor der Klasse HausFenster
	 * Spielmenuefenster Haus-bauen wird zusammengesetzt
	 * (nur GUI-Elemente)
	 * 
	 * @param monopoly
	 */
	public HausFenster(Monopoly monopoly){
		haBauen = new JPanel();
		this.monopoly = monopoly;
		monopoly.TurnIni(true);
		

//		// irgendwo
//		try {
//			monopoly.hausBauen(spieler, feld);
//		} catch (HausbauException hbe) {
//			JOptionPane.showMessageDialog(this, hbe.getMessage(), "Pfusch am Bau!", JOptionPane.WARNING_MESSAGE, 0);
//		}
		
		
		MigLayout haLayout = new MigLayout("", "[]", "[]10[]");
		MigLayout haLayout3 = new MigLayout("", "[]10[]10[]", "[]");

		
		Font haFont1 = new Font("Berlin Sans FB",Font.ITALIC,14);
		Font haFont2 = new Font("Berlin Sans FB Demi",Font.PLAIN,14);
		
		haPanel1 = new JPanel();
		haPanel2 = new JPanel();
		haPanel3 = new JPanel();
		Spieler spieler = monopoly.getTurn().getWerIstDran();
		String inhalt[] = {};
		if(monopoly.getYourStreets(spieler) != null){
			for(int i = 0; i <= monopoly.getYourStreets(spieler).length; i++){
				inhalt[i] = monopoly.getFeldName(i);
			}
		}
		liste  = new JList(inhalt);
		haSP = new JScrollPane(liste);
		haLabel = new JLabel("Hï¿½useranzahl: ");
		haHausAnz = new JTextField();
		haButton = new JButton("bauen");
		haButton2 = new JButton("zurueck");
		position = spieler.getSpielerPosition().getNummer();
		
		setLayout(haLayout);
		haPanel3.setLayout(haLayout3);
		
		add(haPanel2, "cell 0 0, push, grow, shrink");
		add(haPanel3, "cell 0 1, pushx, growx, shrinkx");
		haPanel2.add(haSP,"push, grow, shrink");
		haPanel3.add(haLabel, "cell 0 0, pushx, growx, shrinkx");
		haPanel3.add(haHausAnz, "cell 1 0,w 50, pushx, growx, shrinkx");
		haPanel3.add(haButton, "cell 0 1, pushx, growx, shrinkx");
		haPanel3.add(haButton2, "cell 1 1, pushx, growx, shrinkx");
		
		haSP.setPreferredSize(new Dimension(340,200));
		
		liste.setFont(haFont2);
		haLabel.setFont(haFont2);
		haHausAnz.setFont(haFont2);
		haButton.setFont(haFont1);
		haButton2.setFont(haFont1);
		
		setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 2));
		
		haPanel2.setBackground(new Color(197,251,255));
		haPanel3.setBackground(new Color(197,251,255));
		haButton.setBackground(new Color(255,255,93));
		haButton2.setBackground(new Color(255,255,93));
		liste.setBackground(new Color(255,255,255));
		haHausAnz.setBackground(new Color(255,255,255));
		
		haHausAnz.setEditable(false);
		haHausAnz.setText(""+ monopoly.getHaeuseranzahl(position)); //möglicherweise muss das noch mal anders gemacht werden
		
		haPanel1.setBackground(new Color(197,251,255));
		setOpaque(false);
		

	}
	
	/**
	 * 
	 * @return gibt den haButton(-bauen-) zurueck
	 */
	public JButton getHaButton(){
		return haButton;
	}
	
	/**
	 * 
	 * @return gibt den haButton2(-zurueck-) zurueck
	 */
	public JButton getHaButton2(){
		return haButton2;
	}
	
	/**
	 * 
	 * @return gibt das JTextField haHausAnz zurueck
	 * (beinhaltet die Anzahlt der Haeuser)
	 */
	public JTextField getHaHausAnz(){
		return haHausAnz;
	}
	
	/**
	 * 
	 * @return gibt die JListe liste zurueck
	 * (beinhaltet die Strassen) 
	 */
	public JList getHaListe(){
		return liste;
	}
	
}
