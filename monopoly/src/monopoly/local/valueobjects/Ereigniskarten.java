package monopoly.local.valueobjects;

import java.util.Vector;

import monopoly.local.domain.Monopoly;

public class Ereigniskarten extends Deck {

private Vector<Aktion> deck;
private Monopoly monopoly;

	public Ereigniskarten(Monopoly monopoly){
		this.monopoly = monopoly;
		deck  = new Vector<Aktion>();
	}

	@Override
	public void deckMischen() {
		
		//POSITIV
		
		//Rücke vor bis auf die Seestraße. Wenn du über Los kommst ziehe 4000 ein.
		deck.add(new FeldMitLos(monopoly, monopoly.getSpielfeld()[11],"Rücke vor bis auf die Seestraße.\nWenn du über Los kommst ziehe 4000€ ein."));
		//Du hast in einem Kreuzworträtselwettbewerb gewonnen. Ziehe DM 2000 ein.
		deck.add(new Gewinn(monopoly, 2000,"Du hast in einem Kreuzworträtselwettbewerb gewonnen.\nZiehe 2000€ ein."));
		//Miete und Anleihezinsen werden fällig. Die Bank zahlt Dir DM 3000.
		deck.add(new Gewinn(monopoly, 3000,"Miete und Anleihezinsen werden fällig.\nDie Bank zahlt Dir 3000€."));
		//Du kommst aus dem Gefängnis frei.
		deck.add(new GefaengnisFrei(monopoly, "Sie kommen aus dem Gefängnis frei."));
		//Rücke bis auf Los vor.
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[0],"Rücke bis auf Los vor."));
		//Die Bank zahlt dir eine Dividende von DM 1000.
		deck.add(new Gewinn(monopoly, 1000,"Die Bank zahlt dir eine Dividende von 1000€."));
		//Du erhältst DM 1500.
		deck.add(new Gewinn(monopoly, 1500,"Du erhältst 1500€."));
		//Rücke vor bis zu Schlossallee (falls schon verkauft eher negativ zu sehen).
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[39],"Rücke vor bis zu Schlossallee."));
		//Rücke vor bis zum Opernplatz. Wenn Du über Los kommst, ziehe DM 4000 ein (kann sich ebenso negativ auswirken sobald einem der Opernplatz nicht gehört).
		deck.add(new FeldMitLos(monopoly, monopoly.getSpielfeld()[24],"Rücke vor bis zum Opernplatz.\n Wenn Du über Los kommst, ziehe 2000€ ein."));
		//Gehe 3 Felder zurück.
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[monopoly.getTurn().getWerIstDran().getSpielerPosition().getNummer()-3],"Gehe 3 Felder zurück."));
		
		//NEGATIV
		
		//Lasse alle Deine Häuser renovieren. Zahle an die Bank für jedes Haus DM 500, für jedes Hotel DM 2000.
		deck.add(new Renovation(monopoly, "Lasse alle Deine Häuser renovieren. Zahle an die Bank für jedes Haus 500€, für jedes Hotel 2000€."));
		//Du wirst zu Strassenausbesserungsarbeiten herangezogen. Zahle für deine Häuser und Hotels. DM 800 je Haus. DM 2300 je Hotel an die Bank.
		deck.add(new Strassenausbesserung(monopoly, "Du wirst zu Strassenausbesserungsarbeiten herangezogen. Zahle für deine Häuser und Hotels. 800€ je Haus. 2300€ je Hotel an die Bank."));
		//Betrunken im Dienst. Strafe DM 400.
		deck.add(new Zahlung(monopoly, 400,"Betrunken im Dienst. Strafe 400€."));
		//Strafe für zu schnelles Fahren DM 300.
		deck.add(new Zahlung(monopoly, 300,"Strafe für zu schnelles Fahren 300€."));
		//Gehe in das Gefängnis. 
		deck.add(new FeldOhneLos(monopoly, monopoly.getSpielfeld()[10],"Gehen Sie in das Gefängnis.\nBegeben Sie sich direkt dorthin.\nZiehen Sie nicht über Los.\nZiehen Sie keine 2000€ ein."));
		//Zahle Schulgeld
		deck.add(new Zahlung(monopoly, 500,"Zahle Schulgeld.\n500€."));
	}
	
	
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
