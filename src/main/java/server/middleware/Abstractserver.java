package server.middleware;

/**
 * Abstract class server
 * */

public abstract class Abstractserver {

	public abstract void connect(String ip, int port);
	
	public abstract void open(String owner);
	
}
