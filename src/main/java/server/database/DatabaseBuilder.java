package server.database;

public class DatabaseBuilder extends Database{
	public static void createTables(String pseudo) {
		Database.update("CREATE TABLE IF NOT EXISTS "+pseudo+"( "
				+ "id INTEGER PRIMARY KEY,"
				+ "pseudo TEXT NOT NULL,"
				+ "msg TEXT NOT NULL,"
				+ "date DATE NOT NULL"
				+ ");");
	}
}
