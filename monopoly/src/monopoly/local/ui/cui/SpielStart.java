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
	private boolean schleife = true;
	private boolean roundLoop = true;
	private Spieler spieler;
	private String buffer;
	private int auswahl;
	private Spielverwaltung feldverwaltung;
	private Spielerverwaltung verwaltung;
	public SpielStart(Spielverwaltung feldverwaltung , Spielerverwaltung verwaltung){
		this.feldverwaltung = feldverwaltung;
		this.verwaltung = verwaltung;
	}
	
	public void start(){
			BufferedReader eingabe = new BufferedReader(new InputStreamReader(System.in));
			while(schleife){
				spieler = verwaltung.reihenfolge();
				roundLoop = true;
				do{
					try {
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println("Spieler "+spieler.getSpielerNummer()+" "+spieler.getSpielerName()+" ist dran.");
						System.out.println("Ihr Budget betr�gt : "+spieler.getSpielerBudget());
						System.out.println("Was wollen Sie tun?");
						System.out.println("1:W�rfeln.");
						System.out.println("2:Haus bauen.");
						System.out.println("3:Hypothek aufnehmen.");
						buffer = eingabe.readLine();
						auswahl = Integer.parseInt(buffer);
						
					} catch (IOException e) {
						auswahl = 0;
						System.out.println("Auswahl fehlerhaft.");
					} catch (NumberFormatException e){
						auswahl = 0;
						System.out.println("Auswahl fehlerhaft.");
					}
					Strasse[] yourStreets = feldverwaltung.getYourStreets(spieler);
					switch(auswahl){
					case 1:		int anzahl = verwaltung.wuerfeln();
								wuerfelAnzeigen(anzahl);
								feldverwaltung.move(spieler, anzahl);
								showFeld(feldverwaltung.getSpielfeld(),verwaltung.getAllSpieler());
								System.out.println("Sie befinden sich auf der Stra�e : "+feldverwaltung.getStrasseName(spieler));
								System.out.print("Mietpreis : "+feldverwaltung.miete(spieler));
								System.out.print(" / Kaufpreis : "+feldverwaltung.preis(spieler));
								System.out.print(" / Aktuelles Budget : "+spieler.getSpielerBudget());
								System.out.println("");
								//Stra�e kaufen / miete zahlen hier einf�gen.
								if (feldverwaltung.getBesitzer(spieler.getSpielerPosition()).getSpielerNummer() ==  99){
									boolean loop = true;
									do{
										System.out.println("Wollen Sie die Strasse kaufen?");
										System.out.println("'y' f�r Ja/ 'n' f�r Nein.");
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
											System.out.println("Kosten: -" + feldverwaltung.preis(spieler));
											System.out.println(spieler.getSpielerName()+" ihr Budget betr�gt : "+spieler.getSpielerBudget());
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
									System.out.println("Sie mussten " + feldverwaltung.miete(spieler) + " Miete an " + feldverwaltung.getBesitzer(spieler.getSpielerPosition()).getSpielerName() + " zahlen.");
									System.out.println("Ihr Budget betr�gt jetzt = "+spieler.getSpielerBudget());
								}
								roundLoop=false;
					break;
					case 2:		
								if(yourStreets != null){
									for(Strasse strasse : yourStreets){
										int nr = strasse.getNummer();
										String strassenName = feldverwaltung.getFeldName(nr);
										int hausAnzahl = feldverwaltung.getHaeuseranzahl(nr);
										
										System.out.print(nr+" : "+strassenName+" hat "+hausAnzahl);
										
										if(hausAnzahl == 1){
											System.out.print(" Haus.");
										}else if(hausAnzahl == 0 || (hausAnzahl > 1 && hausAnzahl < 4)){
											System.out.print(" H�user.");
										}else if(hausAnzahl == 4){
											System.out.print(" Häuser. Auf dieser Straße kann jetzt ein Hotel gebaut werden.");
										}
										System.out.println(" ");
									}
									try{
										System.out.println(" ");
										System.out.println("Geben Sie die Stra�en-Nummer, ein auf der Sie ein Haus bauen m�chten.");
										buffer = eingabe.readLine();
										auswahl = Integer.parseInt(buffer);
										boolean pruefen = false;
										for(Strasse strasse : yourStreets){
											int nr = strasse.getNummer();
											if(auswahl == nr){
												pruefen = true;
											}
										}
										
										if(pruefen && (feldverwaltung.getHaeuseranzahl(spieler.getSpielerPosition().getNummer()) < 5)){
											if(feldverwaltung.bauHaus(auswahl, spieler)){
												System.out.println("Das Haus wurde erfolgreich gebaut.");
											}
										}
										
										else if(pruefen == false){
												System.out.println("Die Strasse existiert nicht oder Sie sind nicht ihr Besitzer.");
										} 
										
										else if(pruefen && (feldverwaltung.getHaeuseranzahl(spieler.getSpielerPosition().getNummer()) > 4)){
												System.out.println("Maximale Anzahl an Häusern für diese Straße erreicht.");
										}
										
									} catch (IOException e) {
										e.printStackTrace();
										auswahl = 0;
										System.out.println("Auswahl fehlerhaft.");
									} catch (NumberFormatException e){
										e.printStackTrace();
										auswahl = 0;
										System.out.println("Auswahl fehlerhaft.");
									}
								}else{
									System.out.println("Sie besitzen keine Stra�en.");
								}
							
								roundLoop = true;
					break;
					case 3: 	if(yourStreets != null){
									//for(Strasse strasse : yourStreets){
										//int nr = strasse.getNummer();
										//String strassenName = feldverwaltung.getFeldName(nr);
										System.out.println("Liste der Straßen, die sich in ihrem Besitz befinden:");
										for(Strasse s : yourStreets){
											System.out.print("Name: " + s.getName() + " | Hypothek: " + (s.getHypothek()? " Hypothek zurückzahlen" : " Keine Hypothek") + " | Nr: " + s.getNummer());
											System.out.println(" ");
										}
										//System.out.println(nr+" : "+strassenName+" hat Hypothek aufgenommen : "+strasse.getHypothek());
									//}
									try{
										System.out.println("Geben Sie die Stra�en-Nummer ein, auf der Sie die Hypothek �ndern wollen.");
										buffer = eingabe.readLine();
										auswahl = Integer.parseInt(buffer);
										boolean pruefen = false;
										for(Strasse strasse : yourStreets){
											int nr = strasse.getNummer();
											if(auswahl == nr){
												pruefen = true;
											}
										}
										if(pruefen){
											String str = feldverwaltung.switchHypothek(auswahl);
											System.out.println(str);
										}else{
											System.out.println("Die Strasse existiert nicht oder Sie sind nicht ihr Besitzer.");
										}
										
									} catch (IOException e) {
										e.printStackTrace();
										auswahl = 0;
										System.out.println("Auswahl fehlerhaft.");
									} catch (NumberFormatException e){
										e.printStackTrace();
										auswahl = 0;
										System.out.println("Auswahl fehlerhaft.");
									}
								}else{
									System.out.println("Sie besitzen keine Stra�en.");
								}
								roundLoop = true;
					break;
					default: 	System.out.println("Keine G�ltige Auswahl.");
								roundLoop = true;
					}
				}while(roundLoop);
				if(!verwaltung.checkPleite().isEmpty()){
					for(Spieler player:verwaltung.checkPleite()){
						Strasse[] yourStreets = feldverwaltung.getYourStreets(player);
						for(Strasse strasse : yourStreets){
							strasse.setBesitzer(new Spieler("Bank", 99, null, -1));
						}
						verwaltung.entfernen(player.getSpielerNummer());
					}
				}else{}
				if(verwaltung.getAllSpieler().size() == 1){
					for(Spieler player:verwaltung.getAllSpieler()){
						System.out.println("Der Gewinner ist : "+player.getSpielerName());
						schleife = false;
					}
				}
			}
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
	
	public int wuerfelAnzeigen(int zahl){
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
