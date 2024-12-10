package day10;

import java.awt.Point;
import java.util.List;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hiker {

    private static final int INITIAL = 0;

    private Point currentPosition;
    private int col, rows;
    private List<List<Integer>> map;

    public Hiker(List<List<Integer>> map, Point position) {
        this.map = map;
        this.currentPosition = position;
        this.rows = map.size();
        this.col = map.get(INITIAL).size();
    }

    public int calculateTrailheadScore() {
        return checkAround(currentPosition, new HashSet<>());
    }

    private int checkAround(Point pos, Set<Point> visited) {
        if (visited.contains(pos)) {
            return 0; 
        }
        visited.add(pos); 

        int currentValue = map.get(pos.y).get(pos.x);
        if (currentValue == 9) {
            return 1; 
        }

        int score = 0;
        Point[] directions = {
            new Point(pos.x, pos.y - 1), 
            new Point(pos.x, pos.y + 1), 
            new Point(pos.x - 1, pos.y), 
            new Point(pos.x + 1, pos.y) 
        };

        for (Point neighbor : directions) {
            if (isValid(neighbor, currentValue)) {
                score += checkAround(neighbor, visited);
            }
        }

        return score;
    }

    private boolean isValid(Point point, int currentValue) {
        if (point.x < 0 || point.x >= col || point.y < 0 || point.y >= rows) {
            return false;
        }
        return map.get(point.y).get(point.x) - currentValue == 1;
    }
}

