package monopoly.local.valueobjects;

public class Karte {
	
	private int id;
	private String beschreibung, titel;
	
	public Karte(int id, String beschreibung, String titel){
		this.setId(id);
		this.setBeschreibung(beschreibung);
		this.setTitel(titel);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBeschreibung() {
		return beschreibung;
	}


	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}
	
}
