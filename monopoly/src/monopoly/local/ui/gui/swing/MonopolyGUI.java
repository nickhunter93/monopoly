package monopoly.local.ui.gui.swing;

import monopoly.local.ui.gui.swing.Menue.Menuefenster;
import net.miginfocom.swing.MigLayout;

public class MonopolyGUI {
	

	
	/**
	 * Konstruktor der GUI-Klasse
	 */
	public MonopolyGUI(){
		
	}
	
	private HausFenster haFenster;
	private HypothekFenster hyFenster;
	private Spielfenster spFenster;
	private Menuefenster mFenster;
	
	/**
	 * baut die GUI zusammen
	 */
	private void initialize(){
		mFenster = new Menuefenster();
	}
	
	/**
	 * zum Probieren und bauen der GUI
	 * wird sp�ter geloescht oder verschoben
	 */
	public static void main(String[] args){
		MonopolyGUI monopolyGUI = new MonopolyGUI();
		monopolyGUI.initialize();
		
	
		    
		    
		
	}
}
