package monopoly.local.valueobjects;

import java.util.Vector;

public class Spielfeld {
	private Feld[] feld;
	private int fieldSize = 36;
	
	public Spielfeld(){
		feld = new Strasse[fieldSize];
		for (int i=0;i<fieldSize;i++){
			feld[i] = new Strasse("Teststrasse",2000,50,false,i);
			((Strasse)feld[i]).setBesitzer(new Spieler("Bank", 99, null, -1));
		}
		feld[0] = new Strasse("Los",0,-2000,false,0);
		((Strasse)feld[0]).setBesitzer(new Spieler("Bank", 98, null, -1));
	}
	
	public String switchHypothek(int position){
		if(feld[position] instanceof Strasse){
			Strasse strasse = (Strasse)feld[position];
			return strasse.switchHypothek();
		}
		return "Strasse nicht gefunden.";
	}
	
	public int getFieldSize(){
		return fieldSize;
	}
	
	public Feld getLos(){
		return feld[0];
	}
	
	public Feld getPosition(Spieler spieler,int zugweite){
		Feld position = spieler.getSpielerPosition();
		int newPosition = position.getNummer()+zugweite;
		return feld[newPosition];
	}
	
	public Feld[] getSpielfeld(){
		return this.feld;
	}
	
	public String getFeldName(int nr){
		return feld[nr].getName();
	}
	
	public Feld getFeld(Feld position){
		for(Feld feld:this.feld){
			if(feld.equals(position)){
				return feld;
			}
		}
		return null;
	}
	
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
	
	public boolean bauHaus(int position,Spieler spieler){
		if(feld[position] instanceof Strasse){
			return ((Strasse)feld[position]).bauHaus(spieler);
		}
		return false;
	}
	
	public int getHaeuseranzahl(int position){
		if (feld[position] instanceof Strasse){
		return ((Strasse)feld[position]).getHaeuseranzahl();
		}
		return -1;
	}

}
