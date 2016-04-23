package monopoly.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import monopoly.local.domain.Spielerverwaltung;
import monopoly.local.domain.Spielverwaltung;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;

public class SpielStart {
	private boolean loop = true;
	private boolean roundLoop = true;
	private Spieler player;
	private String buffer;
	private int choise;
	private Spielverwaltung fieldManagement;
	private Spielerverwaltung management;
	
	/**
	 * Konstruktor der Klasse SpielStart
	 * 
	 * @param fieldManagement
	 * @param management
	 */
	public SpielStart(Spielverwaltung fieldManagement , Spielerverwaltung management){
		this.fieldManagement = fieldManagement;
		this.management = management;
	}
	
	/**
	 * startet die Spielschleife die das Auswahlmenü im Spiel auf der Konsole ausgibt
	 * und die Ausgaben des Spielers ließt 
	 * das Spiel wird beendet wenn alle bis auf ein Spieler pleite sind, der Gewinner wird auf der Konsole ausgegeben
	 *   
	 * case 1: der Spieler würfelt und wird um die Augenzahl weiter gesetzt, ist die Straße käuflich kann der Spieler
	 * entscheiden ob er die Straße kaufen möchte, ist die Straße bereits verkauft zahlt der Spieler miete
	 * case 2: der Spieler baut wenn es möglich ist ein Haus auf einer Straße und 
	 * kann wenn es möglich ist seine Runde vortsetzen
	 * case 3: der Spieler nimmt wenn es möglich ist eine Hypothek auf und setzt wenn es möglich ist seine Runde fort
	 */
	public void start(){
			BufferedReader intake = new BufferedReader(new InputStreamReader(System.in));
			while(loop){
				player = management.reihenfolge();
				roundLoop = true;
				do{
					try {
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println("Spieler "+player.getSpielerNummer()+" "+player.getSpielerName()+" ist dran.");
						System.out.println("Ihr Budget beträgt : "+player.getSpielerBudget());
						System.out.println("Was wollen Sie tun?");
						System.out.println("1:Würfeln.");
						System.out.println("2:Haus bauen.");
						System.out.println("3:Hypothek aufnehmen.");
						buffer = intake.readLine();
						choise = Integer.parseInt(buffer);
						
					} catch (IOException e) {
						e.printStackTrace();
						choise = 0;
						System.out.println("Auswahl fehlerhaft.");
					} catch (NumberFormatException e){
						e.printStackTrace();
						choise = 0;
						System.out.println("Auswahl fehlerhaft.");
					}
					Strasse[] yourStreets = fieldManagement.getYourStreets(player);
					switch(choise){
					case 1:		int number = management.wuerfeln();
								wuerfelAnzeigen(number);
								fieldManagement.move(player, number);
								showFeld(fieldManagement.getSpielfeld(),management.getAllSpieler());
								System.out.println("Sie befinden sich auf der Straße : "+fieldManagement.getStrasseName(player));
								if (fieldManagement.getBesitzer(player.getSpielerPosition()).getSpielerNummer() ==  99){
									boolean loop = true;
									do{
										System.out.println("Wollen Sie die Strasse kaufen?");
										System.out.println("'y' für Ja/ 'n' für Nein.");
										char check = 'k';
										try {
											buffer = intake.readLine();
											if(buffer.length() != 0){
												check = buffer.charAt(0);
											}else {
												check = 'k';
											}
											 
										} catch (IOException e) {
											e.printStackTrace();
										}
										if(check == 'y' || check == 'Y'){
											System.out.println(fieldManagement.kaufStrasse(player) ? "Kauf erfolgreich" : "Kauf fehlgeschlagen");
											System.out.println(player.getSpielerName()+" ihr Budget beträgt : "+player.getSpielerBudget());
											loop = false;
										}else if(check == 'n' || check == 'N'){
											loop = false;
										} else {
											loop = true;
											System.out.println("Eingabe Fehlerhaft.");
										}
									}while(loop == true);
									
								}else{
									player.setSpielerBudget(player.getSpielerBudget() - fieldManagement.miete(player));
									management.mieteZahlen(fieldManagement.miete(player), fieldManagement.getBesitzer(player.getSpielerPosition()),player);
									System.out.println("Ihr Budget beträgt jetzt = "+player.getSpielerBudget());
								}
								roundLoop=false;
					break;
					case 2:		
								if(yourStreets != null){
									for(Strasse steet : yourStreets){
										int nr = steet.getNummer();
										String streetName = fieldManagement.getFeldName(nr);
										int houseNumber = fieldManagement.getHaeuseranzahl(nr);
										System.out.print(nr+" : "+streetName+" hat "+houseNumber);
										if(houseNumber == 1){
											System.out.println(" Haus.");
										}else{
											System.out.println(" Häuser.");
										}
									}
									try{
										System.out.println("Geben Sie die Straßen-Nummer, ein auf der Sie ein Haus bauen möchten.");
										buffer = intake.readLine();
										choise = Integer.parseInt(buffer);
										boolean test = false;
										for(Strasse street : yourStreets){
											int nr = street.getNummer();
											if(choise == nr){
												test = true;
											}
										}
										if(test){
											if(fieldManagement.bauHaus(choise, player)){
												System.out.println("Das Haus wurde erfolgreich gebaut.");
											}else{
												System.out.println("Haus konnte nicht gebaut werden.");
											}
										}else{
											System.out.println("Die Strasse existiert nicht oder Sie sind nicht ihr Besitzer.");
										}
										
									} catch (IOException e) {
										e.printStackTrace();
										choise = 0;
										System.out.println("Auswahl fehlerhaft.");
									} catch (NumberFormatException e){
										e.printStackTrace();
										choise = 0;
										System.out.println("Auswahl fehlerhaft.");
									}
								}else{
									System.out.println("Sie besitzen keine Straßen.");
								}
							
								roundLoop = true;
					break;
					case 3: 	if(yourStreets != null){
									for(Strasse street : yourStreets){
										int nr = street.getNummer();
										String streetName = fieldManagement.getFeldName(nr);
										System.out.println(nr+" : "+streetName+" hat Hypothek aufgenommen : "+street.getHypothek());
									}
									try{
										System.out.println("Geben Sie die Straßen-Nummer, ein auf der Sie die Hypothek ändern wollen.");
										buffer = intake.readLine();
										choise = Integer.parseInt(buffer);
										boolean test = false;
										for(Strasse street : yourStreets){
											int nr = street.getNummer();
											if(choise == nr){
												test = true;
											}
										}
										if(test){
											String str = fieldManagement.switchHypothek(choise);
											System.out.println(str);
										}else{
											System.out.println("Die Strasse existiert nicht oder Sie sind nicht ihr Besitzer.");
										}
										
									} catch (IOException e) {
										e.printStackTrace();
										choise = 0;
										System.out.println("Auswahl fehlerhaft.");
									} catch (NumberFormatException e){
										e.printStackTrace();
										choise = 0;
										System.out.println("Auswahl fehlerhaft.");
									}
								}else{
									System.out.println("Sie besitzen keine Straßen.");
								}
								roundLoop = true;
					break;
					default: 	System.out.println("Keine Gültige Auswahl.");
								roundLoop = true;
					}
				}while(roundLoop);
				if(!management.checkPleite().isEmpty()){
					for(Spieler player:management.checkPleite()){
						Strasse[] yourStreets = fieldManagement.getYourStreets(player);
						for(Strasse strasse : yourStreets){
							strasse.setBesitzer(new Spieler("Bank", 99, null, -1));
						}
						management.entfernen(player.getSpielerNummer());
					}
				}else{}
				if(management.getAllSpieler().size() == 1){
					for(Spieler player:management.getAllSpieler()){
						System.out.println("Der Gewinner ist : "+player.getSpielerName());
						loop = false;
					}
				}
			}
		}
	
	/**
	 * gibt das Spielfeld mit den Spielern auf der Konsole aus
	 * 
	 * @param field
	 * @param player
	 */
	public static void showFeld(Feld[] field,Vector<Spieler> player){
		int[] arrayLeft =  {35,34,33,32,31,30,29,28};
		int[] arrayRight = {10,11,12,13,14,15,16,17};
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		System.out.print("|");
		for(int i = 0;i<10;i++){
			int number=0;
			for(Spieler k:player)
				if (k.getSpielerPosition().getNummer() == i) {
					number = number+1;
				}
			System.out.print("  "+number+"  |");
		}
		System.out.println();
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		for(int i=0;i<arrayLeft.length;i++){
			int number=0;
			for(Spieler k:player){
				if (k.getSpielerPosition().getNummer() == arrayLeft[i]) {
					number = number+1;
				}
			}
			int number2=0;
			for(Spieler k:player)
				if (k.getSpielerPosition().getNummer() == arrayRight[i]) {
					number2 = number2+1;
				}
			System.out.println("|  "+number+"  |                                               |  "+number2+"  |");
			if(i!=arrayLeft.length-1)System.out.println("|-----|                                               |-----|");
		}
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		System.out.print("|");
		for(int i = 27;i>17;i--){
			int number=0;
			for(Spieler k:player)
			if (k.getSpielerPosition().getNummer() == i) {
				number = number+1;
				}
			System.out.print("  "+number+"  |");
		}
		System.out.println();
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
	}
	
	/**
	 * gibt die gewürfelte Zahl auf der Konsole aus
	 * 
	 * @param number
	 * @return
	 */
	public int wuerfelAnzeigen(int number){
		switch(number){
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
		return number;
	}
}
