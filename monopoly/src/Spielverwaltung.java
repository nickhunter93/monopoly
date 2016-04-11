
public class Spielverwaltung {
	private Spielfeld feld;
	public Spielverwaltung(){
		feld = new Spielfeld();
	}
	public void move(Spieler spieler,int zugweite){
		if(feld.getFieldSize()>spieler.getSpielerPosition()+zugweite){
			spieler.setSpielerPosition(spieler.getSpielerPosition()+zugweite);
		}else{
			int position = spieler.getSpielerPosition();
			for(int i = 0;i<zugweite;i++){
				if(position<feld.getFieldSize()-1){
					position++;
				}else{
					position = 0;
				}
			}
			spieler.setSpielerPosition(position);
		}
	}
	public FeldValue[] getFeld(){
		return feld.getFeld();
	}
	public boolean bauHaus(int position){
		return feld.bauHaus(position);	
	}
	public int getHaeuseranzahl(int position){
		return feld.getHaeuseranzahl(position);
	}
}
