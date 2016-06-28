package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Renovation implements Aktion {
	

	private Monopoly monopoly;
	private String str;

	/**
	 * Konstruktor der Klasse Renovation
	 * implemtiert Aktion
	 * 
	 * @param monopoly
	 */
	public Renovation(Monopoly monopoly, String str) {
		this.monopoly = monopoly;
		this.str = str;
	}

	/**
	 * Methode zum Ausführen der Renovieren-Aktion 
	 */
	public void ausfuehren() {
		int hausbetrag = 500;
		int hotelbetrag = 2000;
		Spieler spieler = monopoly.getTurn().getWerIstDran();
		if(monopoly.getYourStreets(spieler) != null){
			for(Strasse strasse : monopoly.getYourStreets(spieler)){
				if(strasse.getHaeuseranzahl() == 5){
					spieler.setSpielerBudget(spieler.getSpielerBudget() - hotelbetrag);
				} else{
					spieler.setSpielerBudget(spieler.getSpielerBudget() - (hausbetrag * strasse.getHaeuseranzahl()));
				}
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
