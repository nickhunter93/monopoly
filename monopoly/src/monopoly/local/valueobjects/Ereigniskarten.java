package monopoly.local.valueobjects;

import java.util.Vector;

import monopoly.local.domain.Monopoly;

public class Ereigniskarten extends Deck {

private Vector<Aktion> deck;
private Monopoly monopoly;

	public Ereigniskarten(Monopoly monopoly){
		this.monopoly = monopoly;
	}

	@Override
	public void deckMischen() {
		deck  = new Vector<Aktion>();
		
		//POSITIV
		
		//Rücke vor bis auf die Seestraße. Wenn du über Los kommst ziehe 4000 ein.
		deck.add(new FeldMitLos(monopoly, monopoly.getSpielfeld()[11]));
		//Du hast in einem Kreuzworträtselwettbewerb gewonnen. Ziehe DM 2000 ein.
		deck.add(new Zahlung(monopoly, 2000));
		//Miete und Anleihezinsen werden fällig. Die Bank zahlt Dir DM 3000.
		deck.add(new Zahlung(monopoly, 3000));
		//Du kommst aus dem Gefängnis frei.
		deck.add(new GefaengnisFrei(monopoly));
		//Rücke bis auf Los vor.
		deck.add(new FeldOhneLos(monopoly,monopoly.getLos()));
		//Die Bank zahlt dir eine Dividende von DM 1000.
		deck.add(new Zahlung(monopoly, 1000));
		//Du erhältst DM 1500.
		deck.add(new Zahlung(monopoly, 1500));
		//Rücke vor bis zu Schlossallee (falls schon verkauft eher negativ zu sehen).
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[36]));
		//Rücke vor bis zum Opernplatz. Wenn Du über Los kommst, ziehe DM 4000 ein (kann sich ebenso negativ auswirken sobald einem der Opernplatz nicht gehört).
		deck.add(new FeldMitLos(monopoly, monopoly.getSpielfeld()[24]));
		//Gehe 3 Felder zurück.
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[monopoly.getTurn().getWerIstDran().getSpielerPosition().getNummer()-3]));
		
		//NEGATIV
		
		//Lasse alle Deine Häuser renovieren. Zahle an die Bank für jedes Haus DM 500, für jedes Hotel DM 2000.
		deck.add(new Renovation(monopoly));
		//Du wirst zu Strassenausbesserungsarbeiten herangezogen. Zahle für deine Häuser und Hotels. DM 800 je Haus. DM 2300 je Hotel an die Bank.
		deck.add(new Strassenausbesserung(monopoly));
		//Betrunken im Dienst. Strafe DM 400.
		deck.add(new Zahlung(monopoly, -400));
		//Strafe für zu schnelles Fahren DM 300.
		deck.add(new Zahlung(monopoly, -300));
		//Gehe in das Gefängnis. 
		deck.add(new FeldOhneLos(monopoly, monopoly.getJail()));
		//Zahle Schulgeld
		deck.add(new Zahlung(monopoly, -500));
	}

	@Override
	public Aktion karteZiehen(Monopoly monopoly) {
		if(deck.size() == 0){
			deckMischen();
		}
		// TODO Auto-generated method stub
		Aktion aktion = deck.remove((int) Math.floor(Math.random() * 15));
		aktion.setSpieler(monopoly.getTurn().getWerIstDran());
		
		return aktion;
	}
	

}
