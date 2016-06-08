package monopoly.local.valueobjects;


public class FeldMitLos implements Aktion {
	
	private Feld target;
	private Spieler spieler;

	public FeldMitLos(Spieler spieler, Feld target) {
		this.target = target;
		this.spieler = spieler;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		Feld current = spieler.getSpielerPosition();
		spieler.setSpielerPosition(target);
		if(current.getNummer() >= target.getNummer()){
			spieler.setSpielerBudget(spieler.getSpielerBudget() + 4000);
		}
	}

}


