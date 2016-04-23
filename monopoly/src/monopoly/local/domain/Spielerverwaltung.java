package monopoly.local.domain;
import java.util.Vector;

import monopoly.local.valueobjects.Spieler;

public class Spielerverwaltung {
	private int order;
	
	private Vector<Spieler> playerList = new Vector<Spieler>();
	
	/**
	 * Konstruktor der Klasse Spielerverwaltung
	 */
	public Spielerverwaltung(){
		order = -1;
	}
	
	/**
	 * @return: gibt alle Spieler in einem Vektor zurück
	 */
	public Vector<Spieler> getAllSpieler(){
		return playerList;
	}
	
	/**
	 * @return: gibt einen Spieler an einer bestimmten Stelle i aus
	 */
	public Spieler getSpieler(int i){
		return playerList.get(i);
	}
	
	/**
	 * fügt einen Spieler der spielerListe hinzu 
	 * 
	 * @return
	 */
	public boolean beitreten(Spieler player){
		if(!playerList.isEmpty()){
			if(playerList.lastElement().getSpielerNummer()<6){
				this.playerList.add(player);
				return true;
			}else{
				return false;
			}
		}else{
			this.playerList.add(player);
			return true;
		}
	}
	
	/**
	 * entfernt einen Spieler anhand seiner spielerNummer aus der spielerListe
	 * und lässt die nächten Spieler aufrücken die hinter ihm in der Liste standen
	 * 
	 * @param playerNumber
	 * @return
	 */
	public boolean entfernen(int playerNumber){
		for(int i = 0;i < playerList.size();i++){
			if(playerList.get(i).getSpielerNummer() == playerNumber){
				playerList.remove(i);
				for(int k = 0;k<playerList.size();k++){
					playerList.get(k).setSpielerNummer(k+1);
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param miete: Geldbetrag den der Spieler zahlen muss
	 * @param vermieter: Spieler der das Geld erhält
	 * @param mieter: SPieler der das Geld zahlt
	 */
	public void mieteZahlen(int rent, Spieler landlord, Spieler renter){
		if(landlord.getSpielerNummer() != 99)
		landlord.setSpielerBudget(landlord.getSpielerBudget()+rent);
	}

	/**
	 * @return: 
	 */
	public Spieler reihenfolge(){
		if(order < playerList.size()-1){
			order++;
		}
		else {
			order = -1;
			order++;
		}
		return playerList.get(order);
	}

	/**
	 * @return: gibt einen Vektor mit den Spielern zurück die Pleite sind
	 */
	public Vector<Spieler> checkPleite(){
		Vector<Spieler> v = new Vector<Spieler>();
		for (Spieler player:playerList){
			if(player.getSpielerBudget() < 0){
				v.addElement(player);
			}
		}
		return v;
	}
	
	/**
	 * @return: gibt eine zufällige int Zahl zwischen 1 und 6 aus
	 */
	public int wuerfeln(){
		int number;
		number = (int)(Math.random() * 6) + 1;
		
		return number;
	}
}
