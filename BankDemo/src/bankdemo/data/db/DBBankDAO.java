package bankdemo.data.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bankdemo.AccountType;
import bankdemo.Bank;
import bankdemo.BankAccount;
import bankdemo.data.BankDAO;

public class DBBankDAO implements BankDAO {

	Connection connection = ConnectionManager.getConnection();
	
	
	
	public void save(Bank bank) {
		for ( BankAccount account : bank.getAccounts() ) {
			if ( !exists(account) ){
				insert(account);
			}
			else {
				update(account);
			}
		}
		
		removeAllMissing(bank);
		
		// this is a special case, in that we brought ALL rows into memory on load.  
		// A more logical approach would be to simply remove all rows, then insert all
		// accounts all over again.  This is a very special case though, and is typically
		// not applicable, so I didn't implement it that way.
	}
	
	private boolean exists(BankAccount account) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT ACCOUNT_NUMBER FROM BANKACCOUNT WHERE ACCOUNT_NUMBER=?");
			ps.setString(1, account.getAccountNumber());
			// execute query returns result set, next returns true if there is a (1st) result.
			return ps.executeQuery().next();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	private void insert(BankAccount account) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO BANKACCOUNT (ACCOUNT_NUMBER, BALANCE, INTEREST_RATE, TYPE) VALUES (?, ?, ?, ?)");
			ps.setString(1, account.getAccountNumber());
			ps.setDouble(2, account.getBalance());
			ps.setDouble(3, account.getInterestRate());
			ps.setString(4, account.getAccountType().toString());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void update(BankAccount account) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE BANKACCOUNT SET BALANCE=?, INTEREST_RATE=?, TYPE=? WHERE ACCOUNT_NUMBER=?");
			ps.setDouble(1, account.getBalance());
			ps.setDouble(2, account.getInterestRate());
			ps.setString(3, account.getAccountType().toString());
			ps.setString(4, account.getAccountNumber());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void removeAllMissing(Bank bank) {
		Collection<BankAccount> accountsInDb = getAll();
		Collection<String> accountsToDelete = new LinkedList<String>();
		for (BankAccount a : accountsInDb) {
			if ( bank.getAccount(a.getAccountNumber() ) == null ) {
				accountsToDelete.add(a.getAccountNumber());
			}
		}
		
		if ( accountsToDelete.size() > 0 ) {
			String sql = "DELETE FROM BANKACCOUNT WHERE ACCOUNT_NUMBER IN (" + join(accountsToDelete, ",") + ")";
			try {
				connection.createStatement().executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private Collection<BankAccount> getAll() {
		Collection<BankAccount> accounts = new LinkedList<BankAccount>();
		String q = "SELECT * FROM BANKACCOUNT";
		try {
			ResultSet rs = connection.createStatement().executeQuery(q);
			while ( rs.next() ) {
				BankAccount a = new BankAccount();
				a.setAccountNumber(rs.getString("ACCOUNT_NUMBER"));
				a.setAccountType(AccountType.valueOf(rs.getString("TYPE")));
				a.setBalance(rs.getDouble("BALANCE"));
				a.setInterestRate(rs.getDouble("INTEREST_RATE"));
				accounts.add(a);
			}
		}
		catch (Exception e) {
			System.out.println("An error occurred when trying to find all accounts in the database");
			System.out.println("MOST LIKELY, you forgot to run \"DBUpdate.java\", which creates the tables used in these demos");
			System.out.println("You can run this the same way you ran this program.");
			e.printStackTrace();
		}
		return accounts;
	}
	
	

	public Bank load() {
		return new Bank(getAll());
	}
	
	
	
	private static String join(Collection<String> list, String delim) {
	    StringBuilder sb = new StringBuilder();
	    String loopDelim = "";
	    for(String s : list) {
	        sb.append(loopDelim);
	        sb.append(s);            
	        loopDelim = delim;
	    }
	    return sb.toString();
	}

	public void cleanup() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
