package monopoly.local.valueobjects;

public class Strasse extends Feld {
	
	private int buyPrice, rent, houseNumber,housePrize;
	private Spieler owner;
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
	public Strasse(String name, int prize, int rent, boolean hypothek,int nr){
		super(name,nr);
		this.buyPrice = prize;
		this.rent = rent;
		this.houseNumber = 0;
		this.housePrize = 250;
		this.hypothek = hypothek;
		this.owner = null;
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
		int budget = owner.getSpielerBudget();
		int value = buyPrice / 2;
		if(!hypothek){
			budget = budget + value;
			owner.setSpielerBudget(budget);
			this.hypothek = true;
			return "Hypothek wurde aufgenommen.";
		}else{
			if(budget - value >= 0){
				budget = budget - value;
				owner.setSpielerBudget(budget);
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
	public boolean bauHaus(Spieler player){
		int budget = player.getSpielerBudget();
		if(houseNumber <= 4){
			houseNumber++;
			if(houseNumber < 4){
				if(budget-housePrize >= 0){
					player.setSpielerBudget(budget-housePrize);
				}else {
					return false;
				}
			}else{
				if((budget - (2*housePrize)) >= 0){
					player.setSpielerBudget(budget-(2*housePrize));
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
		return owner;
	}
	
	/**
	 * setzt den Besitzer neu 
	 */
	public void setBesitzer(Spieler owner){
		this.owner = owner;
	}
	
	/**
	 * 
	 * @return: gibt den Kaufpreis der Straße zurück
	 */
	public int getKaufpreis(){
		return buyPrice;
	}
	
	/**
	 * @return: gibt den Mietpreis einer Straße zurück
	 */
	public int getMietpreis(){
		double factor = houseNumber*0.2;
		int price = (int) (rent * (factor+1));
		return price;
	}
	
	/**
	 * @return: gibt die Anzahl der Häuser zurück die auf der Straße stehen
	 */
	public int getHaeuseranzahl(){
		return houseNumber;
	}
	
	/**
	 * wandelt den Kaufpreis, den Mietpreis, die Häuseranzahl und den Status in einen String um
	 */
	public String toString(){
		return(name + "\n" +
	    "Kaufpreis: " + buyPrice + " EUR" + "\n" +
		"Mietpreis: " + rent + " EUR/Monat" + "\n" +
	    "HÃ¤useranzahl: " + houseNumber + "\n" +
		"Status: "
		);
	}
}
