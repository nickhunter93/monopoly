/**
 * erbt von FeldValue
 * baut eine Aktion 
 */
public class Aktion extends FeldValue {
	
	private String aktion;
/**
 * 	Konstrukror der Klasse Aktion
 */
	public Aktion(String name, int wert, boolean status, String aktion){
		super(name, wert, status);
		this.aktion = aktion;
	}
/**
 * @return: gibt einen String zurueck der: den Namen der Aktion beinhaltet
 */
	public String toString(){
		return(name + "\n" + aktion);
	}

}
