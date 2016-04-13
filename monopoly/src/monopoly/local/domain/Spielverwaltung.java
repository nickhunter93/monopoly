package monopoly.local.domain;
import monopoly.local.valueobjects.Feld;
import monopoly.local.valueobjects.Spieler;
import monopoly.local.valueobjects.Spielfeld;

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
					spieler.setSpielerBudget(spieler.getSpielerBudget()-feld.getFeld(0).getMietpreis());
					position = 0;
				}
			}
			Feld newPosition = feld.getFeld(position);
			spieler.setSpielerPosition(newPosition);
		}
	}
	public String getStrasseName(int position){
		return feld.getFeld(position).getName();
	}
	public Spieler getBesitzer(int position){
		return feld.getFeld(position).getBesitzer();
	}
	public int miete(int position){
		return feld.getFeld(position).getMietpreis();
	}
	public boolean kaufStrasse(Spieler spieler){
		if(feld.getFeld(spieler.getSpielerPosition().getNummer()).getBesitzer() == null && spieler.getSpielerBudget() - feld.getFeld(spieler.getSpielerPosition().getNummer()).getKaufpreis() >= 0){
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
