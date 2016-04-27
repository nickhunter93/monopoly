package monopoly.local.valueobjects;

import java.util.Vector;

public class Gemeinschaftskarten {
	
private Vector<Karte> deck;
	
	public Gemeinschaftskarten(){
		deck  = new Vector<Karte>();
		for(int i = 0; i < 16; i++){
			deck.add(new Karte(i, "Beschreibung","Titel"));
		}
	}
}
