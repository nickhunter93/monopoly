package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Zahlung implements Aktion {
	
	private int betrag;
	private Monopoly monopoly;
	private String str;

	/**
	 * Konstruktor der Klasse Zahlung 
	 * implementiert die Klasse Aktion
	 * 
	 * @param betrag: Betrag der gezahlt werden soll
	 * @param str: Beschreibung der Aktion 
	 */
	public Zahlung(Monopoly monopoly, int betrag, String str) {
		this.betrag = betrag;
		this.monopoly = monopoly;
		this.str = str;
	}
	
	/**
	 * Methode zum Ausführen der Aktion Zahlung
	 */
	public void ausfuehren() {
		Spieler spieler = monopoly.getTurn().getWerIstDran();
		spieler.setSpielerBudget(spieler.getSpielerBudget() - betrag);
	}
	
	/**
	 * gibt den String der Aktion zurück 
	 */
	public String toString(){
		return str;
	}


}
