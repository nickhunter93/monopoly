package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public class Birthday extends Aktion {

	/**
	 * Konstruktor der Klasse 
	 * @param spieler
	 * @param spielfeld
	 * @param spielerverwaltung
	 */
	public Birthday(Spieler spieler, Spielfeld spielfeld, Spielerverwaltung spielerverwaltung) {
		super(spieler, spielfeld, spielerverwaltung);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ausfuehren() {
		for(Spieler s : spielerverwaltung.getAllSpieler()){
			if(s.getSpielerNummer() == spieler.getSpielerNummer()){
				spieler.setSpielerBudget(spieler.getSpielerBudget());
			} else {
			s.setSpielerBudget(s.getSpielerBudget() - 2000);
			spieler.setSpielerBudget(spieler.getSpielerBudget() + 2000);
			}
		}
	}

}
