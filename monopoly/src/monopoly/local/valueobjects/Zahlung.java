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
	
	public void ausfuehren() {
		monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() - betrag);
	}
	

}
