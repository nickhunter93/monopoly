package monopoly.local.persistenz;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import monopoly.local.domain.Spielverwaltung.Turn;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Jail;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Strasse;
import monopoly.local.valueobjects.ToJail;

public class PersistenzSpeichern {
	BufferedWriter speicher;

	public PersistenzSpeichern(){
	}

	public boolean saveAll(Vector<Spieler> spielerListe, Feld[] spielfeld,Turn turn){
		try{
			saveSpieler(spielerListe);
			saveFeld(spielfeld);
			saveTurn(turn);
			return true;
		}
		catch(IOException e){
			return false;
		}
	}

	private void saveTurn(Turn turn)throws IOException {
		speicher = new BufferedWriter(new FileWriter("saveTurn"));
		turn.getWerIstDran();
		speicher.write(""+turn.getWerIstDran().getSpielerNummer());
		speicher.newLine();
		switch(turn.getPhase()){
		case JailCheck :
			speicher.write("1");
			break;
		case Dice :
			speicher.write("2");
			break;
		case Passiv :
			speicher.write("3");
			break;
		case End :
			speicher.write("4");
			break;
		default : {}
		break;
		}
		speicher.close();
	}

	public void saveSpieler(Vector<Spieler> spielerListe) throws IOException{
		speicher = new BufferedWriter(new FileWriter("saveSpieler.txt"));
		int anzahl = spielerListe.size();
		speicher.write(anzahl+"");
		speicher.newLine();
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

	public void saveDefaultFeld(Feld[] spielfeld) throws IOException{
		speicher = new BufferedWriter(new FileWriter("saveFeld.txt"));
		speicher.newLine();
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
			if(feld instanceof ToJail){
				ToJail toJail = (ToJail)feld;
				speicher.write(toJail.getName());
				speicher.newLine();
			}	
				speicher.newLine();
				speicher.newLine();
			}
		}
		speicher.close();
	}
	
	public void saveFeld(Feld[] spielfeld) throws IOException{
		speicher = new BufferedWriter(new FileWriter("saveFeld.txt"));
		speicher.newLine();
		for(Feld feld:spielfeld){
			if(feld instanceof Strasse){
				Strasse strasse = (Strasse)feld;
				speicher.write(strasse.getName());
				speicher.newLine();
				speicher.write(strasse.getHaeuseranzahl()+"");
				speicher.newLine();
				speicher.write(strasse.getHypothek() ? "w":"f");
				speicher.newLine();
				speicher.write(strasse.getBesitzer().getSpielerNummer()+"");
				speicher.newLine();
				speicher.newLine();
			}
			if(feld instanceof Jail){
				Jail jail = (Jail)feld;
				speicher.write(jail.getName());
				speicher.newLine();
				for(Spieler spieler:jail.getInsassen()){
					speicher.write(""+spieler.getSpielerNummer());
					speicher.newLine();
				}
			}
			if(feld instanceof ToJail){
				ToJail toJail = (ToJail)feld;
				speicher.write(toJail.getName());
				speicher.newLine();
			}	
			speicher.newLine();
			speicher.newLine();
		}
		speicher.close();
	}
}
