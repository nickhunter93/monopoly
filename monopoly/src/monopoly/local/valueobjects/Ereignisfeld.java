package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Ereignisfeld extends Feld{
	
	private Spieler besitzer = new Spieler("Bank", 98, null, -1);
	private Ereigniskarten deck; 
	
	public Ereignisfeld(String name, int nr){
		super("Ereignisfeld", nr);
	}
	
	public void setDeck(Ereigniskarten deck){
		this.deck = deck;
	}
	
	public Aktion getEreignis(){
		return deck.karteZiehen();
	}
	
	public Spieler getBesitzer(){
		return besitzer;
	}
	

}
