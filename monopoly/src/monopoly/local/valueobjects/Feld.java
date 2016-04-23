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
	protected int number;

	/**
	 * Konstruktor der Klasse Feld
	 * 
	 * @param name: der Name des Feldes
	 * @param nr: die Nummer des Feldes
	 */
	public Feld(String name, int nr) {
		super();
		this.name = name;
		this.number = nr;
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
		return number;
	}

	/**
	 * @param nummer: setzt die Feldnummer neu
	 */
	public void setNummer(int number) {
		this.number = number;
	}
	
	/**
	 * Methode die bestimmt wann ein Feld gleich ist
	 * dann gleich wenn: der Feldname und die Feldnummer übereinstimmen
	 */
	public boolean equals(Feld field){
		if(field.getName().equals(this.name)){
			if(field.getNummer() == this.number){
				return true;
			}
		}
		return false;
	}
}