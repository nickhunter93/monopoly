import java.util.Vector;

public class Spielerverwaltung {
	private Vector<Spieler> spielerListe = new Vector<Spieler>();
	public Spielerverwaltung(){
		
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
}
