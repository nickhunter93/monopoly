package monopoly.local.ui.gui.swing;

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
//		spFenster = new Spielfenster();
//		haFenster = new HausFenster();
//		hyFenster = new HypothekFenster();
		
		//mFenster.mInit();

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
