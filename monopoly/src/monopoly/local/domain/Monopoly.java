package monopoly.local.domain;

import java.util.Vector;

import monopoly.local.domain.Spielverwaltung.Turn;
import monopoly.local.domain.exceptions.GehaltException;
import monopoly.local.domain.exceptions.HausbauException;
import monopoly.local.persistenz.PersistenzLaden;
import monopoly.local.persistenz.PersistenzSpeichern;
import monopoly.local.ui.cui.SpielStart;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Jail;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Spielfeld;
import monopoly.local.valueobjects.Strasse;

public class Monopoly {

	private PersistenzLaden pmLaden;
	private PersistenzSpeichern pmSpeichern;
	private Spielerverwaltung spieler;
	private Spielverwaltung logik;

	public Monopoly() {
		super();
		this.spieler = new Spielerverwaltung();
		this.logik = new Spielverwaltung(spieler);
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
		return pmLaden.loadAll(datei);
	}
	
	public String getFeldName(int nr){
		return logik.getFeldName(nr);
	}
	
	public int getHaeuseranzahl(int position){
		return logik.getHaeuseranzahl(position);
	}
	
	public void bauHaus(int position,Spieler spieler) throws HausbauException{
		logik.bauHaus(position, spieler);
	}
	
	public String switchHypothek(int position)throws GehaltException{
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

	public void kaufStrasse(Spieler kaeufer)throws GehaltException {
		logik.kaufStrasse(kaeufer);
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

	public Feld getLos() {
		return logik.getLos();
	}

	public Jail getJail(){
		return (Jail)logik.getJail();
	}
	
	
	public boolean beitreten(Spieler player) {
		return spieler.beitreten(player);
	}

	public Spieler getSpieler(int i) {
		return spieler.getSpieler(i);
	}

	public boolean entfernen(int spielernummer) {
		return spieler.entfernen(spielernummer);
	}

	public void setAllSpieler(Vector<Spieler> spielerListe) {
		spieler.setAllSpieler(spielerListe);
	}

	public Vector<String> saveFilesLaden() {
	Vector<String> savefiles = pmLaden.loadSaveFiles();
	return savefiles;	
	}
}
