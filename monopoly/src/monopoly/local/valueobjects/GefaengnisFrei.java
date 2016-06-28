package monopoly.local.valueobjects;

import java.io.Serializable;

import monopoly.local.domain.Monopoly;

public class GefaengnisFrei implements Aktion, Serializable {

	private Monopoly monopoly;
	
	public GefaengnisFrei(Monopoly monopoly) {
		this.monopoly = monopoly;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
//		ereignis.gefaengnisfrei(spieler);
//		jail.release(spieler);
		monopoly.getJail().release(monopoly.getTurn().getWerIstDran());
		
	}
	public String toString(){
		return "Sie kommen aus dem Gefängnis Frei.";
	}

}
