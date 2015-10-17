package Question3;
import java.util.*;

class DepositThread extends Thread {
	Bank bank;
	int accountNumber;
	int amount;
	public DepositThread(Bank bank, int accountNumber, int amount) {
		this.bank = bank;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public void run(){
		try {
			synchronized((bank.list).get(accountNumber)) {
				((bank.list).get(accountNumber)).Deposit(amount);
			}
		} 
		catch (IndexOutOfBoundsException e) {
			synchronized(bank.i) {
				try {
					synchronized((bank.list).get(accountNumber)) {
						((bank.list).get(accountNumber)).Deposit(amount);
					}
				}
				catch (IndexOutOfBoundsException f){
					System.out.println("Invalid Account Number");
				}
			}
		}
	}

}