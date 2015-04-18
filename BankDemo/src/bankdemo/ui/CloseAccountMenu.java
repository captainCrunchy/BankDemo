package bankdemo.ui;

import bankdemo.Bank;
import bankdemo.BankAccount;

public class CloseAccountMenu extends Menu {

	private Bank bank;
	private BankAccount account;
	
	public CloseAccountMenu(Menu previous, Bank bank, BankAccount account) {
		super(previous);
		this.bank = bank;
		this.account = account;
	}
	
	@Override
	public Menu invokeMenu() throws Exception {
		System.out.println("Are you sure you want to close the following account?");
		System.out.println("  Account Number:  	" + account.getAccountNumber());
		System.out.println("  Type:				" + account.getAccountType());
		System.out.println("  Balance:			" + this.moneyFormat.format(account.getBalance()));
		System.out.print("Enter y / n:  ");
		String choiceStr = stdin.readLine();
		if ( choiceStr.equalsIgnoreCase("y") ) {
			System.out.println("Account closed");
			bank.closeAccount(account.getAccountNumber());
		}
		return previous;
	}

}
