package ServerPacket;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Client-side remote interface:
 * Server sends notification to clients using this interface
 */
public interface GameEventListener extends Remote {

	public void handleGameEvent(GameEvent event) throws RemoteException;
}
