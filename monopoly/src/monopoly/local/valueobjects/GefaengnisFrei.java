package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class GefaengnisFrei implements Aktion {

	private Spieler spieler;
	private Monopoly monopoly;
	
	public GefaengnisFrei(Monopoly monopoly) {
		this.monopoly = monopoly;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
//		ereignis.gefaengnisfrei(spieler);
//		jail.release(spieler);
		monopoly.getJail().release(spieler);
		
	}
	

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	
	public Spieler getSpieler() {
		return spieler;
	}

}
