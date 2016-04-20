package monopoly.local.valueobjects;

public class Spieler {
	
	private String spielerName;
	private Feld spielerPosition;
	private int spielerNummer, budget;
	
	/**
	 * Konstruktor der Klasse Spieler
	 * 
	 * @param spielerName: Name des Spielers
	 * @param spielerNummer: Nummer die der Spieler besitzt
	 * @param spielerPosition: Position des Spielers
	 * @param budget: der Geldbetrag den der Spieler besitzt
	 */
	public Spieler(String spielerName, int spielerNummer, Feld spielerPosition, int budget){
		this.spielerName = spielerName;
		this.spielerNummer = spielerNummer;
		this.spielerPosition = spielerPosition;
		this.budget = budget;
	}
	
	/**
	 * @return: gibt den NAmen des Spielers als String zurück
	 */
	public String getSpielerName(){
		return spielerName;
	}
	
	/**
	 * @return: gibt den Geldbetrag den der Spieler besitzt als int zurück
	 */
	public int getSpielerBudget(){
		return budget;
	}
	
	/**
	 * ändert den Geldbetrag den der Spieler besitzt
	 */
	public void setSpielerBudget(int budget){
		this.budget = budget;
	}
	
	/**
	 * @return: gibt die Nummer des Spielers zurück
	 */
	public int getSpielerNummer(){
		return spielerNummer;
	}
	
	/**
	 * setzt die Nummer des Spielers neu
	 */
	public void setSpielerNummer(int spielerNummer){
		this.spielerNummer = spielerNummer;
	}
	
	/**
	 * @return: gibt die Spielerposition zurück
	 */
	public Feld getSpielerPosition(){
		return spielerPosition;
	}
	
	/**
	 * setzt die Spielerposition neu
	 */
	public void setSpielerPosition(Feld spielerPosition){
		this.spielerPosition = spielerPosition;
	}
	
	/**
	 * wandelt die Spielernummer, den Spielernamen und die Position des Spielers in einen String um
	 */
	public String toString(){
		return("Spieler " + spielerNummer + "\n" + spielerName + " befindet sich auf Feld " + spielerPosition);
	}
	
	/**
	 * Methode die bestimmt wann ein Spieler gleich ist
	 * gleich wenn: die Spielernamen und die Spielernummern übereinstimmen
	 */
	public boolean equals(Spieler spieler){
		if(this.spielerName.equals(spieler.getSpielerName())){
			if(this.spielerNummer == spieler.getSpielerNummer()){
				return true;
			}
		}
		return false;
	}
}
