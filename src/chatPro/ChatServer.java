package chatPro;

import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

public class ChatServer {
	Registry registry;
	String address;

	public ChatServer() {
		try {
			
			//find the address of local server using InetAddress
			try {
				address = (InetAddress.getLocalHost()).toString();
				System.out.println("[System] Local host address found:" + address);
			} catch (Exception e) {
				System.out.println("can't get inet address.");
			}
			
			//create a chat server with port number 3232
			ChatObject server = new ChatObject("SERVER");
			registry = LocateRegistry.createRegistry(3232);
			registry.rebind("chatserver", server);
			String ipaddress = (address.split("/"))[1];
			System.out.println("[System] Server is starting at " + ipaddress);
			
		} catch (Exception e) {
			System.out.println("[System] Server failed: " + e);
		}
	}

	static public void main(String args[]) {
		try {
			ChatServer server = new ChatServer();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}