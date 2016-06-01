package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class FeldOhneLos extends Aktion {

	private Feld target;
	
	public FeldOhneLos(Spieler spieler, Feld target, Spielfeld spielfeld, Spielerverwaltung spielerverwaltung) {
		super(spieler, spielfeld, spielerverwaltung);
		this.target = target;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ausfuehren() {
		spieler.setSpielerPosition(target);
//		ereignis.aufFeldOhneLos(spieler, spielfeld.getSpielfeld()[7]);		
	}

}
