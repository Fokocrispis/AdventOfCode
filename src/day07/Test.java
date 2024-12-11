package day07;

import java.util.ArrayList;
import java.util.List;

import day06.Map;

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
		
		System.out.println("\nTotal number of valid reports with concatenation: " + processor.getCount2());
		System.out.println("Sum of valid results with concatenation: " + processor.getSum2());
	}
}
