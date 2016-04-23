package monopoly.local.domain;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Spielfeld;
import monopoly.local.valueobjects.Strasse;

public class Spielverwaltung {
	private Spielfeld field;
	
	/**
	 * Konstruktor der Klasse Spielverwaltung
	 */
	public Spielverwaltung(){
		field = new Spielfeld();
	}
	
	/**
	 * bewegt den Spieler um "Zugweite" nach vorne
	 * 
	 * @param spieler: 
	 * @param zugweite: int Zahl wie viele Felder der Spieler gehen darf
	 */
	public void move(Spieler player,int steps){
		int playerPosition = player.getSpielerPosition().getNummer();
		if(field.getFieldSize()>playerPosition+steps){
			Feld newPosition = field.getPosition(player, steps);
			player.setSpielerPosition(newPosition);
		}else{
			int position = playerPosition;
			for(int i = 0;i<steps;i++){
				if(position<field.getFieldSize()-1){
					position++;
				}else{
					if (field.getLos() instanceof Strasse){
						player.setSpielerBudget(player.getSpielerBudget()-((Strasse)field.getLos()).getMietpreis());
						position = -playerPosition;
					}
				}
			}
			Feld newPosition = field.getPosition(player, position);
			player.setSpielerPosition(newPosition);
		}
	}
	
	/**
	 * 
	 * @param player
	 * @return: gibt den Namen der Stra�e an dem sich der Spieler befindet als String zur�ck
	 */
	public String getStrasseName(Spieler player){
		Feld position = player.getSpielerPosition();
		String name = position.getName();
		return name;
	}
	
	/**
	 * 
	 * @param position
	 * @return: gibt den Besitzer des Feldes zur�ck auf der Spieler sich befindet
	 */
	public Spieler getBesitzer(Feld position){
		Spieler owner = null;
		if(position instanceof Strasse){
			owner = ((Strasse) position).getBesitzer();
		}
		return owner;
	}

	/**
	 * setzt den Wert der Hypothek einer Stra�e neu 
	 * durch den Aufruf der switchHypthek-Funktion in der Klasse Spielfeld 
	 */
	public String switchHypothek(int position){
		return field.switchHypothek(position);
	}
	
	/**
	 * 
	 * @param player
	 * @return: gibt den Mietpreis der Stra�e zur�ck auf welcher der Spieler sich befindet
	 */
	public int miete(Spieler player){
		Strasse street = null;
		Feld position = player.getSpielerPosition();
		if(position instanceof Strasse){
			street =((Strasse)position);
		}
		return street.getMietpreis();
	}
	
	/**
	 * setzt den Besitzer der Stra�e auf den Spieler der die Stra�e gekauft hat
	 * und zieht dem Spieler das Geld f�r die Stra�e aus seinem Budget ab
	 */
	public boolean kaufStrasse(Spieler player){
		Strasse street;
		Feld position = player.getSpielerPosition();
		if(position instanceof Strasse){
			street = (Strasse)position;
			Spieler owner = street.getBesitzer();
			int playerNumber = owner.getSpielerNummer();
			int budget = player.getSpielerBudget();
			int subtrahend = street.getKaufpreis();
			int difference = budget - subtrahend;
			if( playerNumber == 99 && difference >= 0){
				street.setBesitzer(player);
				player.setSpielerBudget(difference);
				return true;
			}
		}
		
		
		return false;
	}
	
	/**
	 * @return: gibt das Losfeld zur�ck 
	 */
	public Feld getLos(){
		return field.getLos();
	}
	
	/**
	 * 
	 * @return
	 */
	public Feld[] getSpielfeld(){
		return field.getSpielfeld();
	}
	
	/**
	 * @return: gibt den Feldnamen anhand einer Nummer zur�ck
	 */
	public String getFeldName(int nr){
		return field.getFeldName(nr);
	}
	
	/**
	 * @return: gibt das Feld zur�ck an dem sich der Spieler befindet
	 */
	public Feld getFeld(Spieler player){
		Feld position = player.getSpielerPosition();
		return field.getFeld(position);
	}
	
	/**
	 * baut ein Haus an der Stelle position 
	 * 
	 * @param position
	 * @param player
	 * @return: gibt das Feld mit dem dazu gebauten Haus zur�ck
	 */
	public boolean bauHaus(int position,Spieler player){
		return field.bauHaus(position,player);	
	}
	
	/**
	 * @return: gibt die Stra�en zur�ck die einem Spieler geh�ren
	 */
	public Strasse[] getYourStreets(Spieler player){
		return field.getYourStreets(player);
	}
	
	/**
	 * @return: gibt die Anzahl der H�user zur�ck die auf einem Feld stehen
	 */
	public int getHaeuseranzahl(int position){
		return field.getHaeuseranzahl(position);
	}
	
}
