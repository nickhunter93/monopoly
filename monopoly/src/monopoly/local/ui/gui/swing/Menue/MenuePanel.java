package monopoly.local.ui.gui.swing.Menue;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MenuePanel extends JPanel{
	
	private String hBild = "images/titelbild.png";
	private ImageIcon mBildIcon;
	private Image iBild;
	private int b;
	private int h;
	
	/**
	 * Konstruktor der Klassen MenuePanel
	 * beinhaltet das Hintergrund f�r das Menuefenster 
	 * 
	 * @param breite: Breite des Panels welches das Bild beinhaltet 
	 * @param hoehe: Hoehe des Panel welches das Bild beinhaltet
	 */
	public MenuePanel(){
		super();
		//b = breite;
		//h = hoehe;
		mBildIcon = new ImageIcon(hBild);
		iBild = mBildIcon.getImage();

	}
	public void setB(int b){
		if(!(b <= 0))
		this.b = b;
	}
	public void setH(int h){
		if(!(h <= 0))
		this.h = h;
	}
	
	/**
	 * Methode zum ueberschreiben der PaintComponent-Methode 
	 * sorgt daf�r dass das Bild im Panel angezeigt wird
	 */
	public void paintComponent(Graphics g){
		//BufferedImage resizedImage = new BufferedImage(b,h, BufferedImage.TYPE_INT_RGB);
		//Graphics g2 = resizedImage.createGraphics();
		//g2.drawImage(iBild, 0, 0, null);
		mBildIcon = new ImageIcon(hBild);
		iBild = mBildIcon.getImage();
		super.paintComponent(g);
		if(iBild != null){
			g.drawImage(iBild, 0, 0,this.getWidth(),this.getHeight(), null);
		}
	}
}
