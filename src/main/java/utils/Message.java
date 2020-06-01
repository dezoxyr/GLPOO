package utils;

import java.util.UUID;

public class Message {
	private String sender;
	private String message;
	private UUID convId;
	
	public Message(String sender, String message, UUID convId){
		this.sender = sender;
		this.message = message;
		this.convId = convId;
	}
	
	
	public String getSender() {
		return this.sender;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public UUID getConvId() {
		return this.convId;
	}
	
}
