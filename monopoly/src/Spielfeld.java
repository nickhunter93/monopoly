
public class Spielfeld {
	private Strasse[] feld;
	private int fieldSize = 36;
	public Spielfeld(){
		feld = new Strasse[fieldSize];
		for (int i=0;i<fieldSize;i++){
			feld[i] = new Strasse("test",2000,true,2000,500,0, false);
		}
	}
	public int getFieldSize(){
		return fieldSize;
	}
	public FeldValue[] getFeld(){
		return feld;
	}
	public boolean bauHaus(int position){
		return feld[position].bauHaus();
	}
	public int getHaeuseranzahl(int position){
		return feld[position].getHaeuseranzahl();
	}
}
