package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class FeldOhneLos implements Aktion {

	private Feld target;
	private Monopoly monopoly;
	private String str;
	
	/**
	 * Konstruktor der Klasse FeldOhneLos
	 * implementiert die Klasse Aktion
	 * 
	 * @param monopoly
	 * @param target
	 */
	public FeldOhneLos(Monopoly monopoly, Feld target,String str) {
		this.target = target;
		this.monopoly = monopoly;
		this.str = str;
	}

	/**
	 * Methode zum ausführen um einen Spieler auf ein Feld zu setzen
	 * Spieler kommt nicht über Los/ darf das Geld nicht einziehen wenn er über Los kommt
	 */
	public void ausfuehren() {
		monopoly.getTurn().getWerIstDran().setSpielerPosition(target);
//		ereignis.aufFeldOhneLos(spieler, spielfeld.getSpielfeld()[7]);		
	}
	
	/**
	 * gibt den String der Aktion zurück
	 */
	public String toString(){
		return str;
	}

}
