package monopoly.local.valueobjects;

public class Strasse extends Feld {
	
	private int buyPrice, rent, houseNumber,housePrize;
	private Spieler owner;
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
	 * @return: gibt den Wert der Hypothek als boolean-Wert zur�ck
	 */
	public boolean getHypothek(){
		return this.hypothek;
	}
	
	/**
	 * setzt den Wert der Hypothek neu wenn sie aufgenommen oder bezahlt wurde
	 * gibt einen String mit entsprechendem Text zur�ck 
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
	 * baut ein Haus auf der Stra�e wenn der Spieler ausreichend Geld besitzt 
	 * und es noch m�glich ist H�user zu bauen
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
	 * @return: gibt den Besitzer als einen Spieler zur�ck
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
	 * @return: gibt den Kaufpreis der Stra�e zur�ck
	 */
	public int getKaufpreis(){
		return buyPrice;
	}
	
	/**
	 * @return: gibt den Mietpreis einer Stra�e zur�ck
	 */
	public int getMietpreis(){
		double factor = houseNumber*0.2;
		int price = (int) (rent * (factor+1));
		return price;
	}
	
	/**
	 * @return: gibt die Anzahl der H�user zur�ck die auf der Stra�e stehen
	 */
	public int getHaeuseranzahl(){
		return houseNumber;
	}
	
	/**
	 * wandelt den Kaufpreis, den Mietpreis, die H�useranzahl und den Status in einen String um
	 */
	public String toString(){
		return(name + "\n" +
	    "Kaufpreis: " + buyPrice + " EUR" + "\n" +
		"Mietpreis: " + rent + " EUR/Monat" + "\n" +
	    "Häuseranzahl: " + houseNumber + "\n" +
		"Status: "
		);
	}
}
