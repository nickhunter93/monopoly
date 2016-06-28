package monopoly.local.ui.gui.swing.Menue;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MenueHauptPanel {
	private Font mFont;
	private JButton mButton;
	private JButton mButton1;
	private JButton mButton2;
	private JButton mButton3;
	private JButton mButton4;
	private MenuePanel mPanel;
	
	/**
	 * Konstruktor der Klasse MenueHauptPanel
	 * das Menuefenster welches man sieht wenn man das Spiel startet wird zusammengebaut
	 * (nur GUI-Elemente)
	 */
	public MenueHauptPanel(){
				MigLayout mLayout = new MigLayout("", "[]20[]20[]", "[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]");
				mFont = new Font("Berlin Sans FB",Font.ITALIC,20);
				mButton = new JButton("Beitreten");
				mButton1 = new JButton("Verlassen");
				mButton2 = new JButton("Spiel starten");
				mButton3 = new JButton("Spiel laden");
				mButton4 = new JButton("Multiplayer");
				mPanel = new MenuePanel();
				
				mPanel.setLayout(mLayout);
				//mPanel.setH(mPanel.getHeight());
				//mPanel.setB(mPanel.getWidth());
				mPanel.add(new JLabel(""), "w 125");
				mPanel.add(mButton,"w 300, h 50, cell 2 6, pushx, growx, shrinkx");
				mPanel.add(new JLabel(""), "w 50");
				mPanel.add(new JLabel(""), "w 125");
				mPanel.add(mButton1,"w 300, h 50, cell 2 7, pushx, growx, shrinkx");
				mPanel.add(new JLabel(""), "w 50");
				mPanel.add(new JLabel(""), "w 125");
				mPanel.add(mButton2,"w 300, h 50, cell 2 8, pushx,growx, shrinkx");
				mPanel.add(new JLabel(""), "w 50");
				mPanel.add(new JLabel(""), "w 125");
				mPanel.add(mButton3,"w 300, h 50, cell 2 9, pushx, growx, shrinkx");
				mPanel.add(new JLabel(""), "w 50");
				mPanel.add(mButton4, "w 300, h 50, cell 2 10, pushx, growx, shrinkx");
				
				mButton.setToolTipText("Du tritts einem Spiel bei");
				mButton1.setToolTipText("Du verl�sst ein Spiel");
				mButton2.setToolTipText("Du startest ein Spiel");
				mButton3.setToolTipText("Du l�dst ein angefangenes Spiel");
				mButton4.setToolTipText("Du startest ein mehrspieler Spiel");
				
				mButton.setBackground(new Color(255,255,93));
				mButton1.setBackground(new Color(255,255,93));
				mButton2.setBackground(new Color(255,255,93));
				mButton3.setBackground(new Color(255,255,93));
				mButton4.setBackground(new Color(255,255,93));
				
				mButton.setBorderPainted(false);
				mButton1.setBorderPainted(false);
				mButton2.setBorderPainted(false);
				mButton3.setBorderPainted(false);
				mButton4.setBorderPainted(false);
				
				mButton.setFont(mFont);
				mButton1.setFont(mFont);
				mButton2.setFont(mFont);
				mButton3.setFont(mFont);
				mButton4.setFont(mFont);
				
				
	}
	
	/**
	 * 
	 * @return gibt das mPanel zurueck
	 */
	public MenuePanel getmPanel(){
		return mPanel;
	}
	
	/**
	 * 
	 * @return gibt den mButton(-Beitreten-) zurueck
	 */
	public JButton getmButton() {
		return mButton;
	}
//	public void setmButton(JButton mButton) {
//		this.mButton = mButton;
//	}
	
	/**
	 * 
	 * @return gibt den mButton1(-Verlassen-) zurueck
	 */
	public JButton getmButton1() {
		return mButton1;
	}
	
	
//	public void setmButton1(JButton mButton1) {
//		this.mButton1 = mButton1;
//	}
	
	/**
	 * 
	 * @return gibt den mButton2(-Spiel starten-) zurueck
	 */
	public JButton getmButton2() {
		return mButton2;
	}
	
//	public void setmButton2(JButton mButton2) {
//		this.mButton2 = mButton2;
//	}
	
	/**
	 * 
	 * @return gibt den mButton3(-laden-) zurueck
	 */
	public JButton getmButton3() {
		return mButton3;
	}

	public JButton getmButton4() {
		return mButton4;
	}
	
//	public void setmButton3(JButton mButton3) {
//		this.mButton3 = mButton3;
//	}
}
