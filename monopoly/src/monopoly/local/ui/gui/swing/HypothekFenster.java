package monopoly.local.ui.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import monopoly.local.domain.Monopoly;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;
import net.miginfocom.swing.MigLayout;

public class HypothekFenster extends JPanel {
	//Variablen f�r "Hypothek aufnehmen" Fenster
	private JPanel hypothek;
	private JPanel hyPanel1;
	private JPanel hyPanel2;
	private JPanel hyPanel3;
	private JList<String> hyListe; 
	private JScrollPane hySP;
	private JButton hyButton1;
	private JButton hyButton2;
	private JButton hyButton3;
	private Monopoly monopoly;

	private JTextField haHausAnz;
	private int position;
	
	/**
	 * Konstruktor der Klasse HypothekFenster
	 * hier wird das Spielmenuefenster Hypothek-aufnehmen zusammen gesetzt
	 * (nur GUI-Elemente)
	 * 
	 * @param monopoly
	 */
	public HypothekFenster(Monopoly monopoly){
		super();
		
		hypothek = new JPanel();
		this.monopoly = monopoly;
		
		MigLayout hyLayout1 = new MigLayout("", "[] []", "[]");
		MigLayout hyLayout3 = new MigLayout("", "[]", "[] [] [] []");
		Font hyFont1 = new Font("Berlin Sans FB",Font.ITALIC,14);
		Font hyFont2 = new Font("Berlin Sans FB Demi",Font.PLAIN,14);
		hyPanel1 = new JPanel();
		hyPanel2 = new JPanel();
		hyPanel3 = new JPanel();
		hyButton1 = new JButton("aufnehmen");
		hyButton2 = new JButton("abbezahlen");
		hyButton3 = new JButton("zurueck");
		//String hyInhalt[] = {"Inhalt 1", "Inhalt 2", "Inhalt 3", "Inhalt 4"};
//		Spieler spieler = monopoly.getTurn().getWerIstDran();
//		String hyInhalt[] = {};
//		if(monopoly.getYourStreets(spieler) != null){
//			for(int i = 0; i <= monopoly.getYourStreets(spieler).length; i++){
//				hyInhalt[i] = monopoly.getFeldName(i);
//			}
//		}
		
		String[] str = {};
		hyListe  = new JList<String>(str);
		hySP = new JScrollPane(hyListe);
		JLabel hyLabel1 = new JLabel("");
		JLabel hyLabel2 = new JLabel("");
		
		setLayout(hyLayout1);
		hyPanel3.setLayout(hyLayout3);
		
		add(hyPanel2, "cell 0 0, push, grow, shrink");
		add(hyPanel3, "cell 0 1, pushx, growx, shrinkx");
		hyPanel2.add(hySP, "push, grow, shrink");
		hyPanel3.add(hyButton1, "cell 0 0, pushx, growx, shrinkx");
		hyPanel3.add(hyButton2, "cell 0 1, pushx, growx, shrinkx");
		hyPanel3.add(hyButton3, "cell 0 2, pushx, growx, shrinkx");
		
		hySP.setPreferredSize(new Dimension(340, 200));
		
		hyButton1.setBackground(new Color(255,255,93));
		hyButton2.setBackground(new Color(255,255,93));
		hyButton3.setBackground(new Color(255,255,93));
		hyListe.setBackground(new Color(255,255,255));
		hyPanel2.setBackground(new Color(197,251,255));
		hyPanel3.setBackground(new Color(197,251,255));
		
		hyButton1.setFont(hyFont1);
		hyButton2.setFont(hyFont1);
		hyButton3.setFont(hyFont1);
		hyListe.setFont(hyFont2);
		
		setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 2));
		
		setOpaque(false);
	}
	
	/**
	 * 
	 * @return gibt den hyButton1(-aufnehmen-) zurueck
	 */
	public JButton getHyButton1(){
		return hyButton1;
	}
	
	/**
	 * 
	 * @return gibt den hyButton2(-abbezahlen-) zurueck
	 */
	public JButton getHyButton2(){
		return hyButton2;
	}
	
	/**
	 * 
	 * @return gibt den hyButton3(-zurueck-) zurueck
	 */
	public JButton getHyButton3(){
		return hyButton3;
	}
	
	/**
	 * 
	 * @return gibt die hyListe zurueck
	 * (beinhaltet die Strassen)
	 */
	public JList getHyListe(){
		return hyListe;
	}
	
	public void refreshList(){
		Spieler spieler = monopoly.getTurn().getWerIstDran();
		String inhalt[];
		
		if(spieler != null && monopoly.getYourStreets(spieler) != null){
			Strasse[] strassen = monopoly.getYourStreets(spieler);
			inhalt = new String[strassen.length];
			for(int i = 0; i < strassen.length; i++){
				inhalt[i] = strassen[i].getName() + " hat " + (strassen[i].getHypothek() ? "eine" : "keine") + " Hypothek";
				//Boolean.toString(strassen[i].getHypothek();
			}
			position = spieler.getSpielerPosition().getNummer();
			hyListe.setListData(inhalt);
			hySP.repaint();
			hySP.revalidate();
		}
	}
}
