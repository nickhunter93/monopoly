package monopoly.local.valueobjects;

import monopoly.local.domain.Spielerverwaltung;

public abstract class Aktion {
	
	protected Ereignis ereignis = new Ereignis();
	protected Payment zahlung = new Payment();
	protected Spielfeld spielfeld;
	protected Spielerverwaltung spielerverwaltung;
	private String aktion;
	
	public Aktion(Spielfeld spielfeld, Spielerverwaltung spielerverwaltung){
		setAktion(aktion);
		this.spielfeld = spielfeld;
		this.spielerverwaltung = spielerverwaltung;
	}
	
	public String getAktion() {
		return aktion;
	}

	public void setAktion(String aktion) {
		this.aktion = aktion;
	}

	public abstract void ausfuehren(Spieler spieler);

}
