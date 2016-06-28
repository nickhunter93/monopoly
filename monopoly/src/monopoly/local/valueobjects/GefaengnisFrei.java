package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class GefaengnisFrei implements Aktion {

	private Monopoly monopoly;
	private String str;
	
	/**
	 * Konstruktor der Klasse GefaengnisFrei
	 * implementiert Aktion
	 * 
	 * @param monopoly
	 */
	public GefaengnisFrei(Monopoly monopoly, String str) {
		this.monopoly = monopoly;
		this.str = str;
	}

	/**
	 * befreit einen Spieler aus dem Gefnängnis
	 */
	public void ausfuehren() {
//		ereignis.gefaengnisfrei(spieler);
//		jail.release(spieler);
		monopoly.getJail().release(monopoly.getTurn().getWerIstDran());
		
	}
	
	/**
	 * gibt den String der Aktion zurück
	 */
	public String toString(){
		return str;
	}

}
