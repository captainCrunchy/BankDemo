package bankdemo;

public class BankAccount {
	private double balance;
	private double interestRate;
	private AccountType accountType;
	private String accountNumber;
	
	
	public BankAccount() {
		
	}
	public BankAccount(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public void deposit(double amount) {
		balance += amount;
	}
	
	public void withdrawl(double amount) {
		balance -= amount;
	}
	
	public void applyInterest() {
		this.deposit(this.getBalance() * this.getInterestRate());
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	
	
}
