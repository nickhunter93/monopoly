package monopoly.client;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ServerPacket.GameActionEvent;
import ServerPacket.GameControlEvent;
import ServerPacket.GameEvent;
import ServerPacket.GameEventListener;
import ServerPacket.ServerRemote;
import monopoly.local.domain.Spielverwaltung.Phase;
import monopoly.local.domain.Spielverwaltung.Turn;
import monopoly.local.domain.exceptions.GehaltException;
import monopoly.local.domain.exceptions.HausbauException;
import monopoly.local.domain.exceptions.PlayerException;
import monopoly.local.ui.gui.swing.Menue.Menuefenster;
import monopoly.local.valueobjects.Ereignisfeld;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Gemeinschaftsfeld;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;
import net.miginfocom.swing.MigLayout;

public class SpielfensterClient extends UnicastRemoteObject implements GameEventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8480457548039267832L;
	// Variablen f�r das Spielfenster
	private JFrame spiel;
	private SButtonPanel sBP;
	private SpielPanel sP;
	private SInfoPanel sIP;
	private SInfoPanel sIP2;
	private HausFenster haFenster;
	private HypothekFenster hyFenster;
	private SpeichernFenster speFenster;
	
	// Game server
	private ServerRemote server;

	// Key objects (control of game loop using wait/notify)
	private Object playerKey = new Object();
	private Object actionKey = new Object();

	

	private Vector<Spieler> spielerliste;

	private Spieler player;
	private Spieler spieler;
	private Spieler spieler1;
	private Spieler spieler2;
	private Spieler spieler3;
	private Spieler spieler4;
	private Spieler spieler5;

	private Spielfigur figur;
	private Spielfigur figur1;
	private Spielfigur figur2;
	private Spielfigur figur3;
	private Spielfigur figur4;
	private Spielfigur figur5;

	private int spPosition;
	private int spPosition1;
	private int spPosition2;
	private int spPosition3;
	private int spPosition4;
	private int spPosition5;

	private int spNummer;
	private int spNummer1;
	private int spNummer2;
	private int spNummer3;
	private int spNummer4;
	private int spNummer5;

	Turn turn;

	/**
	 * Konstruktor der Klasse Spielfenster ein neuer JFrame wird erstellt und
	 * alle Komponenten hinzugefuegt enthaelt alle ActionListener f�r das
	 * Spielfenster
	 * 
	 * @param monopoly
	 */
	public SpielfensterClient() throws RemoteException {
		try {
			// Connect to game server
			String serviceName = "GameServer";
			Registry registry = LocateRegistry.getRegistry();
			server = (ServerRemote) registry.lookup(serviceName);
			server.addGameEventListener(this);
			spielerliste = server.getAllSpieler();

			// Register for game events
			server.addGameEventListener(this);
			
			// create and register new player
						String name = JOptionPane.showInputDialog(spiel, "Enter your name:", "Add player", JOptionPane.QUESTION_MESSAGE);
						int spielernummer = server.getAllSpieler().size();
						player = new Spieler(name,spielernummer+1,server.getLos(),2000);
						try {
							server.addPlayer(player);
						} catch (PlayerException e) {
							e.printStackTrace();
						}
			
			// initialize client
			sInit();

			
			
			// start client-side game loop
			loop();
		} catch (RemoteException e) {
			// may occur, if stub class can't be found
			e.printStackTrace();
		} catch (NotBoundException e) {
			// no remote object registered under URL / name
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		try {
			new SpielfensterClient();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void sInit() {
		// Spielfeldfenster
		spiel = new JFrame("Monopoly");
		sP = new SpielPanel();
		sBP = new SButtonPanel();
		sIP = new SInfoPanel();
		sIP2 = new SInfoPanel();
		haFenster = new HausFenster();
		hyFenster = new HypothekFenster();
		speFenster = new SpeichernFenster();
		spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spiel.setLayout(new MigLayout("debug", "[]10[]", "[]10[]"));

		spiel.add(sP, "cell 0 0,span 1 3, w 600, h 600");
		spiel.add(sBP, "cell 1 0, push, grow, shrink");
		spiel.add(sIP, "cell 1 1,h 150, push, grow, shrink");
		spiel.add(sIP2, "cell 1 2,h 150, push, grow, shrink");

		spiel.getContentPane().setBackground(new Color(197, 251, 255));

		bildHinzu(0);

		spiel.setSize(1000, 725);
		spiel.setVisible(true);
		sP.setVisible(true);
		spiel.repaint();
		spiel.revalidate();
		disableGui();
		try {
			turn = server.getTurn();
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		//sIP.getJTextArea().setText(""+turn.getPhase()+"\n"+player.getSpielerPosition());

		//sIP2.getJTextArea().setText("Name :"+player.getSpielerName()+"\nGehalt : "+player.getSpielerBudget()+
		//		"\nPosition : "+player.getSpielerPosition().getName());

//		player.setSpielerPosition(monopoly.getSpielfeld()[1]);
//		try {
//			monopoly.kaufStrasse(player);
//			player.setSpielerPosition(monopoly.getSpielfeld()[3]);
//			monopoly.kaufStrasse(player);
//			player.setSpielerPosition(monopoly.getLos());
//		} catch (GehaltException e2) {
//			e2.printStackTrace();
//		}
		// Info-TextAreas mit sich anpassenden Infos
		// sIP2.getSTextArea().setText(monopoly.getStrasseName(spieler) +
		// monopoly.getHaeuseranzahl(position) + monopoly.get);
		
		// ActionListener fuer den wuerfeln-Button
		sBP.getButton1().addActionListener(e -> {
			try {
				Phase phase = turn.getPhase();
				if(phase == Phase.JailCheck && !(server.isInJail(turn.getWerIstDran()))){
					server.nextTurn();
					phase = server.getTurn().getPhase();
				}{}
				switch (phase) {
				case JailCheck:
					if (server.isInJail(turn.getWerIstDran())) {
						int wurf = server.wuerfel();
						if (wurf == 6) {
							server.getJail().release(player);
							server.nextTurn();
							turn = server.getTurn();
							sBP.getButton1().setText("Würfel");
							sBP.repaint();
							sBP.revalidate();
						} else {
							server.getTurn().Jailed();
							turn = server.getTurn();
							sBP.getButton1().setText("Jailed");
							sBP.repaint();
							sBP.revalidate();
						}
					}else{
						server.nextTurn();
						turn = server.getTurn();
						sBP.getButton1().setText("Würfel");
						sBP.repaint();
						sBP.revalidate();
					}
					break;
				case Dice:
					int zugweite = server.wuerfel();
					server.move(server.getTurn().getWerIstDran(), zugweite);
					bildWeg();
					bildHinzu(0);
					server.nextTurn();
					turn = server.getTurn();
					sBP.getButton1().setText("Passiv");
					sBP.repaint();
					sBP.revalidate();
					break;
				case Passiv:
					Spieler besitzer = player.getSpielerPosition().getBesitzer();
					if(besitzer.getSpielerNummer() == 99){
						String message = "Wollen Sie die Strasse : "+player.getSpielerPosition().getName()+" kaufen?";
						String title = "Strasse kaufen?";
						switch(JOptionPane.showConfirmDialog(spiel, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)){
						case JOptionPane.YES_OPTION : try {
								server.kaufStrasse(player);
							} catch (GehaltException e1) {
								JOptionPane.showMessageDialog(spiel, e1.getMessage(), "Gehalt überschritten!", JOptionPane.WARNING_MESSAGE);
							}
							break;
						case JOptionPane.NO_OPTION :
							break;
						default :
						}
					}else if(besitzer.getSpielerNummer() != player.getSpielerNummer()){
						server.miete(player);
						if(player.getSpielerPosition() instanceof Ereignisfeld || player.getSpielerPosition() instanceof Gemeinschaftsfeld)
						JOptionPane.showConfirmDialog(spiel,server.ereignisausführen(player), "Karte gezogen!", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}
					server.nextTurn();
					turn = server.getTurn();
					sBP.getButton1().setText("Runde Beenden");
					sBP.repaint();
					sBP.revalidate();
					break;
				case End:
					server.nextTurn();
					turn = server.getTurn();
					sBP.getButton1().setText("Würfeln");
					sBP.repaint();
					sBP.revalidate();
					break;
				default:
				}
				if(player.getSpielerPosition() instanceof Strasse){
					Strasse strasse = (Strasse)player.getSpielerPosition();
					sIP.getJTextArea().setText(""+turn.getPhase()+"\n"+strasse);
				}
				sIP2.getJTextArea().setText("Name :"+player.getSpielerName()+"\nGehalt : "+player.getSpielerBudget()+
						"\nPosition : "+player.getSpielerPosition().getName());
				synchronized (actionKey) {
					actionKey.notify();					
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
//				int zugweite = monopoly.wuerfel();
//				Spieler spieler = monopoly.getTurn().getWerIstDran();
//				monopoly.move(spieler, zugweite);
//				bildWeg();
//				bildHinzu(0);
//				String feldName = monopoly.getStrasseName(spieler);
//
//				if (spieler.getSpielerPosition() instanceof Strasse) {
//					JOptionPane jOP = new JOptionPane();
//					jOP.showConfirmDialog(spiel, "Willst du " + feldName + " kaufen");
//					// sIP2.getSTextArea().setText(""+jOP.getOptions().toString());
//					monopoly.nextTurn();
//					turn = monopoly.getTurn();
//					// try {
//					// monopoly.kaufStrasse(spieler);
//					// e1.printStackTrace();
//					// }
//				}
		});
		sP.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				// me.getX()/100;
				System.out.println("\"pos " + Math.round((double) me.getX() / sP.getWidth() * 100) / 100.0 + "al "
						+ Math.round((double) me.getY() / sP.getHeight() * 100) / 100.0 + "al\"");

			}
		});
		// ActionListener fuer den Haus-bauen-Button
		sBP.getButton2().addActionListener(e -> {
			spiel.remove(sBP);
			spiel.add(haFenster, "cell 1 0, push, grow, shrink");
			try {
				Spieler spieler = server.getTurn().getWerIstDran();
				haFenster.refreshList(spieler,server.getYourStreets(spieler));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			spiel.repaint();
			spiel.revalidate();
		});

		// ActionListener fuer den Hypothek-aufnehmen-Button
		sBP.getButton3().addActionListener(e -> {
			spiel.remove(sBP);
			spiel.add(hyFenster, "cell 1 0, push, grow, shrink");
			try {
				Spieler spieler = server.getTurn().getWerIstDran();
				hyFenster.refreshList(spieler,server.getYourStreets(spieler));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			spiel.repaint();
			spiel.revalidate();
		});

		// ActionListener fuer den speichern-Button
		sBP.getButton4().addActionListener(e -> {
			spiel.remove(sBP);
			spiel.add(speFenster, "cell 1 0, pushx, growx, shrinkx");
			spiel.repaint();
			spiel.revalidate();
			// JOptionPane.showMessageDialog(spiel, "Eggs are not supposed
			// to be green.");
		});
		haFenster.getHaListe().addListSelectionListener(e -> {
			if(e.getValueIsAdjusting()){
				try {
					if(!haFenster.getHaListe().isSelectionEmpty()){
						int nummer = haFenster.getHaListe().getSelectedIndex();
						Strasse[] strassen = server.getYourStreets(server.getTurn().getWerIstDran());
						Strasse strasse = strassen[nummer];
						haFenster.getHaHausAnz().setText(strasse.getHaeuseranzahl()+"");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// ActionListener für den bauen-Button
		haFenster.getHaButton().addActionListener(e -> {
			try {
				Spieler spieler = server.getTurn().getWerIstDran();
				if (!haFenster.getHaListe().isSelectionEmpty()) {
					try {
						Feld feld = server.getYourStreets(spieler)[haFenster.getHaListe().getSelectedIndex()];
						server.bauHaus(feld.getNummer(), spieler);
					} catch (HausbauException e11) {
						JOptionPane.showMessageDialog(spiel, e11.getMessage());
					} catch (GehaltException e12) {
						JOptionPane.showMessageDialog(spiel, e12.getMessage());
					}
					spiel.remove(haFenster);
					spiel.add(sBP, "cell 1 0, push, grow, shrink");
					spiel.repaint();
					spiel.revalidate();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		// ActionListener fuer den zurueck-Button
		haFenster.getHaButton2().addActionListener(e -> {
			spiel.remove(haFenster);
			spiel.add(sBP, "cell 1 0, push, grow, shrink");
			spiel.repaint();
			spiel.revalidate();
		});

		// ActionListener fuer den aufnehmen-Button
		hyFenster.getHyButton1().addActionListener(e -> {
			// if hypothek == false -->if(monopoly.getHypothek == false){
			// }
			try {
				Spieler spieler = server.getTurn().getWerIstDran();
				int position = spieler.getSpielerPosition().getNummer();
				if (!hyFenster.getHyListe().isSelectionEmpty()) {
					if (server.getHypothek(position) == false) {
						try {
							server.switchHypothek(position);
						} catch (GehaltException e1) {
							e1.printStackTrace();
						}
						spiel.remove(hyFenster);
						spiel.add(sBP, "cell 1 0, push, grow, shrink");
						spiel.repaint();
						spiel.revalidate();
						sIP2.getJTextArea().setText("Name :"+player.getSpielerName()+"\nGehalt : "+player.getSpielerBudget()+
								"\nPosition : "+player.getSpielerPosition().getName());
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		// ActionListener fuer den abbezahlen-Button
		hyFenster.getHyButton2().addActionListener(e -> {
			// if hypothek == true --> if(monopoly.getHypothek == true){
			// monopoly.switchHypothek(position);}
			try {
				int position = player.getSpielerPosition().getNummer();
				if (!hyFenster.getHyListe().isSelectionEmpty()) {
					if (server.getHypothek(position) == true) {
						try {
							server.switchHypothek(position);
						} catch (GehaltException e1) {
							e1.printStackTrace();
						}
						spiel.remove(hyFenster);
						spiel.add(sBP, "cell 1 0, push, grow, shrink");
						spiel.repaint();
						spiel.revalidate();
						sIP2.getJTextArea().setText("Name :"+player.getSpielerName()+"\nGehalt : "+player.getSpielerBudget()+
								"\nPosition : "+player.getSpielerPosition().getName());
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		// ActionListener fuer den zurueck-Button
		hyFenster.getHyButton3().addActionListener(e -> {
			spiel.remove(hyFenster);
			spiel.add(sBP, "cell 1 0, push, grow, shrink");
			spiel.repaint();
			spiel.revalidate();
		});

		// ActionListener fuer den speichern-Button
		speFenster.getSfButton().addActionListener(e -> {
			try {
				String datei = speFenster.getDatName().getText();
				server.saveAll(datei);
				// zurueck zum Hauptmenue
				JOptionPane.showMessageDialog(spiel, "Speichern erfolgreich");
				// Spielfenster spFenster = new Spielfenster(monopoly);
				// spFenster.sInit();
				// menue.dispose();
				Menuefenster mFenster = new Menuefenster();
				mFenster.mSetVisible();
				spiel.dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		// ActionListener fuer den zurueck-Button
		speFenster.getSfButton2().addActionListener(e -> {
			spiel.remove(speFenster);
			spiel.add(sBP, "cell 1 0, push, grow, shrink");
			spiel.repaint();
			spiel.revalidate();
		});
	}

	public void bildHinzu(int zugweite) {
		int zug = zugweite;
		try {
			spielerliste = server.getAllSpieler();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (spielerliste.size() == 2) {
			spieler = spielerliste.get(0);
			spieler1 = spielerliste.get(1);

			spPosition = spieler.getSpielerPosition().getNummer();
			spPosition1 = spieler1.getSpielerPosition().getNummer();

			spNummer = spieler.getSpielerNummer();
			spNummer1 = spieler1.getSpielerNummer();

			ImageIcon icon = new ImageIcon("images/spielfiguren/red.png");
			spieler.setSpielfigur(icon.getImage());

			ImageIcon icon1 = new ImageIcon("images/spielfiguren/black.png");
			spieler1.setSpielfigur(icon1.getImage());

			figur = new Spielfigur(spieler.getSpielfigur());
			figur1 = new Spielfigur(spieler1.getSpielfigur());

			sP.add(figur, new SpielerPosition().getSpielerPosition(spPosition + zug, spNummer));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur1, new SpielerPosition().getSpielerPosition(spPosition1 + zug, spNummer1));
			spiel.repaint();
			spiel.revalidate();

		}

		if (spielerliste.size() == 3) {

			spieler = spielerliste.get(0);
			spieler1 = spielerliste.get(1);
			spieler2 = spielerliste.get(2);

			spPosition = spieler.getSpielerPosition().getNummer();
			spPosition1 = spieler1.getSpielerPosition().getNummer();
			spPosition2 = spieler2.getSpielerPosition().getNummer();

			spNummer = spieler.getSpielerNummer();
			spNummer1 = spieler1.getSpielerNummer();
			spNummer2 = spieler2.getSpielerNummer();

			ImageIcon icon = new ImageIcon("images/spielfiguren/red.png");
			spieler.setSpielfigur(icon.getImage());

			ImageIcon icon1 = new ImageIcon("images/spielfiguren/black.png");
			spieler1.setSpielfigur(icon1.getImage());

			ImageIcon icon2 = new ImageIcon("images/spielfiguren/blue.png");
			spieler2.setSpielfigur(icon2.getImage());

			figur = new Spielfigur(spieler.getSpielfigur());
			figur1 = new Spielfigur(spieler1.getSpielfigur());
			figur2 = new Spielfigur(spieler2.getSpielfigur());

			sP.add(figur, new SpielerPosition().getSpielerPosition(spPosition, spNummer));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur1, new SpielerPosition().getSpielerPosition(spPosition1, spNummer1));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur2, new SpielerPosition().getSpielerPosition(spPosition2, spNummer2));
			spiel.repaint();
			spiel.revalidate();
		}

		if (spielerliste.size() == 4) {

			spieler = spielerliste.get(0);
			spieler1 = spielerliste.get(1);
			spieler2 = spielerliste.get(2);
			spieler3 = spielerliste.get(3);

			spPosition = spieler.getSpielerPosition().getNummer();
			spPosition1 = spieler1.getSpielerPosition().getNummer();
			spPosition2 = spieler2.getSpielerPosition().getNummer();
			spPosition3 = spieler3.getSpielerPosition().getNummer();

			spNummer = spieler.getSpielerNummer();
			spNummer1 = spieler1.getSpielerNummer();
			spNummer2 = spieler2.getSpielerNummer();
			spNummer3 = spieler3.getSpielerNummer();

			ImageIcon icon = new ImageIcon("images/spielfiguren/red.png");
			spieler.setSpielfigur(icon.getImage());

			ImageIcon icon1 = new ImageIcon("images/spielfiguren/black.png");
			spieler1.setSpielfigur(icon1.getImage());

			ImageIcon icon2 = new ImageIcon("images/spielfiguren/blue.png");
			spieler2.setSpielfigur(icon2.getImage());

			ImageIcon icon3 = new ImageIcon("images/spielfiguren/pink.png");
			spieler3.setSpielfigur(icon3.getImage());

			figur = new Spielfigur(spieler.getSpielfigur());
			figur1 = new Spielfigur(spieler1.getSpielfigur());
			figur2 = new Spielfigur(spieler2.getSpielfigur());
			figur3 = new Spielfigur(spieler3.getSpielfigur());

			sP.add(figur, new SpielerPosition().getSpielerPosition(spPosition, spNummer));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur1, new SpielerPosition().getSpielerPosition(spPosition1, spNummer1));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur2, new SpielerPosition().getSpielerPosition(spPosition2, spNummer2));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur3, new SpielerPosition().getSpielerPosition(spPosition3, spNummer3));
			spiel.repaint();
			spiel.revalidate();
		}

		if (spielerliste.size() == 5) {

			spieler = spielerliste.get(0);
			spieler1 = spielerliste.get(1);
			spieler2 = spielerliste.get(2);
			spieler3 = spielerliste.get(3);
			spieler4 = spielerliste.get(4);

			spPosition = spieler.getSpielerPosition().getNummer();
			spPosition1 = spieler1.getSpielerPosition().getNummer();
			spPosition2 = spieler2.getSpielerPosition().getNummer();
			spPosition3 = spieler3.getSpielerPosition().getNummer();
			spPosition4 = spieler4.getSpielerPosition().getNummer();

			spNummer = spieler.getSpielerNummer();
			spNummer1 = spieler1.getSpielerNummer();
			spNummer2 = spieler2.getSpielerNummer();
			spNummer3 = spieler3.getSpielerNummer();
			spNummer4 = spieler4.getSpielerNummer();

			ImageIcon icon = new ImageIcon("images/spielfiguren/red.png");
			spieler.setSpielfigur(icon.getImage());

			ImageIcon icon1 = new ImageIcon("images/spielfiguren/black.png");
			spieler1.setSpielfigur(icon1.getImage());

			ImageIcon icon2 = new ImageIcon("images/spielfiguren/blue.png");
			spieler2.setSpielfigur(icon2.getImage());

			ImageIcon icon3 = new ImageIcon("images/spielfiguren/pink.png");
			spieler3.setSpielfigur(icon3.getImage());

			ImageIcon icon4 = new ImageIcon("images/spielfiguren/yellow.png");
			spieler4.setSpielfigur(icon4.getImage());

			figur = new Spielfigur(spieler.getSpielfigur());
			figur1 = new Spielfigur(spieler1.getSpielfigur());
			figur2 = new Spielfigur(spieler2.getSpielfigur());
			figur3 = new Spielfigur(spieler3.getSpielfigur());
			figur4 = new Spielfigur(spieler4.getSpielfigur());

			sP.add(figur, new SpielerPosition().getSpielerPosition(spPosition, spNummer));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur1, new SpielerPosition().getSpielerPosition(spPosition1, spNummer1));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur2, new SpielerPosition().getSpielerPosition(spPosition2, spNummer2));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur3, new SpielerPosition().getSpielerPosition(spPosition3, spNummer3));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur4, new SpielerPosition().getSpielerPosition(spPosition4, spNummer4));
			spiel.repaint();
			spiel.revalidate();
		}

		if (spielerliste.size() == 6) {

			spieler = spielerliste.get(0);
			spieler1 = spielerliste.get(1);
			spieler2 = spielerliste.get(2);
			spieler3 = spielerliste.get(3);
			spieler4 = spielerliste.get(4);
			spieler5 = spielerliste.get(5);

			spPosition = spieler.getSpielerPosition().getNummer();
			spPosition1 = spieler1.getSpielerPosition().getNummer();
			spPosition2 = spieler2.getSpielerPosition().getNummer();
			spPosition3 = spieler3.getSpielerPosition().getNummer();
			spPosition4 = spieler4.getSpielerPosition().getNummer();
			spPosition5 = spieler5.getSpielerPosition().getNummer();

			spNummer = spieler.getSpielerNummer();
			spNummer1 = spieler1.getSpielerNummer();
			spNummer2 = spieler2.getSpielerNummer();
			spNummer3 = spieler3.getSpielerNummer();
			spNummer4 = spieler4.getSpielerNummer();
			spNummer5 = spieler5.getSpielerNummer();

			ImageIcon icon = new ImageIcon("images/spielfiguren/red.png");
			spieler.setSpielfigur(icon.getImage());

			ImageIcon icon1 = new ImageIcon("images/spielfiguren/black.png");
			spieler1.setSpielfigur(icon1.getImage());

			ImageIcon icon2 = new ImageIcon("images/spielfiguren/blue.png");
			spieler2.setSpielfigur(icon2.getImage());

			ImageIcon icon3 = new ImageIcon("images/spielfiguren/pink.png");
			spieler3.setSpielfigur(icon3.getImage());

			ImageIcon icon4 = new ImageIcon("images/spielfiguren/yellow.png");
			spieler4.setSpielfigur(icon4.getImage());

			ImageIcon icon5 = new ImageIcon("images/spielfiguren/green.png");
			spieler5.setSpielfigur(icon5.getImage());

			figur = new Spielfigur(spieler.getSpielfigur());
			figur1 = new Spielfigur(spieler1.getSpielfigur());
			figur2 = new Spielfigur(spieler2.getSpielfigur());
			figur3 = new Spielfigur(spieler3.getSpielfigur());
			figur4 = new Spielfigur(spieler4.getSpielfigur());
			figur5 = new Spielfigur(spieler5.getSpielfigur());

			sP.add(figur, new SpielerPosition().getSpielerPosition(spPosition, spNummer));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur1, new SpielerPosition().getSpielerPosition(spPosition1, spNummer1));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur2, new SpielerPosition().getSpielerPosition(spPosition2, spNummer2));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur3, new SpielerPosition().getSpielerPosition(spPosition3, spNummer3));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur4, new SpielerPosition().getSpielerPosition(spPosition4, spNummer4));
			spiel.repaint();
			spiel.revalidate();

			sP.add(figur5, new SpielerPosition().getSpielerPosition(spPosition5, spNummer5));
			spiel.repaint();
			spiel.revalidate();
		}

	}

	public void bildWeg() {
		try {
			spielerliste = server.getAllSpieler();

			if (spielerliste.size() == 2) {
				sP.remove(figur);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur1);
				spiel.repaint();
				spiel.revalidate();
			}

			if (spielerliste.size() == 3) {
				sP.remove(figur);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur1);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur2);
				spiel.repaint();
				spiel.revalidate();
			}

			if (spielerliste.size() == 4) {
				sP.remove(figur);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur1);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur2);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur3);
				spiel.repaint();
				spiel.revalidate();
			}

			if (spielerliste.size() == 5) {
				sP.remove(figur);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur1);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur2);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur3);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur4);
				spiel.repaint();
				spiel.revalidate();
			}

			if (spielerliste.size() == 6) {
				sP.remove(figur);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur1);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur2);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur3);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur4);
				spiel.repaint();
				spiel.revalidate();

				sP.remove(figur5);
				spiel.repaint();
				spiel.revalidate();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void disableGui(){
		sBP.getButton1().setEnabled(false);
		sBP.getButton2().setEnabled(false);
		sBP.getButton3().setEnabled(false);
		sBP.getButton4().setEnabled(false);

	}
	
	public void enableGui(){
		sBP.getButton1().setEnabled(true);
		sBP.getButton2().setEnabled(true);
		sBP.getButton3().setEnabled(true);
		sBP.getButton4().setEnabled(true);

	}
	
	public void loop() {
		try {
			do {
				// Ask server for current turn (i.e. status of game loop)
				// (method blocks, if not enough players registered)
				Turn turn = server.getTurn();
				Spieler currentPlayer = turn.getWerIstDran();
				
				if (currentPlayer.equals(this.player)) {
					// It is this player's turn!

					// update UI, e.g. enable UI elements such as buttons
					enableGui();

					// Pause loop until player has taken some action
					// (-> action is carried out in ActionListener of button;
					//     action will cancel waiting and continue loop)
					try {
						System.out.println("GUI ist aktualisiert. ");
						System.out.println("Client-seitige Game Loop gestoppt, bis Benutzer Aktion durchgefÃ¼hrt hat.");
						synchronized (actionKey) {
							bildHinzu(0);
							actionKey.wait();
						}
						System.out.println("Loop geht weiter...");
					} catch (InterruptedException e) {
						System.err.println(e.getMessage());
					}

					// current turn is finished, let server progress to next turn
				} else {
					// It is another player's turn!
					// Nothing to do; just deactivate UI...
					disableGui();
					// and ... wait for next turn.
					// (playerKey will be notified after server-side game event of type NEXT_PLAYER)
					synchronized (playerKey) {
						try {
							playerKey.wait();
							bildHinzu(0);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			} while (true);
		} catch (RemoteException e1) {
			System.err.println("Something went wrong with RMI; leaving game loop.");
			e1.printStackTrace();
		}
	}

	public void handleGameEvent(GameEvent event) {
		if (event instanceof GameControlEvent) {
			GameControlEvent gce = (GameControlEvent) event;
			switch (gce.getType()) {
			case GAME_STARTED:
				//JOptionPane.showConfirmDialog(spiel, "Spiel gestartet");
				break;
			case NEXT_PLAYER:
				if (gce.getPlayer().equals(player)) {
					// Next player, notify waiting player
					synchronized (playerKey) {
						playerKey.notify();
					}
				}
				break;
			case GAME_OVER:
				disableGui();
				JOptionPane.showMessageDialog(spiel,
						"Game over. Winner is " + gce.getPlayer().getSpielerName() + ".",
						"Game Over",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			default:
			}
		} else if (event instanceof GameActionEvent) {
			GameActionEvent gae = (GameActionEvent) event;
			if (!gae.getPlayer().equals(this.player)) {
				// Event originates from other player and is relevant for me:
				switch (gae.getType()) {
				case NEW_OWNER:
					JOptionPane.showMessageDialog(spiel,
							"Eine Strasse wurde von " + gae.getPlayer().getSpielerName() + " gekauft.",
							"UI Update!",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				default:
				}
			} else {
				// Event originates from me and is relevant for me:
				switch (gae.getType()) {
				case BUY_ITEM:
					JOptionPane.showMessageDialog(spiel,
							"Spieler : " + gae.getPlayer().getSpielerName() + "hat ein Haus gebaut.",
							"Haus Bau",
							JOptionPane.QUESTION_MESSAGE);
					break;
				default:
				}
			}
		}
	}
	
}
