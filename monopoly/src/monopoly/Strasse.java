
public class Strasse extends FeldValue {
	
	private int kaufpreis, mietpreis, haeuseranzahl;
	
	public Strasse(String name, int wert, boolean status, int kaufpreis, int mietpreis, int haeuseranzahl){
		super(name, wert, status);
		this.kaufpreis = kaufpreis;
		this.mietpreis = mietpreis;
		this.haeuseranzahl = haeuseranzahl;
	}
	
	
	public int getKaufpreis(){
		return kaufpreis;
	}
	
	public int getMietpreis(){
		return mietpreis;
	}
	
	public int getHaeuseranzahl(){
		return haeuseranzahl;
	}
	
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
