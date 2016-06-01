package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public abstract class Aktion {
	
	protected Ereignis ereignis = new Ereignis();
	protected Payment zahlung = new Payment();
	protected Spielfeld spielfeld;
	protected Spielerverwaltung spielerverwaltung;
	private String aktion;
	protected Spieler spieler;
	
	public Aktion(Spieler spieler, Spielfeld spielfeld, Spielerverwaltung spielerverwaltung){
		setAktion(aktion);
		this.spielfeld = spielfeld;
		this.spielerverwaltung = spielerverwaltung;
		this.spieler = spieler;
	}
	
	public String getAktion() {
		return aktion;
	}

	public void setAktion(String aktion) {
		this.aktion = aktion;
	}
	
	public Spieler getSpieler(){
		return spieler;
	}
	
	public void setSpieler(Spieler spieler){
		this.spieler = spieler;
	}

	public abstract void ausfuehren();

}
