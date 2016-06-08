package monopoly.local.valueobjects;

public abstract class Deck {

	public Deck(){
		
	}
	
	public abstract void deckMischen();
	
	public abstract Aktion karteZiehen(Spieler spieler);
	
}
