package day8;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public class Test {
	
	private Reader reader  = new Reader();
	private Map<Character, List<Point>> map;
	private Point mapSize;
	
	private Test() throws InterruptedException {
		map = reader.readMapFromFile();
		mapSize = new Point(reader.getCol(), reader.getRows());
		print();
		
		Processor processor = new Processor(map, mapSize);
		}
	
	public static void main(String[] arg) throws InterruptedException {
		new Test();
	}
	
	private void print() {
		   System.out.println("Size: " + mapSize);
	}
}
