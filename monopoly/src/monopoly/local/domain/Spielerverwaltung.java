package monopoly.local.domain;
import java.util.Vector;

import monopoly.local.valueobjects.Jail;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.ToJail;

public class Spielerverwaltung {
	private int reihenfolge;
	private Jail jail;
	private ToJail toJail;
	private Vector<Spieler> spielerListe = new Vector<Spieler>();
	/**
	 * Konstruktor der Klasse Spielerverwaltung
	 */

	public Spielerverwaltung(){
		reihenfolge = -1;
	}
	
	/**
	 * @return: gibt alle Spieler in einem Vektor zur�ck
	 */
	public Vector<Spieler> getAllSpieler(){
		return spielerListe;
	}
	
	public void setAllSpieler(Vector<Spieler> spielerListe){
		this.spielerListe = spielerListe;
	}
	
	/**
	 * @return: gibt einen Spieler an einer bestimmten Stelle i aus
	 */
	public Spieler getSpieler(int i){
		return spielerListe.get(i);
	}
	
	/**
	 * f�gt einen Spieler der spielerListe hinzu 
	 * 
	 * @return
	 */
	public boolean beitreten(Spieler spieler){
		if(!spielerListe.isEmpty()){
			if(spielerListe.lastElement().getSpielerNummer()<6){
				this.spielerListe.add(spieler);
				return true;
			}else{
				return false;
			}
		}else{
			this.spielerListe.add(spieler);
			return true;
		}
	}
	
	/**
	 * entfernt einen Spieler anhand seiner spielerNummer aus der spielerListe
	 * und l�sst die n�chten Spieler aufr�cken die hinter ihm in der Liste standen
	 * 
	 * @param playerNumber
	 * @return
	 */
	public boolean entfernen(int spielerNummer){
		for(int i = 0;i < spielerListe.size();i++){
			if(spielerListe.get(i).getSpielerNummer() == spielerNummer){
				spielerListe.remove(i);
				for(int k = 0;k<spielerListe.size();k++){
					spielerListe.get(k).setSpielerNummer(k+1);
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param miete: Geldbetrag den der Spieler zahlen muss
	 * @param vermieter: Spieler der das Geld erh�lt
	 * @param mieter: SPieler der das Geld zahlt
	 */
	public void mieteZahlen(int miete, Spieler vermieter, Spieler mieter){
		if(vermieter.getSpielerNummer() != 99)
		vermieter.setSpielerBudget(vermieter.getSpielerBudget()+miete);
	}

	/**
	 * durchl�uft die Liste mit Spielern und gibt jeweils einen zur�ck
	 * springt an den Anfang wenn das Ende der SpielerListe erreicht ist
	 * 
	 * @return: gibt die Reihenfolge der Spieler zur�ck 
	 */
	public Spieler reihenfolge(){
		if(reihenfolge < spielerListe.size()-1){
			reihenfolge++;
		}
		else {
			reihenfolge = -1;
			reihenfolge++;
		}
//		reihenfolge = (reihenfolge+1) % spielerListe.size();
		return spielerListe.get(reihenfolge);
	}

	/**
	 * @return: gibt einen Vektor mit den Spielern zur�ck die Pleite sind
	 */
	public Vector<Spieler> checkPleite(){
		Vector<Spieler> v = new Vector<Spieler>();
		for (Spieler spieler:spielerListe){
			if(spieler.getSpielerBudget() < 0){
				v.addElement(spieler);
			}
		}
		return v;
	}
	
	
	/**
	 * befreit einen Spieler der im Gefaengnis ist
	 */
	public void release(Spieler spieler){
		jail.release(spieler);
	}
	
	/**
	 * schickt einen Spieler ins Gef�ngnis
	 */
	public void toJail(Spieler player){
		toJail.getToJail(player);
	}
}
