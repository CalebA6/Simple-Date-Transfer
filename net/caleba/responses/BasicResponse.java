package net.caleba.responses;

public class BasicResponse {

	private int code;
	private String message;
	
	public BasicResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getResponse() {
		return "HTTP/1.0 " + code + " " + message + "\r\n\r\n";
	}
	
}
