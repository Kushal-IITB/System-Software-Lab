package Question3;
import java.util.*;

class Account {
	int Balance;
	int AccountNumber;

	public void Account(int accountNumber){
		this.AccountNumber = accountNumber;
	}

	public void Deposit(int amount) {
		try {
			int balance = this.Balance;
			balance = balance + amount;
			this.Balance = balance;
			System.out.println(amount + " Amount has been added in Account: " + this.AccountNumber + ", New Balance is: " + balance);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid Account Number");
		}
	}
	public void Withdraw(int amount) {
		int Balance = this.Balance;
		if (Balance >= amount) {
			Balance = Balance - amount;
			this.Balance = Balance;
			System.out.println(amount + " Amount has been deducted from Account: " + this.AccountNumber + ", New Balance is: " + Balance);
		}
		else 
			System.out.println("Not Enough Balance in Account: " + this.AccountNumber);
	}
	
}
