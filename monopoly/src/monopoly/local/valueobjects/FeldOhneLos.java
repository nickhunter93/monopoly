package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class FeldOhneLos implements Aktion {

	private Feld target;
	private Monopoly monopoly;
	
	public FeldOhneLos(Monopoly monopoly, Feld target) {
		this.target = target;
		this.monopoly = monopoly;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		monopoly.getTurn().getWerIstDran().setSpielerPosition(target);
//		ereignis.aufFeldOhneLos(spieler, spielfeld.getSpielfeld()[7]);		
	}

}
