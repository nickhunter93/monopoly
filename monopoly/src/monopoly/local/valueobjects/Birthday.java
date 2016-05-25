package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class Birthday extends Aktion {

	public Birthday(Spielfeld spielfeld, Spielerverwaltung spielerverwaltung) {
		super(spielfeld, spielerverwaltung);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ausfuehren(Spieler spieler) {
		zahlung.birthday(spieler);
	}

}
