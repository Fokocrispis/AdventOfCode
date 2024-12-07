package day2;

import java.util.ArrayList;
import java.util.List;

public class Test {
	private List<Report> data = new ArrayList<>();
	
	public Test() {
		data = new Reader().readPointsFromFile();
		print();
	}
	
	public static void main(String[] arg) {
		new Test();
	}
	
	private void print() {
		int counter = 0;
		int counterDampened = 0;
		for (Report report : data) {
			report.print();
			if(report.isValid())
				counter++;
		}
		System.out.println("\n\nReports valid after dampening: \n");
		for (Report report : data) {
			if (report.isValidWithDampener()) {
					counterDampened++;
					report.print();
				}
		}
		System.out.println("\nNumber of valid reports: " + counter);
		System.out.println("Number of valid dampened reports: " + (counterDampened + counter));
	}
}
