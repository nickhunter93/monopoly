package monopoly.local.persistenz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Jail;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;

public class PersistenzSpeichern {
	BufferedWriter speicher;

	public PersistenzSpeichern(){
	}

	public boolean saveAll(Vector<Spieler> spielerListe, Feld[] spielfeld){
		try{
			saveSpieler(spielerListe);
			saveFeld(spielfeld);
			return true;
		}
		catch(IOException e){
			return false;
		}
	}

	public void saveSpieler(Vector<Spieler> spielerListe) throws IOException{
		speicher = new BufferedWriter(new FileWriter("saveSpieler.txt"));
		for(Spieler spieler:spielerListe){
			speicher.write(spieler.getSpielerName());
			speicher.newLine();
			speicher.write(spieler.getSpielerBudget()+"");
			speicher.newLine();
			speicher.write(spieler.getSpielerNummer()+"");
			speicher.newLine();
			speicher.write(spieler.getSpielerPosition().getNummer()+"");
			speicher.newLine();
			speicher.newLine();
		}
		speicher.close();

	}

	public void saveFeld(Feld[] spielfeld) throws IOException{
		speicher = new BufferedWriter(new FileWriter("saveFeld.txt"));
		for(Feld feld:spielfeld){
			if(feld instanceof Strasse){
				Strasse strasse = (Strasse)feld;
				speicher.write(strasse.getName());
				speicher.newLine();
				speicher.write(strasse.getNummer()+"");
				speicher.newLine();
				speicher.write(strasse.getKaufpreis()+"");
				speicher.newLine();
				speicher.write(strasse.getMietpreis()+"");
				speicher.newLine();
				speicher.write(strasse.getHaeuseranzahl()+"");
				speicher.newLine();
				speicher.write(strasse.getHypothek() ? "w":"f");
				speicher.newLine();
				speicher.write(strasse.getHauspreis()+"");
				speicher.newLine();
				speicher.write(strasse.getBesitzer().getSpielerNummer()+"");
				speicher.newLine();
				speicher.newLine();
			}
			if(feld instanceof Jail){
				Jail jail = (Jail)feld;
				speicher.write(jail.getName());
				speicher.newLine();
				speicher.write(jail.getNummer()+"");
				speicher.newLine();
				for(Spieler spieler:jail.getInsassen()){
					speicher.write(spieler.getSpielerNummer());
					speicher.newLine();
				}
				
				speicher.newLine();
				speicher.newLine();
			}
		}
		speicher.close();
	}
}
