package monopoly.local.valueobjects;

import java.io.Serializable;

import monopoly.local.domain.Monopoly;

public class Gewinn implements Aktion, Serializable {
	
	private int betrag;
	private Monopoly monopoly;
	private String str;
	
	/**
	 * Konstruktor der Klasse Gewinn 
	 * implementiert das Interface Aktion 
	 * 
	 * @param betrag: Betrag den man gewinnt 
	 * @param str: Beschreibung der Aktion
	 */
	public Gewinn(Monopoly monopoly, int betrag,String str) {
		this.betrag = betrag;
		this.monopoly = monopoly;
		this.str = str;
	}
	
	/**
	 * Methode zum Ausführen der Aktion Gewinn
	 */
	public void ausfuehren() {
		Spieler spieler = monopoly.getTurn().getWerIstDran();
		spieler.setSpielerBudget(spieler.getSpielerBudget() + betrag);
	}
	
	/**
	 * gibt den String der Aktion zurück
	 */
	public String toString(){
		return str;
	}


}
