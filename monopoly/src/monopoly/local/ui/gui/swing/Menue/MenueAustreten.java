package monopoly.local.ui.gui.swing.Menue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import monopoly.local.domain.Monopoly;
import monopoly.local.valueobjects.Spieler;
import net.miginfocom.swing.MigLayout;

public class MenueAustreten extends JPanel{
	
	private Font mFont;
	private JButton mButton;
	private MenuePanel mPanel;
	private JTextField mTextField;
	
	public MenueAustreten(Monopoly monopoly){
		//Menue	
		JTextArea mTextArea;
		MigLayout mLayout = new MigLayout("", "[]20[]20[]", "[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]");
		mFont = new Font("Berlin Sans FB",Font.ITALIC,20);
        
		mTextArea = new JTextArea("Spielername");
		mTextArea.setEditable(false);
		int pos = 0;
		for(Spieler spieler : monopoly.getAllSpieler()){
			String str = spieler.getSpielerName();
			mTextArea.insert(str, pos);
			pos++;
		}
		mButton = new JButton("Bestätigen");
		mPanel = new MenuePanel(600,600);
		
		mPanel.setLayout(mLayout);
		mPanel.add(mTextArea,"w 425, h 50, cell 2 20, pushx, growx");
		mPanel.add(new JLabel(""), "w 25");
		mPanel.add(mButton,"w 425, h 50, cell 3 20, pushx, growx");
		mPanel.add(new JLabel(""), "w 25");
		add(mPanel);
		
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

	public JTextField getmTextField() {
		return mTextField;
	}

	public void setmTextField(JTextField mTextField) {
		this.mTextField = mTextField;
	}
	
	
}
