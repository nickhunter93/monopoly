package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class GefaengnisFrei extends Aktion {

	private Jail jail;
	
	public GefaengnisFrei(Spieler spieler, Jail jail, Spielfeld spielfeld, Spielerverwaltung spielerverwaltung) {
		super(spieler, spielfeld, spielerverwaltung);
		this.jail = jail;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ausfuehren() {
//		ereignis.gefaengnisfrei(spieler);
		jail.release(spieler);
	}

}
