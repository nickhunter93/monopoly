package monopoly.local.persistenz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		return null;
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
				
			}else if(str.equals("Gefängnis")){
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
		return feld;
	}
}
