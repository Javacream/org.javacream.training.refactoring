package org.javacream.movefield;

public class Account {
	private AccountType accountType;
	private int interestRate;
	
	public double interestForAmount_days (double amount, int days) {
	    return interestRate * amount * days / 365;
	}


}
