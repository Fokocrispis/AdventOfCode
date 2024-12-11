package day09;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public class Test {
	
	private Reader reader;
	private Disk disk;
	
	private Test(){
	    reader = new Reader();
		disk = new Disk(reader.readFromFile());
		disk.orderDiskRefined();
		print();
		System.out.println("\n\n\nChecksum: " + disk.checkSum());
	}
	
	public static void main(String[] arg){
		new Test();
	}
	
	private void print() {
		
		for(String s: disk.getProcessedDisk()) {
			System.out.print(s);
		}
	}
}