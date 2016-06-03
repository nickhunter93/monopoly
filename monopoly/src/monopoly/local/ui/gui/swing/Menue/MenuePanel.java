package monopoly.local.ui.gui.swing.Menue;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MenuePanel extends JPanel{
	
	public MenuePanel(int breite, int hoehe){
		super();
		b = breite;
		h = hoehe;
		mBildIcon = new ImageIcon(hBild);
		iBild = mBildIcon.getImage();
	}
	
	private String hBild = "images/fgZHJ.jpg.jpg";
	private ImageIcon mBildIcon;
	private Image iBild;
	private int b;
	private int h;
	
	public void paintComponent(Graphics g){
		BufferedImage resizedImage = new BufferedImage(b,h, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = resizedImage.createGraphics();
		g2.drawImage(iBild, 0, 0, b, h, null);
		mBildIcon = new ImageIcon(hBild);
		iBild = mBildIcon.getImage();
		super.paintComponent(g);
		if(iBild != null){
			g.drawImage(iBild, 0, 0, null);
		}
	}
}
