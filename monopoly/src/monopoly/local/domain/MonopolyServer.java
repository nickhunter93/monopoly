package monopoly.local.domain;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import ServerPacket.GameActionEvent;
import ServerPacket.GameActionEvent.GameActionEventType;
import ServerPacket.GameControlEvent;
import ServerPacket.GameControlEvent.GameControlEventType;
import ServerPacket.GameEvent;
import ServerPacket.GameEventListener;
import ServerPacket.ServerRemote;
import monopoly.local.domain.Spielverwaltung.Phase;
import monopoly.local.domain.Spielverwaltung.Turn;
import monopoly.local.domain.exceptions.GehaltException;
import monopoly.local.domain.exceptions.HausbauException;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Jail;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;

public class MonopolyServer extends UnicastRemoteObject implements ServerRemote {
	
	private static final long serialVersionUID = -6549201224167353182L;
	
	private static final int NO_OF_PLAYERS = 3;
	
	private List<GameEventListener> listeners;
	private List<Spieler> allPlayers;
	private int currentPlayerIdx = 0;
	private Turn currentTurn;
	private Monopoly monopoly;

	
	private int noOfTurns;
	
	private Object startGameKey = new Object();
	
	
	public MonopolyServer() throws RemoteException {
		listeners = new Vector<>();
		monopoly = new Monopoly();
		allPlayers = monopoly.getAllSpieler();
	}
	
	/*
	 * Notifications for game clients
	 * 
	 * (non-Javadoc)
	 * @see prog2.vl.gameloop.common.ServerRemote#addGameEventListener(prog2.vl.gameloop.common.GameEventListener)
	 */
	
	public void addGameEventListener(GameEventListener listener) throws RemoteException {
		listeners.add(listener);
	}
	
	private void notifyListeners(GameEvent event) throws RemoteException {
		for (GameEventListener listener : listeners) {
			// notify every listener in a dedicated thread
			// (a notification should not block another one).
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						listener.handleGameEvent(event);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			});
			t.start();
		}
	}
	
	/*
	 * Game-setup: new players
	 * (starts game, after second player is registered)
	 * 
	 * (non-Javadoc)
	 * @see prog2.vl.gameloop.common.ServerRemote#addPlayer(prog2.vl.gameloop.common.Spieler)
	 */
	
	public void addPlayer(Spieler spieler) throws RemoteException {
		allPlayers.add(spieler);
		
		if (allPlayers.size() > NO_OF_PLAYERS-1) {
			monopoly.TurnIni(true);
			startGame();
		}
	}

	private void startGame() throws RemoteException {
		// Initialize game
		noOfTurns = 8; 		// game ends after x turns
//		noOfTurns = new Random().nextInt(13);	// game ends after x turns
		currentPlayerIdx = 0;
		Spieler activePlayer = allPlayers.get(currentPlayerIdx);
		currentTurn = monopoly.getTurn();

		// Start game (and release waiting/blocked getTurn() requests)
		synchronized (startGameKey) {
			startGameKey.notifyAll();				
		}
		
		// Notify clients: info "game has started"
		notifyListeners(new GameControlEvent(currentTurn.getWerIstDran(), GameControlEventType.GAME_STARTED));
	}
	
	/*
	 * Controlling the game loop.
	 * (Server is in full control of game status!)
	 * 
	 * (non-Javadoc)
	 * @see prog2.vl.gameloop.common.ServerRemote#getTurn()
	 */
	
	public Turn getTurn() throws RemoteException {
		if ((allPlayers.size() < NO_OF_PLAYERS)
				|| (noOfTurns == 0)) {
			// block method (using wait()-method): 
			// not enough players registered or game over
			synchronized (startGameKey) {
				try {
					startGameKey.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return currentTurn;
	}
	
	public void nextTurn() throws RemoteException {
		monopoly.nextTurn();
		notifyListeners(new GameControlEvent(currentTurn.getWerIstDran(), GameControlEventType.NEXT_PLAYER));
	}
	
	/*
	 * One of probably many game actions that are executed by the clients.
	 * 
	 * (non-Javadoc)
	 * @see prog2.vl.gameloop.common.ServerRemote#someGameAction()
	 */
	public void someGameAction() throws RemoteException {
		
		// Simulate one (of three possible) actions
		int actionID = new Random().nextInt(3);
		GameActionEventType type = GameActionEventType.values()[actionID];
		notifyListeners(new GameActionEvent(currentTurn.getWerIstDran(), type));

		// Game over?
		noOfTurns--;
		if (noOfTurns == 0) {
			// Game over, winner is current player
			notifyListeners(new GameControlEvent(currentTurn.getWerIstDran(), GameControlEventType.GAME_OVER));
		}
	}
	
	
	public static void main(String[] args) {
	
		String serviceName = "GameServer";
		try {			
			 ServerRemote server = new MonopolyServer();
			// Register with RMI registry
			Registry registry = null;
			try {
				registry = LocateRegistry.createRegistry(1099);
				// Registry needs access to classes in project COMMON (-> CLASSPATH)!
				// (Ich starte Registry Ã¼ber Kommandozeile, da kann ich den
				// CLASSPATH der Registry selbst kontrollieren.)
			} catch (RemoteException re) {
				// Registry could neither be found nor created.
				// Try to create a registry explicitly:
				registry = LocateRegistry.createRegistry(1099);
			}
			registry.rebind(serviceName, server);
			
			System.out.println("Game-Server lÃ¤uft...");
		} catch (RemoteException e) {
			// may e.g. occur, if stub class can't be found
			e.printStackTrace();
		}
	}

	@Override
	public void move(Spieler spieler, int zugweite) throws RemoteException {
		monopoly.move(spieler, zugweite);
	}

	@Override
	public String getStrasseName(Spieler spieler) throws RemoteException {
		return monopoly.getStrasseName(spieler);
	}

	@Override
	public Vector<Spieler> getAllSpieler() throws RemoteException {
		return monopoly.getAllSpieler();
	}

	@Override
	public Spieler getBesitzer(Feld position) throws RemoteException {
		return monopoly.getBesitzer(position);
	}

	@Override
	public Feld[] getSpielfeld() throws RemoteException {
		return monopoly.getSpielfeld();
	}

	@Override
	public Strasse[] getYourStreets(Spieler spieler) throws RemoteException {
		return monopoly.getYourStreets(spieler);
	}

	@Override
	public int wuerfel() throws RemoteException {
		return monopoly.wuerfel();
	}

	@Override
	public Monopoly spielStandLaden(String datei) throws RemoteException {
		return monopoly.spielStandLaden(datei);
	}

	@Override
	public String getFeldName(int nr) throws RemoteException {
		return monopoly.getFeldName(nr);
	}

	@Override
	public int getHaeuseranzahl(int position) throws RemoteException {
		return monopoly.getHaeuseranzahl(position);
	}

	@Override
	public void bauHaus(int position, Spieler spieler) throws HausbauException, GehaltException, RemoteException {
		monopoly.bauHaus(position, spieler);
	}

	@Override
	public String switchHypothek(int position) throws GehaltException, RemoteException {
		return monopoly.switchHypothek(position);
	}

	@Override
	public int miete(Spieler spieler) throws RemoteException {
		return monopoly.miete(spieler);
	}

	@Override
	public String ereignisausführen(Spieler spieler) throws RemoteException {
		return monopoly.ereignisausführen(spieler);
	}

	@Override
	public void checkPleite() throws RemoteException {
		monopoly.checkPleite();
	}

	@Override
	public void mieteZahlen(int miete, Spieler besitzer, Spieler mieter) throws RemoteException {
		monopoly.mieteZahlen(miete, besitzer, mieter);
	}

	@Override
	public void kaufStrasse(Spieler kaeufer) throws GehaltException, RemoteException {
		monopoly.kaufStrasse(kaeufer);
	}

	@Override
	public int preis(Spieler spieler2) throws RemoteException {
		return monopoly.preis(spieler2);
	}

	@Override
	public boolean isInJail(Spieler spieler2) throws RemoteException {
		return monopoly.isInJail(spieler2);
	}

	@Override
	public void TurnIni(boolean gamestart) throws RemoteException {
		monopoly.TurnIni(gamestart);
	}

	@Override
	public Feld getLos() throws RemoteException {
		return monopoly.getLos();
	}

	@Override
	public Jail getJail() throws RemoteException {
		return monopoly.getJail();
	}

	@Override
	public boolean beitreten(Spieler player) throws RemoteException {
		return monopoly.beitreten(player);
	}

	@Override
	public Spieler getSpieler(int i) throws RemoteException {
		return monopoly.getSpieler(i);
	}

	@Override
	public boolean entfernen(int spielernummer) throws RemoteException {
		return monopoly.entfernen(spielernummer);
	}

	@Override
	public void setAllSpieler(Vector<Spieler> spielerListe) throws RemoteException {
		monopoly.setAllSpieler(spielerListe);
	}

	@Override
	public Vector<String> saveFilesLaden() throws RemoteException {
		return monopoly.saveFilesLaden();
	}

	@Override
	public void saveAll(String datei) throws RemoteException {
		monopoly.saveAll(datei);
	}

	@Override
	public boolean getHypothek(int position) throws RemoteException {
		return monopoly.getHypothek(position);
	}
}
