package monopoly.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class SButtonPanel extends JPanel{
	//sPanel1 = new JPanel();  private JPanel sPanel1;
	private JButton sButton1;
	private JButton sButton2;
	private JButton sButton3;
	private JButton sButton4;
	private MigLayout p1Layout;
	private Font sFont1;
	
	/**
	 * Konstruktor der Klasse SButtonPanel
	 * hier werden die Buttons zum steuern des Spieles zusammen in ein Panel gesetzt
	 * (nur GUI-Elemente)
	 */
	public SButtonPanel(){
		super();
		p1Layout = new MigLayout("", "[]10[]10[]", "[]10[]10[]10[]10[]10[]10[]");
		setLayout(p1Layout);
		sButton1 = new JButton("Wuerfeln");
		sButton2 = new JButton("Haus bauen");
		sButton3 = new JButton("Hypothek aufnehmen");
		sButton4 = new JButton("Spiel speichern");
		sFont1 = new Font("Berlin Sans FB",Font.ITALIC,14);
		
		add(sButton1, "cell 1 1, push, grow, shrink");
		add(sButton2, "cell 1 2, push, grow, shrink");
		add(sButton3, "cell 1 3, push, grow, shrink");
		add(sButton4, "cell 1 4, push, grow, shrink");
		
		sButton1.setBackground(new Color(255,255,93));
		sButton2.setBackground(new Color(255,255,93));
		sButton3.setBackground(new Color(255,255,93));
		sButton4.setBackground(new Color(255,255,93));
		
		sButton1.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 2));
		sButton2.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 2));
		sButton3.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 2));
		sButton4.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 2));
		
		sButton1.setFont(sFont1);
		sButton2.setFont(sFont1);
		sButton3.setFont(sFont1);
		sButton4.setFont(sFont1);

		setOpaque(false);
	}
	
	/**
	 * 
	 * @return gibt den sButton1(-wuerfeln-) zurueck
	 */
	public JButton getButton1(){
		return sButton1;
	}
	
	/**
	 * 
	 * @return gibt den SButton2(-Haus bauen-) zurueck
	 */
	public JButton getButton2(){
		return sButton2;
	}
	
	/**
	 * 
	 * @return gibt den SButton3(-Hypothek aufnehmen-) zurueck
	 */
	public JButton getButton3(){
		return sButton3;
	}
	
	/**
	 * 
	 * @return gibt den SButton4(-Spiel speichern-) zurueck
	 */
	public JButton getButton4(){
		return sButton4;
	}
}
