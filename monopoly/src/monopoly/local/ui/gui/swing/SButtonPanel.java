package monopoly.local.ui.gui.swing;

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
	
	public SButtonPanel(){
		super();
		p1Layout = new MigLayout("debug", "[]10[]10[]", "[]10[]10[]10[]10[]10[]10[]");
		setLayout(p1Layout);
		sButton1 = new JButton("Würfeln");
		sButton2 = new JButton("Haus bauen");
		sButton3 = new JButton("Hypothek aufnehmen");
		sButton4 = new JButton("Spiel speichern");
		sFont1 = new Font("Berlin Sans FB",Font.ITALIC,14);
		
		add(sButton1, "cell 1 1, push, grow, shrink");
		add(sButton2, "cell 1 2, push, grow, shrink");
		add(sButton3, "cell 1 3, push, grow, shrink");
		add(sButton4, "cell 1 4, push, grow, shrink");
		
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

		setBackground(new Color(255,0,0));
		setOpaque(false);
		
		sButton2.addActionListener(new SButtonListener());
	}
}
