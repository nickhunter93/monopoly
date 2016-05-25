package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class Zahlung extends Aktion {
	
	int betrag;

	public Zahlung(Spielfeld spielfeld, Spielerverwaltung spielerverwaltung , int betrag) {
		super(spielfeld, spielerverwaltung);
		// TODO Auto-generated constructor stub
	}
	
	public int getBetrag(){
		return this.betrag;
	}
	
	public void setBetrag(int betrag){
		this.betrag = betrag;
	}

	@Override
	public void ausfuehren(Spieler spieler) {
		zahlung.payment(spieler, spieler.getSpielerBudget() + betrag);
		
	}

}
