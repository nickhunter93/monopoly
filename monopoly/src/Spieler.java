
public class Spieler {
	
	private String spielerName;
	private int spielerNummer, spielerPosition, budget;
	
	public Spieler(String spielerName, int spielerNummer, int spielerPosition, int budget){
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
	
	public int getSpielerPosition(){
		return spielerPosition;
	}
	public void setSpielerPosition(int spielerPosition){
		this.spielerPosition = spielerPosition;
	}
	
	public String toString(){
		return("Spieler " + spielerNummer + "\n" + spielerName + " befindet sich auf Feld " + spielerPosition);
	}
}
