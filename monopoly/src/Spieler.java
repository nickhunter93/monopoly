
public class Spieler {
	
	private String spielerName;
	private int spielerNummer, spielerPosition;
	
	public Spieler(String spielerName, int spielerNummer, int spielerPosition){
		this.spielerName = spielerName;
		this.spielerNummer = spielerNummer;
		this.spielerPosition = spielerPosition;
	}
	
	public String getSpielerName(){
		return spielerName;
	}
	
	public int getSpielerNummer(){
		return spielerNummer;
	}
	
	public int getSpielerPosition(){
		return spielerPosition;
	}
	
	public String toString(){
		return("Spieler " + spielerNummer + "\n" + spielerName + " befindet sich auf Feld " + spielerPosition);
	}
	
	public void beitreten(){
		
	}
	
	public void austreten(){
		
	}
}
