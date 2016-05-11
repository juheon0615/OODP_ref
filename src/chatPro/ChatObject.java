package chatPro;

import java.rmi.*;
import java.util.Date;
import java.rmi.server.*;
import java.util.Vector;
import java.text.*;
 
public class ChatObject extends UnicastRemoteObject implements ChatInterface  {
 
	public String name;
	public ChatInterface[] client= new ChatInterface[100];
	public int clientCount = 0;
	
	public ChatObject(String n)  throws RemoteException { 
		//sets the name of the instance
		this.name=n;   
	}
	public String getName() throws RemoteException {
		//returns the name of the instance
		return this.name;
	}
 
	public int setClient(ChatInterface c) throws RemoteException{
		//adds newly created clients to client[]
		System.out.println(c.getName()+" added");
		client[clientCount]=c;
		clientCount++;
		return clientCount-1;
	}
	public int getClientNum(){
		//show the number of clients added
		return clientCount;
	}
	
	public ChatInterface[] getClient(){
		// This function
		ChatInterface buf[] = new ChatInterface[clientCount];
		for (int j = 0; j < clientCount;j++){
			buf[j] = client[j];
		}
		for (int i =0; i<clientCount;i++){
			System.out.println("cli count: " + clientCount);
			System.out.println("cli num: "+i+" name: "+buf[i]);
		}
		return buf;
	}
 
	public void send(String s, int clientNum) throws RemoteException{
		//System.out.println(s);
		//calls print function of all clients except for
		//the client with client number "clientNum"
		ChatInterface buf[] = new ChatInterface[clientCount];
		for (int j = 0; j < clientCount;j++){
			buf[j] = client[j];
			if (clientNum != j){
			buf[j].print(s);
			}
		}
		
	}
	public void print(String s) throws RemoteException{
		//used in send()
		
		System.out.println(time()+" " +s);
	}
	
	public String time() throws RemoteException{
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("hh:mm");
		
		return "("+ft.format(date)+")";
	}
}