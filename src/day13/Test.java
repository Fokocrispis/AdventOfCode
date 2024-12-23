package day13;

public class Test {
	
	public Test() {
		Reader reader = new Reader();
		reader.readFromFile();
		reader.printAllResults();
	}

	public static void main(String[] args) {
		new Test();
	}
}
