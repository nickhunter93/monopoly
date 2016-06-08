package monopoly.local.domain.exceptions;

import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;

public class HausbauException extends Exception {

	private Spieler spieler;
	private Feld feld;
	
	public HausbauException(Spieler spieler, Feld feld) {
		super("Spieler " + spieler.getSpielerName() + " kann kein Haus auf Feld " + feld.getName() + " bauen.");
		this.spieler = spieler;
		this.feld = feld;
	}

	public Spieler getSpieler() {
		return spieler;
	}

	public Feld getFeld() {
		return feld;
	}
}
