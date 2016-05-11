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
	 * zum Start des Spieles alle Stra�en geh�ren niemandem
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
//		feld[9] = new Jail("Gef�ngnis",9);
		
		PersistenzLaden test = new PersistenzLaden();
		try {
			feld = test.loadDefaulField();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		feld[9] = new Jail("Gef�ngnis",9);
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
	 * gibt das Gefaengnisfeld zurueck
	 */
	public Feld getJail(){
		return feld[9];
	}
	
	/**
<<<<<<< Updated upstream
	 * gibt das "Gehe ins Gef�ngnis"-Feld zurueck
	 */
	public Feld getToJail(){
		return feld[99];
	}
	
	/**
	 * �ndert die Position des Spielers
=======
	 * �ndert die Position des Spielers
>>>>>>> Stashed changes
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
	
	public void setSpielfeld(Feld[] feld){
		this.feld = feld;
	}
	
	/**
	 * @return gibt den Namen des Feldes anhand der Feldnummer zur�ck
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
	 * @return: gibt einen Vektor mit Stra�en zur�ck die einem Spieler geh�ren
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
	 * baut ein Haus auf einer Stra�e  
	 */
	public boolean bauHaus(int position,Spieler spieler){
		if(feld[position] instanceof Strasse){
			return ((Strasse)feld[position]).bauHaus(spieler);
		}
		return false;
	}
	
	/**
	 * @return gibt die H�useranzahl auf einer Stra�e als int zur�ck
	 */
	public int getHaeuseranzahl(int position){
		if (feld[position] instanceof Strasse){
		return ((Strasse)feld[position]).getHaeuseranzahl();
		}
		return -1;
	}

}
