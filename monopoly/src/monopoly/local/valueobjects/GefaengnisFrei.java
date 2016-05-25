package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class GefaengnisFrei extends Aktion {

	public GefaengnisFrei(Spielfeld spielfeld, Spielerverwaltung spielerverwaltung) {
		super(spielfeld, spielerverwaltung);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ausfuehren(Spieler spieler) {
		ereignis.gefaengnisfrei(spieler);
	}

}
