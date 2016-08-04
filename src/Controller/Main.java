package Controller;

import java.util.Iterator;

public class Main {
	
	public static void main(String[] args) {
		FileIO fileIO = new FileIO(args[0]);
		fileIO.readFile();
		Iterator iterator = fileIO.getActions().iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		fileIO.getBoard().drawBoard();		
	}	
}