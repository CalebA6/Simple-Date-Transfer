package net.caleba.responses;

public class WebpageResponse {

	public String getResponse() {
		String header = "HTTP/1.0 200 Sending Page";
		return header + "\r\n\r\n" + getBody();
	}
	
	private String getBody() {
		return "<html><head><title>Simple Data Transfer</title></head><body>" + getForm() +  "</body></html>";
	}
	
	private String getForm() {
		String content = "Message: <br><textarea id=\"message\"></textarea><br><input type=\"submit\" onclick=\"sendMessage()\">";
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
