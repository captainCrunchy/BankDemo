package bankdemo.ui;

import bankdemo.Bank;
import bankdemo.BankAccount;

public class AccountMenu extends Menu {
	private BankAccount account;
	private Bank bank;
	public AccountMenu(Menu previous, Bank bank, BankAccount account) {
		super(previous);
		this.account = account;
		this.bank = bank;
	}
	@Override
	public Menu invokeMenu() throws Exception {
		System.out.println("Options for Account " + this.account.getAccountNumber());
		System.out.println("-----------------------------------------");
		System.out.println("1)  Show Balance");
		System.out.println("2)  Deposit");
		System.out.println("3)  Withdraw");
		System.out.println("4)  Apply Interst");
		System.out.println("5)  Delete");
		System.out.println("X)  Back");
		String choiceStr = stdin.readLine();
		if ( "1".equals(choiceStr) ) {
			System.out.println("Current balance is:  $" + moneyFormat.format(this.account.getBalance()));
			return this; 
		}
		else if ("2".equals(choiceStr)) {
			return new DepositMenu(this, account);
		}
		else if ("3".equals(choiceStr)) {
			return new WithdrawMenu(this, account);
		}
		else if ("4".equals(choiceStr)) {
			this.account.applyInterest();
			System.out.println("Current balance is:  $" + moneyFormat.format(this.account.getBalance()));
			return this; 
		}
		else if ("5".equals(choiceStr)) {
			return new CloseAccountMenu(this, bank, account);
		}
		else if ( "X".equalsIgnoreCase(choiceStr)){
			return previous;
		}
		else { 
			System.out.println("Invalid selection.");
			return this; 
		}
	}
	
	

}
