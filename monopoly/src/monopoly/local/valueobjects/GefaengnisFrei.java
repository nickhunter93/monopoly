package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class GefaengnisFrei implements Aktion {

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

}
