/**
 * erbt von FeldValue
 * Vorlage zum erstellen von Strassen
 */
public class Strasse extends FeldValue {
	
	private int kaufpreis, mietpreis, haeuseranzahl;
/**
 * 	Konstruktor der Klasse Strasse
 */
	public Strasse(String name, int wert, boolean status, int kaufpreis, int mietpreis, int haeuseranzahl){
		super(name, wert, status);
		this.kaufpreis = kaufpreis;
		this.mietpreis = mietpreis;
		this.haeuseranzahl = haeuseranzahl;
	}
	
	/**
	 * @return: gibt den Kaufpreis der Strasse als Zahl zurueck 
	 */
	public int getKaufpreis(){
		return kaufpreis;
	}
	/**
	 * @return: gibt den Mitpreis als Zahl zurueck
	 */
	public int getMietpreis(){
		return mietpreis;
	}
	/**
	 * @return: gibt die Anzahl der Hauser zurueck die auf der Strasse stehen
	 */
	public int getHaeuseranzahl(){
		return haeuseranzahl;
	}
	/**
	 * @return: gibt einen String zurueck der den Namen, den Kaufpreis, 
	 * den Mietpreis, die Haeuseranzahl und den Status beinhaltet 
	 */
	public String toString(){
		String feldStatus = status ? "Spieler auf dem Feld" : "Feld ist frei";
		return(name + "\n" +
	    "Kaufpreis: " + kaufpreis + " EUR" + "\n" +
		"Mietpreis: " + mietpreis + " EUR/Monat" + "\n" +
	    "Häuseranzahl: " + haeuseranzahl + "\n" +
		"Status: " + feldStatus
		);
	}
}
