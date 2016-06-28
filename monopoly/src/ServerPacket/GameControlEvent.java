package ServerPacket;

import monopoly.local.valueobjects.Spieler;

public class GameControlEvent extends GameEvent {

	private static final long serialVersionUID = 4833660998427328149L;

	public enum GameControlEventType { GAME_STARTED, NEXT_PLAYER, GAME_OVER };
	
	private GameControlEventType type;

	public GameControlEvent(Spieler player, GameControlEventType type) {
		// GAME_STARTED: Spieler beginnt
		// GAME_OVER: Spieler hat gewonnen
		// NEXT_PLAYER: Spieler ist dran
		super(player);	
		this.type = type;
	}

	public GameControlEventType getType() {
		return type;
	}
}
