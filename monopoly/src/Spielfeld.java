/**
 * erstellt eine Array von Feldern die das Spielfeld bilden
 */
public class Spielfeld {
	private FeldValue[] feld;
	private int fieldSize = 36;
/**
 * Konstruktor der Klasse Spielfeld	
 */
	public Spielfeld(){
		feld = new FeldValue[fieldSize];
		for (int i=0;i<fieldSize;i++){
			feld[i] = new Strasse("test",2000,true,2000,500,0);
		}
	}
/**
 * @return: gibt ein Array von Feldern zurück
 */
	public FeldValue[] getFeld(){
		return feld;
	}
}
