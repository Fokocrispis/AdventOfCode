package day8;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import java.awt.Point;
import java.util.*;

public class Processor {
    private final Point mapSize;
    private int count = 0;

    private Map<Character, List<Point>> map;
    private Set<Point> antinodes = new HashSet<>();

    public Processor(Map<Character, List<Point>> map, Point mapSize) {
        this.map = map;
        this.mapSize = mapSize;

        map.forEach((key, value) -> {
            System.out.println("Character: " + key);
            System.out.println("Positions: " + value);
            
            if (value.size() > 1) {
                antinodes.addAll(value);

                for (int i = 0; i < value.size(); i++) {
                    Point current = value.get(i);
                    for (int j = i + 1; j < value.size(); j++) {
                        Point other = value.get(j);

                        boolean high = isAntiNodeHigh(current, other);
                        boolean low = isAntiNodeLow(current, other);

                        System.out.println("Comparing " + current + " and " + other + ": isAntiNodeHigh = " + high);
                        System.out.println("Comparing " + current + " and " + other + ": isAntiNodeLow = " + low);
                    }
                }
            }
        });

        count = antinodes.size();
        System.out.println("Final count of unique antinodes: " + count);
    }

    private boolean isAntiNodeHigh(Point x, Point y) {
        Point distance = distance(x, y);
        int antiX = (int) (x.getX() - distance.getX());
        int antiY = (int) (x.getY() - distance.getY());

        boolean added = false;

        while (isValidPoint(antiX, antiY)) {
            Point newAntinode = new Point(antiX, antiY);
            if (!antinodes.contains(newAntinode)) {
                antinodes.add(newAntinode);
                added = true;
            }
            antiX -= distance.x;
            antiY -= distance.y;
        }

        return added;
    }

    private boolean isAntiNodeLow(Point x, Point y) {
        Point distance = distance(y, x);
        int antiX = (int) (y.getX() - distance.getX());
        int antiY = (int) (y.getY() - distance.getY());

        boolean added = false;

        while (isValidPoint(antiX, antiY)) {
            Point newAntinode = new Point(antiX, antiY);
            if (!antinodes.contains(newAntinode)) {
                antinodes.add(newAntinode);
                added = true;
            }
            antiX -= distance.x;
            antiY -= distance.y;
        }

        return added;
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
