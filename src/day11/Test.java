package day11;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Test {
	private static int LOOPS = 25;
	
	private List<Long> data;
	private List<Stone> stones = new ArrayList<>();
	private Processor processor;

	private Test(){
		data = new Reader().readPointsFromFile();
		populate();
		processor = new Processor(stones);
		
		for(int i=1; i<LOOPS+1; i++) {
			processor.cycleProcess();
			print(i);
		}
		System.out.println("\nNumber of stones: " + processor.getStones().size());
	}
	
	public static void main(String[] arg){
		new Test();
	}
	
	private void populate() {
		for(Long l: data) {
			Stone s = new Stone(l);
			stones.add(s);
		}
	}
	
	private void print(int loop) {
		System.out.println("\nBlinck: " + loop);
		for(Stone s: processor.getStones()) {
			System.out.print(s.getValue() + " ");
		}
	}
}
