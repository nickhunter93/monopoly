
public class Strasse extends FeldValue {
	
	private int kaufpreis, mietpreis, haeuseranzahl;
	boolean hypotheek;
	
	public Strasse(String name, int wert, boolean status, int kaufpreis, int mietpreis, int haeuseranzahl, boolean hypotheek){
		super(name, wert, status);
		this.kaufpreis = kaufpreis;
		this.mietpreis = mietpreis;
		this.haeuseranzahl = haeuseranzahl;
		this.hypotheek = hypotheek;
	}
	
	public void setHypotheek(boolean hypotheek){
		this.hypotheek = hypotheek;
	}

	public int bauHaus(){
		return (haeuseranzahl + 1);
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
