package monopoly.local.valueobjects;

public class Aktion extends Feld	 {

	private String aktion;
	
	/**
	 * Konstruktor der Klasse Aktion
	 * erbt von Feld
	 * 
	 * @param name: Name des Feldes welches �bergeben wird
	 * @param wert: 
	 * @param status: 
	 * @param aktion: 
	 * @param nr: Nummer des Feldes welches �bergeben wurde
	 */
	public Aktion(String name, int wert, boolean status, String aktion,int nr){
//		super(name, wert, status);
		super(name,nr);
		this.aktion = aktion;
	}
	
	/**
	 * gibt den Aktionsnamen als String aus 
	 */
	public String toString(){
		return(name + "\n" + aktion);
	}

}
