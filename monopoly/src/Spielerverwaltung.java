import java.util.Vector;
/**
 * verwaltet das was die Spieler machen
 */
public class Spielerverwaltung {
	private Vector<Spieler> spielerListe = new Vector<Spieler>();
/**
 * Konstruktor der Klasse Spielerverwaltung	
 */
	public Spielerverwaltung(){
		
	}
/**
 * 	@return: gibt einen Vektor zurueck der die Spieler beinhaltet
 */
	public Vector<Spieler> getSpieler(){
		return spielerListe;
	}
/**
 * fuegt ein Spieler der spielerListe hinzu
 * 	@return: ein boolean Wert ob der Spieler erfolgreich hinzugefueg wurde
 */
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
/**
 * entfernt einen Spieler aus der spielerListe und laesst die anderen aufruecken 
 */
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
