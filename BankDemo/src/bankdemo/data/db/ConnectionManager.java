package bankdemo.data.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	public static Connection getConnection() {
		try {
			 Class.forName("org.h2.Driver");
		     Connection conn = DriverManager.getConnection("jdbc:h2:file:bankdb", "sa", "sa");
		     return conn;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}
	}
}
