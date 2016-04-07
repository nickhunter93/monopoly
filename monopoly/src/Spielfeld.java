
public class Spielfeld {
	FeldValue[][] feld;
	public Spielfeld(){
		feld = new FeldValue[10][10];
		for (int i=0;i<10;i++){
			for (int k=0;k<10;k++){
				feld[i][k] = new Strasse("Test",2000,true,2000,500,0);
			}
		}
	}
	public void showFeld(){
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		System.out.print("|");
		for(FeldValue feld1:feld[1]){
			System.out.print(feld1.getName()+" |");
		}
		System.out.println();
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		for(int i=1;i<9;i++){
			System.out.println("|"+feld[i][0].getName()+" |                                               |"+feld[i][9].getName()+" |");
			if(i!=8)System.out.println("|-----|                                               |-----|");
		}
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
		System.out.print("|");
		for(FeldValue feld1:feld[9]){
			System.out.print(feld1.getName()+" |");
		}
		System.out.println();
		System.out.println("|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|");
	}
}
