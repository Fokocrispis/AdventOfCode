package day6;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import day6.Map.Direction;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Guard {

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    private Point position;
    private Direction direction;
    private List<List<Point>> corners = new ArrayList<>();

    public Guard(Point position, Direction direction) {
        this.position = position;
        this.direction = direction;
        for (int i = 0; i < 4; i++) {
            corners.add(new ArrayList<>());
        }
    }

    public void setCorner(Point corner, int index) {
        if (index >= 0 && index < 4) {
            corners.get(index).add(corner);
        } else {
            throw new IllegalArgumentException("Invalid direction index. Must be 0-3.");
        }
    }

    public List<List<Point>> getCorners() {
        return corners;
    }

    public List<Point> getCornersForDirection(int index) {
        if (index >= 0 && index < 4) {
            return corners.get(index);
        } else {
            throw new IllegalArgumentException("Invalid direction index. Must be 0-3.");
        }
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        this.position = new Point(position.x + x, position.y + y);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
