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

<<<<<<< HEAD
import monopoly.local.domain.Monopoly;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;
=======
import monopoly.local.domain.exceptions.HausbauException;
>>>>>>> origin/master
import net.miginfocom.swing.MigLayout;

public class HausFenster extends JPanel {
	//Variablen f�r das "Haus bauen"-Fenster
	private JPanel haBauen;
	private JPanel haPanel1;
	private JPanel haPanel2;
	private JPanel haPanel3;
	private JLabel haLabel;
	private JTextField haHausAnz;
	private JButton haButton;
	private JList<String> liste;
	private JScrollPane haSP;
	private Vector<String> spalten;
	private Monopoly monopoly;
	
	public HausFenster(Monopoly monopoly){
		haBauen = new JPanel();
		this.monopoly = monopoly;
		monopoly.TurnIni(true);
		
<<<<<<< HEAD
		MigLayout haLayout = new MigLayout("", "[]", "[]10[]");
		MigLayout haLayout3 = new MigLayout("", "[]10[]10[]", "[]");
=======
		// irgendwo
		try {
			monopoly.hausBauen(spieler, feld);
		} catch (HausbauException hbe) {
			JOptionPane.showMessageDialog(this, hbe.getMessage(), "Pfusch am Bau!", JOptionPane.WARNING_MESSAGE, 0);
		}
		
		
		MigLayout haLayout = new MigLayout("debug", "[]", "[]10[]");
		MigLayout haLayout3 = new MigLayout("debug", "[]10[]10[]", "[]");
>>>>>>> origin/master
		
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
		haLabel = new JLabel("H�useranzahl: ");
		haHausAnz = new JTextField();
		haButton = new JButton("bauen");
		
		haPanel1.setLayout(haLayout);
		haPanel3.setLayout(haLayout3);
		
		add(haPanel1);
		haPanel1.add(haPanel2, "cell 0 0, push, grow, shrink");
		haPanel1.add(haPanel3, "cell 0 1, pushx, growx, shrinkx");
		haPanel2.add(haSP,"push, grow, shrink");
		haPanel3.add(haLabel, "cell 0 0, pushx, growx, shrinkx");
		haPanel3.add(haHausAnz, "cell 1 0,w 50, pushx, growx, shrinkx");
		haPanel3.add(haButton, "cell 2 0, pushx, growx, shrinkx");
		
		haSP.setPreferredSize(new Dimension(200,200));
		
		liste.setFont(haFont2);
		haLabel.setFont(haFont2);
		haHausAnz.setFont(haFont2);
		haButton.setFont(haFont1);
		
		haPanel2.setBorder(BorderFactory.createLineBorder(new Color(255,155,55), 5));
		haPanel3.setBorder(BorderFactory.createLineBorder(new Color(255,155,55), 5));
		
		haPanel2.setBackground(new Color(255,155,55));
		haPanel3.setBackground(new Color(255,155,55));
		haButton.setBackground(new Color(173,232,202));
		
		haPanel1.setOpaque(false);
		setBackground(new Color(0,0,0));
		

	}
	
	public JButton getHaButton(){
		return haButton;
	}
	
	public JTextField getHaHausAnz(){
		return haHausAnz;
	}
	
	public JList getHaListe(){
		return liste;
	}
	
}
