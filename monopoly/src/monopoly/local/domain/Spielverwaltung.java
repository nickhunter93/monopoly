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
		if(feld.getFieldSize()>spieler.getSpielerPosition().getNummer()+zugweite){
			Feld newPosition = feld.getFeld(spieler.getSpielerPosition().getNummer());
			spieler.setSpielerPosition(newPosition);
		}else{
			int position = spieler.getSpielerPosition().getNummer();
			for(int i = 0;i<zugweite;i++){
				if(position<feld.getFieldSize()-1){
					position++;
				}else{
					if (feld.getFeld(0).getClass().isInstance(Strasse.class)){
						spieler.setSpielerBudget(spieler.getSpielerBudget()-((Strasse)feld.getFeld(0)).getMietpreis());
						position = 0;
					}
				}
			}
			Feld newPosition = feld.getFeld(position);
			spieler.setSpielerPosition(newPosition);
		}
	}
	public String getStrasseName(int position){
		return feld.getFeld(position).getName();
	}
	public Spieler getBesitzer(Feld position){
		Spieler besitzer = null;
		if(feld.getFeld(position).getClass().isInstance(Strasse.class)){
			besitzer = ((Strasse) position).getBesitzer();
		}
		return besitzer;
	}
	public int miete(int position){
		Strasse strasse = null;
		if(feld.getClass().isInstance(Strasse.class)){
			strasse =((Strasse)feld.getFeld(position));
		}
		return strasse.getMietpreis();
	}
	public boolean kaufStrasse(Spieler spieler){
		Strasse strasse;
		if(feld.getClass().isInstance(Strasse.class)){
			strasse = (Strasse)spieler.getSpielerPosition();
		}
		if( strasse.getBesitzer() == null && spieler.getSpielerBudget() - feld.getFeld(spieler.getSpielerPosition().getNummer()).getKaufpreis() >= 0){
			feld.getFeld(spieler.getSpielerPosition().getNummer()).setBesitzer(spieler);
			spieler.setSpielerBudget(spieler.getSpielerBudget()-feld.getFeld(spieler.getSpielerPosition().getNummer()).getKaufpreis());
			return true;
		}
		
		return false;
	}
	public Feld getFeld(int nr){
		return feld.getFeld(nr);
	}
	public boolean bauHaus(int position,Spieler spieler){
		return feld.bauHaus(position,spieler);	
	}
	public int getHaeuseranzahl(int position){
		return feld.getHaeuseranzahl(position);
	}
}
