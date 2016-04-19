package monopoly.local.valueobjects;

import java.util.Vector;

public class Spielfeld {
	private Feld[] feld;
	private int fieldSize = 36;
	public Spielfeld(){
		feld = new Strasse[fieldSize];
		for (int i=0;i<fieldSize;i++){
			feld[i] = new Strasse("test",2000,50,false,i);
		}
		feld[0] = new Strasse("Los",0,-2000,false,0);
		((Strasse)feld[0]).setBesitzer(new Spieler("Bank", 99, null, -1));
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
	public Feld getFeld(Feld position){
		for(Feld feld:this.feld){
			if(feld.equals(position)){
				return feld;
			}
		}
		return null;
	}
	public int[] getYourStreets(Spieler spieler){
		Vector<Strasse> vec = new Vector<Strasse>();
		for(Feld feld : this.feld){
			if(feld.getClass().isInstance(Strasse.class)){
				vec.addElement((Strasse)feld);
			}
		}
		if(!vec.isEmpty()){
			int [] StrassenNummer = new int[vec.size()];
			for (int i=0;i<vec.size();i++){
				StrassenNummer[i] = vec.elementAt(i).getNummer();
			}
			return StrassenNummer;
		}
		return null;
	}
	public boolean bauHaus(int position,Spieler spieler){
		if(feld[position].getClass().isInstance(Strasse.class)){
			return ((Strasse)feld[position]).bauHaus(spieler);
		}
		return false;
	}
	public int getHaeuseranzahl(int position){
		if (feld[position].getClass().isInstance(Strasse.class)){
		return ((Strasse)feld[position]).getHaeuseranzahl();
		}
		return -1;
	}
}
