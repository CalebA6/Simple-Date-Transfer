package net.caleba;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread {
	
	private ServerSocket socket;

	Server() {
		socket = getSocket();
		start();
	}
	
	private ServerSocket getSocket() {
		try {
			return new ServerSocket(80);
		} catch (IOException e) {
			System.err.println("Failed to start server: " + e.getMessage());
			return getSocket();
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				Connection connection = new Connection(socket.accept());
				connection.start();
			} catch (IOException e) {
				System.err.println("Failed to accept connection: " + e.getMessage());
			}
		}
	}
	
}
