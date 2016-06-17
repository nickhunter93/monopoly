package monopoly.local.ui.gui.swing.Menue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import monopoly.local.domain.Monopoly;
import monopoly.local.valueobjects.Spieler;
import net.miginfocom.swing.MigLayout;

public class MenueAustreten{

	//Variablen f�r MenueAustreten
	private Font mFont;
	private JButton mButton;
	private JButton mButton2;
	private MenuePanel mPanel;
	private JList<String> liste;
	private JScrollPane mSP;
	
	/**
	 * Konstruktor der Klasse MenueAustreten
	 * @param monopoly: bekommt Fassadenklasse Monopoly �bergeben
	 * das Menuefenster f�r das Austreten eines Spieler wird zusammengebaut
	 * (nur GUI-Elemente)
	 */
	public MenueAustreten(Monopoly monopoly){
		MigLayout mLayout = new MigLayout("", "[]20[]20[]", "[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]");
		mFont = new Font("Berlin Sans FB",Font.ITALIC,20);
        
		liste = new JList<String>();
		int pos = 0;
		String[] str = new String[monopoly.getAllSpieler().size()];
		
		for(Spieler spieler : monopoly.getAllSpieler()){
			str[pos] = spieler.getSpielerName();
			pos++;
		}
		liste = new JList<String>(str);
		mButton = new JButton("Bestätigen");
		mButton2 = new JButton("zurueck");
		mPanel = new MenuePanel();
		mSP = new JScrollPane(liste);
		
		mPanel.setLayout(mLayout);
		mPanel.add(mSP,"w 425, h 75, cell 2 20,span 2, pushx, growx, shrinkx, flowy, top");
		mPanel.add(mButton,"w 425, h 50, cell 2 21, pushx, growx, shrinkx");
		mPanel.add(mButton2,"w 425, h 50, cell 3 21, pushx, growx, shrinkx");
		mPanel.add(new JLabel(""), "w 25");
		
		mButton.setToolTipText("Du tritts aus einem Spiel aus");
		
		mButton.setBackground(new Color(255,255,93));
		mButton2.setBackground(new Color(255,255,93));
		liste.setBackground(new Color(255,255,255));
		
		mButton.setBorderPainted(false);
		mButton2.setBorderPainted(false);
		
		mButton.setFont(mFont);
		mButton2.setFont(mFont);
		liste.setFont(mFont);
	}

	/**
	 * 
	 * @return der Button mButton(-bestaetigen-) wird zurueckgegeben
	 */
	public JButton getmButton() {
		return mButton;
	}
	
	/**
	 * 
	 * @return der Button mButton2(-zurueck-) wird zurueckgegeben
	 */
	public JButton getmButton2() {
		return mButton2;
	}

	//wozu die setter-Methoden, werden doch nicht gebraucht
//	public void setmButton(JButton mButton) {
//		this.mButton = mButton;
//	}

	/**
	 * 
	 * @return das Panel mPanel wird zurueckgegeben
	 */
	public MenuePanel getmPanel() {
		return mPanel;
	}

//	public void setmPanel(MenuePanel mPanel) {
//		this.mPanel = mPanel;
//	}

	/**
	 * 
	 * @return die Liste liste wird zurueckgeben
	 */
	public JList<String> getListe() {
		return liste;
	}

	//wird diese Methode gebraucht
	public void setmTextField(JList<String> mTextField) {
		this.liste = mTextField;
	}
	
	
}
