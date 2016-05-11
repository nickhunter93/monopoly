package monopoly.local.valueobjects;

public class Strasse extends Feld {
	
	private int kaufpreis, mietpreis, haeuseranzahl,hauspreis;
	private Spieler besitzer;
	boolean hypothek;
	
	/**
	 * Konstrucktor der Klasse Strasse
	 * 
	 * @param name: der Name der Stra�e
	 * @param prize: der Preis f�r den man dei Stra�e kaufen kann
	 * @param rent: der Mietpreis den man bezhalt wenn die Stra�e sschon gekauft wurde
	 * @param hypothek: der Wert f�r die Hypothek der Stra�e
	 * @param nr: die Nummer der Stra�e
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
	 * @return: gibt den Wert der Hypothek als boolean-Wert zur�ck
	 */
	public boolean getHypothek(){
		return this.hypothek;
	}
	
	public void setHypothek(boolean hypothek){
		this.hypothek = hypothek;
	}
	
	/**
	 * setzt den Wert der Hypothek neu wenn sie aufgenommen oder bezahlt wurde
	 * gibt einen String mit entsprechendem Text zur�ck 
	 */
	public String switchHypothek(){
		int budget = besitzer.getSpielerBudget();
		int wert = kaufpreis / 2;
		if(!hypothek){
			budget = budget + wert;
			besitzer.setSpielerBudget(budget);
			this.hypothek = true;
			return "Hypothek im Wert von " + wert + " auf Straße " + getName() + " wurde aufgenommen.";
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
	 * baut ein Haus auf der Stra�e wenn der Spieler ausreichend Geld besitzt 
	 * und es noch m�glich ist H�user zu bauen
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
	 * @return: gibt den Besitzer als einen Spieler zur�ck
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
	 * @return: gibt den Kaufpreis der Stra�e zur�ck
	 */
	public int getKaufpreis(){
		return kaufpreis;
	}
	
	/**
	 * @return: gibt den Mietpreis einer Stra�e zur�ck
	 */
	public int getMietpreis(){
		double faktor = haeuseranzahl*0.2;
		int preis = (int) (mietpreis * (faktor+1));
		return preis;
	}
	
	/**
	 * @return: gibt die Anzahl der H�user zur�ck die auf der Stra�e stehen
	 */
	public int getHaeuseranzahl(){
		return haeuseranzahl;
	}
	
	public int getHauspreis(){
		return this.hauspreis;
	}
	
	public void setHaeuseranzahl(int haeuseranzahl){
		this.haeuseranzahl = haeuseranzahl;
	}
	
	public void setHauspreis(int hauspreis){
		this.hauspreis = hauspreis;
	}
	
	/**
	 * wandelt den Kaufpreis, den Mietpreis, die H�useranzahl und den Status in einen String um
	 */
	public String toString(){
		return(name + "\n" +
	    "Kaufpreis: " + kaufpreis + " EUR" + "\n" +
		"Mietpreis: " + mietpreis + " EUR/Monat" + "\n" +
	    "Häuseranzahl: " + haeuseranzahl + "\n" +
		"Status: "
		);
	}
}
