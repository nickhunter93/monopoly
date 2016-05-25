package monopoly.local.valueobjects;

public class ToJail extends Feld {

	private Jail jail;
	private Spieler besitzer = new Spieler("Bank", 98, null, -1);
	
	/**
	 * Konstruktor der Klasse ToJail 
	 * erbt von Feld
	 */
	public ToJail(String name, int nr, Jail jail) {
		super(name, nr);
		this.jail = jail;
	}

	/**
	 * schickt den Spieler ins Gefaengnis
	 */
	public void getToJail(Spieler player){
		player.setSpielerPosition(jail);		
		jail.addInsasse(player);
	}
	public Spieler getBesitzer() {
		return besitzer;
	}
}
