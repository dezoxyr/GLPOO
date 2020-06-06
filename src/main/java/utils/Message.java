package utils;

public class Message {
	private String sender;
	private String message;
	
	public Message(String sender, String message){
		this.sender = sender;
		this.message = message;
	}
	
	public String getSender() {
		return this.sender;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
