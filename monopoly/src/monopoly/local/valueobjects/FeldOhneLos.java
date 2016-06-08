package monopoly.local.valueobjects;


public class FeldOhneLos implements Aktion {

	private Feld target;
	private Spieler spieler;
	
	public FeldOhneLos(Spieler spieler, Feld target) {
		this.spieler = spieler;
		this.target = target;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
		spieler.setSpielerPosition(target);
//		ereignis.aufFeldOhneLos(spieler, spielfeld.getSpielfeld()[7]);		
	}


	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	
	public Spieler getSpieler() {
		return spieler;
	}
}
