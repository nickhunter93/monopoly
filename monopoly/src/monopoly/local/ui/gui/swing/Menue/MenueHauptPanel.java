package monopoly.local.ui.gui.swing.Menue;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MenueHauptPanel extends JPanel {
	private Font mFont;
	private JButton mButton;
	private JButton mButton1;
	private JButton mButton2;
	private JButton mButton3;
	private MenuePanel mPanel;
	public MenueHauptPanel(){
		//Menue	
				MigLayout mLayout = new MigLayout("", "[]20[]20[]", "[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]");
				mFont = new Font("Berlin Sans FB",Font.ITALIC,20);
		        
				mButton = new JButton("Beitreten");
				mButton1 = new JButton("Verlassen");
				mButton2 = new JButton("Spiel starten");
				mButton3 = new JButton("Spiel laden");
				mPanel = new MenuePanel(600,600);
				
				mPanel.setLayout(mLayout);
				mPanel.add(mButton,"w 425, h 50, cell 2 20, pushx, growx");
				mPanel.add(new JLabel(""), "w 25");
				mPanel.add(mButton1,"w 425, h 50, cell 2 21, pushx, growx");
				mPanel.add(new JLabel(""), "w 25");
				mPanel.add(mButton2,"w 425, h 50, cell 2 22, pushx,growx");
				mPanel.add(new JLabel(""), "w 25");
				mPanel.add(mButton3,"w 425, h 50, cell 2 23, pushx, growx");
				mPanel.add(new JLabel(""), "w 25");
				
				mButton.setToolTipText("Du tritts einem Spiel bei");
				mButton1.setToolTipText("Du verl�sst ein Spiel");
				mButton2.setToolTipText("Du startest ein Spiel");
				mButton3.setToolTipText("Du l�dst ein angefangenes Spiel");
				
				mButton.setBackground(new Color(255,155,55));
				mButton1.setBackground(new Color(255,155,55));
				mButton2.setBackground(new Color(255,155,55));
				mButton3.setBackground(new Color(255,155,55));
				
				mButton.setBorderPainted(false);
				mButton1.setBorderPainted(false);
				mButton2.setBorderPainted(false);
				mButton3.setBorderPainted(false);
				
				mButton.setFont(mFont);
				mButton1.setFont(mFont);
				mButton2.setFont(mFont);
				mButton3.setFont(mFont);
				
				add(mPanel);
				
				
	}
	public JButton getmButton() {
		return mButton;
	}
	public void setmButton(JButton mButton) {
		this.mButton = mButton;
	}
	public JButton getmButton1() {
		return mButton1;
	}
	public void setmButton1(JButton mButton1) {
		this.mButton1 = mButton1;
	}
	public JButton getmButton2() {
		return mButton2;
	}
	public void setmButton2(JButton mButton2) {
		this.mButton2 = mButton2;
	}
	public JButton getmButton3() {
		return mButton3;
	}
	public void setmButton3(JButton mButton3) {
		this.mButton3 = mButton3;
	}
}
