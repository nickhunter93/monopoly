package monopoly.local.ui.gui.swing;

public class SpielerPosition {
	public SpielerPosition() {

	}

	public String getSpielerPosition(int feldnummer, int spielernummer) {
		switch (feldnummer) {
		case 0:
			switch (spielernummer) {
			case 0:
				return "pos 0.91al 0.9al,h 50,w 30";
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			default:
				return "pos 0.91al 0.9al,h 50,w 30";
			}

		case 1:
			switch (spielernummer) {
			case 0:
				return "pos 0.91al 0.9al,h 50,w 30";
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			default:
				return "pos 0.91al 0.9al,h 50,w 30";
			}
		case 2:
			switch (spielernummer) {
			case 0:
				return "pos 0.91al 0.9al,h 50,w 30";
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			default:
				return "pos 0.91al 0.9al,h 50,w 30";
			}
		case 3:
			switch (spielernummer) {
			case 0:
				return "pos 0.91al 0.9al,h 50,w 30";
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			default:
				return "pos 0.91al 0.9al,h 50,w 30";
			}
		default:
			return "pos 0.91al 0.9al,h 50,w 30";
		}
	}
}
