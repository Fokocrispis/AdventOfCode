package day03;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Test {
	private List<Point> data = new ArrayList<>();
	private Reader reader = new Reader();
	
	public Test() {
		data = reader.readAndCalculateSum();
		print();
		}
	
	public static void main(String[] arg) {
		new Test();
	}
	
	private void print() {
		int sumOfProducts=0;
		for(Point p: data) {
			int product = p.x * p.y;
			sumOfProducts += product;
			System.out.println("(x: " + p.x + ") * (y: " + p.y + ")" + " = " + product + " -> Total of: " + sumOfProducts);
		}
		
		System.out.println("Count is: " + reader.getCount());
	}

}
