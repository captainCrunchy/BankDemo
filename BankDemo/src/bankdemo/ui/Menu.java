package bankdemo.ui;

import java.io.BufferedReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public abstract class Menu {
	
	protected NumberFormat moneyFormat = new DecimalFormat("0.00");
	protected BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    protected Menu previous;
	
    
    public Menu(Menu previous) {
		this.previous = previous;
	}
    
    
	protected Menu getPrevious() {
		return previous;
	}


	public abstract Menu invokeMenu() throws Exception;
	
}
