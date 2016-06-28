package monopoly.local.valueobjects;

import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

import monopoly.local.domain.Monopoly;
import monopoly.local.persistenz.PersistenzLaden;

public class Spielfeld implements Serializable {
	private Feld[] feld;
	private int fieldSize = 36;
	
	/**
	 * Konstruktor der Klasse Spielfeld
	 * erstellt ein Spielfeld, 
	 * bestimmt das Feld [0] das Losfeld ist, 
	 * zum Start des Spieles alle Stra�en geh�ren niemandem
	 */
	public Spielfeld(Monopoly monopoly){
//		feld = new Feld [fieldSize];
//		for (int i=0;i<fieldSize;i++){
//			feld[i] = new Strasse("Teststrasse",800,480,false,i);
//			((Strasse)feld[i]).setBesitzer(new Spieler("Bank", 99, null, -1));
//		}
//		feld[0] = new Strasse("Los",0,-2000,false,0);
//		((Strasse)feld[0]).setBesitzer(new Spieler("Bank", 98, null, -1));
//		
//		feld[9] = new Jail("Gef�ngnis",9);
		
		PersistenzLaden test = new PersistenzLaden();
		try {
			feld = test.loadDefaulField(monopoly);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//feld[9] = new Jail("Gef�ngnis",9);
		//feld[19] = new ToJail("Gehe ins Gefängnis",19,(Jail) feld[9]);
	}
	
	/** 
	 * @return: gibt die Anzahl der Felder als int zur�ck 
	 */
	public int getFieldSize(){
		return fieldSize;
	}
	
	/**
	 * @return: gibt das Losfeld zur�ck
	 */
	public Feld getLos(){
		return feld[0];
	}
	
	/**
	 * 
	 * @return: gibt der Ereignisfeld zurück
	 */
	public Feld getEreignisfeld(){
		return feld[5];
	}
	
	/**
	 * 
	 * @return: gibt das Gemeinschaftsfeld zurück 
	 */
	public Feld getGemeinschaftsfeld(){
		return feld[16];
	}
	
	/**
	 * gibt das Gefaengnisfeld zurueck
	 */
	public Feld getJail(){
		return feld[10];
	}
	
	/**
	 * gibt das "Gehe ins Gef�ngnis"-Feld zurueck
	 */
	public Feld getToJail(){
		return feld[20];
	}
	
	/**
	 * ändert die Position des Spielers
	 * 
	 * @param player
	 * @param steps
	 * @return: gibt die neue Position des Spielers zur�ck
	 */
	public Feld setPosition(Spieler spieler,int zugweite){
		Feld position = spieler.getSpielerPosition();
		int newPosition = position.getNummer()+zugweite;
		return feld[newPosition];
	}
	
	/**
	 * @return: gibt das aktuelle Feld zur�ck
	 */
	public Feld[] getSpielfeld(){
		return this.feld;
	}
	
	/**
	 * setzt das Spielfeld neu
	 * 
	 * @param feld
	 */
	public void setSpielfeld(Feld[] feld){
		this.feld = feld;
	}
	
	/**
	 * @return gibt den Namen des Feldes anhand der Feldnummer zurück
	 */
	public String getFeldName(int nr){
		return feld[nr].getName();
	}
	
	/**
	 * @param position: die Position eines Feldes
	 * @return: gibt ein Feld anhand der Feldposition zur�ck
	 */
	public Feld getFeld(Feld position){
		for(Feld feld:this.feld){
			if(feld.equals(position)){
				return feld;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param player
	 * @return: gibt einen Vektor mit Straßen zurück die einem Spieler gehören
	 */
	public Strasse[] getYourStreets(Spieler spieler){
		Vector<Strasse> vec = new Vector<Strasse>();
		for(Feld feld : this.feld){
			if(feld instanceof Strasse){
				Strasse strasse = (Strasse)feld;
				Spieler besitzer = strasse.getBesitzer();
				if(besitzer.equals(spieler)){
					vec.addElement(strasse);
				}
			}
		}
		if(!vec.isEmpty()){
			Strasse [] yourStreets = new Strasse[vec.size()];
			for (int i=0;i<vec.size();i++){
				yourStreets[i] = vec.elementAt(i);
			}
			return yourStreets;
		}
		return null;
	}	
	
	/**
	 * @return gibt die Häuseranzahl auf einer Stra�e als int zur�ck
	 */
	public int getHaeuseranzahl(int position){
		if (feld[position] instanceof Strasse){
		return ((Strasse)feld[position]).getHaeuseranzahl();
		}
		return -1;
	}

}



/**
 * baut ein Haus auf einer Stra�e  
 */