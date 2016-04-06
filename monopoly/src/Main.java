
public class Main {
	public static void main(String[] args){

		Feld[][] spielfeld = new Feld[7][1];
		spielfeld[0][0] = new Feld<Spieler>(new Spieler("Fabian", 1, 10));
		spielfeld[1][0] = new Feld<Strasse>(new Strasse("Teststrasse", 2, true, 250000, 1300, 0));
		spielfeld[2][0] = new Feld<Strasse>(new Strasse("Teststrasse", 3, true, 250000, 1300, 0));
		spielfeld[3][0] = new Feld<Strasse>(new Strasse("Teststrasse", 4, true, 250000, 1300, 0));
		spielfeld[4][0] = new Feld<Strasse>(new Strasse("Teststrasse", 5, true, 250000, 1300, 0));
		spielfeld[5][0] = new Feld<Aktion>(new Aktion("Gefängnis", 6, true, "Gehe ins Gefängnis. Du musst 3 Runden aussetzen."));
		spielfeld[6][0] = new Feld<Strasse>(new Strasse("Teststrasse", 7, true, 250000, 1300, 0));
		
		for(int i = 0; i < spielfeld.length; i++){
			for(int j = 0; j < spielfeld[i].length; j++){
				System.out.println("--------------------------------");
				System.out.println(spielfeld[i][j].getInhalt());
				System.out.println("--------------------------------");
			}
		}
	}

}
