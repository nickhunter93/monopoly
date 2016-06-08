package monopoly.local.valueobjects;

import java.util.Vector;


public class Birthday implements Aktion {
	
private Vector<Spieler> allSpieler;
private Spieler spieler; 
	
	public Birthday(Spieler spieler, Vector<Spieler> allSpieler) {
		this.spieler = spieler;
		this.allSpieler = allSpieler;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		for(Spieler s : allSpieler){
			if(s.getSpielerNummer() == spieler.getSpielerNummer()){
				spieler.setSpielerBudget(spieler.getSpielerBudget());
			} else {
			s.setSpielerBudget(s.getSpielerBudget() - 2000);
			spieler.setSpielerBudget(spieler.getSpielerBudget() + 2000);
			}
		}
	}

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	
	public Spieler getSpieler() {
		return spieler;
	}

}
