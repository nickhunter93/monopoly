package monopoly.local.domain;

import java.util.Vector;

import monopoly.local.domain.Spielverwaltung.Turn;
import monopoly.local.persistenz.PersistenzLaden;
import monopoly.local.persistenz.PersistenzSpeichern;
import monopoly.local.ui.cui.SpielStart;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;

public class Monopoly {

	private PersistenzLaden pmLaden;
	private PersistenzSpeichern pmSpeichern;
	private Spielerverwaltung spieler;
	private Spielverwaltung logik;

	public Monopoly(Spielverwaltung logik , Spielerverwaltung spieler) {
		super();
		this.logik = logik;
		this.spieler = spieler;
		pmLaden = new PersistenzLaden();
	}
	
	public void move(Spieler spieler,int zugweite){
		logik.move(spieler, zugweite);
	}
	
	public String getStrasseName(Spieler spieler){
		return logik.getStrasseName(spieler);
	}
	
	public Vector<Spieler> getAllSpieler(){
		return spieler.getAllSpieler();
	}
	
	public Spieler getBesitzer(Feld position){
		return logik.getBesitzer(position);
	}
	
	public Feld[] getSpielfeld(){
		return logik.getSpielfeld();
	}
	
	public Strasse[] getYourStreets(Spieler spieler){
		return logik.getYourStreets(spieler);
	}
	
	public int wuerfel(){
		return logik.wuerfeln();
	}
	
	public SpielStart spielStandLaden(String datei) {
		return pmLaden.loadAll();
	}
	
	public String getFeldName(int nr){
		return logik.getFeldName(nr);
	}
	
	public int getHaeuseranzahl(int position){
		return logik.getHaeuseranzahl(position);
	}
	
	public boolean bauHaus(int position,Spieler spieler){
		return logik.bauHaus(position, spieler);
	}
	
	public String switchHypothek(int position){
		return logik.switchHypothek(position);
	}
	
	public int miete(Spieler spieler){
		return logik.miete(spieler);
	}
	
	public Turn getTurn(){
		return logik.getTurn();
	}
	
	public void checkPleite(){
		logik.checkPleite();
	}

	public void mieteZahlen(int miete, Spieler besitzer, Spieler mieter) {
		spieler.mieteZahlen(miete, besitzer, mieter);
	}

	public boolean kaufStrasse(Spieler kaeufer) {
		return logik.kaufStrasse(kaeufer);
	}

	public int preis(Spieler spieler2) {
		return logik.preis(spieler2);
	}
	public boolean isInJail(Spieler spieler2){
		return logik.isInJail(spieler2);
	}
	public void TurnIni(boolean gamestart){
		logik.getTurn().initialisiere(gamestart);
	}
	public void nextTurn(){
		logik.nextTurn();
	}
}
