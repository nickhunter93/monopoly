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

	/**
	 * Konstruktor der Klasse Monopoly
	 * Fassadenklasse: verbindet die unteren Schichten(Persistenz, Logik) mit der oberen(GUI) 
	 */
	public Monopoly() {
		super();
		this.spieler = new Spielerverwaltung();
		this.logik = new Spielverwaltung(spieler,this);
		pmLaden = new PersistenzLaden();
		pmSpeichern = new PersistenzSpeichern();
	}
	
	/**
	 * bewegt einen Spieler auf ein Feld
	 * 
	 * @param spieler: Spieler der bewegt wird
	 * @param zugweite: wie weit der Spieler bewegt wird
	 */
	public void move(Spieler spieler,int zugweite){
		logik.move(spieler, zugweite);
	}
	
	/**
	 * 
	 * @return: gibt den Straßennamen auf die der Spieler steht zurück 
	 */
	public String getStrasseName(Spieler spieler){
		return logik.getStrasseName(spieler);
	}
	
	/**
	 * 
	 * @return: gibt einen Vektor mit allen Spilern zurück 
	 */
	public Vector<Spieler> getAllSpieler(){
		return spieler.getAllSpieler();
	}
	
	/**
	 * 
	 * @param position
	 * @return: gibt den Besitzer des Feldes an einer bestimmten Position zurück 
	 */
	public Spieler getBesitzer(Feld position){
		return logik.getBesitzer(position);
	}
	
	/**
	 * 
	 * @return: gibt das Spielfeld zurück
	 */
	public Feld[] getSpielfeld(){
		return logik.getSpielfeld();
	}
	
	/**
	 * 
	 * @return: gibt eine Array mit den Straße eines Spielers zurück 
	 */
	public Strasse[] getYourStreets(Spieler spieler){
		return logik.getYourStreets(spieler);
	}
	
	/**
	 * 
	 * @return: gibt einen zufälligen Wert zwischen 1 und 6
	 */
	public int wuerfel(){
		return logik.wuerfeln();
	}
	
	/**
	 * lädt ein Spiel
	 */
	public Monopoly spielStandLaden(String datei) {
		return pmLaden.loadAll(datei);
	}
	
	/**
	 * gibt den Namen des Feldes als String zurück
	 */
	public String getFeldName(int nr){
		return logik.getFeldName(nr);
	}
	
	/**
	 * gibt die Anzahl der Häuser zurück die auf einer Straße stehen 
	 * @param position
	 * @return
	 */
	public int getHaeuseranzahl(int position){
		return logik.getHaeuseranzahl(position);
	}
	
	/**
	 * baut ein Haus auf einer Straße 
	 * @param position: Position an der das Haus gebaut werden soll
	 * @param spieler: Spieler der das Haus baut
	 * @throws HausbauException
	 * @throws GehaltException
	 */
	public void bauHaus(int position,Spieler spieler) throws HausbauException, GehaltException{
		logik.bauHaus(position, spieler);
	}
	
	/**
	 * ändert den Hypothekwert einer Straße
	 * @param position: Straße auf die der Wert geändert werden soll
	 * @return: String der enthält ob die Hypothek gändert wurde 
	 * @throws GehaltException
	 */
	public String switchHypothek(int position)throws GehaltException{
		return logik.switchHypothek(position);
	}
	
	/**
	 * Methode zum Zahlen der Miete
	 * 
	 * @param spieler: Spieler der Miete zahlen muss
	 * @return
	 */
	public int miete(Spieler spieler){
		return logik.miete(spieler);
	}
	
	/**
	 * Methode zum Ausführen von Ereignissen
	 * 
	 * @param spieler
	 * @return
	 */
	public String ereignisausführen(Spieler spieler){
		return logik.ereignisausführen(spieler);
	}
	
	/**
	 * 
	 * @return: gibt den aktuellen Turn zurück
	 */
	public Turn getTurn(){
		return logik.getTurn();
	}
	
	/**
	 * prüft ob ein Spieler pleite gegangen ist 
	 */
	public void checkPleite(){
		logik.checkPleite();
	}

	/**
	 * Methode zum Erhalten von Miete
	 * @param miete: Betrag der gezahlt wird
	 * @param besitzer: Spieler der den Betrag erhält
	 * @param mieter: Spieler der den Betrag zahlt
	 */
	public void mieteZahlen(int miete, Spieler besitzer, Spieler mieter) {
		spieler.mieteZahlen(miete, besitzer, mieter);
	}

	/**
	 * Methode zum kaufen einer Straße
	 * @param kaeufer
	 * @throws GehaltException
	 */
	public void kaufStrasse(Spieler kaeufer)throws GehaltException {
		logik.kaufStrasse(kaeufer);
	}

	/**
	 * 
	 * @param spieler2
	 * @return: gibt den Preis einer Straße zurück
	 */
	public int preis(Spieler spieler2) {
		return logik.preis(spieler2);
	}
	
	/**
	 * Methode zum prüfen ob ein Spieler im Gefängnis ist 
	 * @param spieler2
	 * @return
	 */
	public boolean isInJail(Spieler spieler2){
		return logik.isInJail(spieler2);
	}
	
	/**
	 * 
	 * @param gamestart
	 */
	public void TurnIni(boolean gamestart){
		logik.getTurn().initialisiere(gamestart);
	}
	
	/**
	 * Methode um einen Spieler in die nächste Phase zu befördern
	 */
	public void nextTurn(){
		logik.nextTurn();
	}

	/**
	 * 
	 * @return: gibt das Los-Feld zurück
	 */
	public Feld getLos() {
		return logik.getLos();
	}

	/**
	 * 
	 * @return: gibt das Gefängnis-Feld zurück
	 */
	public Jail getJail(){
		return (Jail)logik.getJail();
	}
	
	/**
	 * Methode für das hinzufügen eines Spielers
	 * @param player
	 * @return
	 */
	public boolean beitreten(Spieler player) {
		return spieler.beitreten(player);
	}

	/**
	 * 
	 * @param i
	 * @return: gibt einen Spieler an der Stelle i zurück 
	 */
	public Spieler getSpieler(int i) {
		return spieler.getSpieler(i);
	}

	/**
	 * Methode zum entfernen eines Spielers
	 * @param spielernummer
	 * @return: gibt zurück ob ein Spieler erfolgreich entfernt wurde
	 */
	public boolean entfernen(int spielernummer) {
		return spieler.entfernen(spielernummer);
	}

	/**
	 * setzt den Vektor mit Spielern neu
	 * @param spielerListe
	 */
	public void setAllSpieler(Vector<Spieler> spielerListe) {
		spieler.setAllSpieler(spielerListe);
	}

	/**
	 * 
	 * @return: gibt einen Vektor mit allen Speicherständen zurück
	 */
	public Vector<String> saveFilesLaden() {
	Vector<String> savefiles = pmLaden.loadSaveFiles();
	return savefiles;	
	}
	
	/**
	 * Methode zum Speichern eines Spieles
	 * 
	 * @param datei: Namen des gespeicherten Spielstandes
	 */
	public void saveAll(String datei){
		pmSpeichern.saveAll(spieler.getAllSpieler(), logik.getSpielfeld(), logik.getTurn(),datei );
	}
	
	public boolean getHypothek(int position){
		return logik.getHypothek(position);
	}
}
