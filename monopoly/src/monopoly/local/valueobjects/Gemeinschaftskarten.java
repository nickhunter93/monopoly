package monopoly.local.valueobjects;

import java.io.Serializable;
import java.util.Vector;

import monopoly.local.domain.Monopoly;


public class Gemeinschaftskarten extends Deck implements Serializable {
	
private Vector<Aktion> deck;
private Monopoly monopoly;
	
	public Gemeinschaftskarten(Monopoly monopoly){
		this.monopoly = monopoly;
		deck  = new Vector<Aktion>();
	}

	@Override
	public void deckMischen() {
		
		//POSITIV
		
		//Es ist dein Geburtstag. Ziehe von jedem Spieler DM 2000 ein.
		deck.add(new Birthday(monopoly));
		//Aus Lagerverkäufen erhält Du DM 100.
		deck.add(new Gewinn(monopoly, 100,"Aus Lagerverkäufen erhältst du 100 €."));
		//Du erbst DM 2000.
		deck.add(new Gewinn(monopoly, 2000,"Du erbst 2000 €."));
		//Einkommenssteuerrückzahlung. Ziehe DM 400 ein.
		deck.add(new Gewinn(monopoly, 400,"Einkommensteuerrückzahlung.\nZiehe 400 € ein."));
		//Du hast den zweiten Preis in einer Schönheitskonkurrenz gewonnen. Ziehe DM 200 ein.
		deck.add(new Gewinn(monopoly, 200,"Du hast den zweiten Preis in einer Schönheitskonkurrenz gewonnen.\nZiehe 200 € ein."));
		//Rücke vor bis auf Los.
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[0],"Rücke vor bis auf Los.\nZiehe keine 2000€ ein."));
		//Du kommst aus dem Gefängnis frei.
		deck.add(new GefaengnisFrei(monopoly));
		//Bank-Irrtum zu deinen Gunsten. Ziehe DM 4000 ein.
		deck.add(new Gewinn(monopoly, 4000,"Bank-Irrtum zu deinen Gunsten.\nZiehe 4000 € ein."));
		//Die Jahresrente wird fällig. Ziehe DM 2000 ein.
		deck.add(new Gewinn(monopoly, 2000,"Die Jahresrente wird fällig.\nZiehe 2000€ ein."));
		//Du erhältst auf Vorzugs-Aktien 7% Dividende. DM 500.
		deck.add(new Gewinn(monopoly, 500,"Die erhältst auf Vorzugs-Aktien 7% Dividende.\n Du erhältst 500 €."));
		//Gehe zurück zu Badstraße.
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[1],"Ziehe vor bis zur Badstraße.\nZiehe nicht über Los.\nZiehe keine 2000€ ein."));
		
		//NEGATIV
		
		//Zahle eine Strafe von DM 200.
		deck.add(new Zahlung(monopoly, 200,"Zahle eine Strafe von 200€."));
		//Gehe in das Gefängnis. Begib Dich direkt dorthin. Gehe nicht über Los. Ziehe nicht DM 4000 ein.
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[10],"Gehe in das Gefängnis.\nBegib dich direkt dorthin.\nGehe nicht über Los.\nZiehe keine 2000€ ein."));
		//Zahle an das Krankenhaus DM 2000.
		deck.add(new Zahlung(monopoly, 2000,"Zahle an das Krankenhaus 2000€"));
		//Zahle deine Versicherungssumme. DM 1000.
		deck.add(new Zahlung(monopoly, 1000,"Zahle deine Versicherungssumme.\n1000€."));
		//Arzt-Kosten. Zahle DM 100.
		deck.add(new Zahlung(monopoly, 100,"Arzt-Kosten.\nZahle 100€."));
	}

	@Override
	public Aktion karteZiehen() {
		if(deck.size() == 0){
			deckMischen();
		}
		deckMischen();
		// TODO Auto-generated method stub
		Aktion aktion = deck.remove((int) Math.floor(Math.random() * 15));
		return aktion;
	}
	
}
