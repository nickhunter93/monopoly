package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class Renovation extends Aktion {

	public Renovation(Spielfeld spielfeld, Spielerverwaltung spielerverwaltung) {
		super(spielfeld, spielerverwaltung);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ausfuehren(Spieler spieler) {
		zahlung.renovation(spieler);
		
	}

}
