package monopoly.local.ui.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class Spielfenster {
	//Variablen f�r das Spielfenster
	private JFrame spiel;
	private SButtonPanel sBP;
	private SpielPanel sP;
	private SInfoPanel sIP;
	
	
	public Spielfenster(){
		
	}

	public void sInit(){
		//Spielfeldfenster
		spiel = new JFrame("Monopoly");
		sP = new SpielPanel();
		sBP = new SButtonPanel();
		sBP.getButton2().addActionListener(new SButtonListener(spiel));
		sIP = new SInfoPanel();
		spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spiel.setLayout(new MigLayout("debug", "[]10[]", "[]10[]"));
		
		spiel.add(sP, "cell 0 0, w 600, h 600");
		spiel.add(sBP, "cell 1 0, pushx, growx, shrinkx ");
		spiel.add(sIP, "cell 0 1, span 2, pushx, growx, shrinkx");
		
		spiel.getContentPane().setBackground(new Color(0,0,0));
		
		spiel.setSize(600, 600);
		spiel.setVisible(true);
	}
	
//	public class ListenModell extends DefaultTableModel{
//	public ListenModell(){
//		super();
//		
//		spalten = new Vector<String>();
//		spalten.add("Strassennummer");
//		spalten.add("Strassenname");
//		spalten.add("H�useranzahl");
//		this.columnIdentifiers = spalten;
//	}
//	
//}
	public class SButtonListener implements ActionListener{
		private JFrame spiel;
		public SButtonListener(JFrame spiel){
			this.spiel = spiel;
		}
		
		public void actionPerformed(ActionEvent e){
			spiel.remove(sBP);
			HausFenster haFenster = new HausFenster();
			//Spielfenster
//			spiel.add(haFenster, "cell 1 0, pushx, growx, shrinkx ");
			
			SInfoPanel panel = new SInfoPanel();
			spiel.add(panel, "cell 1 0, w 600, h 600, pushx, growx, shrinkx");
			haFenster.haInit();
			spiel.revalidate();
		   }
		
	}
}


