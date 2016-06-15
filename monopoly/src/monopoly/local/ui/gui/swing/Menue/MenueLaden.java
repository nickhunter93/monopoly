package monopoly.local.ui.gui.swing.Menue;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import monopoly.local.domain.Monopoly;
import net.miginfocom.swing.MigLayout;

public class MenueLaden {
	
	private MigLayout mlLayout;
	private MenuePanel mlPanel;
	private JList<String> mlListe;
	private JScrollPane mlSP;
	private JButton mlButton;
	private JButton mlButton2;
	private Font mlFont;
	private Monopoly monopoly;
	
	/**
	 * Konstruktor der Klasse MenueLaden
	 * das Ladenmenuefenster wird zusammengebaut 
	 * (nur GUI-Elemente)
	 */
	public MenueLaden(Monopoly monopoly){
		this.monopoly = monopoly;
		mlLayout = new MigLayout("","[]20[]20[]","[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]");
		mlFont = new Font("Berlin Sans FB",Font.ITALIC,20);
		
		mlPanel = new MenuePanel(600,600);
		Vector<String> saveFiles = monopoly.saveFilesLaden();
		String inhalt[] = new String[saveFiles.size()]; 
		for(int i = 0;i<saveFiles.size();i++){
			inhalt[i] = saveFiles.get(i);
		}
		//TODO liste mit inhalt f�llen, wie komme ich an die namen f�r die speicherst�nde ran
		mlListe = new JList<String>(inhalt);
		mlSP = new JScrollPane(mlListe);
		mlButton = new JButton("laden");
		mlButton2 = new JButton("zurueck");
		
		mlPanel.setLayout(mlLayout);
		mlPanel.add(mlSP,"cell 2 20,span 2, w 425, h 75, pushx, growx, shrinkx, flowy, top");
		mlPanel.add(mlButton,"cell 2 21,w 425, h 50, pushx, growx, shrinkx");
		mlPanel.add(mlButton2,"cell 3 21,w 425, h 50, pushx, growx, shrinkx");
		mlPanel.add(new JLabel(""), "w 25");
		
		mlButton.setFont(mlFont);
		mlButton2.setFont(mlFont);
		mlListe.setFont(mlFont);
		
		mlButton.setBackground(new Color(255,255,93));
		mlButton2.setBackground(new Color(255,255,93));
		
		mlButton.setBorderPainted(false);
		mlButton2.setBorderPainted(false);
		
		
	}
	
	/**
	 * 
	 * @return gibt das mlPanel zurueck
	 */
	public JPanel getMlPanel(){
		return mlPanel;
	}
	
	/**
	 * 
	 * @return gibt den mlButton(-laden-) zurueck
	 */
	public JButton getMlButton(){
		return mlButton;
	}
	
	/**
	 * 
	 * @return gibt den mlButton2(-zurueck-) zurueck
	 */
	public JButton getMlButton2(){
		return mlButton2;
	}
	
	/**
	 * 
	 * @return gibt die mlListe zurueck
	 * (enthaelt die Speicherstaende)
	 */
	public JList getMlListe(){
		return mlListe;
	}
	
	/**
	 * 
	 * @return gibt den Inhalt der Liste als String zurueck
	 */
	public String getListenInhalt(){
		return mlListe.getSelectedValue();
	}
}
