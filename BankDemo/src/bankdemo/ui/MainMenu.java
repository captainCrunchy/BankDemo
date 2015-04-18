package bankdemo.ui;

import bankdemo.Bank;
import bankdemo.BankAccount;
import bankdemo.data.BankDAO;
import bankdemo.data.db.DBBankDAO;

public class MainMenu extends Menu {

	private Bank bank;
	private BankDAO bankDao = new DBBankDAO();
	
	public static void main(String [ ] args)
	{
		try {
		
			Menu menu = new MainMenu();
			while ( menu != null ) {
				menu = menu.invokeMenu();
			}
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MainMenu()throws Exception {
		super(new ExitMenu());
		
		Bank bank = bankDao.load();
		ExitMenu em = (ExitMenu) this.getPrevious();
		em.setBank(bank);
		em.setBankDao(bankDao);
		this.bank = bank;
	}
	
	
	private void listAccounts() {
		System.out.println("Account #\tType\tBalance");
		for ( BankAccount ba : bank.getAccounts()) {
			System.out.println(ba.getAccountNumber() + "\t" + ba.getAccountType().toString() + "\t" 
				+	moneyFormat.format(ba.getBalance()));
		}
		System.out.println("--------------------------------------");
	}
	
	private BankAccount chooseAccount() throws Exception{
		this.listAccounts();
		System.out.println("Please enter the accound number:  " );
		String choiceStr = stdin.readLine();
		
		for ( BankAccount ba : bank.getAccounts()) {
			if ( ba.getAccountNumber().equals(choiceStr)) {
				return ba;
			}
		}
		System.out.println("Invalid account number.");
		return null;
	}
	
	
	@Override
	public Menu invokeMenu() throws Exception {
		System.out.println("--------------------------------------");
		System.out.println("1)  Create account");
		System.out.println("2)  List / Choose from existing accounts");
		System.out.println("X)  Exit");
		System.out.println("Please enter a choice:  " );
		String choiceStr = stdin.readLine();
		if ( "1".equals(choiceStr) ) {
			return new CreateAccountMenu(this, this.bank); 
		}
		else if ("2".equals(choiceStr)) {
			BankAccount account = this.chooseAccount();
			if ( account == null ) return this;
			else return new AccountMenu(this, bank, account);	
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
