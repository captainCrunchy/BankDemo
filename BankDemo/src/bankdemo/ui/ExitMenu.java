package bankdemo.ui;

import bankdemo.Bank;
import bankdemo.data.BankDAO;

public class ExitMenu extends Menu {
	Bank bank;
	BankDAO bankDao;
	
	public ExitMenu() {
		super(null);
		
	}
	
	
	
	public Bank getBank() {
		return bank;
	}



	public void setBank(Bank bank) {
		this.bank = bank;
	}



	public BankDAO getBankDao() {
		return bankDao;
	}



	public void setBankDao(BankDAO bankDao) {
		this.bankDao = bankDao;
	}



	@Override
	public Menu invokeMenu() throws Exception {
		// Save the bank information to the filesystem
		this.bankDao.save(bank);
		System.out.println("Goodbye");
		System.exit(0);
		return null;
	}

}
