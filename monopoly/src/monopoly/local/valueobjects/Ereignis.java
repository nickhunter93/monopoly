package monopoly.local.valueobjects;


public class Ereignis {

	private Jail jail;
	
	
	//Geeignet für Karten, die einen auf eine bestimmte Straße senden, oder um ihn auf Los oder in das Gefängnis zu setzen, ohne dass du Geld erhältst.
	//Auch wenn der Spieler Felder zurück muss, kann man das hier machen.
	
	public void aufFeldOhneLos(Spieler spieler, Feld target){
		spieler.setSpielerPosition(target);
	}
	
	//Setzt den Spieler auf ein bestimmtes Feld. Wenn er über Los kommt, zieht er Geld ein.
	
	public void aufFeldMitLos(Spieler spieler, Feld target){
		Feld current = spieler.getSpielerPosition();
		spieler.setSpielerPosition(target);
		if(current.getNummer() >= target.getNummer()){
			spieler.setSpielerBudget(spieler.getSpielerBudget() + 4000);
		}
	}

	//Setzt den Spieler aus dem Gefängnis frei.
	
	public void gefaengnisFrei(Spieler spieler){
		if(jail.isPlayerIn(spieler)){
			jail.release(spieler);
		}else{
			jail.getInsassen();
		}
	}
	
	public void gefaengnisfrei(Spieler spieler){
		jail.release(spieler);
	}

}
