package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Strassenausbesserung implements Aktion{

	private Monopoly monopoly;
	private String str;

	public Strassenausbesserung(Monopoly monopoly,String str) {
		this.monopoly = monopoly;
		this.str = str;
		// TODO Auto-generated constructor stub
	}

	
	public void ausfuehren(){
		int hausbetrag = 800;
		int hotelbetrag = 2300;
			
		for(Strasse strasse : monopoly.getYourStreets(monopoly.getTurn().getWerIstDran())){
			if(strasse.getHaeuseranzahl() == 5){
				monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() - hotelbetrag);
			} else{
				monopoly.getTurn().getWerIstDran().setSpielerBudget(monopoly.getTurn().getWerIstDran().getSpielerBudget() - (hausbetrag * strasse.getHaeuseranzahl()));
			}
		}
	}
	

	public String toString(){
		return str;
		
	}
}
