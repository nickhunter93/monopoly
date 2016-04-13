
public class Strasse extends FeldValue {
	
	private int kaufpreis, mietpreis, haeuseranzahl,besitzer;
	boolean hypothek;
	
	public Strasse(String name, int kaufpreis, int mietpreis, boolean hypothek){
		super(name);
		this.kaufpreis = kaufpreis;
		this.mietpreis = mietpreis;
		this.haeuseranzahl = 0;
		this.hypothek = hypothek;
		this.besitzer = -1;
	}

	public void setHypothek(boolean hypothek){
		this.hypothek = hypothek;
	}

	public boolean bauHaus(){
		if(haeuseranzahl <= 4){
			haeuseranzahl++;
			return true;
		} else{
			return false;
		}
	}
	
	public int getBesitzer(){
		return besitzer;
	}
	
	public void setBesitzer(int besitzer){
		this.besitzer = besitzer;
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
		return(name + "\n" +
	    "Kaufpreis: " + kaufpreis + " EUR" + "\n" +
		"Mietpreis: " + mietpreis + " EUR/Monat" + "\n" +
	    "Häuseranzahl: " + haeuseranzahl + "\n" +
		"Status: "
		);
	}
}
