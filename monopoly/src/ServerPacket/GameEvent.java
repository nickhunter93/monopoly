package ServerPacket;

import java.io.Serializable;

import monopoly.local.valueobjects.Spieler;

public abstract class GameEvent implements Serializable {

	private static final long serialVersionUID = -5234940566773150168L;
	
	private Spieler player;

	public GameEvent(Spieler player) {
		super();
		this.player = player;
	}

	public Spieler getPlayer() {
		return player;
	}
}
