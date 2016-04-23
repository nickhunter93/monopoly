package monopoly.local.valueobjects;

import java.util.Vector;

public class Spielfeld {
	private Feld[] field;
	private int fieldSize = 36;
	
	/**
	 * Konstruktor der Klasse Spielfeld
	 * erstellt ein Spielfeld, 
	 * bestimmt das Feld [0] das Losfeld ist, 
	 * zum Start des Spieles alle Stra�en niemandem geh�ren
	 */
	public Spielfeld(){
		field = new Strasse[fieldSize];
		for (int i=0;i<fieldSize;i++){
			field[i] = new Strasse("test",2000,50,false,i);
			((Strasse)field[i]).setBesitzer(new Spieler("Bank", 99, null, -1));
		}
		field[0] = new Strasse("Los",0,-2000,false,0);
		((Strasse)field[0]).setBesitzer(new Spieler("Bank", 98, null, -1));
	}
	
	/**
	 * setzt den Wert der Hypthek f�r die Stra�e an der Stelle position neu
	 * 
	 * @param position
	 * @return
	 */
	public String switchHypothek(int position){
		if(field[position] instanceof Strasse){
			Strasse street = (Strasse)field[position];
			return street.switchHypothek();
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
		return field[0];
	}
	
	/**
	 * �ndert die Position des Spielers
	 * 
	 * @param player
	 * @param steps
	 * @return: gibt die neue Position des Spielers zur�ck
	 */
	public Feld getPosition(Spieler player,int steps){
		Feld position = player.getSpielerPosition();
		int newPosition = position.getNummer()+steps;
		return field[newPosition];
	}
	
	/**
	 * @return: gibt das aktuelle Feld zur�ck
	 */
	public Feld[] getSpielfeld(){
		return this.field;
	}
	
	/**
	 * @return gibt den Namen des Feldes anhand der Feldnummer zur�ck
	 */
	public String getFeldName(int nr){
		return field[nr].getName();
	}
	
	/**
	 * @param position: die Position eines Feldes
	 * @return: gibt ein Feld anhand der Feldposition zur�ck
	 */
	public Feld getFeld(Feld position){
		for(Feld field:this.field){
			if(field.equals(position)){
				return field;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param player
	 * @return: gibt einen Vektor mit Stra�en zur�ck die einem Spieler geh�ren
	 */
	public Strasse[] getYourStreets(Spieler player){
		Vector<Strasse> vec = new Vector<Strasse>();
		for(Feld field : this.field){
			if(field instanceof Strasse){
				Strasse street = (Strasse)field;
				Spieler owner = street.getBesitzer();
				if(owner.equals(player)){
					vec.addElement(street);
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
	public boolean bauHaus(int position,Spieler player){
		if(field[position] instanceof Strasse){
			return ((Strasse)field[position]).bauHaus(player);
		}
		return false;
	}
	/**
	 * @return gibt die H�useranzahl auf einer Stra�e als int zur�ck
	 */
	public int getHaeuseranzahl(int position){
		if (field[position] instanceof Strasse){
		return ((Strasse)field[position]).getHaeuseranzahl();
		}
		return -1;
	}

}
