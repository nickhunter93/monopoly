package monopoly.local.persistenz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;

public class PersistenzSpeichern {
	BufferedWriter speicher;
	public PersistenzSpeichern(){
	}

	public void saveFeld(Feld[] spielfeld){
		try {
			speicher = new BufferedWriter(new FileWriter("save.txt"));
			for(Feld feld:spielfeld){
				if(feld instanceof Strasse){
					Strasse strasse = (Strasse)feld;
					speicher.write(strasse.getName());
					speicher.newLine();
					speicher.write(strasse.getNummer());
					speicher.newLine();
					speicher.write(strasse.getKaufpreis());
					speicher.newLine();
					speicher.write(strasse.getMietpreis());
					speicher.newLine();
					speicher.write(strasse.getHaeuseranzahl());
					speicher.newLine();
					speicher.write(strasse.getHypothek() ? "w":"f");
					speicher.newLine();
					speicher.write(strasse.getHauspreis());
					speicher.newLine();
				}
			}
			speicher.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
