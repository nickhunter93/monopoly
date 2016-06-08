package monopoly.local.valueobjects;

import java.util.Vector;

import monopoly.local.domain.Monopoly;


public class Gemeinschaftskarte extends Deck {
	
private Vector<Aktion> deck;
private Monopoly monopoly;
	
	public Gemeinschaftskarte(Monopoly monopoly){
		this.monopoly = monopoly;
	}

	@Override
	public void deckMischen() {
		deck  = new Vector<Aktion>();
		
		//POSITIV
		
		//Es ist dein Geburtstag. Ziehe von jedem Spieler DM 2000 ein.
		deck.add(new Birthday(monopoly.getTurn().getWerIstDran(), monopoly.getAllSpieler()));
		//Aus Lagerverkäufen erhält Du DM 100.
		deck.add(new Zahlung(monopoly.getTurn().getWerIstDran(), 100));
		//Du erbst DM 2000.
		deck.add(new Zahlung(monopoly.getTurn().getWerIstDran(), 2000));
		//Einkommenssteuerrückzahlung. Ziehe DM 400 ein.
		deck.add(new Zahlung(monopoly.getTurn().getWerIstDran(), 400));
		//Du hast den zweiten Preis in einer Schönheitskonkurrenz gewonnen. Ziehe DM 200 ein.
		deck.add(new Zahlung(monopoly.getTurn().getWerIstDran(), 200));
		//Rücke vor bis auf Los.
		deck.add(new FeldOhneLos(monopoly.getTurn().getWerIstDran(), monopoly.getLos()));
		//Du kommst aus dem Gefängnis frei.
		deck.add(new GefaengnisFrei(monopoly.getTurn().getWerIstDran(), monopoly.getJail()));
		//Bank-Irrtum zu deinen Gunsten. Ziehe DM 4000 ein.
		deck.add(new Zahlung(monopoly.getTurn().getWerIstDran(), 4000));
		//Die Jahresrente wird fällig. Ziehe DM 2000 ein.
		deck.add(new Zahlung(monopoly.getTurn().getWerIstDran(), 2000));
		//Du erhältst auf Vorzugs-Aktien 7% Dividende. DM 500.
		deck.add(new Zahlung(monopoly.getTurn().getWerIstDran(), 500));
		//Gehe zurück zu Badstraße.
		deck.add(new FeldOhneLos(monopoly.getTurn().getWerIstDran(),monopoly.getSpielfeld()[1]));
		
		//NEGATIV
		
		//Zahle eine Strafe von DM 200.
		deck.add(new Zahlung(monopoly.getTurn().getWerIstDran(), -200));
		//Gehe in das Gefängnis. Begib Dich direkt dorthin. Gehe nicht über Los. Ziehe nicht DM 4000 ein.
		deck.add(new FeldOhneLos(monopoly.getTurn().getWerIstDran(),monopoly.getJail()));
		//Zahle an das Krankenhaus DM 2000.
		deck.add(new Zahlung(monopoly.getTurn().getWerIstDran(), -2000));
		//Zahle deine Versicherungssumme. DM 1000.
		deck.add(new Zahlung(monopoly.getTurn().getWerIstDran(), -1000));
		//Arzt-Kosten. Zahle DM 100.
		deck.add(new Zahlung(monopoly.getTurn().getWerIstDran(), -100));
	}

	@Override
	public Aktion karteZiehen(Spieler spieler) {
		if(deck.size() == 0){
			deckMischen();
		}
		// TODO Auto-generated method stub
		Aktion aktion = deck.remove((int) Math.floor(Math.random() * 15));
		aktion.setSpieler(spieler);
		
		return aktion;
	}
	
}
