package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class FeldMitLos implements Aktion {
	
	private Feld target;
	private Monopoly monopoly;
	private Spieler spieler;

	public FeldMitLos(Monopoly monopoly, Feld target) {
		this.monopoly = monopoly;
		this.target = target;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		Feld current = spieler.getSpielerPosition();
		spieler.setSpielerPosition(target);
		if(current.getNummer() >= target.getNummer()){
			spieler.setSpielerBudget(spieler.getSpielerBudget() + 4000);
		}
	}
	

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}

	public Spieler getSpieler() {
		return spieler;
	}

}


