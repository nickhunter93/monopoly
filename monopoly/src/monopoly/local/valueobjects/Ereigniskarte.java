package monopoly.local.valueobjects;

import java.util.Vector;

import monopoly.local.domain.Spielerverwaltung;
import monopoly.local.domain.Spielverwaltung;

public class Ereigniskarte extends Deck {

private Vector<Aktion> deck;
private Spieler spieler;
private Spielfeld spielfeld;
private Spielerverwaltung spielerverwaltung;
private Jail jail;
private Strasse[] yourStreets;
private Spielverwaltung feldverwaltung;

	public Ereigniskarte(Spieler spieler, Spielfeld spielfeld, Spielerverwaltung spielerverwaltung, Jail jail, Spielverwaltung feldverwaltung, Strasse[] yourStreets){
		this.spieler = spieler;
		this.spielfeld = spielfeld;
		this.spielerverwaltung = spielerverwaltung;
		this.jail = jail;
		this.feldverwaltung = feldverwaltung;
		this.yourStreets = yourStreets;
	}

	@Override
	public void deckMischen() {
		deck  = new Vector<Aktion>();
		
		//POSITIV
		
		//Rücke vor bis auf die Seestraße. Wenn du über Los kommst ziehe 4000 ein.
		deck.add(new FeldMitLos(spieler, spielfeld.getSpielfeld()[11], spielfeld, spielerverwaltung));
		//Du hast in einem Kreuzworträtselwettbewerb gewonnen. Ziehe DM 2000 ein.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , 2000));
		//Miete und Anleihezinsen werden fällig. Die Bank zahlt Dir DM 3000.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , 3000));
		//Du kommst aus dem Gefängnis frei.
		deck.add(new GefaengnisFrei(spieler, jail, spielfeld, spielerverwaltung));
		//Rücke bis auf Los vor.
		deck.add(new FeldOhneLos(spieler, spielfeld.getLos(), spielfeld, spielerverwaltung));
		//Die Bank zahlt dir eine Dividende von DM 1000.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , 1000));
		//Du erhältst DM 1500.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , 1500));
		//Rücke vor bis zu Schlossallee (falls schon verkauft eher negativ zu sehen).
		deck.add(new FeldOhneLos(spieler, spielfeld.getSpielfeld()[36], spielfeld, spielerverwaltung));
		//Rücke vor bis zum Opernplatz. Wenn Du über Los kommst, ziehe DM 4000 ein (kann sich ebenso negativ auswirken sobald einem der Opernplatz nicht gehört).
		deck.add(new FeldMitLos(spieler, spielfeld.getSpielfeld()[24], spielfeld, spielerverwaltung));
		//Gehe 3 Felder zurück.
		deck.add(new FeldOhneLos(spieler, spielfeld.getSpielfeld()[spieler.getSpielerPosition().getNummer()-3], spielfeld, spielerverwaltung));
		
		//NEGATIV
		
		//Lasse alle Deine Häuser renovieren. Zahle an die Bank für jedes Haus DM 500, für jedes Hotel DM 2000.
		deck.add(new Renovation(spieler, yourStreets, feldverwaltung, spielfeld, spielerverwaltung));
		//Du wirst zu Strassenausbesserungsarbeiten herangezogen. Zahle für deine Häuser und Hotels. DM 800 je Haus. DM 2300 je Hotel an die Bank.
		deck.add(new Strassenausbesserung(spieler, yourStreets, feldverwaltung, spielfeld, spielerverwaltung));
		//Betrunken im Dienst. Strafe DM 400.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , -400));
		//Strafe für zu schnelles Fahren DM 300.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , -300));
		//Gehe in das Gefängnis. 
		deck.add(new FeldOhneLos(spieler, spielfeld.getJail(), spielfeld, spielerverwaltung));
		//Zahle Schulgeld
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , -500));
		
	}

	@Override
	public void karteZiehen(Spieler spieler) {
		if(deck.size() == 0){
			deckMischen();
		}
		// TODO Auto-generated method stub
		Aktion aktion = deck.remove((int) Math.floor(Math.random() * 15));
		aktion.setSpieler(spieler);
	}
	

}
