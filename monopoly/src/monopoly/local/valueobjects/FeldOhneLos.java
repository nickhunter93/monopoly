package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class FeldOhneLos implements Aktion {

	private Feld target;
	private Spieler spieler;
	private Monopoly monopoly;
	
	public FeldOhneLos(Monopoly monopoly, Feld target) {
		this.target = target;
		this.monopoly = monopoly;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		spieler.setSpielerPosition(target);
//		ereignis.aufFeldOhneLos(spieler, spielfeld.getSpielfeld()[7]);		
	}


	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	
	public Spieler getSpieler() {
		return spieler;
	}
}
