package server.database;

/**
 * Class DatabaseBuilder
 * */

public class DatabaseBuilder extends Database implements BuildDB{
	
	/**
	 * Create the database
	 * */
	
	public static void createTables() {
		Database.update("CREATE TABLE IF NOT EXISTS messages("
				+ "id INTEGER PRIMARY KEY,"
				+ "client TEXT NOT NULL,"
				+ "pseudo TEXT NOT NULL,"
				+ "msg TEXT NOT NULL,"
				+ "date DATE NOT NULL"
				+ ");");
	}
}
