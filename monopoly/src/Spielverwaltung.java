
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
				if(position<feld.getFieldSize()){
					position++;
				}else{
					position = 0;
				}
			}
			spieler.setSpielerPosition(position);
		}
	}
	public Spielfeld getFeld(){
		return feld;
	}
}
