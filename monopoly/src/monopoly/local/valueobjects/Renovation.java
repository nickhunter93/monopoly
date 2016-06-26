package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Renovation implements Aktion {
	

	private Monopoly monopoly;

	public Renovation(Monopoly monopoly) {
		this.monopoly = monopoly;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		int hausbetrag = 500;
		int hotelbetrag = 2000;
		if(monopoly.getYourStreets(monopoly.getTurn().getWerIstDran()) != null){
			for(Strasse strasse : monopoly.getYourStreets(monopoly.getTurn().getWerIstDran())){
				if(strasse.getHaeuseranzahl() == 5){
					monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() - hotelbetrag);
				} else{
					monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() - (hausbetrag * strasse.getHaeuseranzahl()));
				}
			}
		}
	}
	
	public String toString(){
		return "Lasse alle Deine Häuser renovieren.\nZahle an die Bank für jedes Haus 500€, für jedes Hotel 2000€.";
	}

}
