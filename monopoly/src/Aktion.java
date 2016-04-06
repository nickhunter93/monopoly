
public class Aktion extends FeldValue {
	
	private String aktion;
	
	public Aktion(String name, int wert, boolean status, String aktion){
		super(name, wert, status);
		this.aktion = aktion;
	}
	
	public String toString(){
		return(name + "\n" + aktion);
	}

}
