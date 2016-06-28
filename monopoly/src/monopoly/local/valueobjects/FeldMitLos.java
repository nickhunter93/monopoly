package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class FeldMitLos implements Aktion {
	
	private Feld target;
	private Monopoly monopoly;
	private String str;

	/**
	 * Konstruktor der Klasse FeldMitLos
	 * implementiert die Klasse Aktion
	 * 
	 * @param monopoly
	 * @param target
	 */
	public FeldMitLos(Monopoly monopoly, Feld target,String str) {
		this.monopoly = monopoly;
		this.target = target;
		this.str = str;
	}

	/**
	 * Methode zum ausführen um einen Spieler auf ein Feld zu setzen
	 * Spieler kommt über Los 
	 */
	public void ausfuehren() {
		Spieler spieler = monopoly.getTurn().getWerIstDran();
		Feld current = spieler.getSpielerPosition();
		spieler.setSpielerPosition(target);
		if(current.getNummer() >= target.getNummer()){
			spieler.setSpielerBudget(spieler.getSpielerBudget() + 4000);
		}
	}
	
	/**
	 * gibt den String der Aktion zurück
	 */
	public String toString(){
		return str;
	}


}


