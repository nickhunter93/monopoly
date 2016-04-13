package monopoly.local.valueobjects;

public class Aktion extends Feld	 {
	
	private String aktion;
	
	public Aktion(String name, int wert, boolean status, String aktion){
//		super(name, wert, status);
		super(name);
		this.aktion = aktion;
	}
	
	public String toString(){
		return(name + "\n" + aktion);
	}

}
