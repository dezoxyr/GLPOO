package utils;

/**
 * Class Message
 * */

public class Message {
	private String sender;
	private String message;
	
	/**
	 * Constructor
	 * @param sender
	 	*Pseudo of the message sender
	 *@param message
	 	*The message 
	 * */
	
	public Message(String sender, String message){
		this.sender = sender;
		this.message = message;
	}
	
	/**
	 * Get the sender of a Message
	 * @return the sender of the Message
	 * */
	
	public String getSender() {
		return this.sender;
	}
	
	/**
	 * get the text of a Message
	 * @return the text of the Message
	 * */
	
	public String getMessage() {
		return this.message;
	}
	
}
