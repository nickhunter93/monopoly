package monopoly.local.valueobjects;


import java.io.Serializable;

import monopoly.local.domain.Monopoly;


public class Birthday implements Aktion, Serializable {
	
private Monopoly monopoly;
	public Birthday(Monopoly monopoly) {
		this.monopoly = monopoly;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		for(Spieler s : monopoly.getAllSpieler()){
			if(s.getSpielerNummer() == monopoly.getTurn().getWerIstDran().getSpielerNummer()){
				monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget());
			} else {
			s.setSpielerBudget(s.getSpielerBudget() - 2000);
			monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() + 2000);
			}
		}
	}

	public String toString(){
		return ""+monopoly.getTurn().getWerIstDran()+" hat Geburtstag. \nJeder Spieler zahlt ihm 2000 €.";
	}
}
