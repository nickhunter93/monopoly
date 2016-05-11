package monopoly.local.valueobjects;

import java.util.Vector;

public class Ereigniskarte {

private Vector<Karte> deck;

	public Ereigniskarte(){
		deck  = new Vector<Karte>();
		for(int i = 0; i < 16; i++){
			deck.add(new Karte(i, "Beschreibung","Titel"));
		}
	}

}
