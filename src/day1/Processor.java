package day1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Processor {
	public Processor() {
	}

	public int distanceCalculator(List<List<Integer>> points) {
		int distance = 0;
			for (int i = 0; i < points.get(Test.LEFT).size(); i++) {
				distance += Math.abs(points.get(Test.RIGHT).get(i) - points.get(Test.LEFT).get(i));
			}
		return distance;
	}
	
	public int similarityCalculator(List<List<Integer>> points){
		int similarity = 0;
		
		for (Integer left : points.get(Test.LEFT)) {
			int temp = 0;
			for (Integer right : points.get(Test.RIGHT)) {
				if(left.equals(right)) {
					temp++;
				}	
			}
			similarity += left*temp;
		}
		
		return similarity;
	}
	
	public void sort(List<List<Integer>> points) {
		Sort.quickSort(points.get(Test.LEFT), 0, points.get(Test.LEFT).size()-1);
		Sort.quickSort(points.get(Test.RIGHT), 0, points.get(Test.RIGHT).size()-1);
	}
}
