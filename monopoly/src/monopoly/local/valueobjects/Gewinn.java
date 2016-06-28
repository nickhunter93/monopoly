package monopoly.local.valueobjects;

import java.io.Serializable;

import monopoly.local.domain.Monopoly;

public class Gewinn implements Aktion, Serializable {
	
	private int betrag;
	private Monopoly monopoly;
	private String str;
	
	public Gewinn(Monopoly monopoly, int betrag,String str) {
		this.betrag = betrag;
		this.monopoly = monopoly;
		this.str = str;
		// TODO Auto-generated constructor stub
	}
	
	public void ausfuehren() {
		monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() + betrag);
	}
	
	public String toString(){
		return str;
	}


}
