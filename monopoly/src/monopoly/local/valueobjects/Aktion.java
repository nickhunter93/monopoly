package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public interface Aktion {

	public void ausfuehren();
	
	public void setSpieler(Spieler spieler);
	
	public Spieler getSpieler();

}
