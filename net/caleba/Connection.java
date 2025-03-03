package net.caleba;

import java.io.IOException;
import java.net.Socket;

import net.caleba.responses.BasicResponse;
import net.caleba.responses.WebpageResponse;

public class Connection extends Thread {

	Connection(Socket connection) {
		try {
			Request request = new Request(connection.getInputStream());
			switch(request.getMethod()) {
				case "GET": 
					WebpageResponse response = new WebpageResponse();
					connection.getOutputStream().write(response.getResponse().getBytes());
					break;
				case "POST": 
					new Message(request.getBody());
					connection.getOutputStream().write(new BasicResponse(200, "Recieved").getResponse().getBytes());
				default: 
					connection.getOutputStream().write(new BasicResponse(501, "Method not implemented").getResponse().getBytes());
			}
			connection.close();
		} catch (IOException e) {
			System.err.println("Failed to read request: " + e.getMessage());
		}
	}
	
}
