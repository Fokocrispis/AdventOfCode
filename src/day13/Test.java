package day13;

import java.io.IOException;

public class Test {
	
	public Test() {
		Reader reader = new Reader();
		reader.readFromFile();
		reader.printAllResults();
//		System.out.println("Final sum: " + reader.getSum());
	}

	public static void main(String[] args) {
		new Test();
	}
}
