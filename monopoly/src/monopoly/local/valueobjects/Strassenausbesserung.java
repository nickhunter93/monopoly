package monopoly.local.valueobjects;


public class Strassenausbesserung implements Aktion{

	private Strasse[] yourStreets;
	private Spieler spieler;

	public Strassenausbesserung(Spieler spieler, Strasse[] yourStreets) {
		this.yourStreets = yourStreets;
		this.spieler = spieler;
		// TODO Auto-generated constructor stub
	}

	
	public void ausfuehren(){
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
	

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	
	public Spieler getSpieler() {
		return spieler;
	}
}
