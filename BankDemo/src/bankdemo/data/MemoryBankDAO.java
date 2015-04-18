package bankdemo.data;

import bankdemo.Bank;

public class MemoryBankDAO implements BankDAO{

	public void save(Bank bank) {
		// We are willfully forgetting this... the memory-based data 
		// access object won't persist anything...
	}
	
	public Bank load() {
		// The memory-based data access object won't persist anything, 
		// so we'll just return an empty Bank
		
		return new Bank();
	}
	
	public void cleanup() {
		System.out.println("I've forgotten everything...");
	}
}
