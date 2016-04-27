package monopoly.local.valueobjects;

public abstract class Aktion /* extends Feld */	 {
	
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
	public Aktion(){
//		super(name, wert, status);
		//super(name,nr);
		this.setAktion(aktion);
	}

	public abstract void ausfuehren();

	public String getAktion() {
		return aktion;
	}

	public void setAktion(String aktion) {
		this.aktion = aktion;
	}
}
