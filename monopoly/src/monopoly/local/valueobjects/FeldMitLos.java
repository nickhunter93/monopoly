package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class FeldMitLos implements Aktion {
	
	private Feld target;
	private Monopoly monopoly;
	private String str;
	
	public FeldMitLos(Monopoly monopoly, Feld target,String str) {
		this.monopoly = monopoly;
		this.target = target;
		this.str = str;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		Feld current = monopoly.getTurn().getWerIstDran().getSpielerPosition();
		monopoly.getTurn().getWerIstDran().setSpielerPosition(target);
		if(current.getNummer() >= target.getNummer()){
			monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() + 4000);
		}
	}
	
	public String toString(){
		return str;
	}


}


