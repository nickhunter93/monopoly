package monopoly.local.valueobjects;


import monopoly.local.domain.Monopoly;


public class Birthday implements Aktion {
	
private Monopoly monopoly;
private String str;

	public Birthday(Monopoly monopoly, String str) {
		this.monopoly = monopoly;
		this.str = str;
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
		return str;
	}
}
