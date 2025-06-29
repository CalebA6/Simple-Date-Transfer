package net.caleba.responses;

import net.caleba.Controller;

public class WebpageResponse {

	public String getResponse() {
		String header = "HTTP/1.0 200 Sending Page";
		return header + "\r\n\r\n" + getBody();
	}
	
	private String getBody() {
		return "<html><head><title>Simple Data Transfer</title></head><body>" + getTable() +  "</body></html>";
	}
	
	private String getTable() {
		return "<table style=\"width: 100%; \"><tr>" + getReceivedMessage() + getSendMessage() + "</tr></table>";
	}
	
	private String getReceivedMessage() {
		String message = Controller.getMessage();
		message = message.replace("\r\n", "<br>");
		message = message.replace("\n", "<br>");
		message = message.replace("\r", "<br>");
		// Maybe want to consider CSS at some point in the future.
		return "<td style=\"border: 1px solid; vertical-align: top; \">Received Message: <br>" + message + "</td>";
	}
	
	private String getSendMessage() {
		return "<td style=\"border: 1px solid; vertical-align: top; \">" + getForm() + "</td>";
	}
	
	private String getForm() {
		String content = "Send Message: <br><textarea id=\"message\"></textarea><br><input type=\"submit\" onclick=\"sendMessage()\">";
		return content + sendMessage();
	}
	
	private String sendMessage() {
		String createMessager = "const messager = new XMLHttpRequest();";
		String sendMessage = "messager.open('POST', '/'); messager.send(document.getElementById('message').value);";
		return "<script>function sendMessage() {" + createMessager + handleLoad() + sendMessage + "}</script>";
	}
	
	private String handleLoad() {
		String onSuccess = "window.alert('Message sent.');";
		String onFailed = "window.alert('Message failed to send.');";
		return "messager.onload = function () {if(messager.status == 200) {" + onSuccess + "} else {" + onFailed + "}}\n";
	}
	
}
