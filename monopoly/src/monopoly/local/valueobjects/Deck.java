package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public abstract class Deck {

	/**
	 * Konstruktor der Klasse Deck
	 */
	public Deck(){
		
	}
	
	public abstract void deckMischen();
	
	public abstract Aktion karteZiehen();
	
}