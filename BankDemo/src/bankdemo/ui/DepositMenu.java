package bankdemo.ui;

import bankdemo.BankAccount;

public class DepositMenu extends Menu {

	protected BankAccount account;
	
	public DepositMenu(Menu previous, BankAccount account) {
		super(previous);
		this.account = account;
	}
	@Override
	public Menu invokeMenu() throws Exception {
		System.out.println("Please enter the amount to deposit (X to cancel):  ");
		System.out.println("-----------------------------------------");
		String choiceStr = stdin.readLine();
		if ( "x".equalsIgnoreCase(choiceStr) ) return previous;
		else {
			try {
				account.deposit(Double.parseDouble(choiceStr));
			} catch (Exception e) {
				System.out.println("Please enter a number");
				return this;
			}
			return previous;
		}
	}

}
