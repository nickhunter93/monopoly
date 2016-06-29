package monopoly.client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
public class Spielfigur extends JPanel {

private Image spielfigur;
private int breite;
private int höhe;
	public Spielfigur(Image spielfigur){
		super();
		this.spielfigur = spielfigur;
		breite = 15;
		höhe = 30;
		this.setOpaque(false);
	}
	public void paintComponent(Graphics g){
	//	BufferedImage resizedImage = new BufferedImage(breite,höhe,BufferedImage.TYPE_INT_RGB);
	//	Graphics g2 = resizedImage.createGraphics();
		super.paintComponent(g);
		if(spielfigur != null){
			g.drawImage(spielfigur, 0, 0,breite,höhe, null);
		}
	}
}
