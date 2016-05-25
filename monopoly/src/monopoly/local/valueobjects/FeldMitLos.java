package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class FeldMitLos extends Aktion {
	
	public FeldMitLos(Spielfeld spielfeld, Spielerverwaltung spielerverwaltung) {
		super(spielfeld, spielerverwaltung);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ausfuehren(Spieler spieler) {
			ereignis.aufFeldMitLos(spieler, spielfeld.getSpielfeld()[7]);
		}
		
	}


