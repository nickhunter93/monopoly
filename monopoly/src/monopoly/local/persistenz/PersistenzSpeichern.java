package monopoly.local.persistenz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;

public class PersistenzSpeichern {
	BufferedWriter speicher;
	public PersistenzSpeichern(){
	}

	public void saveFeld(Feld[] spielfeld){
		try {
			speicher = new BufferedWriter(new FileWriter("save.txt"));
			for(Feld feld:spielfeld){
				speicher.write(feld.getName());
				speicher.newLine();
			}
			speicher.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
