package monopoly.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import monopoly.local.domain.Monopoly;
import monopoly.local.domain.Spielerverwaltung;
import monopoly.local.domain.Spielverwaltung;
import monopoly.local.persistenz.PersistenzLaden;
import monopoly.local.persistenz.PersistenzSpeichern;
import monopoly.local.valueobjects.Spieler;

public class Menue {
	private Monopoly monopoly;
	
	/**
	 * Main Methode in der Klasse Menue
	 */
	public Menue(){
		monopoly = new Monopoly();
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
				System.out.println("4:Spielstand laden.");
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
					spielernummer = monopoly.getAllSpieler().size();
					Spieler player = new Spieler(name,spielernummer+1,monopoly.getLos(),2000);
					if(monopoly.beitreten(player)){
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
				
				if(monopoly.getAllSpieler().size() == 0){
					System.out.println("Es sind noch keine Spieler im Spiel.");
				}else{
				
				try {
					System.out.println("Geben Sie die Spieler Nummer an.");
					for(int i=0;i<monopoly.getAllSpieler().size();i++){
						Spieler spieler = this.monopoly.getSpieler(i);
						int spielerNummer = spieler.getSpielerNummer();
						String spielerName = spieler.getSpielerName();
						System.out.println(spielerNummer+" :"+spielerName+".");
					}
					str = eingabe.readLine();
					spielernummer = Integer.parseInt(str);
					str = monopoly.getSpieler(spielernummer-1).getSpielerName();
					
					if(monopoly.entfernen(spielernummer)){
						System.out.println("Spieler "+str+" wurde entfernt.");
					}
					
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Fehler beim entfernen eines Spielers.");
				} catch (NumberFormatException e) {
					System.out.println("Geben Sie eine SpielerNUMMER ein.");
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Kein Spieler mit der Nummer gefunden.");
				}
			}
			}
			break;
			case 3 :	int spielerAnzahl = monopoly.getAllSpieler().size();
			if(spielerAnzahl >= 2){
				SpielStart spiel = new SpielStart(monopoly);
				spiel.start(true);
			}else{
				System.out.println("Es gibt nicht gen�gend Spieler (min. 2).");
			}
			break;
			
			case 4 :	
				System.out.println("Wählen Sie einen Spielstand aus.");
				Vector<String> savefiles = monopoly.saveFilesLaden();
				for(int i = 0 ; i < savefiles.size() ; i++){
					System.out.println(i+1+savefiles.elementAt(i));
				}
				try {
					buffer = eingabe.readLine();
					int savewahl = Integer.parseInt(buffer);
					buffer = savefiles.get(savewahl-1);
					SpielStart spiel = monopoly.spielStandLaden(buffer);
					spiel.start(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			break;
			default:	System.out.println("Keine G�ltige Auswahl.");
			}
		}
	}
}
