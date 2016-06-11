package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public abstract class Deck {

	public Deck(){
		
	}
	
	public abstract void deckMischen();
	
	public abstract Aktion karteZiehen(Monopoly monopoly);
	
}
