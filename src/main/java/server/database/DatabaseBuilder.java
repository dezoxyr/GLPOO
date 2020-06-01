package server.database;

public class DatabaseBuilder extends Database{
	public static void createTables() {
		Database.update("CREATE TABLE IF NOT EXISTS messages( "
				+ "id INTEGER PRIMARY KEY,"
				+ "convId TEXT NOT NULL,"
				+ "pseudo TEXT NOT NULL,"
				+ "msg TEXT NOT NULL,"
				+ "date DATE NOT NULL"
				+ ");");
	}
}
