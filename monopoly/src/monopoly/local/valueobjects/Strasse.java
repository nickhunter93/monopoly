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

	public void setHypothek(boolean hypothek){
		this.hypothek = hypothek;
	}

	public boolean bauHaus(Spieler spieler){
		if(haeuseranzahl <= 4){
			haeuseranzahl++;
			if(haeuseranzahl < 4){
				spieler.setSpielerBudget(spieler.getSpielerBudget()-hauspreis);
			}else{
				spieler.setSpielerBudget(spieler.getSpielerBudget()-(2*hauspreis));
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
