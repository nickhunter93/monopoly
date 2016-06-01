package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;
import monopoly.local.domain.Spielverwaltung;

public class Strassenausbesserung extends Aktion{

	private Strasse[] yourStreets;
	private Spielverwaltung feldverwaltung;

	public Strassenausbesserung(Spieler spieler, Strasse[] yourStreets, Spielverwaltung feldverwaltung, Spielfeld spielfeld, Spielerverwaltung spielerverwaltung) {
		super(spieler, spielfeld, spielerverwaltung);
		this.feldverwaltung = feldverwaltung;
		this.yourStreets = yourStreets;
		// TODO Auto-generated constructor stub
	}

	
	public void ausfuehren(){
		yourStreets = feldverwaltung.getYourStreets(spieler);
		int hausbetrag = 800;
		int hotelbetrag = 2300;
			
		for(Strasse strasse : yourStreets){
			if(strasse.getHaeuseranzahl() == 5){
				spieler.setSpielerBudget(spieler.getSpielerBudget() - hotelbetrag);
			} else{
			spieler.setSpielerBudget(spieler.getSpielerBudget() - (hausbetrag * strasse.getHaeuseranzahl()));
			}
		}
	}
}
