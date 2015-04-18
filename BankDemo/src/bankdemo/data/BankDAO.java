package bankdemo.data;

import bankdemo.Bank;


/**
 * The BankDAO interface defines the methods that any implementation of the bank's data access 
 * object MUST provide.
 * @author sfrees
 *
 */
public interface BankDAO {
	
	
	public void save(Bank bank) ;
	
	
	public Bank load() ;
	
	public void cleanup();
}
