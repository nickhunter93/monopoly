package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Strassenausbesserung implements Aktion{

	private Monopoly monopoly;
	private String str;

	/**
	 * Konstruktor der Klasse Strssenausbesserung
	 * implementiert Aktion
	 * 
	 * @param str: Beschreigung der Aktion
	 */
	public Strassenausbesserung(Monopoly monopoly,String str) {
		this.monopoly = monopoly;
		this.str = str;

	}

	/**
	 * Methode zum Ausführen der Strassenausbesserungsaktion
	 */
	public void ausfuehren(){
		int hausbetrag = 800;
		int hotelbetrag = 2300;
			
		for(Strasse strasse : monopoly.getYourStreets(monopoly.getTurn().getWerIstDran())){
			if(strasse.getHaeuseranzahl() == 5){
				monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() - hotelbetrag);
			} else if (strasse.getHaeuseranzahl() == 0){
				monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget());
			} else {
				monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() - (hausbetrag * strasse.getHaeuseranzahl()));
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
