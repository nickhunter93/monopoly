package monopoly.local.valueobjects;

import java.util.Vector;

public class Jail extends Feld{
	
	private Vector<Spieler> insassen;
	private Spieler besitzer = new Spieler("Bank", 98, null, -1);
	
	public Jail(String name,int nummer){
		super(name, nummer);
		insassen = new Vector<Spieler>();
	}

	public Vector<Spieler> getInsassen() {
		return insassen;
	}

	public void addInsasse(Spieler spieler){
		insassen.add(spieler);
	}
	
	public void release(Spieler spieler){
		insassen.remove(spieler);
	}
	
	public boolean isPlayerIn(Spieler spieler){
		return insassen.contains(spieler);
	}

	public Spieler getBesitzer() {
		return besitzer;
	}
}
