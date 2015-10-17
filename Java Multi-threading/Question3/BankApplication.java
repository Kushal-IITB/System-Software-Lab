package Question3;
import java.util.*;

public class BankApplication {

	public static void main(String[] args) throws InterruptedException {
		Bank bank = new Bank();
		Thread temp = new DepositThread(bank,3,50);
		temp.start();
		Thread[] addThreads = new Thread[20];
		for(int i=0;i<20;i++){
			addThreads[i] = new AddAccountThread(bank);
			addThreads[i].start();
		}
		Thread[] depositThreads = new DepositThread[20];
		for(int i=0;i<20;i++){
			depositThreads[i] = new DepositThread(bank,i,25);
			depositThreads[i].start();
		}
		Thread[] withdrawThreads = new WithdrawlThread[20];
		for(int i=0;i<20;i++){
			withdrawThreads[i] = new WithdrawlThread(bank,i,22);
			withdrawThreads[i].start();
		}
	}

}