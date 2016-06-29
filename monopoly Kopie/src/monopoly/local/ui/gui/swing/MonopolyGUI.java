package monopoly.local.ui.gui.swing;

import monopoly.local.ui.gui.swing.Menue.Menuefenster;
import net.miginfocom.swing.MigLayout;

public class MonopolyGUI {
	

	
	/**
	 * Konstruktor der GUI-Klasse
	 */
	public MonopolyGUI(){
		
	}
	
	private Menuefenster mFenster;
	
	/**
	 * baut die GUI zusammen
	 */
	private void initialize(){
		mFenster = new Menuefenster();
	}
	
	/**
	 * startet die GUI
	 */
	public static void main(String[] args){
		MonopolyGUI monopolyGUI = new MonopolyGUI();
		monopolyGUI.initialize();
		
	
		    
		    
		
	}
}
