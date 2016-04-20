package monopoly.local.ui.cui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import monopoly.local.domain.Spielerverwaltung;
import monopoly.local.domain.Spielverwaltung;
import monopoly.local.valueobjects.Spieler;

public class MonopolyCUI {
	public static void main(String[] args) {
		
		// MonopolyCUI-Objekt erzeugen
		// ... starten
		
		boolean schleife = true;
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
			case 3 :	SpielStart spiel = new SpielStart(feldverwaltung,verwaltung);
						spiel.start();
			break;
			default:	System.out.println("Keine Gültige Auswahl.");
			}
		}
	}	
}

