package monopoly.local.ui.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import monopoly.local.domain.Monopoly;
import monopoly.local.domain.Spielverwaltung.Phase;
import monopoly.local.domain.Spielverwaltung.Turn;
import monopoly.local.domain.exceptions.GehaltException;
import monopoly.local.domain.exceptions.HausbauException;
import monopoly.local.ui.gui.swing.Menue.Menuefenster;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;
import net.miginfocom.swing.MigLayout;

public class Spielfenster {
	// Variablen f�r das Spielfenster
	private JFrame spiel;
	private SButtonPanel sBP;
	private SpielPanel sP;
	private SInfoPanel sIP;
	private SInfoPanel sIP2;
	private Monopoly monopoly;
	private HausFenster haFenster;
	private HypothekFenster hyFenster;
	private SpeichernFenster speFenster;

	private Vector<Spieler> spielerliste;

	Spieler player;
	Spieler spieler;
	Spieler spieler1;
	Spieler spieler2;
	Spieler spieler3;
	Spieler spieler4;
	Spieler spieler5;

	Spielfigur figur;
	Spielfigur figur1;
	Spielfigur figur2;
	Spielfigur figur3;
	Spielfigur figur4;
	Spielfigur figur5;

	int spPosition;
	int spPosition1;
	int spPosition2;
	int spPosition3;
	int spPosition4;
	int spPosition5;

	int spNummer;
	int spNummer1;
	int spNummer2;
	int spNummer3;
	int spNummer4;
	int spNummer5;

	Turn turn;

	/**
	 * Konstruktor der Klasse Spielfenster ein neuer JFrame wird erstellt und
	 * alle Komponenten hinzugefuegt enthaelt alle ActionListener f�r das
	 * Spielfenster
	 * 
	 * @param monopoly
	 */
	public Spielfenster(Monopoly monopoly) {
		this.monopoly = monopoly;
		spielerliste = monopoly.getAllSpieler();
	}

	public void sInit() {
		// Spielfeldfenster
		spiel = new JFrame("Monopoly");
		sP = new SpielPanel();
		sBP = new SButtonPanel();
		sIP = new SInfoPanel();
		sIP2 = new SInfoPanel();
		haFenster = new HausFenster(monopoly);
		hyFenster = new HypothekFenster(monopoly);
		speFenster = new SpeichernFenster(monopoly);
		spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spiel.setLayout(new MigLayout("", "[]10[]", "[]10[]"));

		spiel.add(sP, "cell 0 0,span 1 3, w 600, h 600");
		spiel.add(sBP, "cell 1 0, push, grow, shrink");
		spiel.add(sIP, "cell 1 1,h 150, push, grow, shrink");
		spiel.add(sIP2, "cell 1 2,h 150, push, grow, shrink");

		spiel.getContentPane().setBackground(new Color(197, 251, 255));

		bildHinzu(0);

		spiel.setSize(1000, 725);
		spiel.setVisible(true);
		turn = monopoly.getTurn();
		player = turn.getWerIstDran();
//		player.setSpielerPosition(monopoly.getSpielfeld()[1]);
//		try {
//			monopoly.kaufStrasse(player);
//			player.setSpielerPosition(monopoly.getSpielfeld()[3]);
//			monopoly.kaufStrasse(player);
//			player.setSpielerPosition(monopoly.getLos());
//		} catch (GehaltException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		// Info-TextAreas mit sich anpassenden Infos
		// sIP2.getSTextArea().setText(monopoly.getStrasseName(spieler) +
		// monopoly.getHaeuseranzahl(position) + monopoly.get);
		
		// ActionListener fuer den wuerfeln-Button
		sBP.getButton1().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Phase phase = turn.getPhase();
				if(phase == Phase.JailCheck && !(monopoly.isInJail(turn.getWerIstDran()))){
					monopoly.nextTurn();
					phase = turn.getPhase();
				}
				switch (phase) {
				case JailCheck:
					if (monopoly.isInJail(turn.getWerIstDran())) {
						int wurf = monopoly.wuerfel();
						if (wurf == 6) {
							monopoly.getJail().release(player);
							monopoly.nextTurn();
							turn = monopoly.getTurn();
							sBP.getButton1().setText("Würfel");
							sBP.repaint();
							sBP.revalidate();
						} else {
							monopoly.getTurn().Jailed();
							turn = monopoly.getTurn();
							sBP.getButton1().setText("Jailed");
							sBP.repaint();
							sBP.revalidate();
						}
					}else{
						monopoly.nextTurn();
						turn = monopoly.getTurn();
						sBP.getButton1().setText("Würfel");
						sBP.repaint();
						sBP.revalidate();
					}
					break;
				case Dice:
					int zugweite = monopoly.wuerfel();
					Spieler spieler = monopoly.getTurn().getWerIstDran();
					monopoly.move(spieler, zugweite);
					bildWeg();
					bildHinzu(0);
					monopoly.nextTurn();
					turn = monopoly.getTurn();
					sBP.getButton1().setText("Passiv");
					sBP.repaint();
					sBP.revalidate();
					break;
				case Passiv:
					Spieler besitzer = player.getSpielerPosition().getBesitzer();
					if(besitzer.getSpielerNummer() == 99){
						JOptionPane pane = new JOptionPane();
						String message = "Wollen Sie die Strasse : "+player.getSpielerPosition().getName()+" kaufen?";
						String title = "Strasse kaufen?";
						pane.setOptionType(JOptionPane.YES_NO_OPTION);
						pane.setMessageType(JOptionPane.QUESTION_MESSAGE);
						switch(pane.showConfirmDialog(spiel, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)){
						case JOptionPane.YES_OPTION : try {
								monopoly.kaufStrasse(player);
							} catch (GehaltException e1) {
								JOptionPane.showMessageDialog(spiel, e1.getMessage(), "Gehalt überschritten!", JOptionPane.WARNING_MESSAGE);
							}
							break;
						case JOptionPane.NO_OPTION :
							break;
						default :
						}
					}else if(besitzer.getSpielerNummer() != player.getSpielerNummer()){
						monopoly.miete(player);
					}
					monopoly.nextTurn();
					turn = monopoly.getTurn();
					sBP.getButton1().setText("Runde Beenden");
					sBP.repaint();
					sBP.revalidate();
					break;
				case End:
					monopoly.nextTurn();
					turn = monopoly.getTurn();
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
				player = turn.getWerIstDran();
				sIP2.getJTextArea().setText("Name :"+player.getSpielerName()+"\nGehalt : "+player.getSpielerBudget()+
						"\nPosition : "+player.getSpielerPosition().getName());
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
//					// } catch (GehaltException e1) {
//					// // TODO Auto-generated catch block
//					// e1.printStackTrace();
//					// }
//				}
			}                                                       
		});
		sP.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				// me.getX()/100;
				System.out.println("\"pos " + Math.round((double) me.getX() / sP.getWidth() * 100) / 100.0 + "al "
						+ Math.round((double) me.getY() / sP.getHeight() * 100) / 100.0 + "al\"");

			}
		});
		// ActionListener fuer den Haus-bauen-Button
		sBP.getButton2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spiel.remove(sBP);
				spiel.add(haFenster, "cell 1 0, push, grow, shrink");
				haFenster.refreshList();
				spiel.repaint();
				spiel.revalidate();
			}
		});

		// ActionListener fuer den Hypothek-aufnehmen-Button
		sBP.getButton3().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spiel.remove(sBP);
				spiel.add(hyFenster, "cell 1 0, push, grow, shrink");
				hyFenster.refreshList();
				spiel.repaint();
				spiel.revalidate();
			}
		});

		// ActionListener fuer den speichern-Button
		sBP.getButton4().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spiel.remove(sBP);
				spiel.add(speFenster, "cell 1 0, pushx, growx, shrinkx");
				spiel.repaint();
				spiel.revalidate();
				// JOptionPane.showMessageDialog(spiel, "Eggs are not supposed
				// to be green.");
			}
		});

		// ActionListener für den bauen-Button
		haFenster.getHaButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Spieler spieler = monopoly.getTurn().getWerIstDran();
				int position = spieler.getSpielerPosition().getNummer();
				if (!haFenster.getHaListe().isSelectionEmpty()) {
					try {
						monopoly.bauHaus(position, spieler);
					} catch (HausbauException e1) {
						JOptionPane.showMessageDialog(spiel, e1.getMessage());
					} catch (GehaltException e1) {
						JOptionPane.showMessageDialog(spiel, e1.getMessage());
					}
					spiel.remove(haFenster);
					spiel.add(sBP, "cell 1 0, push, grow, shrink");
					spiel.repaint();
					spiel.revalidate();
				}
			}
		});

		// ActionListener fuer den zurueck-Button
		haFenster.getHaButton2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spiel.remove(haFenster);
				spiel.add(sBP, "cell 1 0, push, grow, shrink");
				spiel.repaint();
				spiel.revalidate();
			}
		});

		// ActionListener fuer den aufnehmen-Button
		hyFenster.getHyButton1().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if hypothek == false -->if(monopoly.getHypothek == false){
				// }
				Spieler spieler = monopoly.getTurn().getWerIstDran();
				int position = spieler.getSpielerPosition().getNummer();
				if (!hyFenster.getHyListe().isSelectionEmpty()) {
					if (monopoly.getHypothek(position) == false) {
						try {
							monopoly.switchHypothek(position);
						} catch (GehaltException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						spiel.remove(hyFenster);
						spiel.add(sBP, "cell 1 0, push, grow, shrink");
						spiel.repaint();
						spiel.revalidate();
						JOptionPane.showMessageDialog(spiel, "Eggs are not supposed to be green.");
					}
				}
			}
		});

		// ActionListener fuer den abbezahlen-Button
		hyFenster.getHyButton2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if hypothek == true --> if(monopoly.getHypothek == true){
				// monopoly.switchHypothek(position);}
				if (!hyFenster.getHyListe().isSelectionEmpty()) {
					// if(){
					Spieler spieler = monopoly.getTurn().getWerIstDran();
					int position = spieler.getSpielerPosition().getNummer();
					// monopoly.switchHypothek(position);
					// spiel.remove(hyFenster);
					// spiel.add(sBP, "cell 1 0, push, grow, shrink");
					// spiel.repaint();
					// spiel.revalidate();
					JOptionPane.showMessageDialog(spiel, "Eggs are not supposed to be green.");
					// }
				}
			}
		});

		// ActionListener fuer den zurueck-Button
		hyFenster.getHyButton3().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spiel.remove(hyFenster);
				spiel.add(sBP, "cell 1 0, push, grow, shrink");
				spiel.repaint();
				spiel.revalidate();
			}
		});

		// ActionListener fuer den speichern-Button
		speFenster.getSfButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String datei = speFenster.getDatName().getText();
				monopoly.saveAll(datei);
				// zurueck zum Hauptmenue
				JOptionPane.showMessageDialog(spiel, "Speichern erfolgreich");
				// Spielfenster spFenster = new Spielfenster(monopoly);
				// spFenster.sInit();
				// menue.dispose();
				Menuefenster mFenster = new Menuefenster();
				mFenster.mSetVisible();
				spiel.dispose();
			}
		});

		// ActionListener fuer den zurueck-Button
		speFenster.getSfButton2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spiel.remove(speFenster);
				spiel.add(sBP, "cell 1 0, push, grow, shrink");
				spiel.repaint();
				spiel.revalidate();
			}
		});
	}

	public void bildHinzu(int zugweite) {
		int zug = zugweite;

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
		Vector<Spieler> spielerliste = monopoly.getAllSpieler();

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
	}
}
