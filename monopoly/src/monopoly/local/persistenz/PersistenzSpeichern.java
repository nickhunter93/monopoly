package monopoly.local.persistenz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenzSpeichern {
	BufferedWriter speicher;
	public PersistenzSpeichern(){
		try {
			speicher = new BufferedWriter(new FileWriter("save.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
