package day8;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public class Processor {
    private final Point mapSize;
    private int count=0;
    
    private Map<Character, List<Point>> map;
    private List<Point> antinodes = new ArrayList<>();

    public Processor(Map<Character, List<Point>> map, Point mapSize) {
        this.map = map;
        this.mapSize = mapSize;

        map.forEach((key, value) -> {
            System.out.println("Character: " + key);
            System.out.println("Positions: " + value);

            for (int i = 0; i < value.size(); i++) {
                Point current = value.get(i);
                for (int j = i + 1; j < value.size(); j++) {
                    Point other = value.get(j);
                    boolean high = isAntiNodeHigh(current, other);
                    boolean low = isAntiNodeLow(current, other);
                    System.out.println("Comparing " + current + " and " + other + ": isAntiNodeHigh = " + high);
                    System.out.println("Comparing " + current + " and " + other + ": isAntiNodeLow = " + low);
                    if (high) count++;
                    if (low) count++;
                }
            }
        });

        System.out.println("Final count: " + count);
    }

    private boolean isAntiNodeHigh(Point x, Point y) {
        Point distance = distance(x,y);
        int antiX = (int) (x.getX() - distance.getX());
        int antiY = (int) (x.getY() - distance.getY());
        if (isValidPoint(antiX, antiY) && !antinodes.contains(new Point(antiX, antiY))) {
            antinodes.add(new Point(antiX, antiY));
            return true;
        }
        return false;
    }
    
    private boolean isAntiNodeLow(Point x, Point y) {
    	 Point distance = distance(y,x);
         int antiX = (int) (y.getX() - distance.getX());
         int antiY = (int) (y.getY() - distance.getY());
         if(isValidPoint(antiX, antiY)
          		&&!antinodes.contains(new Point(antiX, antiY))) {
         	antinodes.add(new Point(antiX, antiY));
         	return true;
         }
         else
         return false;
    }
    
    private boolean isValidPoint(int x, int y) {
        return x >= 0 && x < mapSize.x && y >= 0 && y < mapSize.y;
    }

    private Point distance(Point a, Point b) {
        int distanceX = b.x - a.x;
        int distanceY = b.y - a.y;
        return new Point(distanceX, distanceY);
    }

    public Map<Character, List<Point>> getMap() {
        return map;
    }

    public Point getMapSize() {
        return mapSize;
    }
}
