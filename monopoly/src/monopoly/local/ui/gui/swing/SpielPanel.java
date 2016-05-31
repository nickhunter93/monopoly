package monopoly.local.ui.gui.swing;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SpielPanel extends JPanel{
	
	public SpielPanel(){
		super();
	}
	
	private String spielBrett = "images/fgZHJ.jpg.jpg";
	private ImageIcon sBildIcon;
	private Image sBild;
	
	public void paintComponent(Graphics g){
		sBildIcon = new ImageIcon(spielBrett);
		sBild = sBildIcon.getImage();
		super.paintComponent(g);
		if(sBild != null){
			g.drawImage(sBild, 0, 0, null);
		}

}
	
}
