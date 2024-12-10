package day10;

import java.awt.Point;
import java.util.List;

public class Test {
	
	private List<List<Integer>> map;
	private Reader reader;
	private Hiker hiker;
	private int sum=0;
	
	private Test(){
		reader = new Reader();
		map = reader.readPointsFromFile();
		print();
		hiker = new Hiker(map, reader.getZeros().get(0));
		
		for(Point p: reader.getZeros()) {
			sum += new Hiker(map, p).calculateTrailheadScore();
		}
		System.out.println("\nSum: " + sum);
		sum = 0;
		
		for(Point p: reader.getZeros()) {
			sum += new Hiker(map, p).calculateTrailheadVarianceScore();
		}
		System.out.println("Variants sum: " + sum);
	}
	
	public static void main(String[] arg){
		new Test();
	}
	
	private void print() {
		for(List<Integer> l: map) {
			for(Integer i: l) {
				System.out.print(i);
			}
			System.out.println();
		}
		
		System.out.println("Columns: " + reader.getColumns() + ", Rows: " + reader.getRows());
	}
}