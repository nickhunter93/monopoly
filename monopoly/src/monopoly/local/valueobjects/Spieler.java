package monopoly.local.valueobjects;

public class Spieler {
	
	private String playerName;
	private Feld playerPosition;
	private int playerNumber, budget;
	
	/**
	 * Konstruktor der Klasse Spieler
	 * 
	 * @param playerName: Name des Spielers
	 * @param playerNumber: Nummer die der Spieler besitzt
	 * @param playerPosition: Position des Spielers
	 * @param budget: der Geldbetrag den der Spieler besitzt
	 */
	public Spieler(String playerName, int playerNumber, Feld playerPosition, int budget){
		this.playerName = playerName;
		this.playerNumber = playerNumber;
		this.playerPosition = playerPosition;
		this.budget = budget;
	}
	
	/**
	 * @return: gibt den NAmen des Spielers als String zurück
	 */
	public String getSpielerName(){
		return playerName;
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
		return playerNumber;
	}
	
	/**
	 * setzt die Nummer des Spielers neu
	 */
	public void setSpielerNummer(int playerNumber){
		this.playerNumber = playerNumber;
	}
	
	/**
	 * @return: gibt die Spielerposition zurück
	 */
	public Feld getSpielerPosition(){
		return playerPosition;
	}
	
	/**
	 * setzt die Spielerposition neu
	 */
	public void setSpielerPosition(Feld playerPosition){
		this.playerPosition = playerPosition;
	}
	
	/**
	 * wandelt die Spielernummer, den Spielernamen und die Position des Spielers in einen String um
	 */
	public String toString(){
		return("Spieler " + playerNumber + "\n" + playerName + " befindet sich auf Feld " + playerPosition);
	}
	
	/**
	 * Methode die bestimmt wann ein Spieler gleich ist
	 * gleich wenn: die Spielernamen und die Spielernummern übereinstimmen
	 */
	public boolean equals(Spieler player){
		if(this.playerName.equals(player.getSpielerName())){
			if(this.playerNumber == player.getSpielerNummer()){
				return true;
			}
		}
		return false;
	}
}
