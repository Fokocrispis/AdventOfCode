package day05;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SafetyManual {
	
	private List<Point> ordering = new ArrayList<>();
	private List<List<Integer>> update = new ArrayList<>();
	
	public SafetyManual(List<Point> ordering, List<List<Integer>> update) {
		this.ordering = ordering;
		this.update = update;
	}
	
	public List<Point> getOrdering(){
		return ordering;
	}
	
	public List<List<Integer>> getUpdate(){
		return update;
	}
}
