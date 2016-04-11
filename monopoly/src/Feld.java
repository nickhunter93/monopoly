/**
 * baut ein Feld das einen Inhalt besitzen kann
 */
public class Feld<T> {
	
	private T inhalt;
/**
 * Konstruktor der Klasse Feld	
 */
	public Feld(T inhalt){
		this.inhalt = inhalt;
	}
/**
 * @return: gibt den Inhalt des Feldes zurueck
 * @throws: NullPointerException: geworfen wenn das Feld keinen Inhalt hat	
 */
	public T getInhalt(){
		try{
			return inhalt;
		} catch(Exception e){
			throw new NullPointerException("Kein Inhalt");
		}
	}
}
