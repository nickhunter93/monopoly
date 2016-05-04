package monopoly.local.domain;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Jail;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Spielfeld;
import monopoly.local.valueobjects.Strasse;

public class Spielverwaltung {
	private Spielfeld feld;
	
	/**
	 * Konstruktor der Klasse Spielverwaltung
	 */
	public Spielverwaltung(){
		feld = new Spielfeld();
	}
	
	/**
	 * bewegt den Spieler um "Zugweite" nach vorne
	 * 
	 * @param spieler: 
	 * @param zugweite: int Zahl wie viele Felder der Spieler gehen darf
	 */
	public void move(Spieler spieler,int zugweite){
		int spielerPosition = spieler.getSpielerPosition().getNummer();
		if(feld.getFieldSize()>spielerPosition+zugweite){
			Feld newPosition = feld.setPosition(spieler, zugweite);
			spieler.setSpielerPosition(newPosition);
		}else{
			int position = spielerPosition;
			for(int i = 0;i<zugweite;i++){
				if(position<feld.getFieldSize()-1){
					position++;
				}else{
					if (feld.getLos() instanceof Strasse){
						spieler.setSpielerBudget(spieler.getSpielerBudget()-((Strasse)feld.getLos()).getMietpreis());
						position = -spielerPosition;
					}
				}
			}
			Feld newPosition = feld.setPosition(spieler, position);
			spieler.setSpielerPosition(newPosition);
		}
	}
	
	/**
	 * 
	 * @param player
	 * @return: gibt den Namen der Stra�e an dem sich der Spieler befindet als String zur�ck
	 */
	public String getStrasseName(Spieler spieler){
		Feld position = spieler.getSpielerPosition();
		String name = position.getName();
		return name;
	}
	
	/**
	 * 
	 * @param position
	 * @return: gibt den Besitzer des Feldes zur�ck auf der Spieler sich befindet
	 */
	public Spieler getBesitzer(Feld position){
		Spieler besitzer = null;
		if(position instanceof Strasse){
			besitzer = ((Strasse) position).getBesitzer();
		}
		return besitzer;
	}

	/**
	 * setzt den Wert der Hypothek einer Stra�e neu 
	 * durch den Aufruf der switchHypthek-Funktion in der Klasse Spielfeld 
	 */
	public String switchHypothek(int position){
		return feld.switchHypothek(position);
	}
	
	/**
	 * 
	 * @param player
	 * @return: gibt den Mietpreis der Stra�e zur�ck auf welcher der Spieler sich befindet
	 */
	public int miete(Spieler spieler){
		Strasse strasse = null;
		Feld position = spieler.getSpielerPosition();
		if(position instanceof Strasse){
			strasse =((Strasse)position);
		}
		return strasse.getMietpreis();
	}
	
	/**
	 * 
	 * @param spieler
	 * @return: gibt den Kaufpreis der Strasse zurueck
	 */
	public int preis(Spieler spieler){
		Strasse strasse = null;
		Feld position = spieler.getSpielerPosition();
		if(position instanceof Strasse){
			strasse =((Strasse)position);
		}
		return strasse.getKaufpreis();
	}
	
	/**
	 * setzt den Besitzer der Stra�e auf den Spieler der die Stra�e gekauft hat
	 * und zieht dem Spieler das Geld f�r die Stra�e aus seinem Budget ab
	 */
	public boolean kaufStrasse(Spieler spieler){
		Strasse strasse;
		Feld position = spieler.getSpielerPosition();
		if(position instanceof Strasse){
			strasse = (Strasse)position;
		
			Spieler besitzer = strasse.getBesitzer();
			int spielerNummer = besitzer.getSpielerNummer();
			int budget = spieler.getSpielerBudget();
			int subtrahend = strasse.getKaufpreis();
			int differenz = budget - subtrahend;
			if( spielerNummer == 99 && differenz >= 0){
				strasse.setBesitzer(spieler);
				spieler.setSpielerBudget(differenz);
				return true;
			}
		}
		
		
		return false;
	}
	
	/**
	 * @return: gibt das Losfeld zur�ck 
	 */
	public Feld getLos(){
		return feld.getLos();
	}
	
	/**
	 * 
	 * @return: gibt das Gef�ngnisfeld zurueck
	 */
	public Feld getJail(){
		return feld.getJail();
	}
	
	/**
	 * 
	 * @return: gibt das Spielfeld zur�ck
	 */
	public Feld[] getSpielfeld(){
		return feld.getSpielfeld();
	}
	
	public void setSpielfeld(Feld[] feld){
		this.feld.setSpielfeld(feld);
	}
	
	/**
	 * @return: gibt den Feldnamen anhand einer Nummer zur�ck
	 */
	public String getFeldName(int nr){
		return feld.getFeldName(nr);
	}
	
	/**
	 * @return: gibt das Feld zur�ck an dem sich der Spieler befindet
	 */
	public Feld getFeld(Spieler spieler){
		Feld position = spieler.getSpielerPosition();
		return feld.getFeld(position);
	}
	
	/**
	 * baut ein Haus an der Stelle position 
	 * 
	 * @param position
	 * @param player
	 * @return: gibt das Feld mit dem dazu gebauten Haus zur�ck
	 */
	public boolean bauHaus(int position,Spieler spieler){
		return feld.bauHaus(position,spieler);	
	}
	
	/**
	 * @return: gibt die Stra�en zur�ck die einem Spieler geh�ren
	 */
	public Strasse[] getYourStreets(Spieler spieler){
		return feld.getYourStreets(spieler);
	}
	
	/**
	 * @return: gibt die Anzahl der H�user zur�ck die auf einem Feld stehen
	 */
	public int getHaeuseranzahl(int position){
		return feld.getHaeuseranzahl(position);
	}
	
	/**
	 * pr�ft ob ein Spieler im Gefaengnis ist
	 */
	public boolean isInJail(Spieler spieler){
		Feld feld = spieler.getSpielerPosition();
		if(feld instanceof Jail ){
			Jail jail = (Jail)feld;
			return jail.isPlayerIn(spieler);
		}
		
		return false;
	}
	
}
