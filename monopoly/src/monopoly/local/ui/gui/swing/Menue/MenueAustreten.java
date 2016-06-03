package monopoly.local.ui.gui.swing.Menue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import monopoly.local.domain.Monopoly;
import monopoly.local.valueobjects.Spieler;
import net.miginfocom.swing.MigLayout;

public class MenueAustreten{
	
	private Font mFont;
	private JButton mButton;
	private MenuePanel mPanel;
	private JList<String> liste;
	
	public MenueAustreten(Monopoly monopoly){
		//Menue	
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
		mPanel = new MenuePanel(600,600);
		
		mPanel.setLayout(mLayout);
		mPanel.add(liste,"w 425, h 50, cell 2 20, pushx, growx");
		mPanel.add(new JLabel(""), "w 25");
		mPanel.add(mButton,"w 425, h 50, cell 2 20, pushx, growx");
		mPanel.add(new JLabel(""), "w 25");
		
		mButton.setToolTipText("Du tritts aus einem Spiel aus");
		
		mButton.setBackground(new Color(255,155,55));
		
		mButton.setBorderPainted(false);
		
		
		
		mButton.setFont(mFont);
	}

	public JButton getmButton() {
		return mButton;
	}

	public void setmButton(JButton mButton) {
		this.mButton = mButton;
	}

	public MenuePanel getmPanel() {
		return mPanel;
	}

	public void setmPanel(MenuePanel mPanel) {
		this.mPanel = mPanel;
	}

	public JList<String> getListe() {
		return liste;
	}

	public void setmTextField(JList<String> mTextField) {
		this.liste = mTextField;
	}
	
	
}
