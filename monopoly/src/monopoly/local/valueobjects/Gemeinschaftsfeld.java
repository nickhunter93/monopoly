package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Gemeinschaftsfeld extends Feld{

	private Spieler besitzer = new Spieler("Bank", 98, null, -1);
	private Gemeinschaftskarten deck; 
	
	public Gemeinschaftsfeld(String name, int nr){
		super("Gemeinschaftsfeld", nr);
	}
	
	public void setDeck(Gemeinschaftskarten deck){
		this.deck = deck;
	}
	
	public Aktion getEreignis(){
		return deck.karteZiehen();
	}
	
	public Spieler getBesitzer(){
		return besitzer;
	}

}