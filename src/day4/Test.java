package day4;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public static int INITIAL = 0;
	private List<char[]> list;
	private Reader reader = new Reader();
	
	public Test() {
		list = reader.readAndCount();
		print();
		}
	
	public static void main(String[] arg) {
		new Test();
	}
	
	private void print() {
		for(char[] array: list) {
			for(char c: array)
				System.out.print(c);
			System.out.println("");
		}
		
		System.out.println("\nNumber of lines: " + list.size());
		System.out.println("Number of characters per line: " + list.get(INITIAL).length);
		System.out.println("\nFinal answer: " + new Processor(list).getCount());
	}

}
