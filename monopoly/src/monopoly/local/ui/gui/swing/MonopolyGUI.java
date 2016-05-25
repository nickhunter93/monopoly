package monopoly.local.ui.gui.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.color.*;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.*;

import java.util.Vector;

public class MonopolyGUI extends JFrame{
	private Image hBild;
	
	//Variablen für das Menue
//	private JFrame menue;
//	private JButton mButton;
//	private JButton mButton1;
//	private JButton mButton2;
//	private JButton mButton3;
//	private JPanel mPanel;
//	private JPanel mPanel2;
	
	//Variablen für das Spielfenster
	private JFrame spiel;
	private JPanel sPanel1;
	private JPanel sPanel2;
	private JPanel sPanel3;
	private JButton sButton1;
	private JButton sButton2;
	private JButton sButton3;
	private JButton sButton4;
	private JTextField sTextBox;
	
	//Variablen für das "Haus bauen"-Fenster
//	private JFrame haBauen;
//	private JPanel haPanel;
//	private JTextField haStrEing;
//	private JTextField haHausAnz;
//	private JButton haButton;
//	private JList liste;
	
	/**
	 * Konstruktor der GUI-Klasse
	 */
	public MonopolyGUI(){
		super();
	}
	
	/**
	 * baut die GUI zusammen
	 */
	private void initialize(){
//		
//		//laedt das Hintergrundbild
//		try{
//			hBild  = ImageIO.read(new File("Bild 1.jpg"));
//		}
//		catch(IOException ioe){
//			
//		}
		
		
		//Menue		
//		menue = new JFrame("Menue");
//		menue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		menue.setLayout(new BorderLayout());
//		
//		mPanel = new JPanel();
//		mPanel2 = new JPanel();
//		
//		mButton = new JButton("Beitreten");
//		mButton1 = new JButton("Austreten");
//		mButton2 = new JButton("Spiel starten");
//		mButton3 = new JButton("Spielstand landen");
//		
//		mPanel.setLayout(new BorderLayout());
//		mPanel2.setLayout(new GridLayout(11,1));
//		
//		menue.getContentPane().add(mPanel, BorderLayout.CENTER);
//		mPanel.add(mPanel2, BorderLayout.CENTER);
//		mPanel.add(new JLabel("                                                             "), BorderLayout.NORTH);
//		mPanel.add(new JLabel("                                                             "), BorderLayout.SOUTH);
//		mPanel.add(new JLabel("                                                             "), BorderLayout.EAST);
//		mPanel.add(new JLabel("                                                             "), BorderLayout.WEST);
//		
//		mPanel2.add(new JLabel());
//		mPanel2.add(new JLabel());
//		mPanel2.add(mButton);
//		mPanel2.add(new JLabel());
//		mPanel2.add(mButton1);
//		mPanel2.add(new JLabel());
//		mPanel2.add(mButton2);
//		mPanel2.add(new JLabel());
//		mPanel2.add(mButton3);
//		mPanel2.add(new JLabel());
//		mPanel2.add(new JLabel());
//		
//		mPanel.setOpaque(false);
//		mPanel2.setOpaque(false);
//		
//		menue.getContentPane().setBackground(new Color(255,255,100));
//		mButton.setBorder(BorderFactory.createLineBorder(new Color(1,10,100), 1, true));
//		mButton.setBackground(new Color(255,100,255));
//		mButton1.setBorder(BorderFactory.createLineBorder(new Color(1,10,100), 1, true));
//		mButton1.setBackground(new Color(100,100,255));
//		mButton2.setBorder(BorderFactory.createLineBorder(new Color(1,10,100), 1, true));
//		mButton2.setBackground(new Color(100,255,100));
//		mButton3.setBorder(BorderFactory.createLineBorder(new Color(1,10,100), 1, true));
//		mButton3.setBackground(new Color(100,255,255));
//		
//		//JOptionPane.showMessageDialog(menue, "Eggs are not supposed to be green.");
//		
//		menue.setSize(900, 900);
//		menue.setVisible(true);
		
		
		//Spielfeldfenster
		spiel = new JFrame("Monopoly");
		spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spiel.setLayout(new BorderLayout());
		
		sPanel1 = new JPanel();
		sPanel2 = new JPanel();
		sPanel3 = new JPanel();
		sButton1 = new JButton("     sButton 1     ");
		sButton2 = new JButton("     sButton 2     ");
		sButton3 = new JButton("     sButton 3     ");
		sButton4 = new JButton("     sButton 4     ");
		sTextBox = new JTextField("Brave Textbox du laeufst schon mal nicht mehr aus dem Bild. Jetzt brauchst du nur noch Zeilenumbrueche.       Info-Textbox die den entsprechenden Text enthalten soll   ",75);
		
		sPanel1.setLayout(new BorderLayout());
		sPanel2.setLayout(new GridLayout(11,1));
		
		spiel.add(sPanel1, BorderLayout.EAST);
		spiel.add(sPanel3, BorderLayout.SOUTH);
		sPanel1.add(sPanel2, BorderLayout.CENTER);
		sPanel1.add(new JPanel(), BorderLayout.NORTH);
		sPanel1.add(new JPanel(), BorderLayout.SOUTH);
		sPanel1.add(new JPanel(), BorderLayout.EAST);
		sPanel1.add(new JPanel(), BorderLayout.WEST);
		
		sPanel2.add(new JLabel());
		sPanel2.add(new JLabel());
		sPanel2.add(sButton1);
		sPanel2.add(new JLabel());
		sPanel2.add(sButton2);
		sPanel2.add(new JLabel());
		sPanel2.add(sButton3);
		sPanel2.add(new JLabel());
		sPanel2.add(sButton4);
		sPanel2.add(new JLabel());
		sPanel2.add(new JLabel());
		
		sPanel3.add(sTextBox);
		
		sPanel1.setOpaque(false);
		sPanel2.setOpaque(false);
		sPanel3.setOpaque(false);
		spiel.getContentPane().setBackground(new Color(255,255,100));
		sButton1.setBorder(BorderFactory.createLineBorder(new Color(1,10,100), 0, true));
		sButton1.setBackground(new Color(0,200,200));
		sButton2.setBorder(BorderFactory.createLineBorder(new Color(10,1,100), 0, true));
		sButton2.setBackground(new Color(200,0,200));
		sButton3.setBorder(BorderFactory.createLineBorder(new Color(10,100,1), 0, true));
		sButton3.setBackground(new Color(200,200,0));
		sButton4.setBorder(BorderFactory.createLineBorder(new Color(100,10,1), 0, true));
		sButton4.setBackground(new Color(100,100,100));
		sTextBox.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 0, true));
		sTextBox.setBackground(new Color(255,255,190));
		
//		Object[] haOptions = {"bauen", "trolololol"};
//		Object[] hyOptions = {"Hypothek aufnehmen","Hypothek abbezahlen"};
//		JOptionPane.showOptionDialog(spiel,"Hier sollen die Optionen für das bauen der Häuser hin","Haus bauen",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,haOptions,haOptions[0]);
//		JOptionPane.showOptionDialog(spiel,"Hier sollen die Optionen für die Hypotheken hin.","Hypothek aufnehmen/abbezahlen",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, hyOptions, hyOptions[0]);
		
		spiel.setSize(900, 900);
		spiel.setVisible(true);
		
		
		//Pop-Up-Fenster Haus bauen
//		haBauen = new JFrame("Haus bauen");
//		haBauen.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		haBauen.getContentPane().setLayout(new GridLayout(2,1));
//		
//		haPanel = new JPanel();
//		haStrEing = new JTextField();
//		haHausAnz = new JTextField();
//		haButton = new JButton("bauen");
//		DefaultListModel listenModell = new DefaultListModel();
//		liste  = new JList(listenModell);
//		
//		String data[] = {"1    ","2    ","3    ","4    "};
//		for(int i = 0; i<data.length; i++){
//			listenModell.addElement(data[i]);
//		}
//		
//		haPanel.setLayout(new GridLayout(7,3));
//		
//		haBauen.add(liste);
//		haBauen.add(new JLabel());
//		haBauen.add(haPanel);
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel("       Strassennummer: "));
//		haPanel.add(haStrEing);
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel("       Anzahl der Häuser: "));
//		haPanel.add(haHausAnz);
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		haPanel.add(haButton);
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		haPanel.add(new JLabel());
//		
//		
//		
//		haBauen.setSize(600, 400);
//		haBauen.setVisible(true);
	}
	
	/**
	 * ueberschreibt die Methode paintComponent zum darstellen des Hintergrund bildes
	 */
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		if(hBild != null){
			g.drawImage(hBild, 0, 0, this);
		}
	}
	
//	public class ListenModell extends DefaultTableModel{
//		public ListenModell(){
//			super();
//			
//			spalten = new Vector<String>();
//			spalten.add("Strassennummer");
//			spalten.add("Strassenname");
//			spalten.add("Häuseranzahl");
//			this.columnIdentifiers = spalten;
//		}
//		
//	}
	
	/**
	 * zum Probieren und bauen der GUI
	 * wird später geloescht oder verschoben
	 */
	public static void main(String[] args){
		MonopolyGUI monopolyGUI = new MonopolyGUI();
		monopolyGUI.initialize();
		
	}
}
