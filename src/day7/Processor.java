package day7;

import java.util.List;

public class Processor {
	private static int TEST = 1;
	
	private List<Report> data;
	private int count;
	private long sum = 0;
	
	public Processor(List<Report> data) {
		this.data = data;

		for(Report report: data) {
			boolean isValid = report.evaluate(report.getCalibrator(), report.getOperators());
			System.out.print(report.getCalibrator() + ": " + report.getOperand());
			System.out.printf(" | Report is valid: %s %n",  isValid);
			if(isValid) {
				sum += report.getCalibrator();
				count++;
			}
		}
	}
	
	public int getCount() {
		return count;
	}
	
	public long getSum() {
		return sum;
	}
}
