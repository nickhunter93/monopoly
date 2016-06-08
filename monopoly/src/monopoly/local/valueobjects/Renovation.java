package monopoly.local.valueobjects;


public class Renovation implements Aktion {
	

	private Strasse[] yourStreets;
	private Spieler spieler;

	public Renovation(Spieler spieler, Strasse[] yourStreets) {
		this.spieler = spieler;
		this.yourStreets = yourStreets;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		int hausbetrag = 500;
		int hotelbetrag = 2000;
		
		for(Strasse strasse : yourStreets){
			if(strasse.getHaeuseranzahl() == 5){
				spieler.setSpielerBudget(spieler.getSpielerBudget() - hotelbetrag);
			} else{
			spieler.setSpielerBudget(spieler.getSpielerBudget() - (hausbetrag * strasse.getHaeuseranzahl()));
			}
		}
	}

}
