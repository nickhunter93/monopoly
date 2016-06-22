package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class FeldMitLos implements Aktion {
	
	private Feld target;
	private Monopoly monopoly;

	public FeldMitLos(Monopoly monopoly, Feld target) {
		this.monopoly = monopoly;
		this.target = target;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		Feld current = monopoly.getTurn().getWerIstDran().getSpielerPosition();
		monopoly.getTurn().getWerIstDran().setSpielerPosition(target);
		if(current.getNummer() >= target.getNummer()){
			monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() + 4000);
		}
	}
	

}


