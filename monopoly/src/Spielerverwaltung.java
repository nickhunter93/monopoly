import java.util.Vector;

public class Spielerverwaltung {
	private int reihenfolge;
	
	private Vector<Spieler> spielerListe = new Vector<Spieler>();
	public Spielerverwaltung(){
		reihenfolge = -1;
	}
	public Vector<Spieler> getSpieler(){
		return spielerListe;
	}
	
	public boolean beitreten(Spieler spieler){
		if(!spielerListe.isEmpty()){
			if(spielerListe.lastElement().getSpielerNummer()<6){
				this.spielerListe.add(spieler);
				return true;
			}else{
				return false;
			}
		}else{
			this.spielerListe.add(spieler);
			return true;
		}
	}
	
	public void entfernen(int spielerNummer){
		for(int i = 0;i < spielerListe.size();i++){
			if(spielerListe.get(i).getSpielerNummer() == spielerNummer){
				spielerListe.remove(i);
				for(int k = 0;k<spielerListe.size();k++){
					spielerListe.get(k).setSpielerNummer(k+1);
				}
			}
		}
	}

	public Spieler reihenfolge(){
		if(reihenfolge < spielerListe.size()-1){
			reihenfolge++;
		}
		else {
			reihenfolge = -1;
			reihenfolge++;
		}
		return spielerListe.get(reihenfolge);
	}

	
	public int wuerfeln(){
		int zahl;
		zahl = (int)(Math.random() * 6) + 1;
		switch(zahl){
			case 1: 
				System.out.println("-------");
				System.out.println("|     |");
				System.out.println("|  o  |");
				System.out.println("|     |");
				System.out.println("-------");
			break;

			case 2: 
				System.out.println("-------");
				System.out.println("|o    |");
				System.out.println("|     |");
				System.out.println("|    o|");
				System.out.println("-------");
			break;

			case 3: 
				System.out.println("-------");
				System.out.println("| o o |");
				System.out.println("|     |");
				System.out.println("|  o  |");
				System.out.println("-------");
			break;

			case 4:
				System.out.println("-------");
				System.out.println("| o o |");
				System.out.println("|     |");
				System.out.println("| o o |");
				System.out.println("-------");
			break;

			case 5: 
				System.out.println("-------");
				System.out.println("| o o |");
				System.out.println("|  o  |");
				System.out.println("| o o |");
				System.out.println("-------");
			break;

			case 6: 
				System.out.println("-------");
				System.out.println("| o o |");
				System.out.println("| o o |");
				System.out.println("| o o |");
				System.out.println("-------");
			break;
		}
		return zahl;
	}
}
