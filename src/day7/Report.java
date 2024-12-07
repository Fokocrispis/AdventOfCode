package day7;

import java.util.ArrayList;
import java.util.List;

public class Report {
	private long calibrator;
	private List<Long> operands = new ArrayList<>();
	private int operators;
	private Boolean isValid=null;

	public Report(long calibrator, List<Long> operand) {
		this.calibrator = calibrator;
		this.operands = operand;
		this.operators = operand.size()-1;
	}
	
	public boolean evaluate(long result, final int index) {
		if (index == 0) {
			isValid = true;
			return result == operands.get(index);
		}
	
		double division = (double) (result) / operands.get(index);
		
		if(isInteger(division) && evaluate((long)division, index-1)) {
			return true;
		}
		long substraction = result - this.operands.get(index);
		return evaluate(substraction, index-1);
	}
	
	public int getOperators() {
		return operators;
	}
	
	public List<Long> getOperand(){
		return operands;
	}
	
	public void setOperand(List<Long> operand) {
		this.operands = operand;
	}
	
	public long getCalibrator() {
		return calibrator;
	}
	
	public void setCalibrator(long calibrator) {
		this.calibrator = calibrator;
	}
	
	private boolean isInteger(double value) {
		return value%1==0;
	}
}
