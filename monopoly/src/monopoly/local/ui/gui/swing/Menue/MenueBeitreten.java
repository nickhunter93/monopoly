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
	
	private Font mFont;
	private JTextField mTextField;
	private JButton mButton;
	private MenuePanel mPanel;
	
	public MenueBeitreten(){
		//Menue	
		MigLayout mLayout = new MigLayout("debug", "[]20[]20[]", "[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]");
		mFont = new Font("Berlin Sans FB",Font.ITALIC,20);
		
		mTextField = new JTextField("Spielername");
		mButton = new JButton("Bestätigen");
		mPanel = new MenuePanel(600,600);
		
		mPanel.setLayout(mLayout);
		mPanel.add(mTextField,"w 425, h 50, cell 2 20, pushx, growx");
		mPanel.add(new JLabel(""), "w 25");
		mPanel.add(mButton,"w 425, h 50, cell 3 20, pushx, growx");
		mPanel.add(new JLabel(""), "w 25, cell 3 21");
		
		mButton.setToolTipText("Du tritts einem Spiel bei");
		
		mTextField.setBackground(new Color(255,155,55));
		mButton.setBackground(new Color(255,155,55));
		
		mButton.setBorderPainted(false);
		
		mTextField.setFont(mFont);
		mButton.setFont(mFont);
	}

	public MenuePanel getmPanel(){
		return mPanel;
	}
	
	public JTextField getmTextField() {
		return mTextField;
	}

	public void setmTextField(JTextField mTextField) {
		this.mTextField = mTextField;
	}

	public JButton getmButton() {
		return mButton;
	}

	public void setmButton(JButton mButton) {
		this.mButton = mButton;
	}
	
	
}
