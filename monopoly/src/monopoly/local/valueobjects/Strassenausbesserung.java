package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Strassenausbesserung implements Aktion{

	private Spieler spieler;
	private Monopoly monopoly;

	public Strassenausbesserung(Monopoly monopoly) {
		this.monopoly = monopoly;
		// TODO Auto-generated constructor stub
	}

	
	public void ausfuehren(){
		int hausbetrag = 800;
		int hotelbetrag = 2300;
			
		for(Strasse strasse : monopoly.getYourStreets(spieler)){
			if(strasse.getHaeuseranzahl() == 5){
				spieler.setSpielerBudget(spieler.getSpielerBudget() - hotelbetrag);
			} else{
				spieler.setSpielerBudget(spieler.getSpielerBudget() - (hausbetrag * strasse.getHaeuseranzahl()));
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
