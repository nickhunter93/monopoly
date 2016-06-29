package ServerPacket;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import monopoly.local.domain.Monopoly;
import monopoly.local.domain.Spielverwaltung.Turn;
import monopoly.local.domain.exceptions.GehaltException;
import monopoly.local.domain.exceptions.HausbauException;
import monopoly.local.domain.exceptions.PlayerException;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Jail;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;

/*
 * Server-side remote interface
 * (any method a client would want to invoke on a (game) server
 */
public interface ServerRemote extends Remote {
	
	public void addGameEventListener(GameEventListener listener) throws RemoteException;
	
	public void addPlayer(Spieler p) throws RemoteException, PlayerException;
	
	public void move(Spieler spieler,int zugweite)throws RemoteException;
	
	public String getStrasseName(Spieler spieler)throws RemoteException;
	
	public Vector<Spieler> getAllSpieler()throws RemoteException;
	
	public Spieler getBesitzer(Feld position)throws RemoteException;
	
	public Feld[] getSpielfeld()throws RemoteException;
	
	public Strasse[] getYourStreets(Spieler spieler)throws RemoteException;
	
	public int wuerfel()throws RemoteException;
	
	public Monopoly spielStandLaden(String datei)throws RemoteException;
	
	public String getFeldName(int nr)throws RemoteException;
	
	public int getHaeuseranzahl(int position)throws RemoteException;
	
	public void bauHaus(int position,Spieler spieler) throws HausbauException, GehaltException, RemoteException;
	
	public String switchHypothek(int position)throws GehaltException, RemoteException;
	
	public int miete(Spieler spieler)throws RemoteException;
	
	public String ereignisausführen(Spieler spieler)throws RemoteException;
	
	public Turn getTurn()throws RemoteException;
	
	public void checkPleite()throws RemoteException;

	public void mieteZahlen(int miete, Spieler besitzer, Spieler mieter)throws RemoteException;

	public void kaufStrasse(Spieler kaeufer)throws GehaltException, RemoteException;

	public int preis(Spieler spieler2)throws RemoteException;
	
	public boolean isInJail(Spieler spieler2)throws RemoteException;
	
	public void TurnIni(boolean gamestart)throws RemoteException;
	
	public void nextTurn()throws RemoteException;

	public Feld getLos()throws RemoteException;

	public Jail getJail()throws RemoteException;
	
	public boolean beitreten(Spieler player)throws RemoteException ;
	
	public Spieler getSpieler(int i)throws RemoteException ;

	public boolean entfernen(int spielernummer) throws RemoteException;

	public void setAllSpieler(Vector<Spieler> spielerListe)throws RemoteException;

	public Vector<String> saveFilesLaden() throws RemoteException;
	
	public void saveAll(String datei)throws RemoteException;
	
	public boolean getHypothek(int position)throws RemoteException;
	
}
