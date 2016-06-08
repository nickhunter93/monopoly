package monopoly.local.valueobjects;


public class Zahlung implements Aktion {
	
	private int betrag;
	private Spieler spieler;

	public Zahlung(Spieler spieler, int betrag) {
		this.spieler = spieler;
		this.betrag = betrag;
		// TODO Auto-generated constructor stub
	}
	
	public int getBetrag(){
		return this.betrag;
	}
	
	public void setBetrag(int betrag){
		this.betrag = betrag;
	}

	public void ausfuehren() {
		betrag = spieler.getSpielerBudget() + betrag;
		spieler.setSpielerBudget(betrag);
	}
	

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	
	public Spieler getSpieler() {
		return spieler;
	}

}
