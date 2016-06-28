package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Renovation implements Aktion {
	

	private Monopoly monopoly;

	/**
	 * Konstruktor der Klasse Renovation
	 * implemtiert Aktion
	 * 
	 * @param monopoly
	 */
	public Renovation(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	/**
	 * Methode zum Ausführen der Renovieren-Aktion 
	 */
	public void ausfuehren() {
		int hausbetrag = 500;
		int hotelbetrag = 2000;
		Spieler spieler = monopoly.getTurn().getWerIstDran();
		
		for(Strasse strasse : monopoly.getYourStreets(spieler)){
			if(strasse.getHaeuseranzahl() == 5){
				spieler.setSpielerBudget(spieler.getSpielerBudget() - hotelbetrag);
			} else{
				spieler.setSpielerBudget(spieler.getSpielerBudget() - (hausbetrag * strasse.getHaeuseranzahl()));
			}
		}
	}


}
