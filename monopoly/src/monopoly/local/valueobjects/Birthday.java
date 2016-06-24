package monopoly.local.valueobjects;


import monopoly.local.domain.Monopoly;


public class Birthday implements Aktion {
	
private Spieler spieler; 
private Monopoly monopoly;
	
	public Birthday(Monopoly monopoly) {
		this.monopoly = monopoly;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		for(Spieler s : monopoly.getAllSpieler()){
			if(s.getSpielerNummer() == monopoly.getTurn().getWerIstDran().getSpielerNummer()){
				spieler.setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget());
			} else {
			s.setSpielerBudget(s.getSpielerBudget() - 2000);
			monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() + 2000);
			}
		}
	}

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	
	public Spieler getSpieler() {
		return spieler;
	}

}
