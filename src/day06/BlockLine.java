package day06;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import day06.Map.Direction;

public class BlockLine {

    private List<Point> blockLine = new ArrayList<>();
    private Direction direction;
    private Point startPoint;
    private List<Point> blocks;
    private int col, rows;

    public BlockLine(Point startPoint, int direction, List<Point> blocks, Reader reader) {
        this.startPoint = startPoint;
        this.blocks = blocks;
        col = reader.getCol();
		rows = reader.getRow();

        switch (direction) {
            case 0:
                this.direction = Direction.UP;
                break;
            case 1:
                this.direction = Direction.RIGHT;
                break;
            case 2:
                this.direction = Direction.DOWN;
                break;
            case 3:
                this.direction = Direction.LEFT;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        populateBlockLine();
    }

    private void populateBlockLine() {
        Point current = getNextPoint(startPoint);
        while (current != null && !isPointInBlocks(current)) {
            blockLine.add(new Point(current));
            current = getNextPoint(current);
        }
    }

    private Point getNextPoint(Point current) {
        switch (direction) {
            case UP:
            	if(current.y + 1<rows) {
            		return new Point(current.x, current.y + 1);
            	}
            	else
                return null; 
            case DOWN:
            	if(current.y - 1>=0) {
            		return new Point(current.x, current.y - 1);
            	}
            	else
                return null; 
            case LEFT:
            	if(current.x + 1<col) {
            		return new Point(current.x+1, current.y);
            	}
            	else
                return null; 
            case RIGHT:
            	if(current.x - 1>=0) {
            		return new Point(current.x-1, current.y);
            	}
            	else
                return null; 
            default:
                return null;
        }
    }

    private boolean isPointInBlocks(Point point) {
        return blocks.contains(point);
    }

    public List<Point> getBlockLine() {
        return blockLine;
    }
}

