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
	
	/**
	 * Konstruktor der Klasse SInfoPanel
	 * hier wird das Informationenfeld zusammengesetzt
	 * (nur GUI-Elemente)
	 */
	public SInfoPanel(){
		p2Layout = new MigLayout("", "[]", "[]");
		sFont2 = new Font("Berlin Sans FB Demi",Font.PLAIN,14);
		sTextArea = new JTextArea(25,6);
		JScrollPane sP = new JScrollPane(sTextArea);
		
		setLayout(p2Layout);
		
		add(sP, "push, grow, shrink");
		
		sTextArea.setText("Viel Spaß beim Spielen");
		sTextArea.setEditable(false);
		sTextArea.setAutoscrolls(true);
		sTextArea.setLineWrap(true);
		sTextArea.setWrapStyleWord(true);
		sTextArea.setFont(sFont2);
		
		sP.setPreferredSize(new Dimension(20,20));
		
		setBackground(new Color(197,251,255));
		setOpaque(false);
	}

	/**
	 * 
	 * @return gibt das JTextArea Info.feld zurueck 
	 */
	public JTextArea getJTextArea(){
		return sTextArea;
	}
}
