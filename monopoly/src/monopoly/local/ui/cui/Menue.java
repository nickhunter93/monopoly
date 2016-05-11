package monopoly.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import monopoly.local.domain.Spielerverwaltung;
import monopoly.local.domain.Spielverwaltung;
import monopoly.local.persistenz.PersistenzLaden;
import monopoly.local.persistenz.PersistenzSpeichern;
import monopoly.local.valueobjects.Spieler;

public class Menue {
	Spielerverwaltung spielerverwaltung;
	Spielverwaltung feldverwaltung;
	
	/**
	 * Main Methode in der Klasse Menue
	 */
	public Menue(){
		spielerverwaltung = new Spielerverwaltung();
		feldverwaltung = new Spielverwaltung();
		this.menueOefnen();
	}
	
	/**
	 * startet eine Schleife die das Auswahlmen� auf der Konsole ausgibt und die Eingaben des 
	 * Spielers lie�t 
	 * case 1: f�gt wenn es m�glich ist ein Spieler hinzu 
	 * case 2: l�scht wenn es m�glich ist einen Spieler
	 * case 3: startet ein neues Spiel
	 */
	public void menueOefnen(){
		boolean schleife = true;
		while(schleife){
			String buffer;
			int auswahl = 0;
			BufferedReader eingabe = new BufferedReader(new InputStreamReader(System.in));
			try{
				System.out.println("Men�:");
				System.out.println("1:Beitreten.");
				System.out.println("2:Entfernen.");
				System.out.println("3:Spiel starten.");
				System.out.println("4:Spielstand speichern.");
				System.out.println("5:Spielstand laden.");
				buffer = eingabe.readLine();
				auswahl = Integer.parseInt(buffer);
			}catch(IOException e ){
				e.printStackTrace();
				System.err.println("Men� Auswahl fehlerhaft.");
				auswahl = 0;
			}catch(NumberFormatException e){
				System.err.println("Men� Auswahl fehlerhaft.");
				auswahl = 0;
			}
			switch(auswahl){
			case 1 : 	if(true){
				String name;
				int spielernummer;

				try {
					System.out.println("Geben Sie den Namen an.");
					name = eingabe.readLine();
					spielernummer = spielerverwaltung.getAllSpieler().size();
					Spieler player = new Spieler(name,spielernummer+1,feldverwaltung.getLos(),2000);
					if(spielerverwaltung.beitreten(player)){
						System.out.println("Spieler " + (spielernummer+1) + " von 6 erfolgreich hinzugef�gt.");
					}else{
						System.out.println("Maximale Spieleranzahl erreicht.");
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("Fehler beim Hinzuf�gen eines neuen Spielers.");
				}
			}
			break;			
			case 2 :	if(true){
				String str;
				int spielernummer;

				try {
					System.out.println("Geben Sie die Spieler Nummer an.");
					for(int i=0;i<spielerverwaltung.getAllSpieler().size();i++){
						Spieler spieler = spielerverwaltung.getSpieler(i);
						int spielerNummer = spieler.getSpielerNummer();
						String spielerName = spieler.getSpielerName();
						System.out.println(spielerNummer+" :"+spielerName+".");
					}
					str = eingabe.readLine();
					spielernummer = Integer.parseInt(str);
					str = spielerverwaltung.getSpieler(spielernummer-1).getSpielerName();
					if(spielerverwaltung.entfernen(spielernummer)){
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
			case 3 :	int spielerAnzahl = spielerverwaltung.getAllSpieler().size();
			if(spielerAnzahl >= 2){
				SpielStart spiel = new SpielStart(feldverwaltung,spielerverwaltung);
				spiel.start();
			}else{
				System.out.println("Es gibt nicht gen�gend Spieler (min. 2).");
			}
			break;
			
			case 4 :	
				PersistenzSpeichern speichern = new PersistenzSpeichern();
				speichern.saveAll(spielerverwaltung.getAllSpieler(), feldverwaltung.getSpielfeld());
			break;
			
			case 5 :	
				PersistenzLaden laden = new PersistenzLaden();
				SpielStart spiel = laden.loadAll();
				spiel.start();
			break;
			default:	System.out.println("Keine G�ltige Auswahl.");
			}
		}
	}
}
