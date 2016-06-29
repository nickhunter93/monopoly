package monopoly.local.domain.exceptions;

import monopoly.local.valueobjects.Spieler;

public class GehaltException extends Exception {
	private Spieler spieler;
	
	/**
	 *Konstruktor der Klasse GehaltException
	 *erbt von Exeption
	 *  
	 * @param spieler
	 */
	public GehaltException(Spieler spieler){
		super("Spieler "+spieler.getSpielerName()+" hat nicht genug Geld für diese Aktion.");
		this.spieler = spieler;
	}
}
