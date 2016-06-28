package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class FeldOhneLos implements Aktion {

	private Feld target;
	private Monopoly monopoly;
	private String str;
	
	public FeldOhneLos(Monopoly monopoly, Feld target,String str) {
		this.target = target;
		this.monopoly = monopoly;
		this.str = str;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		monopoly.getTurn().getWerIstDran().setSpielerPosition(target);
//		ereignis.aufFeldOhneLos(spieler, spielfeld.getSpielfeld()[7]);		
	}
	
	public String toString(){
		return str;
	}

}
