package day7;

import java.util.ArrayList;
import java.util.List;

public class Report {
	private long calibrator;
	private int operators;
	private Boolean isValid=null;
	
	private List<Long> operands = new ArrayList<>();
	private List<Report> variants = new ArrayList<>();

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
		if(evaluate(substraction, index-1)) {
			return true;
		}
		return false;
	}
	
	public boolean evaluateWithConcatenation(long result, final int index) {
		if (index == 0) {
			isValid = true;
			return result == operands.get(index);
		}
		double division = (double) (result) / operands.get(index);
		if(isInteger(division) && evaluateWithConcatenation((long)division, index-1)) {
			return true;
		}
		long substraction = result - this.operands.get(index);
		if(evaluateWithConcatenation(substraction, index-1)) {
			return true;
		}
		
		if(isConcatenation(result, operands.get(index))) {
			final long splitted = deconcatenate(result, operands.get(index));
			return evaluateWithConcatenation(splitted, index-1);
		}
		
		return false;
	}
	
	public boolean isConcatenation(long result, long operand) {
		String stringA = Long.toString(result);
		String stringB = Long.toString(operand);
		return result>0
			&&stringA.length()>1
			&&stringA.endsWith(stringB)
			&&stringA.length()>stringB.length();
	}
	
	public long deconcatenate(long result, long operand) {
		String newString = Long.toString(result).substring(0, (Long.toString(result).length())- Long.toString(operand).length());
		return Long.parseLong(newString);
	}
	
	public int getOperators() {
		return operators;
	}
	
	public List<Long> getOperands(){
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
