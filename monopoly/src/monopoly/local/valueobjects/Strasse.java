package monopoly.local.valueobjects;

import java.io.Serializable;
import java.util.Vector;

import monopoly.local.domain.exceptions.HausbauException;

public class Strasse extends Feld implements Serializable {
	
	private int kaufpreis, mietpreis, haeuseranzahl,hauspreis;
	private boolean hypothek;
	private Vector<Feld> neighbors;
	/**
	 * Konstrucktor der Klasse Strasse
	 * 
	 * @param name: der Name der Stra�e
	 * @param prize: der Preis f�r den man dei Stra�e kaufen kann
	 * @param rent: der Mietpreis den man bezhalt wenn die Stra�e sschon gekauft wurde
	 * @param hypothek: der Wert f�r die Hypothek der Stra�e
	 * @param nr: die Nummer der Stra�e
	 */
	public Strasse(String name, int kaufpreis, int mietpreis, boolean hypothek,int nr){
		super(name,nr);
		this.kaufpreis = kaufpreis;
		this.mietpreis = mietpreis;
		this.haeuseranzahl = 0;
		this.hauspreis = 250;
		this.hypothek = hypothek;
		this.besitzer = null;
		this.neighbors = null;
	}
	
	/**
	 * @return: gibt den Wert der Hypothek als boolean-Wert zur�ck
	 */
	
	public void setNeightbors(Vector<Feld> neighbors){
		this.neighbors = neighbors;
	}
	/**
	 * 
	 * @return: gibt einen Vektor mit den Nachbarn zurück 
	 */
	public Vector<Feld> getNeighbors(){
		return neighbors;
	}
	
	/**
	 * 
	 * @return: gibt zurück ob eine Strße mit einer Hypothek belegt ist 
	 */
	public boolean getHypothek(){
		return this.hypothek;
	}
	
	/**
	 * setzt den boolean-Wert einer Straße neu
	 * 
	 * @param hypothek
	 */
	public void setHypothek(boolean hypothek){
		this.hypothek = hypothek;
	}
	
	/** 
	 * @return: gibt den Besitzer als einen Spieler zur�ck
	 */
	public Spieler getBesitzer(){
		return besitzer;
	}
	
	/**
	 * setzt den Besitzer neu 
	 */
	public void setBesitzer(Spieler besitzer){
		this.besitzer = besitzer;
	}
	
	/**
	 * 
	 * @return: gibt den Kaufpreis der Stra�e zur�ck
	 */
	public int getKaufpreis(){
		return kaufpreis;
	}
	
	/**
	 * @return: gibt den Mietpreis einer Stra�e zur�ck
	 */
	public int getMietpreis(){
		double faktor = haeuseranzahl*0.2;
		int preis = (int) (mietpreis * (faktor+1));
		return preis;
	}
	
	/**
	 * @return: gibt die Anzahl der H�user zur�ck die auf der Stra�e stehen
	 */
	public int getHaeuseranzahl(){
		return haeuseranzahl;
	}
	
	/**
	 * gibt den Preis eines Hauses zurück 
	 * 
	 * @return: Hauspreis
	 */
	public int getHauspreis(){
		return this.hauspreis;
	}
	
	/**
	 * setzt die Zahl der Häuser auf einer Straße neu
	 * 
	 * @param haeuseranzahl
	 */
	public void setHaeuseranzahl(int haeuseranzahl){
		this.haeuseranzahl = haeuseranzahl;
	}
	
	/**
	 * setzt den Preis für ein Haus neu
	 * 
	 * @param hauspreis
	 */
	public void setHauspreis(int hauspreis){
		this.hauspreis = hauspreis;
	}
	
	/**
	 * wandelt den Kaufpreis, den Mietpreis, die H�useranzahl und den Status in einen String um
	 */
	public String toString(){
		return(name + "\n" +
	    "Kaufpreis: " + kaufpreis + " EUR" + "\n" +
		"Mietpreis: " + mietpreis + " EUR/Monat" + "\n" +
	    "Häuseranzahl: " + haeuseranzahl + "\n" +
		"Besitzer: "+besitzer.getSpielerName()
		);
	}
}
