package monopoly.local.valueobjects;


public class GefaengnisFrei implements Aktion {

	private Jail jail;
	private Spieler spieler;
	
	public GefaengnisFrei(Spieler spieler, Jail jail) {
		this.spieler = spieler;
		this.jail = jail;
		// TODO Auto-generated constructor stub
	}

	public void ausfuehren() {
//		ereignis.gefaengnisfrei(spieler);
		jail.release(spieler);
	}
	

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	
	public Spieler getSpieler() {
		return spieler;
	}

}
