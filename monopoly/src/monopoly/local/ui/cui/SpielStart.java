package monopoly.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import monopoly.local.domain.Monopoly;
import monopoly.local.domain.Spielerverwaltung;
import monopoly.local.domain.Spielverwaltung;
import monopoly.local.domain.Spielverwaltung.Phase;
import monopoly.local.domain.Spielverwaltung.Turn;
import monopoly.local.domain.exceptions.GehaltException;
import monopoly.local.domain.exceptions.HausbauException;
import monopoly.local.persistenz.PersistenzSpeichern;
import monopoly.local.valueobjects.Ereignisfeld;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Gemeinschaftsfeld;
import monopoly.local.valueobjects.Jail;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;

public class SpielStart {
	private boolean schleife = true;
	private boolean roundLoop = true;
	private Spieler spieler;
	private String buffer;
	private int auswahl;
	private Monopoly monopoly;

	/**
	 * Konstruktor der Klasse SpielStart
	 */
	public SpielStart(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	/**
	 * startet die Spielschleife die das Auswahlmenï¿½ im Spiel auf der Konsole
	 * ausgibt und die Ausgaben des Spielers lieï¿½t das Spiel wird beendet wenn
	 * alle bis auf ein Spieler pleite sind, der Gewinner wird auf der Konsole
	 * ausgegeben
	 * 
	 * case 1: der Spieler wï¿½rfelt und wird um die Augenzahl weiter gesetzt,
	 * ist die Straï¿½e kï¿½uflich kann der Spieler entscheiden ob er die
	 * Straï¿½e kaufen mï¿½chte, ist die Straï¿½e bereits verkauft zahlt der
	 * Spieler Miete case 2: der Spieler baut wenn es mï¿½glich ist ein Haus auf
	 * einer Straï¿½e und kann wenn es mï¿½glich ist seine Runde vortsetzen case
	 * 3: der Spieler nimmt wenn es mï¿½glich ist eine Hypothek auf und setzt
	 * wenn es mï¿½glich ist seine Runde fort
	 */
	public void start(boolean gamestart) {
		BufferedReader eingabe = new BufferedReader(new InputStreamReader(System.in));
		monopoly.TurnIni(gamestart);
		while (schleife) {
			Turn aktuellerTurn = monopoly.getTurn();
			spieler = aktuellerTurn.getWerIstDran();
			roundLoop = true;
			switch (aktuellerTurn.getPhase()) {

			// ******************** PHASE JailCheck ******
			case JailCheck: {
				if (monopoly.isInJail(spieler)) {
					do {
						System.out.println("1:Würfeln.");
						try {
							buffer = eingabe.readLine();
							auswahl = Integer.parseInt(buffer);
							if (auswahl == 1) {
								int anzahl = monopoly.wuerfel();
								wuerfelAnzeigen(anzahl);
								if (anzahl == 6) {
									System.out.println("Sie kommen aus dem Gefängnis frei.");
								} else {
									System.out.println("Sie bleiben im Gefängnis.");
									aktuellerTurn.Jailed();
								}
								roundLoop = false;
							} else {
								roundLoop = true;
							}
						} catch (IOException e) {
							auswahl = 0;
							System.out.println("Auswahl fehlerhaft.");
							roundLoop = true;
						} catch (NumberFormatException e) {
							auswahl = 0;
							System.out.println("Auswahl fehlerhaft.");
							roundLoop = true;
						}

					} while (roundLoop);
				} else {

				}
			}
				// ******************** PHASE JailCheck END **
				break;
			// ******************** PHASE DICE ***********

			case Dice: {
				do {
					try {
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println("Spieler " + spieler.getSpielerNummer() + " " + spieler.getSpielerName()
								+ " ist dran.");
						System.out.println("Ihr Budget beträgt : " + spieler.getSpielerBudget());
						System.out.println("Was wollen Sie tun?");
						System.out.println("1:Würfeln.");
						System.out.println("2:Haus bauen.");
						System.out.println("3:Hypothek aufnehmen.");
						System.out.println("4:Spielstand speichern.");
						buffer = eingabe.readLine();
						auswahl = Integer.parseInt(buffer);

					} catch (IOException e) {
						auswahl = 0;
						System.out.println("Auswahl fehlerhaft.");
					} catch (NumberFormatException e) {
						auswahl = 0;
						System.out.println("Auswahl fehlerhaft.");
					}
					Strasse[] yourStreets = monopoly.getYourStreets(spieler);
					switch (auswahl) {
					case 1: // pruefe ob im Gefaengnis

						int anzahl = monopoly.wuerfel();
						wuerfelAnzeigen(anzahl);						
						monopoly.move(spieler, 5);
						showFeld(monopoly.getSpielfeld(), monopoly.getAllSpieler());
						System.out.println("Sie befinden sich auf der Straße : " + monopoly.getStrasseName(spieler));

						if (monopoly.getBesitzer(spieler.getSpielerPosition()).equals(spieler)) {
							System.out.println("Diese Straße gehört Ihnen bereits.");
						} else {
							System.out.print("Mietpreis : " + monopoly.miete(spieler));
							System.out.print(" / Kaufpreis : " + monopoly.preis(spieler));
							System.out.print(" / Aktuelles Budget : " + spieler.getSpielerBudget());
							System.out.println("");
						}
						if(aktuellerTurn.getPhase() == Phase.End && spieler.getSpielerPosition() instanceof Jail){
							System.out.println("Sie sind nun im Gefängnis");
						} else if(aktuellerTurn.getPhase() == Phase.End && spieler.getSpielerPosition() instanceof Ereignisfeld){
							System.out.println("Sie sind auf einem Ereignisfeld");
						}else if(aktuellerTurn.getPhase() == Phase.End && spieler.getSpielerPosition() instanceof Gemeinschaftsfeld){
							System.out.println("Sie sind auf einem Gemeinschaftsfeld");
						}
						roundLoop = false;

						break;
					case 2:
						if (yourStreets != null) {
							for (Strasse strasse : yourStreets) {
								int nr = strasse.getNummer();
								String strassenName = monopoly.getFeldName(nr);
								int hausAnzahl = monopoly.getHaeuseranzahl(nr);

								System.out.print(nr + " : " + strassenName + " hat " + hausAnzahl);

								if (hausAnzahl == 1) {
									System.out.print(" Haus.");
								} else if (hausAnzahl == 0 || (hausAnzahl > 1 && hausAnzahl < 4)) {
									System.out.print(" Hï¿½user.");
								} else if (hausAnzahl == 4) {
									System.out
											.print(" HÃ¤user. Auf dieser StraÃŸe kann jetzt ein Hotel gebaut werden.");
								}
								System.out.println(" ");
							}
							try {
								System.out.println(" ");
								System.out.println(
										"Geben Sie die Straï¿½en-Nummer, ein auf der Sie ein Haus bauen mï¿½chten.");
								buffer = eingabe.readLine();
								auswahl = Integer.parseInt(buffer);
								boolean pruefen = false;
								for (Strasse strasse : yourStreets) {
									int nr = strasse.getNummer();
									if (auswahl == nr) {
										pruefen = true;
									}
								}

								if (pruefen) {
									try{
										monopoly.bauHaus(auswahl, spieler); 
										System.out.println("Das Haus wurde erfolgreich gebaut.");
									}catch(HausbauException e){
										System.out.println(e.getMessage());
									}catch(GehaltException e){
										System.out.println(e.getMessage());
									}
									
								}

								else if (pruefen == false) {
									System.out.println("Die Strasse existiert nicht oder Sie sind nicht ihr Besitzer.");
								}

								else if (pruefen
										&& (monopoly.getHaeuseranzahl(spieler.getSpielerPosition().getNummer()) > 4)) {
									System.out.println("Maximale Anzahl an HÃ¤usern fÃ¼r diese StraÃŸe erreicht.");
								}

							} catch (IOException e) {
								e.printStackTrace();
								auswahl = 0;
								System.out.println("Auswahl fehlerhaft.");
							} catch (NumberFormatException e) {
								e.printStackTrace();
								auswahl = 0;
								System.out.println("Auswahl fehlerhaft.");
							}
						} else {
							System.out.println("Sie besitzen keine Straï¿½en.");
						}

						roundLoop = true;
						break;
					case 3:
						if (yourStreets != null) {
							// for(Strasse strasse : yourStreets){
							// int nr = strasse.getNummer();
							// String strassenName =
							// feldverwaltung.getFeldName(nr);
							System.out.println("Liste der StraÃŸen, die sich in ihrem Besitz befinden:");
							for (Strasse s : yourStreets) {
								System.out.print("Name: " + s.getName() + " | Hypothek: "
										+ (s.getHypothek() ? " Hypothek zurÃ¼ckzahlen" : " Keine Hypothek") + " | Nr: "
										+ s.getNummer());
								System.out.println(" ");
							}
							// System.out.println(nr+" : "+strassenName+" hat
							// Hypothek aufgenommen : "+strasse.getHypothek());
							// }
							try {
								System.out.println(
										"Geben Sie die Straï¿½en-Nummer ein, auf der Sie die Hypothek ï¿½ndern wollen.");
								buffer = eingabe.readLine();
								auswahl = Integer.parseInt(buffer);
								boolean pruefen = false;
								for (Strasse strasse : yourStreets) {
									int nr = strasse.getNummer();
									if (auswahl == nr) {
										pruefen = true;
									}
								}
								if (pruefen) {
									String str;
									try {
										str = monopoly.switchHypothek(auswahl);
										System.out.println(str);
									} catch (GehaltException e) {
										System.out.println(e.getMessage());
									}
								} else {
									System.out.println("Die Strasse existiert nicht oder Sie sind nicht ihr Besitzer.");
								}

							} catch (IOException e) {
								e.printStackTrace();
								auswahl = 0;
								System.out.println("Auswahl fehlerhaft.");
							} catch (NumberFormatException e) {
								e.printStackTrace();
								auswahl = 0;
								System.out.println("Auswahl fehlerhaft.");
							}
						} else {
							System.out.println("Sie besitzen keine Straï¿½en.");
						}
						roundLoop = true;
						break;

					case 4:
						System.out.println("Geben Sie den Namen der Speicher Datei an.");
						try {
							buffer = eingabe.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println("Spielstand erfolgreich gespeichert.");
						PersistenzSpeichern speichern = new PersistenzSpeichern();
						speichern.saveAll(monopoly.getAllSpieler(), monopoly.getSpielfeld(),aktuellerTurn,buffer);
						break;

					default:
						System.out.println("Keine Gï¿½ltige Auswahl.");
						roundLoop = true;
					}
				} while (roundLoop);
			}
				// ********************************* PHASE DICE END
				// ***********************************
				break;
			// ********************************* PHASE Passiv
			// *************************************

			case Passiv: {
				if (monopoly.getBesitzer(spieler.getSpielerPosition()).getSpielerNummer() == 99) {
					boolean loop = true;
					do {
						System.out.println("Wollen Sie die Strasse kaufen?");
						System.out.println("'y' fï¿½r Ja/ 'n' fï¿½r Nein.");
						char check = 'k';
						try {
							buffer = eingabe.readLine();
							if (buffer.length() != 0) {
								check = buffer.charAt(0);
							} else {
								check = 'k';
							}

						} catch (IOException e) {
							e.printStackTrace();
						}
						if (check == 'y' || check == 'Y' || check == 'j' || check == 'J') {
							try {
								monopoly.kaufStrasse(spieler);
								System.out.println("Kauf erfolgreich.");
							} catch (GehaltException e) {
								System.out.println(e.getMessage());
							}
							System.out.println("Kosten: -" + monopoly.preis(spieler));
							System.out.println(
									spieler.getSpielerName() + " ihr Budget beträgt : " + spieler.getSpielerBudget());
							loop = false;
						} else if (check == 'n' || check == 'N') {
							loop = false;
						} else {
							loop = true;
							System.out.println("Eingabe Fehlerhaft.");
						}
					} while (loop == true);

				} else {

					if (monopoly.getBesitzer(spieler.getSpielerPosition()).getSpielerNummer() == spieler
							.getSpielerNummer()) {
						System.out.println("Sie mussten keine Miete zahlen.");
					} else {
						spieler.setSpielerBudget(spieler.getSpielerBudget() - monopoly.miete(spieler));
						monopoly.mieteZahlen(monopoly.miete(spieler),
								monopoly.getBesitzer(spieler.getSpielerPosition()), spieler);
						System.out.println("Sie mussten " + monopoly.miete(spieler) + " Miete an "
								+ monopoly.getBesitzer(spieler.getSpielerPosition()).getSpielerName() + " zahlen.");
						System.out.println("Ihr Budget beträgt jetzt = " + spieler.getSpielerBudget());
					}
					
				}

			}
				// ************************** PHASE Passiv END
				// *********************
				break;
			// ************************** PHASE End ****************************
			case End: {
				do {
					try {
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println("Spieler " + spieler.getSpielerNummer() + " " + spieler.getSpielerName()
								+ " ist dran.");
						System.out.println("Ihr Budget beträgt : " + spieler.getSpielerBudget());
						System.out.println("Was wollen Sie tun?");
						System.out.println("1:Runde Beenden");
						System.out.println("2:Haus bauen.");
						System.out.println("3:Hypothek aufnehmen.");
						System.out.println("4:Spielstand speichern.");
						buffer = eingabe.readLine();
						auswahl = Integer.parseInt(buffer);

					} catch (IOException e) {
						auswahl = 0;
						System.out.println("Auswahl fehlerhaft.");
					} catch (NumberFormatException e) {
						auswahl = 0;
						System.out.println("Auswahl fehlerhaft.");
					}
					Strasse[] yourStreets = monopoly.getYourStreets(spieler);
					switch (auswahl) {
					case 1: // pruefe ob im Gefaengnis

						roundLoop = false;

						break;
					case 2:
						if (yourStreets != null) {
							for (Strasse strasse : yourStreets) {
								int nr = strasse.getNummer();
								String strassenName = monopoly.getFeldName(nr);
								int hausAnzahl = monopoly.getHaeuseranzahl(nr);

								System.out.print(nr + " : " + strassenName + " hat " + hausAnzahl);

								if (hausAnzahl == 1) {
									System.out.print(" Haus.");
								} else if (hausAnzahl == 0 || (hausAnzahl > 1 && hausAnzahl < 4)) {
									System.out.print(" Hï¿½user.");
								} else if (hausAnzahl == 4) {
									System.out
											.print(" HÃ¤user. Auf dieser StraÃŸe kann jetzt ein Hotel gebaut werden.");
								}
								System.out.println(" ");
							}
							try {
								System.out.println(" ");
								System.out.println(
										"Geben Sie die Straï¿½en-Nummer, ein auf der Sie ein Haus bauen mï¿½chten.");
								buffer = eingabe.readLine();
								auswahl = Integer.parseInt(buffer);
								boolean pruefen = false;
								for (Strasse strasse : yourStreets) {
									int nr = strasse.getNummer();
									if (auswahl == nr) {
										pruefen = true;
									}
								}

								if (pruefen) {
									try{
										monopoly.bauHaus(auswahl, spieler);
										System.out.println("Das Haus wurde erfolgreich gebaut.");
									}catch(HausbauException e){
										System.out.println(e.getMessage());
									}catch(GehaltException e){
										System.out.println(e.getMessage());
									}
								}

								else if (pruefen == false) {
									System.out.println("Die Strasse existiert nicht oder Sie sind nicht ihr Besitzer.");
								}

								else if (pruefen
										&& (monopoly.getHaeuseranzahl(spieler.getSpielerPosition().getNummer()) > 4)) {
									System.out.println("Maximale Anzahl an HÃ¤usern fÃ¼r diese StraÃŸe erreicht.");
								}

							} catch (IOException e) {
								e.printStackTrace();
								auswahl = 0;
								System.out.println("Auswahl fehlerhaft.");
							} catch (NumberFormatException e) {
								e.printStackTrace();
								auswahl = 0;
								System.out.println("Auswahl fehlerhaft.");
							}
						} else {
							System.out.println("Sie besitzen keine Straï¿½en.");
						}

						roundLoop = true;
						break;
					case 3:
						if (yourStreets != null) {
							// for(Strasse strasse : yourStreets){
							// int nr = strasse.getNummer();
							// String strassenName =
							// feldverwaltung.getFeldName(nr);
							System.out.println("Liste der StraÃŸen, die sich in ihrem Besitz befinden:");
							for (Strasse s : yourStreets) {
								System.out.print("Name: " + s.getName() + " | Hypothek: "
										+ (s.getHypothek() ? " Hypothek zurÃ¼ckzahlen" : " Keine Hypothek") + " | Nr: "
										+ s.getNummer());
								System.out.println(" ");
							}
							// System.out.println(nr+" : "+strassenName+" hat
							// Hypothek aufgenommen : "+strasse.getHypothek());
							// }
							try {
								System.out.println(
										"Geben Sie die Straï¿½en-Nummer ein, auf der Sie die Hypothek ï¿½ndern wollen.");
								buffer = eingabe.readLine();
								auswahl = Integer.parseInt(buffer);
								boolean pruefen = false;
								for (Strasse strasse : yourStreets) {
									int nr = strasse.getNummer();
									if (auswahl == nr) {
										pruefen = true;
									}
								}
								if (pruefen) {
									String str;
									try {
										str = monopoly.switchHypothek(auswahl);
										System.out.println(str);
									} catch (GehaltException e) {
										System.out.println(e.getMessage());
									}
								} else {
									System.out.println("Die Strasse existiert nicht oder Sie sind nicht ihr Besitzer.");
								}

							} catch (IOException e) {
								e.printStackTrace();
								auswahl = 0;
								System.out.println("Auswahl fehlerhaft.");
							} catch (NumberFormatException e) {
								e.printStackTrace();
								auswahl = 0;
								System.out.println("Auswahl fehlerhaft.");
							}
						} else {
							System.out.println("Sie besitzen keine Straï¿½en.");
						}
						roundLoop = true;
						break;

					case 4:
						System.out.println("Geben Sie den Namen der Speicher Datei ein.");
						try {
							buffer = eingabe.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						PersistenzSpeichern speichern = new PersistenzSpeichern();
						speichern.saveAll(monopoly.getAllSpieler(), monopoly.getSpielfeld(),aktuellerTurn,buffer);
						break;

					default:
						System.out.println("Keine Gï¿½ltige Auswahl.");
						roundLoop = true;
					}
				} while (roundLoop);
			}

			// ************************** PHASE End END ************************
			default: {
			}
				break;
			}
			monopoly.nextTurn();
			// ************************** RUNDEN ENDE CHECK ********************
			monopoly.checkPleite();
			if (monopoly.getAllSpieler().size() == 1) {
				for (Spieler player : monopoly.getAllSpieler()) {
					System.out.println("Der Gewinner ist : " + player.getSpielerName());
					schleife = false;
				}
			}
		}
	}

	/**
	 * gibt das Spielfeld mit den Spielern auf der Konsole aus
	 */
	public static void showFeld(Feld[] feld, Vector<Spieler> spieler) {
		int[] arrayLinks = { 35, 34, 33, 32, 31, 30, 29, 28 };
		int[] arrayRechts = { 10, 11, 12, 13, 14, 15, 16, 17 };
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		System.out.print("|");
		for (int i = 0; i < 10; i++) {
			int anzahl = 0;
			for (Spieler k : spieler)
				if (k.getSpielerPosition().getNummer() == i) {
					anzahl = anzahl + 1;
				}
			System.out.print("  " + anzahl + "  |");
		}
		System.out.println();
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		for (int i = 0; i < arrayLinks.length; i++) {
			int anzahl = 0;
			for (Spieler k : spieler) {
				if (k.getSpielerPosition().getNummer() == arrayLinks[i]) {
					anzahl = anzahl + 1;
				}
			}
			int anzahl2 = 0;
			for (Spieler k : spieler)
				if (k.getSpielerPosition().getNummer() == arrayRechts[i]) {
					anzahl2 = anzahl2 + 1;
				}
			System.out.println(
					"|  " + anzahl + "  |                                               |  " + anzahl2 + "  |");
			if (i != arrayLinks.length - 1)
				System.out.println("|-----|                                               |-----|");
		}
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		System.out.print("|");
		for (int i = 27; i > 17; i--) {
			int anzahl = 0;
			for (Spieler k : spieler)
				if (k.getSpielerPosition().getNummer() == i) {
					anzahl = anzahl + 1;
				}
			System.out.print("  " + anzahl + "  |");
		}
		System.out.println();
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
	}

	/**
	 * gibt die gewï¿½rfelte Zahl auf der Konsole aus
	 */
	public int wuerfelAnzeigen(int zahl) {
		switch (zahl) {
		case 1:
			System.out.println("-------");
			System.out.println("|     |");
			System.out.println("|  o  |");
			System.out.println("|     |");
			System.out.println("-------");
			break;

		case 2:
			System.out.println("-------");
			System.out.println("|o    |");
			System.out.println("|     |");
			System.out.println("|    o|");
			System.out.println("-------");
			break;

		case 3:
			System.out.println("-------");
			System.out.println("| o o |");
			System.out.println("|     |");
			System.out.println("|  o  |");
			System.out.println("-------");
			break;

		case 4:
			System.out.println("-------");
			System.out.println("| o o |");
			System.out.println("|     |");
			System.out.println("| o o |");
			System.out.println("-------");
			break;

		case 5:
			System.out.println("-------");
			System.out.println("| o o |");
			System.out.println("|  o  |");
			System.out.println("| o o |");
			System.out.println("-------");
			break;

		case 6:
			System.out.println("-------");
			System.out.println("| o o |");
			System.out.println("| o o |");
			System.out.println("| o o |");
			System.out.println("-------");
			break;
		}
		return zahl;
	}
}
