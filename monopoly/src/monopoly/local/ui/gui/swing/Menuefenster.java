package monopoly.local.ui.gui.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class Menuefenster {
	//Variablen für das Menue
		private JFrame menue;
		private JButton mButton;
		private JButton mButton1;
		private JButton mButton2;
		private JButton mButton3;
		private MenuePanel mPanel;
		private Font mFont;
	
	public Menuefenster(){
		
	}

	public void mInit(){
		//Menue	
		MigLayout mLayout = new MigLayout("", "[]20[]20[]", "[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]");
		mFont = new Font("Berlin Sans FB",Font.ITALIC,20);
		
		menue = new JFrame("Menue");
		menue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
		menue.add(mPanel);
		
		mButton.setToolTipText("Du tritts einem Spiel bei");
		mButton1.setToolTipText("Du verlässt ein Spiel");
		mButton2.setToolTipText("Du startest ein Spiel");
		mButton3.setToolTipText("Du lädst ein angefangenes Spiel");
		
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
		
		menue.setSize(600, 600);
		menue.setVisible(true);
	}
}
