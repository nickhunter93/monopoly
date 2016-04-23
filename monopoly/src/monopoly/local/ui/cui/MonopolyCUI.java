package monopoly.local.ui.cui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import monopoly.local.domain.Spielerverwaltung;
import monopoly.local.domain.Spielverwaltung;
import monopoly.local.valueobjects.Spieler;

public class MonopolyCUI {
	
	/**
	 * Main Methode in der Klasse MonopolyCUI
	 * 
	 * startet eine Schleife die das Auswahlmenü auf der Konsole ausgibt und die Eingaben des 
	 * Spielers ließt 
	 * case 1: fügt wenn es möglich ist ein Spieler hinzu 
	 * case 2: löscht wenn es möglich ist einen Spieler
	 * case 3: startet ein neues Spiel
	 */
	public static void main(String[] args) {
		
		// MonopolyCUI-Objekt erzeugen
		// ... starten
		
		boolean loop = true;
		Spielverwaltung fielManagement = new Spielverwaltung();
		Spielerverwaltung management = new Spielerverwaltung();
		while(loop){
			String buffer;
			int choise = 0;
			BufferedReader intake = new BufferedReader(new InputStreamReader(System.in));
			try{
			System.out.println("Menü:");
			System.out.println("1:Beitreten.");
			System.out.println("2:Entfernen.");
			System.out.println("3:Spiel starten.");
			buffer = intake.readLine();
			choise = Integer.parseInt(buffer);
			}catch(IOException e ){
				e.printStackTrace();
				System.err.println("Menü Auswahl fehlerhaft.");
				choise = 0;
			}catch(NumberFormatException e){
					System.err.println("Menü Auswahl fehlerhaft.");
					choise = 0;
			}
			switch(choise){
			case 1 : 	if(true){
							String name;
							int playerNumber;
					
							try {
								System.out.println("Geben Sie den Namen an.");
								name = intake.readLine();
								playerNumber = management.getAllSpieler().size();
								Spieler player = new Spieler(name,playerNumber+1,fielManagement.getLos(),2000);
								if(management.beitreten(player)){
									System.out.println("Spieler " + (playerNumber+1) + " von 6 erfolgreich hinzugefügt.");
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
							int playerNumber;
		
							try {
								System.out.println("Geben Sie die Spieler Nummer an.");
								for(int i=0;i<management.getAllSpieler().size();i++){
									System.out.println(management.getSpieler(i).getSpielerNummer()+" :"+management.getSpieler(i).getSpielerName()+".");
								}
								str = intake.readLine();
								playerNumber = Integer.parseInt(str);
								str = management.getSpieler(playerNumber-1).getSpielerName();
								if(management.entfernen(playerNumber)){
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
			case 3 :	SpielStart game = new SpielStart(fielManagement,management);
						game.start();
			break;
			default:	System.out.println("Keine Gültige Auswahl.");
			}
		}
	}	
}

