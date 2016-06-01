package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class FeldMitLos extends Aktion {
	
	private Feld target;

	public FeldMitLos(Spieler spieler, Feld target, Spielfeld spielfeld, Spielerverwaltung spielerverwaltung) {
		super(spieler, spielfeld, spielerverwaltung);
		this.target = target;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ausfuehren() {
		Feld current = spieler.getSpielerPosition();
		spieler.setSpielerPosition(target);
		if(current.getNummer() >= target.getNummer()){
			spieler.setSpielerBudget(spieler.getSpielerBudget() + 4000);
		}
	}

}


