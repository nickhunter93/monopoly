package monopoly.local.persistenz;

import monopoly.local.valueobjects.Gemeinschaftskarte;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;

public class Gemeinschaftsfeld extends Feld{

	
	private Gemeinschaftskarte deck; 
	
	public Gemeinschaftsfeld(String name, int nr){
		super("Gemeinschaftsfeld", nr);
	}
	
	public void getEreignis(Spieler spieler){
		deck.karteZiehen(spieler);
	}
	

}