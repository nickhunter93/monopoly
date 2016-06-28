package monopoly.local.domain.exceptions;

import monopoly.local.valueobjects.Spieler;

public class PlayerException extends Exception {
	Spieler spieler;
	public PlayerException(Spieler spieler){
		super("Spieler "+spieler.getSpielerName()+" kann dem Spiel nicht beitreten.");
		this.spieler = spieler;
	}
}
