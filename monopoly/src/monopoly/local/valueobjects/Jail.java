package monopoly.local.valueobjects;

import java.util.Vector;

public class Jail extends Feld{
	
	private Vector<Spieler> insassen;
	private Spieler besitzer = new Spieler("Bank", 98, null, -1);
	
	/**
	 * Konstruktor der Klasse Jail
	 * 
	 * @param name: Name des Feldes
	 * @param nummer: Feldnummer
	 */
	public Jail(String name,int nummer){
		super(name, nummer);
		insassen = new Vector<Spieler>();
	}

	/**
	 * 
	 * @return: gibt einen Vektor mit Spielern zurück die inhaftiert sind
	 */
	public Vector<Spieler> getInsassen() {
		return insassen;
	}

	/**
	 * fügt einen Spieler den Insassenvektor hinzu 
	 * 
	 * @param spieler: Spieler der dem Vektor hinzugefügt wird 
	 */
	public void addInsasse(Spieler spieler){
		insassen.add(spieler);
		spieler.setSpielerPosition(this);
	}
	
	/**
	 * entfehrnt einen Spieler aus dem Insassenvektor
	 * 
	 * @param spieler: Spieler das aus dem Vektor entfehrnt wird
	 */
	public void release(Spieler spieler){
		insassen.remove(spieler);
	}
	
	/**
	 * prüft ob ein Spieler im Gefängnis ist 
	 * 
	 * @param spieler: Spieler der überprüft wird
	 * @return: gibt einen boolean-Wert zurück
	 */
	public boolean isPlayerIn(Spieler spieler){
		return insassen.contains(spieler);
	}

	/**
	 * gibt den Besitzer des Feldes zurück
	 */
	public Spieler getBesitzer() {
		return besitzer;
	}
}
