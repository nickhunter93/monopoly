package monopoly.local.valueobjects;

import monopoly.local.domain.Spielverwaltung;
import monopoly.local.domain.Spielerverwaltung;

public class Payment extends SpezialAktion {
	
	private Spielverwaltung feldverwaltung;
	private Spielerverwaltung verwaltung;
	private Strasse[] yourStreets;
	
	public Payment() {
	}
	
	//Geignet für Karten, die einen Spieler einfache Gelbeträge hinzugeben oder abziehen.

	public void payment(Spieler spieler, int betrag){
		betrag = spieler.getSpielerBudget() + betrag;
		spieler.setSpielerBudget(betrag);
	}

	//Ein Spieler hat Geburtstag und erhält von jedem Mitspieler 2000.
	
	public void birthday(Spieler spieler){
		for(Spieler s : verwaltung.getAllSpieler()){
			if(s.getSpielerNummer() == spieler.getSpielerNummer()){
				spieler.setSpielerBudget(spieler.getSpielerBudget());
			} else {
			s.setSpielerBudget(s.getSpielerBudget() - 2000);
			spieler.setSpielerBudget(spieler.getSpielerBudget() + 2000);
			}
		}
	}
	
	//Der Spieler muss für jedes Haus auf seinen Straßen 500 zahlen und für jedes Hotel 2000.
	
	public void renovation(Spieler spieler){
		yourStreets = feldverwaltung.getYourStreets(spieler);
		int hausbetrag = 500;
		int hotelbetrag = 2000;
		
		for(Strasse strasse : yourStreets){
			if(strasse.getHaeuseranzahl() == 5){
				spieler.setSpielerBudget(spieler.getSpielerBudget() - hotelbetrag);
			} else{
			spieler.setSpielerBudget(spieler.getSpielerBudget() - (hausbetrag * strasse.getHaeuseranzahl()));
			}
		}
	}
	
	//Der Spieler muss für jedes Haus auf seinen Straßen 800 zahlen und für jedes Hotel 2300.
	
	public void strassenausbesserung(Spieler spieler){
		yourStreets = feldverwaltung.getYourStreets(spieler);
		int hausbetrag = 800;
		int hotelbetrag = 2300;
			
		for(Strasse strasse : yourStreets){
			if(strasse.getHaeuseranzahl() == 5){
				spieler.setSpielerBudget(spieler.getSpielerBudget() - hotelbetrag);
			} else{
			spieler.setSpielerBudget(spieler.getSpielerBudget() - (hausbetrag * strasse.getHaeuseranzahl()));
			}
		}
	}

}
