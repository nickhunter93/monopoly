package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Renovation implements Aktion {
	

	private Spieler spieler;
	private Monopoly monopoly;

	public Renovation(Monopoly monopoly) {
		this.monopoly = monopoly;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		int hausbetrag = 500;
		int hotelbetrag = 2000;
		
		for(Strasse strasse : monopoly.getYourStreets(spieler)){
			if(strasse.getHaeuseranzahl() == 5){
				spieler.setSpielerBudget(spieler.getSpielerBudget() - hotelbetrag);
			} else{
				spieler.setSpielerBudget(spieler.getSpielerBudget() - (hausbetrag * strasse.getHaeuseranzahl()));
			}
		}
	}

	public void setSpieler(Spieler spieler) {
		// TODO Auto-generated method stub
		this.spieler = spieler;	
	}
	
	public Spieler getSpieler() {
		return spieler;
	}

}
