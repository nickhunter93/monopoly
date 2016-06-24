package monopoly.local.valueobjects;

import java.util.Vector;

import monopoly.local.domain.Monopoly;


public class Gemeinschaftskarten extends Deck {
	
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
		deck.add(new Zahlung(monopoly, 100));
		//Du erbst DM 2000.
		deck.add(new Zahlung(monopoly, 2000));
		//Einkommenssteuerrückzahlung. Ziehe DM 400 ein.
		deck.add(new Zahlung(monopoly, 400));
		//Du hast den zweiten Preis in einer Schönheitskonkurrenz gewonnen. Ziehe DM 200 ein.
		deck.add(new Zahlung(monopoly, 200));
		//Rücke vor bis auf Los.
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[0]));
		//Du kommst aus dem Gefängnis frei.
		deck.add(new GefaengnisFrei(monopoly));
		//Bank-Irrtum zu deinen Gunsten. Ziehe DM 4000 ein.
		deck.add(new Zahlung(monopoly, 4000));
		//Die Jahresrente wird fällig. Ziehe DM 2000 ein.
		deck.add(new Zahlung(monopoly, 2000));
		//Du erhältst auf Vorzugs-Aktien 7% Dividende. DM 500.
		deck.add(new Zahlung(monopoly, 500));
		//Gehe zurück zu Badstraße.
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[1]));
		
		//NEGATIV
		
		//Zahle eine Strafe von DM 200.
		deck.add(new Zahlung(monopoly, -200));
		//Gehe in das Gefängnis. Begib Dich direkt dorthin. Gehe nicht über Los. Ziehe nicht DM 4000 ein.
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[10]));
		//Zahle an das Krankenhaus DM 2000.
		deck.add(new Zahlung(monopoly, -2000));
		//Zahle deine Versicherungssumme. DM 1000.
		deck.add(new Zahlung(monopoly, -1000));
		//Arzt-Kosten. Zahle DM 100.
		deck.add(new Zahlung(monopoly, -100));
	}

	@Override
	public Aktion karteZiehen() {
		if(deck.size() == 0){
			deckMischen();
		}
		deckMischen();
		// TODO Auto-generated method stub
		Aktion aktion = deck.remove((int) Math.floor(Math.random() * 15));
		//Aktion aktion = new Birthday(monopoly);

		return aktion;
	}
	
}
