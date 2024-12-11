package day05;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	
	private static int NULL = 0;
	
	private SafetyManual manual;
	private Processor processor;
	private ArrayList<Node> graph = new ArrayList<>();
	
	public Test() {
		manual = new Reader().readPointsFromFile();
		Sort.bubbleSort(manual.getOrdering());
		print();
		
		processor = new Processor(manual);
		System.out.println("\nSum of middle values: " + processor.getSum());
		System.out.println("\nCorrected middle values: " + processor.getInvalidSum());
	}
	
	public static void main(String[] arg) {
		new Test();
	}
	
	private void print() {
		for (Point p : manual.getOrdering()) {
			System.out.println("x: " + p.x + ", y: " + p.y);
		}
		System.out.println("");
		for (List<Integer> l : manual.getUpdate()) {
			System.out.println(l);
		}
		System.out.println("");
	}
}
