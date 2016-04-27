package monopoly.local.valueobjects;

//public class Feld<T> {
//	
//	private T inhalt;
//	
//	public Feld(T inhalt){
//		this.inhalt = inhalt;
//	}
//	
//	public T getInhalt(){
//		try{
//			return inhalt;
//		} catch(Exception e){
//			throw new NullPointerException("Kein Inhalt");
//		}
//	}
//}

public class Feld {
	
	protected String name;
	protected int nummer;

	/**
	 * Konstruktor der Klasse Feld
	 * 
	 * @param name: der Name des Feldes
	 * @param nr: die Nummer des Feldes
	 */
	public Feld(String name, int nr) {
		super();
		this.name = name;
		this.nummer = nr;
	}

	/**
	 * @return name: gibt den Namen des Feldes als String zurück
	 */
	public String getName() {
		return name;
	}

	/** 
	 * @param name: setzt den Feldnamen neu
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return nummer: gibt die Feldnummer zurück
	 */
	public int getNummer() {
		return nummer;
	}

	/**
	 * @param nummer: setzt die Feldnummer neu
	 */
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	/**
	 * Methode die bestimmt wann ein Feld gleich ist
	 * dann gleich wenn: der Feldname und die Feldnummer übereinstimmen
	 */
	public boolean equals(Feld feld){
		if(feld.getName().equals(this.name)){
			if(feld.getNummer() == this.nummer){
				return true;
			}
		}
		return false;
	}
}