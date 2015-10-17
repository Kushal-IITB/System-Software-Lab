package question2;

public class Writer extends Thread{
	private Book book;
	
	public Writer(Book b){
		book = b;
	}
	
	private void writeBook(){
		synchronized (book) {
			while(Reader.activeReaderCount!=0) {
				try {
					book.wait();			//wait for all readers to leave
				}
				catch (InterruptedException f) {
				}
			}
			System.out.println("Writer is entering");
			try{
				Thread.sleep(10000); // write for 10 seconds
			}
			catch(InterruptedException e){
				// ignore
			}
			
				book.setContent(book.getContent()+1);
				System.out.println("Writer is leaving");
				book.notifyAll();
		}
	}
	
	public void run(){
		for(int i=0; i<5; i++){
			writeBook();
			try{
				Thread.sleep(2000); // think for 2 seconds
			}
			catch(InterruptedException e){
				// ignore
			}
		}
	}
}
