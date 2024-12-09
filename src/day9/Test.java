package day9;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public class Test {
	
	private Reader reader;
	private Disk disk;
	
	private Test(){
	    reader = new Reader();
		disk = new Disk(reader.readFromFile());
//		print();
		disk.orderDisk();
//		print();
	}
	
	public static void main(String[] arg){
		new Test();
	}
	
	private void print() {
		for(String s: disk.getDisk()) {
			System.out.print(s);
		}
		
		System.out.println();
		
		for(String s: disk.getProcessedDisk()) {
			System.out.print(s);
		}
	}
}