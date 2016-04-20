package monopoly.local.valueobjects;

public class Spieler {
	
	private String spielerName;
	private Feld spielerPosition;
	private int spielerNummer, budget;
	
	public Spieler(String spielerName, int spielerNummer, Feld spielerPosition, int budget){
		this.spielerName = spielerName;
		this.spielerNummer = spielerNummer;
		this.spielerPosition = spielerPosition;
		this.budget = budget;
	}
	
	public String getSpielerName(){
		return spielerName;
	}
	
	public int getSpielerBudget(){
		return budget;
	}
	public void setSpielerBudget(int budget){
		this.budget = budget;
	}
	
	public int getSpielerNummer(){
		return spielerNummer;
	}
	
	public void setSpielerNummer(int spielerNummer){
		this.spielerNummer = spielerNummer;
	}
	
	public Feld getSpielerPosition(){
		return spielerPosition;
	}
	public void setSpielerPosition(Feld spielerPosition){
		this.spielerPosition = spielerPosition;
	}
	
	public String toString(){
		return("Spieler " + spielerNummer + "\n" + spielerName + " befindet sich auf Feld " + spielerPosition);
	}
	
	public boolean equals(Spieler spieler){
		if(this.spielerName.equals(spieler.getSpielerName())){
			if(this.spielerNummer == spieler.getSpielerNummer()){
				return true;
			}
		}
		return false;
	}
}
