package monopoly.local.valueobjects;

import java.util.Vector;

public class Ereigniskarten {

private Vector<Karte> deck;

	public Ereigniskarten(){
		deck  = new Vector<Karte>();
		for(int i = 0; i < 16; i++){
			deck.add(new Karte(i, "Beschreibung","Titel", new SpezialAktion()));
		}
	}

}
