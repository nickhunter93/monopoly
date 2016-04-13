
public class Spielfeld {
	private Strasse[] feld;
	private int fieldSize = 36;
	public Spielfeld(){
		feld = new Strasse[fieldSize];
		for (int i=0;i<fieldSize;i++){
			feld[i] = new Strasse("test",2000,500,false);
		}
		feld[0] = new Strasse("Los",0,-2000,false);
		feld[0].setBesitzer(99);
	}
	public int getFieldSize(){
		return fieldSize;
	}
	public Strasse[] getFeld(){
		return feld;
	}
	public boolean bauHaus(int position){
		return feld[position].bauHaus();
	}
	public int getHaeuseranzahl(int position){
		return feld[position].getHaeuseranzahl();
	}
}
