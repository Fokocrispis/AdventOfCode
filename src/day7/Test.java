package day7;

import java.util.ArrayList;
import java.util.List;

import day6.Map;


public class Test{
	
	private List<Report> data;
	private Processor processor;

	private Test(){
		data = new Reader().readReportsFromFile();
		processor = new Processor(data);
		print();
		}
	
	public static void main(String[] arg){
		new Test();
	}
	
	private void print() {
		System.out.println("\nTotal number of valid reports: " + processor.getCount());
		System.out.println("Sum of valid results: " + processor.getSum());
	}
}
