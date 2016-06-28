package monopoly.local.valueobjects;

import java.awt.Image;
import java.io.Serializable;

public class Spieler implements Serializable {
	
	private String spielerName;
	private Feld spielerPosition;
	private int spielerNummer, budget;
	private Image spielfigur;
	
	/**
	 * Konstruktor der Klasse Spieler
	 * 
	 * @param playerName: Name des Spielers
	 * @param playerNumber: Nummer die der Spieler besitzt
	 * @param playerPosition: Position des Spielers
	 * @param budget: der Geldbetrag den der Spieler besitzt
	 */
	public Spieler(String spielerName, int spielerNummer, Feld spielerPosition, int budget){
		this.spielerName = spielerName;
		this.spielerNummer = spielerNummer;
		this.spielerPosition = spielerPosition;
		this.budget = budget;
	}
	
	public void setSpielfigur(Image spielfigur){
		this.spielfigur = spielfigur;
	}
	
	public Image getSpielfigur(){
		return spielfigur;
	}
	
	
	/**
	 * @return: gibt den NAmen des Spielers als String zur�ck
	 */
	public String getSpielerName(){
		return spielerName;
	}
	
	/**
	 * @return: gibt den Geldbetrag den der Spieler besitzt als int zur�ck
	 */
	public int getSpielerBudget(){
		return budget;
	}
	
	/**
	 * �ndert den Geldbetrag den der Spieler besitzt
	 */
	public void setSpielerBudget(int budget){
		this.budget = budget;
	}
	
	/**
	 * @return: gibt die Nummer des Spielers zur�ck
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
	 * @return: gibt die Spielerposition zur�ck
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
	 * gleich wenn: die Spielernamen und die Spielernummern �bereinstimmen
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
