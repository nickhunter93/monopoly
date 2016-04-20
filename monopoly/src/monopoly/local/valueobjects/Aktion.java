package monopoly.local.valueobjects;

public class Aktion extends Feld	 {
	
	private String aktion;
	
	public Aktion(String name, int wert, boolean status, String aktion,int nr){
//		super(name, wert, status);
		super(name,nr);
		this.aktion = aktion;
	}
	
	public String toString(){
		return(name + "\n" + aktion);
	}

}
