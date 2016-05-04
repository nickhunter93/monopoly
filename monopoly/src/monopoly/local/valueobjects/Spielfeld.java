package monopoly.local.valueobjects;

import java.io.IOException;
import java.util.Vector;

import monopoly.local.persistenz.PersistenzLaden;

public class Spielfeld {
	private Feld[] feld;
	private int fieldSize = 36;
	
	/**
	 * Konstruktor der Klasse Spielfeld
	 * erstellt ein Spielfeld, 
	 * bestimmt das Feld [0] das Losfeld ist, 
	 * zum Start des Spieles alle Straßen gehören niemandem
	 */
	public Spielfeld(){
//		feld = new Feld [fieldSize];
//		for (int i=0;i<fieldSize;i++){
//			feld[i] = new Strasse("Teststrasse",800,480,false,i);
//			((Strasse)feld[i]).setBesitzer(new Spieler("Bank", 99, null, -1));
//		}
//		feld[0] = new Strasse("Los",0,-2000,false,0);
//		((Strasse)feld[0]).setBesitzer(new Spieler("Bank", 98, null, -1));
//		
//		feld[9] = new Jail("Gefängnis",9);
		
		PersistenzLaden test = new PersistenzLaden();
		try {
			Feld[] feld = test.loadDefaulField();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * setzt den Wert der Hypothek an der Stelle Position.
	 */
	public String switchHypothek(int position){
		if(feld[position] instanceof Strasse){
			Strasse strasse = (Strasse)feld[position];
			return strasse.switchHypothek();
		}
		return "Strasse nicht gefunden.";
	}
	
	/** 
	 * @return: gibt die Anzahl der Felder als int zurück 
	 */
	public int getFieldSize(){
		return fieldSize;
	}
	
	/**
	 * @return: gibt das Losfeld zurück
	 */
	public Feld getLos(){
		return feld[0];
	}
	
	/**
	 * 
	 */
	public Feld getJail(){
		return feld[9];
	}
	
	/**
	 * ändert die Position des Spielers
	 * 
	 * @param player
	 * @param steps
	 * @return: gibt die neue Position des Spielers zurück
	 */
	public Feld setPosition(Spieler spieler,int zugweite){
		Feld position = spieler.getSpielerPosition();
		int newPosition = position.getNummer()+zugweite;
		return feld[newPosition];
	}
	
	/**
	 * @return: gibt das aktuelle Feld zurück
	 */
	public Feld[] getSpielfeld(){
		return this.feld;
	}
	
	/**
	 * @return gibt den Namen des Feldes anhand der Feldnummer zurück
	 */
	public String getFeldName(int nr){
		return feld[nr].getName();
	}
	
	/**
	 * @param position: die Position eines Feldes
	 * @return: gibt ein Feld anhand der Feldposition zurück
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
	 * baut ein Haus auf einer Straße  
	 */
	public boolean bauHaus(int position,Spieler spieler){
		if(feld[position] instanceof Strasse){
			return ((Strasse)feld[position]).bauHaus(spieler);
		}
		return false;
	}
	
	/**
	 * @return gibt die Häuseranzahl auf einer Straße als int zurück
	 */
	public int getHaeuseranzahl(int position){
		if (feld[position] instanceof Strasse){
		return ((Strasse)feld[position]).getHaeuseranzahl();
		}
		return -1;
	}

}
