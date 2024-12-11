package day04;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Processor {
	
	private List<char[]> dataSet;
	private int rowSize;
	private int columnSize;
	private int totalCount =0;
	Pattern mulPattern = Pattern.compile("XMAS");
	
	List<Point> locationA = new ArrayList<>();
	
	public Processor(List<char[]> dataSet) {
		this.dataSet = dataSet;
		rowSize = dataSet.size();
		columnSize = dataSet.get(Test.INITIAL).length;
		totalCount += verticalAndHorizontalCheck();
		totalCount += diagonalCheck();
		System.out.println("X-MAS count: " + pointIterator(locationA));
	}
	
	public int getCount() {
		return totalCount;
	}
	
	private int pointIterator(List<Point> xLocations) {
	    int count = 0;

	    for (Point p : xLocations) {
	        if (isValidXMAS(p)) {
	            count++;
	            System.out.println("X-MAS Found at: (" + p.x + ", " + p.y + ")");
	        }
	    }

	    return count;
	}

	private boolean isValidXMAS(Point p) {
	    int row = p.y;
	    int col = p.x;
	    if (row <= 0 || row >= rowSize - 1 || col <= 0 || col >= columnSize - 1) {
	        return false;
	    }
	    StringBuilder diagonal1 = new StringBuilder();
	    diagonal1.append(dataSet.get(row - 1)[col - 1]); 
	    diagonal1.append(dataSet.get(row)[col]);      
	    diagonal1.append(dataSet.get(row + 1)[col + 1]);


	    StringBuilder diagonal2 = new StringBuilder();
	    diagonal2.append(dataSet.get(row + 1)[col - 1]); 
	    diagonal2.append(dataSet.get(row)[col]);        
	    diagonal2.append(dataSet.get(row - 1)[col + 1]); 

	    return (diagonal1.toString().equals("MAS") || diagonal1.toString().equals("SAM")) &&
	           (diagonal2.toString().equals("MAS") || diagonal2.toString().equals("SAM"));
	}
	
	private int verticalAndHorizontalCheck() {
		int counter = 0;

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < columnSize; col++) {
				if (dataSet.get(row)[col] == 'X' || dataSet.get(row)[col] == 'S') {
					if (row <= rowSize - 4) {
						StringBuilder buffer = new StringBuilder();
						for (int k = 0; k < 4; k++) {
							buffer.append(dataSet.get(row + k)[col]);
						}
						if (buffer.toString().equals("XMAS") || buffer.toString().equals("SAMX")) {
							counter++;
							System.out.println("Vertical count: " + counter);
						}
					}

					if (col <= columnSize - 4) {
						StringBuilder buffer = new StringBuilder();
						for (int k = 0; k < 4; k++) {
							buffer.append(dataSet.get(row)[col + k]);
						}
						if (buffer.toString().equals("XMAS") || buffer.toString().equals("SAMX")) {
							counter++;
							System.out.println("Horizontal count: " + counter);
						}
					}
				}
				else if(dataSet.get(row)[col] == 'A') {
					locationA.add(new Point(col, row));
				}
			}
		}
		return counter;
	}


	private int diagonalCheck() {
	    int counter = 0;

	    // Forward Diagonals 
	    for (int row = 0; row <= rowSize - 4; row++) {
	        for (int col = 0; col <= columnSize - 4; col++) {
	            StringBuilder buffer = new StringBuilder();
	            for (int k = 0; k < 4; k++) {
	                buffer.append(dataSet.get(row + k)[col + k]);
	            }
	            if (buffer.toString().equals("XMAS") || buffer.toString().equals("SAMX")) {
	                counter++;
	                System.out.println("F-Diagonal count: " + counter);
	            }
	        }
	    }

	    // Backward Diagonals
	    for (int row = 3; row < rowSize; row++) {
	        for (int col = 0; col <= columnSize - 4; col++) {
	            StringBuilder buffer = new StringBuilder();
	            for (int k = 0; k < 4; k++) {
	                buffer.append(dataSet.get(row - k)[col + k]);
	            }
	            if (buffer.toString().equals("XMAS") || buffer.toString().equals("SAMX")) {
	                counter++;
	                System.out.println("B-Diagonal count: " + counter);
	            }
	        }
	    }

	    return counter;
	}

}
