package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class GefaengnisFrei implements Aktion {

	private Monopoly monopoly;
	
	/**
	 * Konstruktor der Klasse GefaengnisFrei
	 * implementiert Aktion
	 * 
	 * @param monopoly
	 */
	public GefaengnisFrei(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	/**
	 * befreit einen Spieler aus dem Gefnängnis
	 */
	public void ausfuehren() {
//		ereignis.gefaengnisfrei(spieler);
//		jail.release(spieler);
		monopoly.getJail().release(monopoly.getTurn().getWerIstDran());
		
	}

}
