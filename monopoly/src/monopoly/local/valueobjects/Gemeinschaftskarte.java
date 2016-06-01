package monopoly.local.valueobjects;

import java.util.Vector;
import monopoly.local.domain.Spielerverwaltung;


public class Gemeinschaftskarte extends Deck {
	
private Vector<Aktion> deck;
private Spieler spieler;
private Spielfeld spielfeld;
private Spielerverwaltung spielerverwaltung;
private Jail jail;
	
	public Gemeinschaftskarte(Spieler spieler, Jail jail, Spielfeld spielfeld, Spielerverwaltung spielerverwaltung){
		this.spieler = spieler;
		this.spielerverwaltung = spielerverwaltung;
		this.spielfeld = spielfeld;
		this.jail = jail;
	}

	@Override
	public void deckMischen() {
		deck  = new Vector<Aktion>();
		
		//POSITIV
		
		//Es ist dein Geburtstag. Ziehe von jedem Spieler DM 2000 ein.
		deck.add(new Birthday(spieler, spielfeld, spielerverwaltung));
		//Aus Lagerverkäufen erhält Du DM 100.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , 100));
		//Du erbst DM 2000.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , 2000));
		//Einkommenssteuerrückzahlung. Ziehe DM 400 ein.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , 400));
		//Du hast den zweiten Preis in einer Schönheitskonkurrenz gewonnen. Ziehe DM 200 ein.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , 200));
		//Rücke vor bis auf Los.
		deck.add(new FeldOhneLos(spieler, spielfeld.getLos(), spielfeld, spielerverwaltung));
		//Du kommst aus dem Gefängnis frei.
		deck.add(new GefaengnisFrei(spieler, jail, spielfeld, spielerverwaltung));
		//Bank-Irrtum zu deinen Gunsten. Ziehe DM 4000 ein.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , 4000));
		//Die Jahresrente wird fällig. Ziehe DM 2000 ein.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , 2000));
		//Du erhältst auf Vorzugs-Aktien 7% Dividende. DM 500.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , 500));
		//Gehe zurück zu Badstraße.
		deck.add(new FeldOhneLos(spieler, spielfeld.getSpielfeld()[1], spielfeld, spielerverwaltung));
		
		//NEGATIV
		
		//Zahle eine Strafe von DM 200.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , -200));
		//Gehe in das Gefängnis. Begib Dich direkt dorthin. Gehe nicht über Los. Ziehe nicht DM 4000 ein.
		deck.add(new FeldOhneLos(spieler, spielfeld.getJail(), spielfeld, spielerverwaltung));
		//Zahle an das Krankenhaus DM 2000.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , -2000));
		//Zahle deine Versicherungssumme. DM 1000.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , -1000));
		//Arzt-Kosten. Zahle DM 100.
		deck.add(new Zahlung(spieler, spielfeld, spielerverwaltung , -100));
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
