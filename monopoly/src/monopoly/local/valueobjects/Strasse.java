package monopoly.local.valueobjects;

public class Strasse extends Feld {
	
	private int kaufpreis, mietpreis, haeuseranzahl,hauspreis;
	private Spieler besitzer;
	boolean hypothek;
	
	public Strasse(String name, int kaufpreis, int mietpreis, boolean hypothek,int nr){
		super(name,nr);
		this.kaufpreis = kaufpreis;
		this.mietpreis = mietpreis;
		this.haeuseranzahl = 0;
		this.hauspreis = 250;
		this.hypothek = hypothek;
		this.besitzer = null;
	}
	
	public boolean getHypothek(){
		return this.hypothek;
	}
	
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
	
	public Spieler getBesitzer(){
		return besitzer;
	}
	
	public void setBesitzer(Spieler besitzer){
		this.besitzer = besitzer;
	}
	
	public int getKaufpreis(){
		return kaufpreis;
	}
	
	public int getMietpreis(){
		double faktor = haeuseranzahl*0.2;
		int preis = (int) (mietpreis * (faktor+1));
		return preis;
	}
	
	public int getHaeuseranzahl(){
		return haeuseranzahl;
	}
	
	public String toString(){
		return(name + "\n" +
	    "Kaufpreis: " + kaufpreis + " EUR" + "\n" +
		"Mietpreis: " + mietpreis + " EUR/Monat" + "\n" +
	    "Häuseranzahl: " + haeuseranzahl + "\n" +
		"Status: "
		);
	}
}
