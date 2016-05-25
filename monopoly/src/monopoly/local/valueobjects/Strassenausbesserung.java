package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class Strassenausbesserung extends Aktion{

	public Strassenausbesserung(Spielfeld spielfeld, Spielerverwaltung spielerverwaltung) {
		super(spielfeld, spielerverwaltung);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ausfuehren(Spieler spieler) {
		zahlung.strassenausbesserung(spieler);
	}

}
