package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class FeldOhneLos extends Aktion {

	public FeldOhneLos(Spielfeld spielfeld, Spielerverwaltung spielerverwaltung) {
		super(spielfeld, spielerverwaltung);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ausfuehren(Spieler spieler) {
		ereignis.aufFeldOhneLos(spieler, spielfeld.getSpielfeld()[7]);
		
	}

}
