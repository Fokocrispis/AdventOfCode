package day11;

import java.util.ArrayList;
import java.util.List;

public class Processor {
	
	private static int MULT = 2024;
	private static int LOOPS = 25;
	
	private List<Stone> stones;
	private List<Stone> processedStones = new ArrayList<>();
	
	public Processor(List<Stone> stones)  {
		this.stones = stones;
	}
	
	public void cycleProcess() {
			processedStones.clear(); 
			for (Stone s : stones) {
				process(s);
			}
			stones = new ArrayList<>(processedStones);
	}
	
	private void process(Stone stone) {
		switch(stone.getType()) {
		case Stone.EVEN:
			Stone s1 =  new Stone((long) stone.split().x);
			Stone s2 =  new Stone((long) stone.split().y);
			
			int index = stones.indexOf(stone);
			processedStones.add(s1);
			processedStones.add(s2);

			break;
		case Stone.ODD:
			Long value = stone.getValue();
			processedStones.add(new Stone(value*MULT));
			break;
		case Stone.ZERO:
			processedStones.add(new Stone(1));
			break;
		default:
			break;
		}
	}
	
	public List<Stone> getStones(){
		return stones;
	}
	
	public List<Stone> getProcessedStones(){
		return processedStones;
	}
}
