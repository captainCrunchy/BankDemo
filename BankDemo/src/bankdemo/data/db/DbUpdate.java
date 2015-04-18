package bankdemo.data.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUpdate {

	
	public static void main(String[] args) throws SQLException {
		// Create the Bank Table, if it does not exist.
		Connection con = ConnectionManager.getConnection();
		String sql = "CREATE TABLE IF NOT EXISTS BANKACCOUNT  (ACCOUNT_NUMBER varchar(6), BALANCE double, INTEREST_RATE double, TYPE varchar(20))";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		System.out.println("BANKACCOUNT table created.");
	}

}
