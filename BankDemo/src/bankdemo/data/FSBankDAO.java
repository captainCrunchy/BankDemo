package bankdemo.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.PrintStream;

import bankdemo.AccountType;
import bankdemo.Bank;
import bankdemo.BankAccount;



// This class wasn't covered in class, and is not something you are required
// to study - but its a simple implementation of persistence without a database, 
// just using individual text files for each bank account object in the bank.
public class FSBankDAO implements BankDAO{

	public void save(Bank bank) {
		try {
			for ( BankAccount account : bank.getAccounts() ) {
				// Save the account to a file (type, interest rate, balance)
				File f=new File( account.getAccountNumber() + ".txt");
			    FileOutputStream fop = new FileOutputStream(f);
			    PrintStream p = new PrintStream(fop);
			    p.println(account.getAccountNumber());
			    p.println(account.getAccountType().toString());
			    p.println(account.getInterestRate());
			    p.println(account.getBalance());
			    p.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Bank load() {
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith("txt");
		    }
		};
		
		Bank b = new Bank();
		
		File dir = new File(".");
		File []children = dir.listFiles(filter);
		for ( File f : children ) {
			try {
				BufferedReader in = new BufferedReader( new FileReader(f));
			    String number = in.readLine();
				String type = in.readLine();
				String interest = in.readLine();
				String balance = in.readLine();
				BankAccount a = new BankAccount(AccountType.valueOf(type));
				a.setInterestRate(Double.parseDouble(interest));
				a.setBalance(Double.parseDouble(balance));
				a.setAccountNumber(number);
				b.getAccounts().add(a);
				in.close();
			} 
			catch (Exception e ) {
				e.printStackTrace();
			}
		}
		
		return b;
	}

	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
}
