package monopoly.local.valueobjects;

import java.io.Serializable;

import monopoly.local.domain.Monopoly;

public class Ereignisfeld extends Feld implements Serializable{
	
	private Spieler besitzer = new Spieler("Bank", 98, null, -1);
	private Ereigniskarten deck; 
	
	/**
	 * Konstruktor der Klasse Ereignis 
	 * 
	 * @param name: Name des Feldes 
	 * @param nr: Feldnummer 
	 */
	public Ereignisfeld(String name, int nr){
		super("Ereignisfeld", nr);
	}
	
	/**
	 * setzt das Kartendeck neu (mischen???)
	 * @param deck
	 */
	public void setDeck(Ereigniskarten deck){
		this.deck = deck;
	}
	
	/**
	 * 
	 * @return: gibt das aktuelle Deck zurück
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
