package monopoly.local.ui.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class Spielfenster {
	//Variablen für das Spielfenster
	private JFrame spiel;
	private JPanel sPanel1;
	private JPanel sPanel2;
	private SpielPanel sPanel3;
	private JButton sButton1;
	private JButton sButton2;
	private JButton sButton3;
	private JButton sButton4;
	private JTextArea sTextArea;
	private Font sFont1;
	private Font sFont2;
	
	public Spielfenster(){
		
	}

	public void sInit(){
		//Spielfeldfenster
		spiel = new JFrame("Monopoly");
		spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spiel.setLayout(new MigLayout("", "[]10[]", "[]10[]"));
		
		MigLayout p1Layout = new MigLayout("debug", "[]10[]10[]", "[]10[]10[]10[]10[]10[]10[]");
		MigLayout p2Layout = new MigLayout("debug", "[]", "[]");
		sFont1 = new Font("Berlin Sans FB",Font.ITALIC,14);
		sFont2 = new Font("Berlin Sans FB Demi",Font.PLAIN,14);
		sPanel1 = new JPanel();
		sPanel2 = new JPanel();
		sPanel3 = new SpielPanel();
		sButton1 = new JButton("Würfeln");
		sButton2 = new JButton("Haus bauen");
		sButton3 = new JButton("Hypothek aufnehmen");
		sButton4 = new JButton("Spiel speichern");
		sTextArea = new JTextArea(25,6);
		JScrollPane sP = new JScrollPane(sTextArea); 
		
		spiel.add(sPanel3, "cell 0 0, w 600, h 600");
		spiel.add(sPanel1, "cell 1 0, pushx, growx, shrinkx ");
		spiel.add(sPanel2, "cell 0 1, span 2, pushx, growx, shrinkx");
		
		sPanel1.setLayout(p1Layout);
		sPanel2.setLayout(p2Layout);
		
		
		sPanel1.add(sButton1, "cell 1 1, push, grow, shrink");
		sPanel1.add(sButton2, "cell 1 2, push, grow, shrink");
		sPanel1.add(sButton3, "cell 1 3, push, grow, shrink");
		sPanel1.add(sButton4, "cell 1 4, push, grow, shrink");
		sPanel2.add(sP, "push, grow, shrink");
		
		sTextArea.setText("Wuhuuuuu geschafft. :)");
		sTextArea.setEditable(false);
		sTextArea.setAutoscrolls(true);
		sTextArea.setLineWrap(true);
		sTextArea.setWrapStyleWord(true);
		sTextArea.setFont(sFont2);
		
		sP.setMinimumSize(new Dimension(200,20));
		
		sButton1.setBackground(new Color(173,232,202));
		sButton2.setBackground(new Color(173,232,202));
		sButton3.setBackground(new Color(173,232,202));
		sButton4.setBackground(new Color(173,232,202));
		
		sButton1.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 2));
		sButton2.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 2));
		sButton3.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 2));
		sButton4.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 2));
		
		sButton1.setFont(sFont1);
		sButton2.setFont(sFont1);
		sButton3.setFont(sFont1);
		sButton4.setFont(sFont1);
		
		sPanel3.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 4));
		
		sPanel1.setBackground(new Color(255,0,0));
		sPanel2.setBackground(new Color(0,0,255));
		sPanel3.setBackground(new Color(0,255,0));
		
		sPanel1.setOpaque(false);
		sPanel2.setOpaque(false);
		
		spiel.getContentPane().setBackground(new Color(0,0,0));
//		Object[] haOptions = {"bauen", "trolololol"};
//		Object[] hyOptions = {"Hypothek aufnehmen","Hypothek abbezahlen"};
//		JOptionPane.showOptionDialog(spiel,"Hier sollen die Optionen für das bauen der Häuser hin","Haus bauen",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,haOptions,haOptions[0]);
//		JOptionPane.showOptionDialog(spiel,"Hier sollen die Optionen für die Hypotheken hin.","Hypothek aufnehmen/abbezahlen",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, hyOptions, hyOptions[0]);
		
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
//		spalten.add("Häuseranzahl");
//		this.columnIdentifiers = spalten;
//	}
//	
//}
}
