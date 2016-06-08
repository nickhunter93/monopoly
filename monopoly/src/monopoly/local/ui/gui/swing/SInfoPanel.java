package monopoly.local.ui.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class SInfoPanel extends JPanel{
	private JTextArea sTextArea;
	private Font sFont2;
	private MigLayout p2Layout;
	
	public SInfoPanel(){
		p2Layout = new MigLayout("", "[]", "[]");
		sFont2 = new Font("Berlin Sans FB Demi",Font.PLAIN,14);
		sTextArea = new JTextArea(25,6);
		JScrollPane sP = new JScrollPane(sTextArea);
		
		setLayout(p2Layout);
		
		add(sP, "push, grow, shrink");
		
		sTextArea.setText("Dich muss ich bestimmt iwie aendern können..."
				+ "                Wir sollten nicht vergessen (wieder) einzubauen das man nicht mit null Spielern ein Spiel anfangen kann");
		sTextArea.setEditable(false);
		sTextArea.setAutoscrolls(true);
		sTextArea.setLineWrap(true);
		sTextArea.setWrapStyleWord(true);
		sTextArea.setFont(sFont2);
		
		sP.setMinimumSize(new Dimension(200,20));
		
		setBackground(new Color(0,0,255));
		setOpaque(false);
	}

	public JTextArea getSTextArea(){
		return sTextArea;
	}
}
