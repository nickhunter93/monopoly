import java.util.Vector;

public class Spielerverwaltung {
	private int reihenfolge;
	
	private Vector<Spieler> spielerListe = new Vector<Spieler>();
	public Spielerverwaltung(){
		reihenfolge = -1;
	}
	public Vector<Spieler> getAllSpieler(){
		return spielerListe;
	}
	public Spieler getSpieler(int i){
		return spielerListe.get(i);
	}
	public boolean beitreten(Spieler spieler){
		if(!spielerListe.isEmpty()){
			if(spielerListe.lastElement().getSpielerNummer()<6){
				this.spielerListe.add(spieler);
				return true;
			}else{
				return false;
			}
		}else{
			this.spielerListe.add(spieler);
			return true;
		}
	}
	
	public boolean entfernen(int spielerNummer){
		for(int i = 0;i < spielerListe.size();i++){
			if(spielerListe.get(i).getSpielerNummer() == spielerNummer){
				spielerListe.remove(i);
				for(int k = 0;k<spielerListe.size();k++){
					spielerListe.get(k).setSpielerNummer(k+1);
				}
			}
		}
		return true;
	}

	public Spieler reihenfolge(){
		if(reihenfolge < spielerListe.size()-1){
			reihenfolge++;
		}
		else {
			reihenfolge = -1;
			reihenfolge++;
		}
		return spielerListe.get(reihenfolge);
	}

	
	public int wuerfeln(){
		int zahl;
		zahl = (int)(Math.random() * 6) + 1;
		
		return zahl;
	}
}
