package monopoly.local.valueobjects;

import java.io.Serializable;

import monopoly.local.domain.Monopoly;

public abstract class Deck implements Serializable {

	public Deck(){
		
	}
	
	public abstract void deckMischen();
	
	public abstract Aktion karteZiehen();
	
}
