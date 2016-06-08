package monopoly.local.valueobjects;

public class Gemeinschaftsfeld extends Feld{

	
	private Gemeinschaftskarte deck; 
	
	public Gemeinschaftsfeld(String name, int nr){
		super("Gemeinschaftsfeld", nr);
	}
	
	public void getEreignis(Spieler spieler){
		deck.karteZiehen(spieler);
	}
	

}