import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Main {
	public static void main(String[] args) {

		//for(int i = 0; i < spielfeld.length; i++){
			//for(int j = 0; j < spielfeld[i].length; j++){
				//System.out.println("--------------------------------");
				//System.out.println(spielfeld[i][j].getInhalt());
				//System.out.println("--------------------------------");
			//}
		//}
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
								spielernummer = verwaltung.getSpieler().size();
								Spieler player = new Spieler(name,spielernummer+1,0);
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
								for(int i=0;i<verwaltung.getSpieler().size();i++){
									System.out.println(verwaltung.getSpieler().get(i).getSpielerNummer()+" :"+verwaltung.getSpieler().get(i).getSpielerName()+".");
								}
								str = eingabe.readLine();
								spielernummer = Integer.parseInt(str);
								verwaltung.entfernen(spielernummer);
								System.out.println("Spieler " + spielernummer + " wurde erfolgreich entfernt.");
								
							} catch (IOException e) {
								e.printStackTrace();
								System.err.println("Fehler beim entfernen eines Spielers.");
							}
						}
			break;
			case 3 :	schleife = false;
						showFeld(feldverwaltung.getFeld().getFeld(),verwaltung.getSpieler());
						feldverwaltung.move(verwaltung.reihenfolge(), 20);
						showFeld(feldverwaltung.getFeld().getFeld(),verwaltung.getSpieler());
						feldverwaltung.move(verwaltung.reihenfolge(), 20);
						showFeld(feldverwaltung.getFeld().getFeld(),verwaltung.getSpieler());
						feldverwaltung.move(verwaltung.reihenfolge(), 21);
			break;
			default:	System.out.println("Keine Gültige Auswahl.");
			}
		}
		showFeld(feldverwaltung.getFeld().getFeld(),verwaltung.getSpieler());
	}
	public static void showFeld(FeldValue[] feld,Vector<Spieler> spieler){
		int[] arrayLinks =  {35,34,33,32,31,30,29,28};
		int[] arrayRechts = {10,11,12,13,14,15,16,17};
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		System.out.print("|");
		for(int i = 0;i<10;i++){
			int anzahl=0;
			for(Spieler k:spieler)
				if (k.getSpielerPosition() == i) {
					anzahl = anzahl+1;
				}
			System.out.print("  "+anzahl+"  |");
		}
		System.out.println();
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		for(int i=0;i<arrayLinks.length;i++){
			int anzahl=0;
			for(Spieler k:spieler){
				if (k.getSpielerPosition() == arrayLinks[i]) {
					anzahl = anzahl+1;
				}
			}
			int anzahl2=0;
			for(Spieler k:spieler)
				if (k.getSpielerPosition() == arrayRechts[i]) {
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
			if (k.getSpielerPosition() == i) {
				anzahl = anzahl+1;
				}
			System.out.print("  "+anzahl+"  |");
		}
		System.out.println();
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");

	}

}
