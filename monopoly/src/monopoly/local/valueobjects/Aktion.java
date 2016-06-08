package monopoly.local.valueobjects;


public interface Aktion {

	public void ausfuehren();
	
	public void setSpieler(Spieler spieler);
	
	public Spieler getSpieler();

}
