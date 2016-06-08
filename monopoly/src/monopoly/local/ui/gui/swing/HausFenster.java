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

import monopoly.local.domain.exceptions.HausbauException;
import net.miginfocom.swing.MigLayout;

public class HausFenster extends JPanel {
	//Variablen f�r das "Haus bauen"-Fenster
	private JPanel haBauen;
	private JPanel haPanel;
	private JPanel haPanel2;
	private JPanel haPanel3;
	private JLabel haLabel;
	private JTextField haHausAnz;
	private JButton haButton;
	private JList<String> liste;
	private JScrollPane haSP;
	private Vector<String> spalten;
	
	public HausFenster(){
		haBauen = new JPanel();

		
		// irgendwo
		try {
			monopoly.hausBauen(spieler, feld);
		} catch (HausbauException hbe) {
			JOptionPane.showMessageDialog(this, hbe.getMessage(), "Pfusch am Bau!", JOptionPane.WARNING_MESSAGE, 0);
		}
		
		
		MigLayout haLayout = new MigLayout("debug", "[]", "[]10[]");
		MigLayout haLayout3 = new MigLayout("debug", "[]10[]10[]", "[]");
		
		Font haFont1 = new Font("Berlin Sans FB",Font.ITALIC,14);
		Font haFont2 = new Font("Berlin Sans FB Demi",Font.PLAIN,14);
		
		haPanel = new JPanel();
		haPanel2 = new JPanel();
		haPanel3 = new JPanel();
		String Inhalt[] = {"Hier", "kommt", "die", "Liste", "mit", "den", "Stra�en", "hin", ".", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"};
		liste  = new JList(Inhalt);
		haSP = new JScrollPane(liste);
		haLabel = new JLabel("H�useranzahl: ");
		haHausAnz = new JTextField();
		haButton = new JButton("bauen");
		
		haPanel.setLayout(haLayout);
		haPanel3.setLayout(haLayout3);
		
		add(haPanel);
//		haBauen.add(haPanel);
		haPanel.add(haPanel2, "cell 0 0, push, grow, shrink");
		haPanel.add(haPanel3, "cell 0 1, pushx, growx, shrinkx");
		haPanel2.add(haSP);
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
		
		haPanel.setOpaque(false);
		haBauen.setBackground(new Color(0,0,0));

	}

	public void haInit(){
		//Pop-Up-Fenster Haus bauen
		
	}
}
