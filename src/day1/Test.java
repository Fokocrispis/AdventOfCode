package day1;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	List<List<Integer>> dataEntry = new ArrayList<List<Integer>>();
	Sort sorting = new Sort();
	Processor processor = new Processor();
	
	public Test() {
		dataEntry = new Reader().readPointsFromFile();
		print();
	}
	
	public static void main(String[] arg) {
		new Test();
	}
	
	private void print() {
		processor.sort(dataEntry);
		
	    List<Integer> left = dataEntry.get(LEFT);
	    List<Integer> right = dataEntry.get(RIGHT);

	    for (int i = 0; i < left.size(); i++) {
	        int distance = right.get(i) - left.get(i);
	        System.out.println(i + ": " + right.get(i) + " - " + left.get(i) + " = " + distance);
	    }
	    
	    System.out.println("\nTotal Distance: " + processor.distanceCalculator(dataEntry));
	    
	    System.out.println("Similarity: " + processor.similarityCalculator(dataEntry));
	}
}
