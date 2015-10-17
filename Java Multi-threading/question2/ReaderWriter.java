package question2;

public class ReaderWriter{
	public static void main(String args[]){
		Book book = new Book();
		Reader[] readers = new Reader[20];
		for(int i=0; i<20; i++){
			readers[i] = new Reader(book,i+1);
			readers[i].start();
		}
		Writer writer = new Writer(book);
		writer.start();
	}
}
