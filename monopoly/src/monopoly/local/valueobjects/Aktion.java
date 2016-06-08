package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public abstract class Aktion {
	
	protected Ereignis ereignis = new Ereignis();
	protected Payment zahlung = new Payment();
	protected Spielfeld spielfeld;
	protected Spielerverwaltung spielerverwaltung;
	private String aktion;
	protected Spieler spieler;
	
	/**
	 * Konstruktor der Klasse Aktion 
	 * @param spieler: Spieler der eine Aktion erh�lt/ausf�hrt
	 * @param spielfeld: das aktuelle Spielfeld
	 * @param spielerverwaltung: die aktuelle Spielerverwaltung
	 */
	public Aktion(Spieler spieler, Spielfeld spielfeld, Spielerverwaltung spielerverwaltung){
		setAktion(aktion);
		this.spielfeld = spielfeld;
		this.spielerverwaltung = spielerverwaltung;
		this.spieler = spieler;
	}
	
	/**
	 * gibt eine Aktion zur�ck 
	 * @return
	 */
	public String getAktion() {
		return aktion;
	}

	/**
	 * setzt eine Aktion neu
	 * @param aktion: Aktion die neu gesetzt werden soll
	 */
	public void setAktion(String aktion) {
		this.aktion = aktion;
	}
	
	/**
	 * @return: gibt einen Spieler zur�ck 
	 */
	public Spieler getSpieler(){
		return spieler;
	}
	
	/**
	 * setzt den Spieler f�r eine Aktion neu
	 * @param spieler: Spieler der neu gesetzt wird
	 */
	public void setSpieler(Spieler spieler){
		this.spieler = spieler;
	}

	/**
	 * Abstrakte Methode zum ausf�hren einer Aktion
	 */
	public abstract void ausfuehren();

}
