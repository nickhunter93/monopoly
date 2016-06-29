package monopoly.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class SpielPanel extends JPanel{
	
	private String spielBrett = "images/spielbrett.png";
	private ImageIcon sBildIcon;
	private Image sBild;
	
	/**
	 * Konstruktor der Klasse SpielPanel
	 * beinhaltet das Spielfeld
	 */
	public SpielPanel(){
		super();
		setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 4));
		setLayout(new MigLayout("", "[]10[]", "[]10[]"));
	}
	
	/**
	 * Methode zum ueberschreiben der PainComponent-Methode 
	 * damit das Bild angezeigt wird
	 */
	public void paintComponent(Graphics g){
		sBildIcon = new ImageIcon(spielBrett);
		sBild = sBildIcon.getImage();
		super.paintComponent(g);
		if(sBild != null){
			g.drawImage(sBild, 0, 0, null);
		}

}
	
}
