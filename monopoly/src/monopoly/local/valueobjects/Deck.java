package monopoly.local.valueobjects;

import java.io.Serializable;

import monopoly.local.domain.Monopoly;

public abstract class Deck implements Serializable {

	/**
	 * Konstruktor der Klasse Deck
	 */
	public Deck(){
		
	}
	
	public abstract void deckMischen();
	
	public abstract Aktion karteZiehen();
	
}