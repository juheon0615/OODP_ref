package chatPro;
import java.rmi.*;
import java.util.Vector;
 
//interface used
public interface ChatInterface extends Remote{
	public String getName() throws RemoteException;
	public void print(String s) throws RemoteException;
	public void send(String msg, int clientNum) throws RemoteException;
	public int setClient(ChatInterface c)throws RemoteException;
	public ChatInterface[] getClient() throws RemoteException;
	public int getClientNum()throws RemoteException;
}