package monopoly.local.ui.gui.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.*;

public class MonopolyGUI extends JFrame{
	private Image hBild;
	
	private JFrame menue;
	private JButton button;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JPanel panel;
	private JPanel panel2;
	
//	private JFrame spiel;
//	private JPanel sPanel1;
//	private JPanel sPanel2;
//	private JPanel sPanel3;
//	private JButton sButton1;
//	private JButton sButton2;
//	private JButton sButton3;
//	private JButton sButton4;
//	private JTextField sTextBox;
	
	
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
		
		//laedt das Hintergrundbild
		try{
			hBild  = ImageIO.read(new File("Bild 1.jpg"));
		}
		catch(IOException ioe){
			
		}
		
		
		//Menue
		
		menue = new JFrame("Menue");
		menue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menue.setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel2 = new JPanel();
		
		button = new JButton("Beitreten");
		button1 = new JButton("Austreten");
		button2 = new JButton("Spiel starten");
		button3 = new JButton("Spielstand landen");
		
		panel.setLayout(new BorderLayout());
		panel2.setLayout(new GridLayout(11,1));
		
		menue.getContentPane().add(panel, BorderLayout.CENTER);
		panel.add(panel2, BorderLayout.CENTER);
		panel.add(new JLabel("                                                                     "), BorderLayout.NORTH);
		panel.add(new JLabel("                                                                     "), BorderLayout.SOUTH);
		panel.add(new JLabel("                                                                     "), BorderLayout.EAST);
		panel.add(new JLabel("                                                                     "), BorderLayout.WEST);
		
		panel2.add(new JLabel());
		panel2.add(new JLabel());
		panel2.add(button);
		panel2.add(new JLabel());
		panel2.add(button1);
		panel2.add(new JLabel());
		panel2.add(button2);
		panel2.add(new JLabel());
		panel2.add(button3);
		panel2.add(new JLabel());
		panel2.add(new JLabel());
		
		menue.setSize(900, 900);
		menue.setVisible(true);
		
		
//		//Spielfeld
//		spiel = new JFrame("Monopoly");
//		spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		spiel.setLayout(new BorderLayout());
//		
//		sPanel1 = new JPanel();
//		sPanel2 = new JPanel();
//		sPanel3 = new JPanel();
//		sButton1 = new JButton("sButton 1");
//		sButton2 = new JButton("sButton 2");
//		sButton3 = new JButton("sButton 3");
//		sButton4 = new JButton("sButton 4");
//		sTextBox = new JTextField("Bla bla blubb... wie kriege ich dich auf deine Groesse die du haben sollst ohne das der Text iwann aus dem Bild raus läuft???!       Info-Textbox die den entsprechenden Text enthalten soll   ");
//		
//		sPanel1.setLayout(new BorderLayout());
//		sPanel2.setLayout(new GridLayout(11,1));
//		
//		spiel.add(sPanel1, BorderLayout.EAST);
//		spiel.add(sPanel3, BorderLayout.SOUTH);
//		sPanel1.add(sPanel2, BorderLayout.CENTER);
//		sPanel1.add(new JLabel("                      "), BorderLayout.NORTH);
//		sPanel1.add(new JLabel("                      "), BorderLayout.SOUTH);
//		sPanel1.add(new JLabel("                      "), BorderLayout.EAST);
//		sPanel1.add(new JLabel("                      "), BorderLayout.WEST);
//		
//		sPanel2.add(new JLabel());
//		sPanel2.add(new JLabel());
//		sPanel2.add(sButton1);
//		sPanel2.add(new JLabel());
//		sPanel2.add(sButton2);
//		sPanel2.add(new JLabel());
//		sPanel2.add(sButton3);
//		sPanel2.add(new JLabel());
//		sPanel2.add(sButton4);
//		sPanel2.add(new JLabel());
//		sPanel2.add(new JLabel());
//		
//		//sTextBox.setSize(800, 100);
//		//sTextBox.setMinimumSize(getSize());
//		
//		sPanel3.add(sTextBox);
//		
//		spiel.setSize(900, 900);
//		spiel.setVisible(true);
//		
//		
//		
		
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
	
	/**
	 * zum Probieren und bauen der GUI
	 * wird später geloescht oder verschoben
	 */
	public static void main(String[] args){
		MonopolyGUI monopolyGUI = new MonopolyGUI();
		monopolyGUI.initialize();
		
	}
}
