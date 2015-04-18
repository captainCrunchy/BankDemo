package bankdemo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Bank {

	private Collection<BankAccount> accounts;
	private static final Random random =  new Random();
	private static final NumberFormat format = new DecimalFormat("00000");
	
	public Bank() {
		this.accounts = new ArrayList<BankAccount>();
	}
	public Bank(Collection<BankAccount> accounts) {
		this.accounts = accounts;
	}
	
	public BankAccount openCheckingAccount() {
		BankAccount newAccount = createAccount(AccountType.Checking);
		newAccount.setInterestRate(0);
		return newAccount;
	}
	
	public BankAccount openSavingAccount() {
		BankAccount newAccount = createAccount(AccountType.Savings);
		newAccount.setInterestRate(0);
		return newAccount;
	}
	
	public void closeAccount(String accountNumber) {
		BankAccount ba = this.getAccount(accountNumber);
		if ( ba != null ) {
			this.accounts.remove(ba);
		}
	}
	
	public Collection<BankAccount> getAccounts() {
		return this.accounts;
	}
	
	
	
	public BankAccount getAccount(String accountNumber) {
		for ( BankAccount ba: this.accounts ) {
			if ( ba.getAccountNumber().equals(accountNumber)) return ba;
		}
		return null;
	}
	
	private BankAccount createAccount(AccountType type) {
		BankAccount newAccount = new BankAccount(type);
		newAccount.setAccountNumber(format.format(Math.abs(random.nextInt()%10000)));
		newAccount.setBalance(0);
		this.accounts.add(newAccount);
		return newAccount;
	}
}
