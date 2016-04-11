/**
 * Oberklasse zu: Strasse,
 * bildet den Inhalt des Feldes
 */
public class FeldValue {
	public String name;
	public int wert;
	public boolean status;
/**
 * Konstruktor von FeldValue	
 */
	public FeldValue(String name, int wert, boolean status){
		this.name = name;
		this.wert = wert;
		this.status = status;
	}
	
/**
 * @return: gibt den Namen als String zurueck
 */
	public String getName(){
		return name;
	}
/**
 * @return: gibt den Wert des Feldes als Zahl zurueck	
 */
	public int getWert(){
		return wert;
	}
/**
 * @return: gibt den Statur als boolean-Wert zurueck 	
 */
	public boolean getStatus(){
		return status;
	}
}
