package monopoly.local.ui.gui.swing.Menue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import monopoly.local.valueobjects.Spieler;
import net.miginfocom.swing.MigLayout;

public class MenueBeitreten {
	
	//Variablen für MenueBeitreten
	private Font mFont;
	private JTextField mTextField;
	private JButton mButton;
	private JButton mButton2;
	private MenuePanel mPanel;
	
	/**
	 * Konstruktor der Klasse MenueBeitreten
	 * das MenueFenster wird zusammengebaut 
	 *(nur GUI-Elemente)
	 */
	public MenueBeitreten(){
		//Menue	
		MigLayout mLayout = new MigLayout("", "[]20[]20[]", "[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]");
		mFont = new Font("Berlin Sans FB",Font.ITALIC,20);
		
		mTextField = new JTextField("Spielername");
		mButton = new JButton("BestÃ¤tigen");
		mButton2 = new JButton("zurueck");
		mPanel = new MenuePanel(600,600);
		
		mPanel.setLayout(mLayout);
		mPanel.add(mTextField,"w 425,span 2, h 75, cell 2 20, pushx, growx, shrinkx");
		mPanel.add(new JLabel(""), "w 25");
		mPanel.add(mButton,"w 425, h 50, cell 2 21, pushx, growx, shrinkx");
		mPanel.add(mButton2,"w 425, h 50, cell 3 21, pushx, growx, shrinkx");
		mPanel.add(new JLabel(""), "w 25, cell 4 21");
		
		
		mButton.setToolTipText("Du tritts einem Spiel bei");
		
		mTextField.setBackground(new Color(255,255,255));
		mButton.setBackground(new Color(255,255,93));
		mButton2.setBackground(new Color(255,255,93));
		
		mButton.setBorderPainted(false);
		mButton2.setBorderPainted(false);
		
		mTextField.setFont(mFont);
		mButton.setFont(mFont);
		mButton2.setFont(mFont);
	}

	//wird diese Methode gebraucht?
	public MenuePanel getmPanel(){
		return mPanel;
	}
	
	/**
	 * 
	 * @return gibt das TextField mTextField zurueck
	 * (enthaelt den Spielernamen)
	 */
	public JTextField getmTextField() {
		return mTextField;
	}

//	public void setmTextField(JTextField mTextField) {
//		this.mTextField = mTextField;
//	}

	/**
	 * 
	 * @return gibt den Button mButton(-bestaetigen-) zurueck
	 */
	public JButton getmButton() {
		return mButton;
	}

//	public void setmButton(JButton mButton) {
//		this.mButton = mButton;
//	}
	
	/**
	 * 
	 * @return gibt den Button mButton2(-zurueck-) zurueck
	 */
	public JButton getmButton2(){
		return mButton2;
	}
}
