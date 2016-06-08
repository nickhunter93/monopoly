package monopoly.local.domain;
import monopoly.local.persistenz.Ereignisfeld;
import monopoly.local.persistenz.Gemeinschaftsfeld;
import monopoly.local.valueobjects.Aktion;
import monopoly.local.valueobjects.Ereigniskarten;
import monopoly.local.domain.exceptions.GehaltException;
import monopoly.local.domain.exceptions.HausbauException;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Jail;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Spielfeld;
import monopoly.local.valueobjects.Strasse;
import monopoly.local.valueobjects.ToJail;

public class Spielverwaltung {
	private Spielfeld feld;
	private Spielerverwaltung spieler;
	private Ereigniskarten ereignisKarten;
	private Turn aktuellerTurn;
/*	
	private Turn aktuellerTurn;
	
	enum Phase { DICE, IN_JAIL, OUT_OF_Jail, BUY, ... }
	public class Turn {
		Spieler werIstDran;
		int phase; // - enum Verwendung
	}
	Turn getTurn();
	... cui macht aktion auf Monopoly
	(Monopoly verändert Spielzustand -> Phase)
	- entweder: Aktion gibt _immer_ Turn-Objekt
	- oder: CUI fragt nach Aktion: getTurn() und dann: switch (turnObjekt) case DICE: ... case JAIL: ...
	*/
	/**
	 * Konstruktor der Klasse Spielverwaltung
	 */
	public Spielverwaltung(Spielerverwaltung spieler){
		feld = new Spielfeld();
		this.spieler = spieler;
		aktuellerTurn = new Turn();
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
		if(position instanceof Jail){
			besitzer = ((Jail) position).getBesitzer();
		}
		if(position instanceof ToJail){
			besitzer = ((ToJail) position).getBesitzer();
		}
		if(position instanceof Ereignisfeld){
			besitzer = ((Ereignisfeld) position).getBesitzer();
		}
		return besitzer;
	}

	/**
	 * setzt den Wert der Hypothek einer Stra�e neu 
	 * durch den Aufruf der switchHypthek-Funktion in der Klasse Spielfeld 
	 */
	public String switchHypothek(int position) throws GehaltException{
		if(feld.getSpielfeld()[position] instanceof Strasse){
			Strasse strasse = (Strasse)feld.getSpielfeld()[position];
			int budget = strasse.getBesitzer().getSpielerBudget();
			int wert = strasse.getKaufpreis() / 2;
			if(!strasse.getHypothek()){
				budget = budget + wert;
				strasse.getBesitzer().setSpielerBudget(budget);
				strasse.setHypothek(true);
				return "Hypothek im Wert von " + wert + " auf Straße " + strasse.getName() + " wurde aufgenommen.";
			}else{
				if(budget - wert >= 0){
					budget = budget - wert;
					strasse.getBesitzer().setSpielerBudget(budget);
					strasse.setHypothek(false);
					return "Hypothek wurde bezahlt.";
				}else{
					throw new GehaltException(aktuellerTurn.getWerIstDran());
				}
			}
		}
		return "Keine Strasse gefunden.";
		
	}
	
	/**
	 * 
	 * @param player
	 * @return: gibt den Mietpreis der Stra�e zur�ck auf welcher der Spieler sich befindet
	 */
	public int miete(Spieler spieler){
		Strasse strasse = null;
		Feld position = spieler.getSpielerPosition();
		int miete = 0;
		if(position instanceof Strasse){
			strasse =((Strasse)position);
			miete = strasse.getMietpreis();
		}
		if(position instanceof Jail){
			miete = 0;
		}
		if(position instanceof ToJail){
			ToJail toJail = (ToJail)position;
			toJail.getToJail(spieler);
			this.getTurn().phase = Phase.End;
		}
		if(position instanceof Ereignisfeld){
			Ereignisfeld ereignisfeld = (Ereignisfeld)position;
			ereignisfeld.getEreignis(spieler);
		}
		if(position instanceof Gemeinschaftsfeld){
			Gemeinschaftsfeld gemeinschaftsfeld = (Gemeinschaftsfeld)position;
			gemeinschaftsfeld.getEreignis(spieler);
		}
		return miete;
	}
	
	/**
	 * 
	 * @param spieler
	 * @return: gibt den Kaufpreis der Strasse zurueck
	 */
	public int preis(Spieler spieler){
		Strasse strasse = null;
		Feld position = spieler.getSpielerPosition();
		int kaufpreis = 0;
		if(position instanceof Strasse){
			strasse =((Strasse)position);
			kaufpreis = strasse.getKaufpreis();
		}
		if(position instanceof Jail){
			kaufpreis = 0;
		}
		if(position instanceof ToJail){
			kaufpreis = 0;
		}
		return kaufpreis;
	}
	
	/**
	 * setzt den Besitzer der Stra�e auf den Spieler der die Stra�e gekauft hat
	 * und zieht dem Spieler das Geld f�r die Stra�e aus seinem Budget ab
	 */
	public void kaufStrasse(Spieler spieler)throws GehaltException{
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
				return;
			}
		}
		
		
		throw new GehaltException(spieler);
	}
	
	/**
	 * @return: gibt das Losfeld zur�ck 
	 */
	public Feld getLos(){
		return feld.getLos();
	}
	
	public Feld getEreignisfeld(){
		return feld.getEreignisfeld();
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
	 */
	public Feld getToJailField(){
		return feld.getToJail();
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
	public void bauHaus(int position , Spieler spieler) throws HausbauException {
		int budget = spieler.getSpielerBudget();
		if(getSpielfeld()[position] instanceof Strasse){
			Strasse strasse = (Strasse)getSpielfeld()[position];
			if(strasse.getHaeuseranzahl() < 4){
				strasse.setHaeuseranzahl(strasse.getHaeuseranzahl()+1);
				if(strasse.getHaeuseranzahl() < 4){
					if(budget-strasse.getHauspreis() >= 0){
						spieler.setSpielerBudget(budget-strasse.getHauspreis());
					}else {
						throw new HausbauException(spieler, getSpielfeld()[position]);
					}
				}else{
					if((budget - (2*strasse.getHauspreis())) >= 0){
						spieler.setSpielerBudget(budget-(2*strasse.getHauspreis()));
					}else {
						throw new HausbauException(spieler, getSpielfeld()[position]);
					}
				}
			} else{
				throw new HausbauException(spieler, getSpielfeld()[position]);
			}
		}
		
	}
	
	/**
	 * @return: gibt die Stra�en zur�ck die einem Spieler geh�ren
	 */
	public Strasse[] getYourStreets(Spieler spieler){
		return feld.getYourStreets(spieler);
	}
	
	public Spielfeld getSpielfeldObjekt(){
		return feld;
	}
	
	public void karteZiehen(Spieler wer) {
		ereignisKarten.deckMischen();
		Aktion aktion = ereignisKarten.karteZiehen(wer);
		aktion.ausfuehren();
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
	
	/**
	 * @return: gibt eine zuf�llige int Zahl zwischen 1 und 6 aus
	 */
	public int wuerfeln(){
		int zahl;
		zahl = (int)(Math.random() * 6) + 1;
		
		return zahl;
	}
	
	public void checkPleite(){
		if(!spieler.checkPleite().isEmpty()){
			for(Spieler player:spieler.checkPleite()){
				Strasse[] yourStreets = this.getYourStreets(player);
				for(Strasse strasse : yourStreets){
					strasse.setBesitzer(new Spieler("Bank", 99, null, -1));
				}
				spieler.entfernen(player.getSpielerNummer());
			}
		}else{}
	}
	
	
	public Turn getTurn() {
		return aktuellerTurn;
	}

	public void nextTurn() {
		if(aktuellerTurn.phase == Phase.End ){
		aktuellerTurn.werIstDran = spieler.reihenfolge();// index
		aktuellerTurn.nextPhase();
		}
		else{
			aktuellerTurn.nextPhase();
		}
	}
	
	public enum Phase { 
		
		JailCheck, Dice, Passiv, End;

		public Phase next() {
			Phase phase = values()[(this.ordinal()+1) % values().length];
			return phase;
		}
	};
	
	public class Turn {
		
		private Spieler werIstDran;
		private Phase phase;
		public Turn() {
			//phase = Phase.JailCheck;
		}
		
		
		//int phase; // - enum Verwendung
		public void initialisiere(boolean gamestart){
			if(gamestart){
				werIstDran = spieler.reihenfolge();
				phase = Phase.JailCheck;
			}
		}
		public Spieler getWerIstDran(){
			return this.werIstDran;
		}
		public void setWerIstDran(Spieler werIstDran){
			this.werIstDran = werIstDran;
		}
		public Phase getPhase(){
			return this.phase;
		}
		public void setPhase(Phase phase){
			this.phase = phase;
		}
		public void nextPhase(){
			this.phase = phase.next();
		}
		public void Jailed(){
			this.phase = Phase.End;
		}
	}
	
}
