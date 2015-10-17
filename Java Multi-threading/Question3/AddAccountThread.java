package Question3;
import java.util.*;

class AddAccountThread extends Thread {
	Bank bank;
	public AddAccountThread(Bank bank) {
		this.bank = bank;
	}

	public void run() {
			bank.AddAccount();
	}
}