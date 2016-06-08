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

import monopoly.local.domain.Monopoly;
import monopoly.local.valueobjects.Spieler;
import net.miginfocom.swing.MigLayout;

public class HypothekFenster extends JPanel {
	//Variablen für "Hypothek aufnehmen" Fenster
	private JPanel hypothek;
	private JPanel hyPanel1;
	private JPanel hyPanel2;
	private JPanel hyPanel3;
	private JList<String> hyListe; 
	private JScrollPane hySP;
	private JButton hyButton1;
	private JButton hyButton2;
	private Monopoly monopoly;
	
	public HypothekFenster(Monopoly monopoly){
		super();
		
		hypothek = new JPanel();
		this.monopoly = monopoly;
		monopoly.TurnIni(true);
		
		MigLayout hyLayout1 = new MigLayout("", "[]10[]", "[]");
		MigLayout hyLayout3 = new MigLayout("", "[]", "[]10[]10[]10[]");
		Font hyFont1 = new Font("Berlin Sans FB",Font.ITALIC,14);
		Font hyFont2 = new Font("Berlin Sans FB Demi",Font.PLAIN,14);
		hyPanel1 = new JPanel();
		hyPanel2 = new JPanel();
		hyPanel3 = new JPanel();
		hyButton1 = new JButton("aufnehmen");
		hyButton2 = new JButton("abbezahlen");
		//String hyInhalt[] = {"Inhalt 1", "Inhalt 2", "Inhalt 3", "Inhalt 4"};
		Spieler spieler = monopoly.getTurn().getWerIstDran();
		String hyInhalt[] = {};
		if(monopoly.getYourStreets(spieler) != null){
			for(int i = 0; i <= monopoly.getYourStreets(spieler).length; i++){
				hyInhalt[i] = monopoly.getFeldName(i);
			}
		}
		hyListe  = new JList<String>(hyInhalt);
		hySP = new JScrollPane(hyListe);
		JLabel hyLabel1 = new JLabel("");
		JLabel hyLabel2 = new JLabel("");
		
		hyPanel1.setLayout(hyLayout1);
		hyPanel3.setLayout(hyLayout3);
		
		add(hyPanel1);
		hyPanel1.add(hyPanel2, "cell 0 0, push, grow, shrink");
		hyPanel1.add(hyPanel3, "cell 0 1, pushx, growx, shrinkx");
		hyPanel2.add(hySP, "push, grow, shrink");
		hyPanel3.add(hyLabel1, "cell 0 0, pushx, growx, shrinkx");
		hyPanel3.add(hyButton1, " pushx, growx, shrinkx");
		hyPanel3.add(hyButton2, " pushx, growx, shrinkx");
		hyPanel3.add(hyLabel2, "cell 3 0, pushx, growx, shrinkx");
		
		hySP.setPreferredSize(new Dimension(200, 200));
		
//		hyLabel1.setMinimumSize(new Dimension(100,25));
//		hyLabel2.setMinimumSize(new Dimension(100,25));
		
		hyButton1.setBackground(new Color(173,232,202));
		hyButton2.setBackground(new Color(173,232,202));
		hyListe.setBackground(new Color(90, 200, 90));
		hyPanel2.setBackground(new Color(0,100,200));
		hyPanel3.setBackground(new Color(200,100,0));
		
		hyButton1.setFont(hyFont1);
		hyButton2.setFont(hyFont1);
		hyListe.setFont(hyFont2);
		
		hyPanel2.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 3));
		hyPanel3.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 3));
		
		hyPanel1.setOpaque(false);
		setBackground(new Color(0,0,0));
		
		//setVisible(true);
	}
	
	public JButton getHyButton1(){
		return hyButton1;
	}
	
	public JButton getHyButton2(){
		return hyButton2;
	}
	
	public JList getHyListe(){
		return hyListe;
	}
}
