package Question3;
import java.util.*;

class Bank {
	List<Account> list = new ArrayList<Account>();
	static Integer i = 0;
	public void AddAccount() {
		synchronized(this.i){
			int accountNumber = list.size();
			Account account = new Account();
			account.AccountNumber = accountNumber;
			System.out.println("Account with Account Number " + accountNumber + " has been created");
			list.add(account);
		}
	}
}