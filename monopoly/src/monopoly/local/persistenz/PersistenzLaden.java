package monopoly.local.persistenz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import monopoly.local.domain.Monopoly;
import monopoly.local.domain.Spielverwaltung.Phase;
import monopoly.local.ui.cui.SpielStart;
import monopoly.local.valueobjects.Ereignisfeld;
import monopoly.local.valueobjects.Ereigniskarten;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Gemeinschaftsfeld;
import monopoly.local.valueobjects.Gemeinschaftskarten;
import monopoly.local.valueobjects.Jail;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;
import monopoly.local.valueobjects.ToJail;

public class PersistenzLaden {
	private Monopoly monopoly;
	private BufferedReader laden;
	private String filename;
	public PersistenzLaden(){
		
	}
	
	public Vector<String> loadSaveFiles(){
		Vector<String> savefiles = new Vector<String>();
		String save;
		try{
		laden = new BufferedReader(new FileReader("savefiles"));
		save = laden.readLine();
		while(save != null && !save.isEmpty() ){
			savefiles.addElement(save);
			save = laden.readLine();
		}
		return savefiles;
		}catch(IOException e) {
			return null;
		}
	}
	
	public Monopoly loadAll(String filename){
		try {
			monopoly = new Monopoly();
			this.filename = filename;
			Feld[] feld = monopoly.getSpielfeld();
			
			Vector<Spieler> spielerListe;
			spielerListe = this.loadSpieler(feld);
			monopoly.setAllSpieler(spielerListe);
			
			this.loadField(spielerListe, feld);
			
			Spieler activPlayer = spielerListe.get(loadActivePlayer()-1);
			monopoly.getTurn().setWerIstDran(activPlayer);
			monopoly.getTurn().setPhase(loadPhase());
			return monopoly;
		} catch (IOException e) {
			System.out.println("FEHLER BEIM LADEN");
			return null;
		}
		
	}
	
	private Phase loadPhase() throws IOException {
		laden = new BufferedReader(new FileReader(filename+"Turn"));
		laden.readLine();
		int fall = Integer.parseInt(laden.readLine());
		Phase phase;
		switch (fall){
		case 1 :
			phase = Phase.JailCheck;
			break;
		case 2 :
			phase = Phase.Dice;
			break;
		case 3 :
			phase = Phase.Passiv;
			break;
		case 4 :
			phase = Phase.End;
			break;
		default :
		phase = Phase.End;
		}
		laden.close();
		return phase;
	}

	private int loadActivePlayer() throws IOException {
		laden = new BufferedReader(new FileReader(filename+"Turn"));
		int spielernummer = Integer.parseInt(laden.readLine());
		
		laden.close();
		return spielernummer;
	}

	public Vector<Spieler> loadSpieler(Feld[] feld) throws IOException{
		
		laden = new BufferedReader(new FileReader(filename+"Spieler.txt"));
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
		laden.close();
		return spielerListe;
		
	}
	
	public void loadField(Vector<Spieler> spielerListe,Feld[] feld)throws IOException{
		laden = new BufferedReader(new FileReader(filename+"Feld.txt"));
		laden.readLine();
		for(int i = 0 ; i<feld.length ; i++){
			String str = laden.readLine();
			if(str.equals("LOS")){
				
				str =laden.readLine();
				str =laden.readLine();
				str =laden.readLine();
				str =laden.readLine();
				str =laden.readLine();
				
			}else if(str.equals("Gefängnis")){
				boolean schleife = true;
				feld[i] = new Jail("Gefängnis",i);
				while(schleife == true){
					str = laden.readLine();
					if (!str.isEmpty()){
						int spielernummer = Integer.parseInt(str);
						Jail jail = ((Jail)feld[i]);
						jail.addInsasse(monopoly.getSpieler(spielernummer-1));
					}else{
						schleife = false;
					}
				}
			}else if(str.equals("Gehe ins Gefängnis")){
				laden.readLine();
			}else if(str.equals("Ereignisfeld")){
				laden.readLine();
				laden.readLine();
				laden.readLine();
			}else if(str.equals("Gemeinschaftsfeld")){
				laden.readLine();
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
				laden.readLine();
			}
			laden.readLine();
		}
		laden.close();
	}
	
	public Feld[] loadDefaulField(Monopoly monopoly)throws IOException{
		laden = new BufferedReader(new FileReader("defaultFeld.txt"));
		Feld[] feld = new Feld[40];
		for(int i = 0 ; i<feld.length ; i++){
			String str = laden.readLine();
			if(str.equals("LOS")){
				
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
				
			}else if(str.equals("Gefängnis")){
				laden.readLine();
				feld[i] = new Jail(str,i);
				laden.readLine();
				laden.readLine();
			}else if(str.equals("Gehe ins Gefängnis")){
				laden.readLine();
				int jailNumber = Integer.parseInt(laden.readLine());
				feld[i] = new ToJail(str,i,(Jail)feld[jailNumber]);
				laden.readLine();
			}else if(str.equals("Ereignisfeld")){
				laden.readLine();
				feld[i] = new Ereignisfeld(str,i);
				Ereignisfeld ereignis = ((Ereignisfeld)feld[i]);
				ereignis.setDeck(new Ereigniskarten(monopoly));
				laden.readLine();
				
				
			}else if(str.equals("Gemeinschaftsfeld")){
				laden.readLine();
				feld[i] = new Gemeinschaftsfeld(str,i);
				Gemeinschaftsfeld gemeinschaft = ((Gemeinschaftsfeld)feld[i]);
				gemeinschaft.setDeck(new Gemeinschaftskarten(monopoly));
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
		
		laden = new BufferedReader(new FileReader("defaultNeighbors.txt"));
		String str = "";
		while(str != null){
			str = laden.readLine();
			int feldnummer = Integer.parseInt(str);
			char symbol = '0';
			Vector<Feld> neighbors = new Vector<Feld>();
			String neighbornumberString = "";
			do{
				do{
					neighbornumberString = neighbornumberString.concat(""+symbol);
					symbol = (char) laden.read();
				}while(symbol != ','&& symbol != ';');
				int neighbornumber = Integer.parseInt(neighbornumberString);
				if(feld[neighbornumber] instanceof Strasse){
					neighbors.addElement(feld[neighbornumber]);
				}
				neighbornumberString = "";
				if(symbol != ';'){
					symbol = '0';
				}
			}while(symbol != ';');
			if(feld[feldnummer] instanceof Strasse){
				Strasse strasse = (Strasse)feld[feldnummer];
				strasse.setNeightbors(neighbors);
			}
			laden.readLine();
			str = laden.readLine();
		}
		
		
		laden.close();
		return feld;
	}
}
