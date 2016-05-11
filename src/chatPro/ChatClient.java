package chatPro;

import java.net.InetAddress;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.rmi.registry.*;

public class ChatClient {

	public static void main(String[] argv) {
		int clientNum=0;
		String address="";
		try {
			try {
				address = (InetAddress.getLocalHost()).toString();
				address = (address.split("/"))[1];//use only the ip address
				System.out.println("[System] Local host address found:" + address);
			} catch (Exception e) {
				System.out.println("can't get inet address.");
			}//get local host address
			
			
			//connect to the local address with the port number "3232"
			Registry registry;
			registry = LocateRegistry.getRegistry(address, (new Integer("3232")).intValue());
			ChatInterface server = (ChatInterface) registry.lookup("chatserver");
			
			//get user name
			Scanner s = new Scanner(System.in);
			System.out.print("Enter Your name and press Enter: ");
			String name = s.nextLine().trim();
			
			//send log-in message
			ChatInterface client = new ChatObject(name);
			String msg = "=====[" + client.getName() + "] logged in!=====";
			clientNum = server.setClient(client);
			server.send(msg,clientNum);
			
			
			
			while (true) {
				//wait for user input and send user input
				msg = s.nextLine().trim();
				msg = "[" + client.getName() + "] " + msg;
				server.send(msg,clientNum);
			}

		} catch (Exception e) {
			System.out.println("[System] Server failed: " + e);
		}
	}
}