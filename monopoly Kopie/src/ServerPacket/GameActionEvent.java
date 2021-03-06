package ServerPacket;

import monopoly.local.valueobjects.Spieler;

public class GameActionEvent extends GameEvent {

	private static final long serialVersionUID = -2391443656175761807L;

	public enum GameActionEventType { ATTACK, NEW_OWNER, BUY_ITEM };
	
	private GameActionEventType type;

	public GameActionEvent(Spieler player, GameActionEventType type) {
		// ATTACK: Spieler wird angegriffen
		// NEW_OWNER: Spieler ist neuer Eigentümer (einer Provinz)
		// BUY_ITEM: Spieler kann Item kaufen
		super(player);
		
		this.type = type;
	}

	public GameActionEventType getType() {
		return type;
	}
}
