package monopoly.local.valueobjects;

public class ToJail extends Feld {

	private Jail jail;
	private Spielfeld field;
	
	/**
	 * Konstruktor der Klasse ToJail 
	 * erbt von Feld
	 */
	public ToJail(String name, int nr) {
		super(name, nr);
		
	}

	/**
	 * schickt den Spieler ins Gefaengnis
	 */
	public void getToJail(Spieler player){
		player.setSpielerPosition(field.getJail());		
		jail.addInsasse(player);
	}
}
