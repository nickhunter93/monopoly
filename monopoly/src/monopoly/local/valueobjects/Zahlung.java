package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Zahlung implements Aktion {
	
	private int betrag;
	private Monopoly monopoly;

	public Zahlung(Monopoly monopoly, int betrag) {
		this.betrag = betrag;
		this.monopoly = monopoly;
		// TODO Auto-generated constructor stub
	}
	
	public int getBetrag(){
		return this.betrag;
	}
	
	public void setBetrag(int betrag){
		this.betrag = betrag;
	}

	public void ausfuehren() {
		betrag = monopoly.getTurn().getWerIstDran().getSpielerBudget() + betrag;
	}
	

}
