package monopoly.local.valueobjects;

import java.io.Serializable;

public class Karte implements Serializable{
	
	private int id;
	private String beschreibung, titel;
	
	/**
	 * Konstruktor der Klasse Karte
	 * 
	 * @param id
	 * @param beschreibung
	 * @param titel
	 */
	public Karte(int id, String beschreibung, String titel){
		this.setId(id);
		this.setBeschreibung(beschreibung);
		this.setTitel(titel);
	}

	/**
	 * 
	 * @return: gibt die Feld-Id zurück
	 */
	public int getId() {
		return id;
	}

	/**
	 * setzt die Feld-Id neu
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return: gibt die Beschreibung des Feldes zurück 
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * set die Beschreibung des Feldes neu
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * 
	 * @return: gibt den Titel des Feldes zurück 
	 */
	public String getTitel() {
		return titel;
	}

	/**
	 * setzt den Titel des Feldes neu
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
}
