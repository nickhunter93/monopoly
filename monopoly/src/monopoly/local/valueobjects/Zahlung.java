package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class Zahlung extends Aktion {
	
	private int betrag;

	public Zahlung(Spieler spieler,Spielfeld spielfeld, Spielerverwaltung spielerverwaltung , int betrag) {
		super(spieler,spielfeld, spielerverwaltung);
		this.betrag = betrag;
		// TODO Auto-generated constructor stub
	}
	
	public int getBetrag(){
		return this.betrag;
	}
	
	public void setBetrag(int betrag){
		this.betrag = betrag;
	}

	@Override
	public void ausfuehren() {
		betrag = spieler.getSpielerBudget() + betrag;
		spieler.setSpielerBudget(betrag);
	}

}
