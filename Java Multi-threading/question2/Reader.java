package question2;

import java.util.Random;

public class Reader extends Thread{
	private int readerNumber;
	public static int activeReaderCount = 0;
	private int initVersion =-1 ;
	private Book book;
	public Reader(Book book, int x){
		readerNumber = x;
		this.book = book;
	}

	public void run(){
		Random rand = new Random();
		try{
			Thread.sleep(rand.nextInt(30000)); // wait for 0-30 secs 
		}
		catch(InterruptedException e){
			// ignore
		}
		synchronized (book) {
				//locking the book to get correct activeReaderCount
			initVersion = book.getContent();
			activeReaderCount++;
			System.out.println("Reader " + readerNumber + " is Entering, Number of Readers: " + activeReaderCount);
			System.out.println("Reader " + readerNumber + " is Reading Book Version: " + initVersion);
		}
		try{
			
			Thread.sleep(5000); // read for 5 seconds
		}
		catch(InterruptedException e){
			// ignore
		}
		synchronized (book) {
			activeReaderCount--;
			System.out.println("Reader " + readerNumber + " is Leaving, Number of Readers: " + activeReaderCount);
			book.notify();
		}
		while (book.getContent() == initVersion) {
			try {
				Thread.sleep(rand.nextInt(50000));
				//book.wait();
			}
			catch(InterruptedException f){

			}
		}
		try{
			Thread.sleep(rand.nextInt(30000)); // wait for 0-30 secs 
		}
		catch(InterruptedException e){
			// ignore
		}
		synchronized (book) {
			initVersion = book.getContent();
			activeReaderCount++;
			System.out.println("Reader " + readerNumber + " is Entering, Number of Readers: " + activeReaderCount);
			System.out.println("Reader " + readerNumber + " is Reading Book Version: " + initVersion);
		}
		try{
			Thread.sleep(5000); // read for 5 seconds
			
		}
		catch(InterruptedException e){
			// ignore
		}
		synchronized (book) {
			activeReaderCount--;
			System.out.println("Reader " + readerNumber + " is Leaving, Number of Readers: " + activeReaderCount);
			book.notify();
		}
	}
}
