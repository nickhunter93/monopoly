package monopoly.local.persistenz;

import monopoly.local.valueobjects.Ereigniskarten;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;

public class Ereignisfeld extends Feld{
	
	private Spieler besitzer = new Spieler("Bank", 98, null, -1);
	private Ereigniskarten deck; 
	
	public Ereignisfeld(String name, int nr){
		super("Ereignisfeld", nr);
	}
	
	public void setDeck(Ereigniskarten deck){
		this.deck = deck;
	}
	
	public void getEreignis(Spieler spieler){
		deck.karteZiehen(spieler);
	}
	
	public Spieler getBesitzer(){
		return besitzer;
	}
	

}
