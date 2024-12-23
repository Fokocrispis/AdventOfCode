package day14;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Map {
	
	private List<List<Integer>> map = new ArrayList<>();
	private Point quadrant;
	
	public Map(Point dimensions) {
		for(int i=0; i<dimensions.y; i++) {
			List rows = new ArrayList<>();
			map.add(rows);
			for(int j = 0; j<dimensions.x; j++) {
				map.get(i).add(0);
			}
		}
		quadrant = new Point(map.get(0).size()/2 ,map.size()/2);
//		System.out.println("Quadrant size: " + quadrant);
	}
	
	public List<List<Integer>> getMap(){
		return map;
	}
	
	public void setRobot(Robot robot) {
		Point pos = robot.getPosition();
		int value = map.get(pos.y).get(pos.x) + 1;
		map.get(pos.y).remove(pos.x);
		map.get(pos.y).add(pos.x, value);
	}
	
	public void print() {
		for(int i=0; i<50; i++) {
			System.out.println("");
		}
		
		for(List<Integer> l : map) {
			for(Integer i : l) {
				System.out.print(i);
			}
			System.out.println("");
		}
	}
	
	public void reset() {
		for (List<Integer> l : map) {
			int size = l.size();
			l.clear();
			for (int j = 0; j < size; j++) {
				l.add(0);
			}
		}
	}
	
	public int sumQuadrants() {
		int s1 = 0,s2 = 0,s3 = 0,s4 = 0;
		
		for(int i=0; i<quadrant.y; i++) {
			for(int j = 0; j<quadrant.x; j++) {
				s1 += map.get(i).get(j);
			}
		}
		
		for(int i=0; i<quadrant.y; i++) {
			for(int j = quadrant.x+1; j<map.get(0).size(); j++) {
				s2 += map.get(i).get(j);
			}
		}
		
		for(int i=quadrant.y+1; i<map.size(); i++) {
			for(int j = 0; j<quadrant.x; j++) {
				s3 += map.get(i).get(j);
			}
		}
		
		for(int i=quadrant.y+1; i<map.size(); i++) {
			for(int j = quadrant.x+1; j<map.get(0).size(); j++) {
				s4 += map.get(i).get(j);
			}
		}
		return s1*s2*s3*s4;
	}
}
