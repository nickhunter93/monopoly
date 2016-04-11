/**
 * baut die Spieler
 */
public class Spieler {
	
	private String spielerName;
	private int spielerNummer, spielerPosition;
/**
 * Konstrutor der Klasse Spieler	
 */
	public Spieler(String spielerName, int spielerNummer, int spielerPosition){
		this.spielerName = spielerName;
		this.spielerNummer = spielerNummer;
		this.spielerPosition = spielerPosition;
	}
/**
 * @return: gibt den Namen des Spielers als String zurueck	
 */
	public String getSpielerName(){
		return spielerName;
	}
/**
 * @return: gibt die Nummer des Spielers als Zahl zurueck	
 */
	public int getSpielerNummer(){
		return spielerNummer;
	}
/**
 * aendert die Nummer des Spielers 
 */
	public void setSpielerNummer(int spielerNummer){
		this.spielerNummer = spielerNummer;
	}
/**
 *@return: gibt die Position des Spilers als Zahl zuruek	
 */
	public int getSpielerPosition(){
		return spielerPosition;
	}
/**
 * @return: gibt einen String zurueckd der: die Spielernummer, den Spielernamen
 * und seine Position beinhaltet
 */
	public String toString(){
		return("Spieler " + spielerNummer + "\n" + spielerName + " befindet sich auf Feld " + spielerPosition);
	}
	
	public void beitreten(){
		
	}
	
	public void austreten(){
		
	}
}
