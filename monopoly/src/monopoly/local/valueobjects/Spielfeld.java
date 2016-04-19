package monopoly.local.valueobjects;

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
	public Feld getFeld(Feld position){
		for(Feld feld:feld){
			if(feld.equals(position)){
				return feld;
			}
		}
		return null;
	}
	public boolean bauHaus(int position,Spieler spieler){
		return feld[position].bauHaus(spieler);
	}
	public int getHaeuseranzahl(int position){
		return feld[position].getHaeuseranzahl();
	}
}
