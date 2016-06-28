package monopoly.local.valueobjects;


import monopoly.local.domain.Monopoly;


public class Birthday implements Aktion {
	
private Monopoly monopoly;
private String str;

/**
 * Konstruktor der Klasse Birthday
 * implementiert Aktion 
 */
	public Birthday(Monopoly monopoly, String str) {
		this.monopoly = monopoly;
		this.str = str;
	}

	/**
	 * Methode für das Ausführen der Kartenaktion Geburtstag 
	 */
	public void ausfuehren() {
Spieler spieler = monopoly.getTurn().getWerIstDran();
		
		for(Spieler s : monopoly.getAllSpieler()){
			if(s.getSpielerNummer() == spieler.getSpielerNummer()){
				spieler.setSpielerBudget(spieler.getSpielerBudget());
			} else {
			s.setSpielerBudget(s.getSpielerBudget() - 2000);
			spieler.setSpielerBudget(spieler.getSpielerBudget() + 2000);
			}
		}
	}

	/**
	 * gibt den String der Aktion zurück
	 */
	public String toString(){
		return str;
	}
}
