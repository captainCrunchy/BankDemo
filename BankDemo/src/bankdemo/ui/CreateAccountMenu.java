package bankdemo.ui;

import bankdemo.Bank;

public class CreateAccountMenu extends Menu {

	protected Bank bank;
	public CreateAccountMenu(Menu previous, Bank bank) {
		super(previous);
		this.bank = bank;
	}
	@Override
	public Menu invokeMenu() throws Exception {
		System.out.println("Options for Creating new account ");
		System.out.println("-----------------------------------------");
		System.out.println("1)  Create Checking");
		System.out.println("2)  Create Savings");
		System.out.println("X)  Cancel");
		
		String choiceStr = stdin.readLine();
		if ( "1".equals(choiceStr) ) {
			return new AccountMenu(this.previous, this.bank, this.bank.openCheckingAccount());
		}
		else if ("2".equals(choiceStr)) {
			return new AccountMenu(this.previous, this.bank, this.bank.openSavingAccount());
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
