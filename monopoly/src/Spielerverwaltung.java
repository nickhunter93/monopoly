import java.util.Vector;

public class Spielerverwaltung {
	private int reihenfolge;
	
	private Vector<Spieler> spielerListe = new Vector<Spieler>();
	public Spielerverwaltung(){
		reihenfolge = -1;
	}
	public Vector<Spieler> getSpieler(){
		return spielerListe;
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
	public void entfernen(int spielerNummer){
		for(int i = 0;i < spielerListe.size();i++){
			if(spielerListe.get(i).getSpielerNummer() == spielerNummer){
				spielerListe.remove(i);
				for(int k = 0;k<spielerListe.size();k++){
					spielerListe.get(k).setSpielerNummer(k+1);
				}
			}
		}
	}
	public Spieler reihenfolge(){
		if(reihenfolge < spielerListe.size()){
			reihenfolge++;
		}
		else reihenfolge = -1;
		return spielerListe.get(reihenfolge);
	}
}
