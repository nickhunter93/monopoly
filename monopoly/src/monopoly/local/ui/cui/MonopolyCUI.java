package monopoly.local.ui.cui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import monopoly.local.domain.Spielerverwaltung;
import monopoly.local.domain.Spielverwaltung;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;

public class MonopolyCUI {
	public static void main(String[] args) {
		
		// MonopolyCUI-Objekt erzeugen
		// ... starten
		
		boolean schleife = true;
		boolean roundLoop = true;
		Spieler spieler;
		Spielverwaltung feldverwaltung = new Spielverwaltung();
		Spielerverwaltung verwaltung = new Spielerverwaltung();
		while(schleife){
			String buffer;
			int auswahl = 0;
			BufferedReader eingabe = new BufferedReader(new InputStreamReader(System.in));
			try{
			System.out.println("Menü:");
			System.out.println("1:Beitreten.");
			System.out.println("2:Entfernen.");
			System.out.println("3:Spiel starten.");
			buffer = eingabe.readLine();
			auswahl = Integer.parseInt(buffer);
			}catch(IOException e ){
				e.printStackTrace();
				System.err.println("Menü Auswahl fehlerhaft.");
				auswahl = 0;
			}catch(NumberFormatException e){
					System.err.println("Menü Auswahl fehlerhaft.");
					auswahl = 0;
			}
			switch(auswahl){
			case 1 : 	if(true){
							String name;
							int spielernummer;
					
							try {
								System.out.println("Geben Sie den Namen an.");
								name = eingabe.readLine();
								spielernummer = verwaltung.getAllSpieler().size();
								Spieler player = new Spieler(name,spielernummer+1,feldverwaltung.getLos(),2000);
								if(verwaltung.beitreten(player)){
									System.out.println("Spieler " + (spielernummer+1) + " von 6 erfolgreich hinzugefügt.");
								}else{
									System.out.println("Maximale Spieleranzahl erreicht.");
								}
							} catch (IOException e) {
								e.printStackTrace();
								System.err.println("Fehler beim Hinzufügen eines neuen Spielers.");
							}
						}
			break;			
			case 2 :	if(true){
							String str;
							int spielernummer;
		
							try {
								System.out.println("Geben Sie die Spieler Nummer an.");
								for(int i=0;i<verwaltung.getAllSpieler().size();i++){
									System.out.println(verwaltung.getSpieler(i).getSpielerNummer()+" :"+verwaltung.getSpieler(i).getSpielerName()+".");
								}
								str = eingabe.readLine();
								spielernummer = Integer.parseInt(str);
								str = verwaltung.getSpieler(spielernummer-1).getSpielerName();
								if(verwaltung.entfernen(spielernummer)){
									System.out.println("Spieler "+str+" wurde entfernt.");
								}else{
									System.out.println("Kein Spieler mit der Nummer gefunden.");
								}
							} catch (IOException e) {
								e.printStackTrace();
								System.err.println("Fehler beim entfernen eines Spielers.");
							}
						}
			break;
			case 3 :	while(schleife){
							spieler = verwaltung.reihenfolge();
							roundLoop = true;
							do{
								try {
									System.out.println("Spieler "+spieler.getSpielerNummer()+" "+spieler.getSpielerName()+" ist dran.");
									System.out.println("Was wollen Sie tun?");
									System.out.println("1:Würfeln.");
									System.out.println("2:Haus bauen.");
									buffer = eingabe.readLine();
									auswahl = Integer.parseInt(buffer);
									
								} catch (IOException e) {
									e.printStackTrace();
									auswahl = 0;
									System.out.println("Auswahl fehlerhaft.");
								} catch (NumberFormatException e){
									e.printStackTrace();
									auswahl = 0;
									System.out.println("Auswahl fehlerhaft.");
								}
								switch(auswahl){
								case 1:		int anzahl = verwaltung.wuerfeln();
											wuerfelAnzeigen(anzahl);
											feldverwaltung.move(spieler, anzahl);
											showFeld(feldverwaltung.getSpielfeld(),verwaltung.getAllSpieler());
											System.out.println("Sie befinden sich auf der Straße : "+feldverwaltung.getStrasseName(spieler));
											//Straße kaufen / miete zahlen hier einfügen.
											if (feldverwaltung.getBesitzer(spieler.getSpielerPosition()).getSpielerNummer() ==  99){
												boolean loop = true;
												do{
													System.out.println("Wollen Sie die Strasse kaufen?");
													System.out.println("'y' für Ja/ 'n' für Nein.");
													char check = 'k';
													try {
														buffer = eingabe.readLine();
														if(buffer.length() != 0){
															check = buffer.charAt(0);
														}else {
															check = 'k';
														}
														 
													} catch (IOException e) {
														e.printStackTrace();
													}
													if(check == 'y' || check == 'Y'){
														System.out.println(feldverwaltung.kaufStrasse(spieler) ? "Kauf erfolgreich" : "Kauf fehlgeschlagen");
														loop = false;
													}else if(check == 'n' || check == 'N'){
														loop = false;
													} else {
														loop = true;
														System.out.println("Eingabe Fehlerhaft.");
													}
												}while(loop == true);
												
											}else{
												spieler.setSpielerBudget(spieler.getSpielerBudget() - feldverwaltung.miete(spieler));
												verwaltung.mieteZahlen(feldverwaltung.miete(spieler), feldverwaltung.getBesitzer(spieler.getSpielerPosition()),spieler);
												System.out.println("Ihr Budget beträgt jetzt = "+spieler.getSpielerBudget());
											}
											roundLoop=false;
								break;
								case 2:		//Haus bauen hier einfügen.
								break;
								default: 	System.out.println("Keine Gültige Auswahl.");
											roundLoop = true;
								}
							}while(roundLoop);
							if(!verwaltung.checkPleite().isEmpty()){
								for(Spieler player:verwaltung.checkPleite()){
									verwaltung.entfernen(player.getSpielerNummer());
								}
								schleife = false;
							}else{}
							if(verwaltung.getAllSpieler().size() == 1){
								for(Spieler player:verwaltung.getAllSpieler()){
									System.out.println("Der Gewinner ist : "+player.getSpielerName());
								}
							}
						}
			break;
			default:	System.out.println("Keine Gültige Auswahl.");
			}
		}
		showFeld(feldverwaltung.getSpielfeld(),verwaltung.getAllSpieler());
	}
	public static void showFeld(Feld[] feld,Vector<Spieler> spieler){
		int[] arrayLinks =  {35,34,33,32,31,30,29,28};
		int[] arrayRechts = {10,11,12,13,14,15,16,17};
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		System.out.print("|");
		for(int i = 0;i<10;i++){
			int anzahl=0;
			for(Spieler k:spieler)
				if (k.getSpielerPosition().getNummer() == i) {
					anzahl = anzahl+1;
				}
			System.out.print("  "+anzahl+"  |");
		}
		System.out.println();
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		for(int i=0;i<arrayLinks.length;i++){
			int anzahl=0;
			for(Spieler k:spieler){
				if (k.getSpielerPosition().getNummer() == arrayLinks[i]) {
					anzahl = anzahl+1;
				}
			}
			int anzahl2=0;
			for(Spieler k:spieler)
				if (k.getSpielerPosition().getNummer() == arrayRechts[i]) {
					anzahl2 = anzahl2+1;
				}
			System.out.println("|  "+anzahl+"  |                                               |  "+anzahl2+"  |");
			if(i!=arrayLinks.length-1)System.out.println("|-----|                                               |-----|");
		}
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		System.out.print("|");
		for(int i = 27;i>17;i--){
			int anzahl=0;
			for(Spieler k:spieler)
			if (k.getSpielerPosition().getNummer() == i) {
				anzahl = anzahl+1;
				}
			System.out.print("  "+anzahl+"  |");
		}
		System.out.println();
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
	}
	public static int wuerfelAnzeigen(int zahl){
		switch(zahl){
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

