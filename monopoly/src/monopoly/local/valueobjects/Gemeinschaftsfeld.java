package monopoly.local.valueobjects;

import monopoly.local.domain.Monopoly;

public class Gemeinschaftsfeld extends Feld{

	private Spieler besitzer = new Spieler("Bank", 98, null, -1);
	private Gemeinschaftskarten deck; 
	
	/**
	 * Konstruktor der Klasse Gemeischaftsfeld 
	 * erbt von Feld
	 * 
	 * @param name: Name des Feldes
	 * @param nr: Feldnummer
	 */
	public Gemeinschaftsfeld(String name, int nr){
		super("Gemeinschaftsfeld", nr);
	}
	
	/**
	 * Methode zum setzen des Decks
	 */
	public void setDeck(Gemeinschaftskarten deck){
		this.deck = deck;
	}
	
	/**
	 * 
	 * @return: gibt eine Aktion zurück
	 */
	public Aktion getEreignis(){
		return deck.karteZiehen();
	}
	
	/**
	 * gibt den Besitzer des Feldes zurück 
	 */
	public Spieler getBesitzer(){
		return besitzer;
	}

}