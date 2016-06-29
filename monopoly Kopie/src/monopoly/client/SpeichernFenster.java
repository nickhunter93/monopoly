package monopoly.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ServerPacket.ServerRemote;
import monopoly.local.domain.Monopoly;
import net.miginfocom.swing.MigLayout;

public class SpeichernFenster extends JPanel{
	
	private MigLayout sfLayout;
	private JPanel sfPanel;
	private JButton sfButton;
	private JButton sfButton2;
	private JScrollPane sfSP;
	private JTextField datName;
	
	/**
	 * Konstruktor der Klasse SpeichernFenster
	 * hier wird das Spielmenuefenster f�r das Speichern zusammengesetzt
	 * (nur GUI-Elemente)
	 * 
	 * @param monopoly
	 */
	public SpeichernFenster(){
		super();
			
		sfLayout = new MigLayout("","[] [] []","[] [] []");
		
		Font sfFont1 = new Font("Berlin Sans FB",Font.ITALIC,14);
		Font sfFont2 = new Font("Berlin Sans FB Demi",Font.PLAIN,14);
		
		sfPanel = new JPanel();
		sfButton = new JButton("speichern");
		sfButton2 = new JButton("zurueck");
		datName = new JTextField("Dateiname");
		sfSP = new JScrollPane(datName);
		
		sfSP.setMinimumSize(new Dimension(340,50));
		
		setLayout(sfLayout);
		
		add(sfSP, "cell 0 1, span 2, pushx, growx, shrinkx");
		add(sfButton,"cell 0 2,h 50, pushx, growx, shrinkx");
		add(sfButton2,"cell 1 2,h 50, pushx, growx, shrinkx");
		
		datName.setFont(sfFont2);
		sfButton.setFont(sfFont1);
		sfButton2.setFont(sfFont1);
		
		sfButton.setBackground(new Color(255,255,93));
		sfButton2.setBackground(new Color(255,255,93));
		setBackground(new Color(197,251,255));
		
		datName.setBorder(BorderFactory.createEmptyBorder());
		setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 2));

	}
	
	/**
	 * 
	 * @return gibt den sfButton(-speichern-) zurueck
	 */
	public JButton getSfButton(){
		return sfButton;
	}
	
	/**
	 * 
	 * @return gibt den sfButton2(-zurueck-) zurueck
	 */
	public JButton getSfButton2(){
		return sfButton2;
	}
	
	/**
	 * 
	 * @return gibt das JTextField datName zurueck
	 * (enthaelt den Dateinamen)
	 */
	public JTextField getDatName(){
		return datName;
	}
}
