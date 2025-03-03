package net.caleba;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Request {
	
	private String method = "";
	private String lineBreak = null;
	private Map<String, String> fields;
	private String body = "";

	Request(InputStream input) throws IOException {
		String[] headers = readLine(input).split(" ");
		method = headers[0];
		
		fields = new HashMap<>();
		while(true) {
			String line = readLine(input);
			if(line.equals("")) break;
			String[] field = line.split(": ");
			fields.put(field[0], field[1]);
		}
		
		if(fields.containsKey("Content-Length")) {
			int bodyLength = Integer.parseInt(fields.get("Content-Length"));
			for(int i=0; i<bodyLength; ++i) {
				body += (char)input.read();
			}
		}
	}
	
	String getMethod() {
		return method;
	}
	
	String getBody() {
		return body;
	}
	
	private String readLine(InputStream input) throws IOException {
		String line = "";
		boolean firstLine = true;
		char nextChar = 0;
		while(true) {
			char inputChar;
			if(nextChar != 0) {
				inputChar = nextChar;
				nextChar = 0;
			} else {
				inputChar = (char)input.read();
			}
			if(inputChar == '\n' && firstLine && lineBreak.equals("\r")) {
				lineBreak = "\r\n";
				firstLine = false;
				continue;
			}
			if(lineBreak != null && inputChar == lineBreak.charAt(0)) {
				for(int i=1; i<lineBreak.length(); ++i) {
					nextChar = (char)input.read();
					if(nextChar == lineBreak.charAt(i)) {
						return line;
					}
				}
			} else if(lineBreak == null && (inputChar == '\n' || inputChar == '\r')) {
				lineBreak = new String(new char[] {inputChar});
				return line;
			}
			line += inputChar;
		}
	}
	
}
