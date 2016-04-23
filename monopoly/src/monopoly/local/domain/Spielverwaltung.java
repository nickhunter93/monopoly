package monopoly.local.domain;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Spielfeld;
import monopoly.local.valueobjects.Strasse;

public class Spielverwaltung {
	private Spielfeld feld;
	public Spielverwaltung(){
		feld = new Spielfeld();
	}
	
	public void move(Spieler spieler,int zugweite){
		int spielerPosition = spieler.getSpielerPosition().getNummer();
		if(feld.getFieldSize()>spielerPosition+zugweite){
			Feld newPosition = feld.getPosition(spieler, zugweite);
			spieler.setSpielerPosition(newPosition);
		}else{
			int position = spielerPosition;
			for(int i = 0;i<zugweite;i++){
				if(position<feld.getFieldSize()-1){
					position++;
				}else{
					if (feld.getLos() instanceof Strasse){
						spieler.setSpielerBudget(spieler.getSpielerBudget()-((Strasse)feld.getLos()).getMietpreis());
						position = -spielerPosition;
					}
				}
			}
			Feld newPosition = feld.getPosition(spieler, position);
			spieler.setSpielerPosition(newPosition);
		}
	}
	
	public String getStrasseName(Spieler spieler){
		Feld position = spieler.getSpielerPosition();
		String name = position.getName();
		return name;
	}
	
	public Spieler getBesitzer(Feld position){
		Spieler besitzer = null;
		if(position instanceof Strasse){
			besitzer = ((Strasse) position).getBesitzer();
		}
		return besitzer;
	}

	public String switchHypothek(int position){
		return feld.switchHypothek(position);
	}
	
	public int miete(Spieler spieler){
		Strasse strasse = null;
		Feld position = spieler.getSpielerPosition();
		if(position instanceof Strasse){
			strasse =((Strasse)position);
		}
		return strasse.getMietpreis();
	}
	
	public int preis(Spieler spieler){
		Strasse strasse = null;
		Feld position = spieler.getSpielerPosition();
		if(position instanceof Strasse){
			strasse =((Strasse)position);
		}
		return strasse.getKaufpreis();
	}
	
	public boolean kaufStrasse(Spieler spieler){
		Strasse strasse;
		Feld position = spieler.getSpielerPosition();
		if(position instanceof Strasse){
			strasse = (Strasse)position;
		
			Spieler besitzer = strasse.getBesitzer();
			int spielerNummer = besitzer.getSpielerNummer();
			int budget = spieler.getSpielerBudget();
			int subtrahend = strasse.getKaufpreis();
			int differenz = budget - subtrahend;
			if( spielerNummer == 99 && differenz >= 0){
				strasse.setBesitzer(spieler);
				spieler.setSpielerBudget(differenz);
				return true;
			}
		}
		
		
		return false;
	}
	
	public Feld getLos(){
		return feld.getLos();
	}
	
	public Feld[] getSpielfeld(){
		return feld.getSpielfeld();
	}
	
	public String getFeldName(int nr){
		return feld.getFeldName(nr);
	}
	
	public Feld getFeld(Spieler spieler){
		Feld position = spieler.getSpielerPosition();
		return feld.getFeld(position);
	}
	
	public boolean bauHaus(int position,Spieler spieler){
		return feld.bauHaus(position,spieler);	
	}
	
	public Strasse[] getYourStreets(Spieler spieler){
		return feld.getYourStreets(spieler);
	}
	
	public int getHaeuseranzahl(int position){
		return feld.getHaeuseranzahl(position);
	}
	
}
