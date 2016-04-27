package monopoly.local.valueobjects;

public class Strasse extends Feld {
	
	private int kaufpreis, mietpreis, haeuseranzahl,hauspreis;
	private Spieler besitzer;
	boolean hypothek;
	
	/**
	 * Konstrucktor der Klasse Strasse
	 * 
	 * @param name: der Name der Straße
	 * @param prize: der Preis für den man dei Straße kaufen kann
	 * @param rent: der Mietpreis den man bezhalt wenn die Straße sschon gekauft wurde
	 * @param hypothek: der Wert für die Hypothek der Straße
	 * @param nr: die Nummer der Straße
	 */
	public Strasse(String name, int kaufpreis, int mietpreis, boolean hypothek,int nr){
		super(name,nr);
		this.kaufpreis = kaufpreis;
		this.mietpreis = mietpreis;
		this.haeuseranzahl = 0;
		this.hauspreis = 250;
		this.hypothek = hypothek;
		this.besitzer = null;
	}
	
	/**
	 * @return: gibt den Wert der Hypothek als boolean-Wert zurück
	 */
	public boolean getHypothek(){
		return this.hypothek;
	}
	
	/**
	 * setzt den Wert der Hypothek neu wenn sie aufgenommen oder bezahlt wurde
	 * gibt einen String mit entsprechendem Text zurück 
	 */
	public String switchHypothek(){
		int budget = besitzer.getSpielerBudget();
		int wert = kaufpreis / 2;
		if(!hypothek){
			budget = budget + wert;
			besitzer.setSpielerBudget(budget);
			this.hypothek = true;
			return "Hypothek im Wert von " + wert + " auf StraÃŸe " + getName() + " wurde aufgenommen.";
		}else{
			if(budget - wert >= 0){
				budget = budget - wert;
				besitzer.setSpielerBudget(budget);
				this.hypothek = false;
				return "Hypothek wurde bezahlt.";
			}else{
				return "Hypothek konnte nicht bezahlt werden.";
			}
		}
	}

	/**
	 * baut ein Haus auf der Straße wenn der Spieler ausreichend Geld besitzt 
	 * und es noch möglich ist Häuser zu bauen
	 */
	public boolean bauHaus(Spieler spieler){
		int budget = spieler.getSpielerBudget();
		if(haeuseranzahl < 4){
			haeuseranzahl++;
			if(haeuseranzahl < 4){
				if(budget-hauspreis >= 0){
					spieler.setSpielerBudget(budget-hauspreis);
				}else {
					return false;
				}
			}else{
				if((budget - (2*hauspreis)) >= 0){
					spieler.setSpielerBudget(budget-(2*hauspreis));
				}else {
					return false;
				}
			}
			return true;
		} else{
			return false;
		}
	}
	
	/** 
	 * @return: gibt den Besitzer als einen Spieler zurück
	 */
	public Spieler getBesitzer(){
		return besitzer;
	}
	
	/**
	 * setzt den Besitzer neu 
	 */
	public void setBesitzer(Spieler besitzer){
		this.besitzer = besitzer;
	}
	
	/**
	 * 
	 * @return: gibt den Kaufpreis der Straße zurück
	 */
	public int getKaufpreis(){
		return kaufpreis;
	}
	
	/**
	 * @return: gibt den Mietpreis einer Straße zurück
	 */
	public int getMietpreis(){
		double faktor = haeuseranzahl*0.2;
		int preis = (int) (mietpreis * (faktor+1));
		return preis;
	}
	
	/**
	 * @return: gibt die Anzahl der Häuser zurück die auf der Straße stehen
	 */
	public int getHaeuseranzahl(){
		return haeuseranzahl;
	}
	
	/**
	 * wandelt den Kaufpreis, den Mietpreis, die Häuseranzahl und den Status in einen String um
	 */
	public String toString(){
		return(name + "\n" +
	    "Kaufpreis: " + kaufpreis + " EUR" + "\n" +
		"Mietpreis: " + mietpreis + " EUR/Monat" + "\n" +
	    "HÃ¤useranzahl: " + haeuseranzahl + "\n" +
		"Status: "
		);
	}
}
