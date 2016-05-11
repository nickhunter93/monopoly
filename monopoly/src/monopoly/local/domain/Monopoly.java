package monopoly.local.domain;

import monopoly.local.persistenz.PersistenzLaden;
import monopoly.local.ui.cui.SpielStart;

public class Monopoly {

	private PersistenzLaden pmLaden;

	public Monopoly() {
		super();
		pmLaden = new PersistenzLaden();
	}
	
	public SpielStart spielStandLaden(String datei) {
		return pmLaden.loadAll();
	}
	
}
