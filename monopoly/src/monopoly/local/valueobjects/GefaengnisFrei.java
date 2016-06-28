package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class GefaengnisFrei implements Aktion {

	private Monopoly monopoly;
	private String str;
	
	public GefaengnisFrei(Monopoly monopoly, String str) {
		this.monopoly = monopoly;
		this.str = str;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
//		ereignis.gefaengnisfrei(spieler);
//		jail.release(spieler);
		monopoly.getJail().release(monopoly.getTurn().getWerIstDran());
		
	}
	public String toString(){
		return str;
	}

}
