package monopoly.local.persistenz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import monopoly.local.domain.Spielerverwaltung;
import monopoly.local.domain.Spielverwaltung;
import monopoly.local.ui.cui.SpielStart;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Jail;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;

public class PersistenzLaden {
	BufferedReader laden;
	public PersistenzLaden(){
		
	}
	
	public SpielStart loadAll(){
		try {
			Spielerverwaltung verwaltung = new Spielerverwaltung();
			Spielverwaltung feldverwaltung = new Spielverwaltung(verwaltung);
			
			Feld[] feld = feldverwaltung.getSpielfeld();
			
			Vector<Spieler> spielerListe;
			spielerListe = this.loadSpieler(feld);
			
			this.loadField(spielerListe, feld);
			
			verwaltung.setAllSpieler(spielerListe);
			
			SpielStart start = new SpielStart(feldverwaltung,verwaltung);
			return start;
		} catch (IOException e) {
			System.out.println("FEHLER BEIM LADEN");
			return null;
		}
		
	}
	
	public Vector<Spieler> loadSpieler(Feld[] feld) throws IOException{
		
		laden = new BufferedReader(new FileReader("saveSpieler.txt"));
		int spieleranzahl = Integer.parseInt(laden.readLine());
		Vector<Spieler> spielerListe = new Vector<Spieler>();
		
		for(int i = 0 ; i < spieleranzahl ; i++){
			String str = laden.readLine();
			int budget = Integer.parseInt(laden.readLine());
			int nummer = Integer.parseInt(laden.readLine());
			int position = Integer.parseInt(laden.readLine());
			Spieler spieler = new Spieler(str,nummer,feld[position],budget);
			laden.readLine();
			spielerListe.add(spieler);
		}
		return spielerListe;
		
	}
	
	public void loadField(Vector<Spieler> spielerListe,Feld[] feld)throws IOException{
		laden = new BufferedReader(new FileReader("saveFeld.txt"));
		laden.readLine();
		for(int i = 0 ; i<36 ; i++){
			String str = laden.readLine();
			if(str.equals("Los")){
				
				laden.readLine();
				laden.readLine();
				laden.readLine();
				
				laden.readLine();
				
			}else if(str.equals("Gef�ngnis")){
				feld[i] = new Jail("Gef�ngnis",i);
				laden.readLine();
				laden.readLine();
			}else{
				int hausanzahl = Integer.parseInt(laden.readLine());
				boolean hypothek = laden.readLine().equals("w") ? true : false;
				int besitzer = Integer.parseInt(laden.readLine());
				((Strasse)feld[i]).setHaeuseranzahl(hausanzahl);
				((Strasse)feld[i]).setHypothek(hypothek);
				for(Spieler spieler:spielerListe){
					if (spieler.getSpielerNummer() == besitzer){
						((Strasse)feld[i]).setBesitzer(spieler);
					}
				}
				laden.readLine();
				
			}
		}
		laden.close();
	}
	
	public Feld[] loadDefaulField()throws IOException{
		laden = new BufferedReader(new FileReader("defaultFeld.txt"));
		Feld[] feld = new Feld[36];
		for(int i = 0 ; i<36 ; i++){
			String str = laden.readLine();
			if(str.equals("Los")){
				
				laden.readLine();
				int kaufpreis = Integer.parseInt(laden.readLine());
				int mietpreis = Integer.parseInt(laden.readLine());
				int hausanzahl = Integer.parseInt(laden.readLine());
				boolean hypothek = laden.readLine().equals("w") ? true : false;
				int hauspreis = Integer.parseInt(laden.readLine());
				Spieler spieler = new Spieler("Bank", 98, null, -1);
				
				feld[i] = new Strasse(str,kaufpreis,mietpreis,hypothek,i);
				((Strasse)feld[i]).setHaeuseranzahl(hausanzahl);
				((Strasse)feld[i]).setHauspreis(hauspreis);
				((Strasse)feld[i]).setBesitzer(spieler);
				laden.readLine();
				laden.readLine();
				
			}else if(str.equals("Gef�ngnis")){
				laden.readLine();
				feld[i] = new Jail(str,i);
				laden.readLine();
				laden.readLine();
			}else{
				
				laden.readLine();
				int kaufpreis = Integer.parseInt(laden.readLine());
				int mietpreis = Integer.parseInt(laden.readLine());
				int hausanzahl = Integer.parseInt(laden.readLine());
				boolean hypothek = laden.readLine().equals("w") ? true : false;
				int hauspreis = Integer.parseInt(laden.readLine());
				Spieler spieler = new Spieler("Bank", 99, null, -1);
				
				feld[i] = new Strasse(str,kaufpreis,mietpreis,hypothek,i);
				((Strasse)feld[i]).setHaeuseranzahl(hausanzahl);
				((Strasse)feld[i]).setHauspreis(hauspreis);
				((Strasse)feld[i]).setBesitzer(spieler);
				laden.readLine();
				laden.readLine();
			}
		}
		laden.close();
		return feld;
	}
}
