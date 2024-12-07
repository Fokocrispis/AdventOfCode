package day7;

import java.util.List;

public class Processor {
	private static int TEST = 0;
	
	private List<Report> data;
	private int count, count2;
	private long sum, sum2;
	
	public Processor(List<Report> data) {
		this.data = data;
		
		System.out.println("\n--------------------------- Results --------------------------- \n");

		for(Report report: data) {
			boolean isValid = report.evaluate(report.getCalibrator(), report.getOperators());
			System.out.print(report.getCalibrator() + ": " + report.getOperands());
			System.out.printf(" | Report is valid: %s %n",  isValid);
			if(isValid) {
				sum += report.getCalibrator();
				count++;
			}
		}
		
		System.out.println("\n--------------------------- Concatenated results --------------------------- \n");
		
		for(Report report: data) {
			boolean isValid = report.evaluateWithConcatenation(report.getCalibrator(), report.getOperators());
			System.out.print(report.getCalibrator() + ": " + report.getOperands());
			System.out.printf(" | Report is valid with concatenation: %s %n",  isValid);
			if(isValid) {
				sum2 += report.getCalibrator();
				count2++;
			}
		}
	}
	
	public int getCount() {
		return count;
	}
	
	public int getCount2() {
		return count2;
	}
	
	public long getSum() {
		return sum;
	}
	
	public long getSum2() {
		return sum2;
	}
}
