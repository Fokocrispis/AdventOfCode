package day6;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Map {
	private static int WAIT = 0;
	private char[][] map;
	private Guard guard;
	private int col, rows;
	private int count=1;
	private char path = 'U';
	private Thread thread;
	private List<List<BlockLine>> pathways = new ArrayList<>();
	private int sumOf0=0;
	private Point initialPosition;
	private Reader reader;
	private List<Point> listOf0 = new ArrayList<>();
	private int cornerOrder=0;

	public Map(Reader reader) throws InterruptedException {
		this.reader= reader;
		this.map = reader.readMapFromFile();
		col = reader.getCol();
		rows = reader.getRow();
		guard = new Guard(findGuard(), Direction.UP);
		initialPosition = guard.getPosition();
		System.out.println(guard.getPosition());
		System.out.println("Columns: " + col + ", Rows: " + rows);
		loop();
		
		try {
            System.out.println("Waiting for the thread to finish...");
            thread.join(); 
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("Main thread is resuming.");
        
		int counter = 0;
		for (List<Point> corners : guard.getCorners()) {
		    System.out.println("\nDirection " + counter + ":");
		    pathways.add(new ArrayList<>());
		    counter++;
		}
		guard.setPosition(initialPosition);
		guard.setDirection(Direction.UP);
		
		loop();
		try {
            System.out.println("Waiting for the thread to finish...");
            thread.join(); 
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Sum of 0: " + sumOf0);
	}
	
	private void loop() {
		thread = new Thread(() -> {
			Point lastPosition = guard.getPosition();
			while (guard.getPosition().x>0&&guard.getPosition().y>0&&
				   guard.getPosition().x<col-1&&guard.getPosition().y<rows-1) {

				clearConsole();
				Point p = checkCollision();
				
				guard.setPosition(p.x, p.y);
				map[lastPosition.y][lastPosition.x] = 'X';//path;
				
				if(map[guard.getPosition().y][guard.getPosition().x] == '.') {
					map[guard.getPosition().y][guard.getPosition().x] = 'X';
					count++;
				}
				
				map[guard.getPosition().y][guard.getPosition().x] = guard.getDirection().getAsciiValue();
				lastPosition = new Point(guard.getPosition().x, guard.getPosition().y);
				print();
				try {
					Thread.sleep(WAIT);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			clearConsole();
			print();
			System.out.println("\nNumber of unique locations: " + count);
		});
		thread.start();
	}
	
	private Point checkCollision() {
		switch (guard.getDirection()) {
		case UP:
			if(map[guard.getPosition().y-1][guard.getPosition().x] == '#') {
				guard.setDirection(Direction.RIGHT);
//				guard.setCorner(new Point(guard.getPosition().x, guard.getPosition().y-1), guard.UP);
//				pathways.get(guard.UP).add(new BlockLine(
//											new Point(guard.getPosition().x, guard.getPosition().y-1),
//										  	guard.UP, reader.getBlocks(), reader));
				path = 'R';
				return new Point(1, 0);
			}
			else {
				if(!pathways.isEmpty()) {
					for(BlockLine bl : pathways.get(Guard.RIGHT)) {
						for(Point p: bl.getBlockLine()) {
							if (guard.getPosition().equals(p)) {
								listOf0.add(new Point(p.x, p.y-1));
							    sumOf0++;
							}
						}
					}
				}
				return new Point(0, -1);
			}
		case DOWN:
			if(map[guard.getPosition().y+1][guard.getPosition().x] == '#') {
				guard.setDirection(Direction.LEFT);
//				guard.setCorner(new Point(guard.getPosition().x, guard.getPosition().y+1), guard.DOWN);
//				path = 'L';
//				pathways.get(guard.DOWN).add(new BlockLine(
//						new Point(guard.getPosition().x, guard.getPosition().y+1),
//					  	guard.DOWN, reader.getBlocks(), reader));
				return new Point(-1, 0);
			}
			else {
				if(!pathways.isEmpty()) {
					for(BlockLine bl : pathways.get(Guard.LEFT)) {
						for(Point p: bl.getBlockLine()) {
							if (guard.getPosition().equals(p)) {
							    sumOf0++;
							    listOf0.add(new Point(p.x, p.y+1));
							}
						}
					}
				}
				return new Point(0, 1);
			}
		case LEFT:
			if(map[guard.getPosition().y][guard.getPosition().x-1] == '#') {
				guard.setDirection(Direction.UP);
//				guard.setCorner(new Point(guard.getPosition().x-1, guard.getPosition().y), guard.LEFT);
//				path = 'U';
//				pathways.get(guard.LEFT).add(new BlockLine(
//						new Point(guard.getPosition().x-1, guard.getPosition().y),
//					  	guard.LEFT, reader.getBlocks(), reader));
				return new Point(0, -1);
			}
			else {
				if(!pathways.isEmpty()) {
					for(BlockLine bl : pathways.get(Guard.UP)) {
						for(Point p: bl.getBlockLine()) {
							if (guard.getPosition().equals(p)) {
							    sumOf0++;
							    listOf0.add(new Point(p.x-1, p.y));
							}
						}
					}
				}
				return new Point(-1, 0);
			}
		case RIGHT:
			if(map[guard.getPosition().y][guard.getPosition().x+1] == '#') {
				guard.setDirection(Direction.DOWN);
//				guard.setCorner(new Point(guard.getPosition().x+1, guard.getPosition().y), guard.RIGHT);
//				path = 'D';
//				pathways.get(guard.RIGHT).add(new BlockLine(
//						new Point(guard.getPosition().x+1, guard.getPosition().y),
//					  	guard.RIGHT, reader.getBlocks(), reader));
				return new Point(0, 1);
			}
			else {
				if(!pathways.isEmpty()) {
					for(BlockLine bl : pathways.get(Guard.DOWN)) {
						for(Point p: bl.getBlockLine()) {
							if (guard.getPosition().equals(p)) {
							    sumOf0++;
							    listOf0.add(new Point(p.x+1, p.y));
							}
						}
					}
				}
				return new Point(1, 0);
			}
		default:
			break;
		}
		return null;
	}

	private Point findGuard() {
		Point p = new Point(0,0);
		for(char[] line : map) {
			for(char c: line) {
				if(c == '^')
					return p;
				p.x +=1;
			}
			p.x=0;
			p.y +=1;
		}
		
		return null;
	}
	
	public char[][] getMap(){
		return map;
	}
	
	public enum Direction{
	    UP('^'),
	    DOWN('v'),
	    LEFT('<'),
	    RIGHT('>');

	    private final char asciiValue;

	    Direction(char asciiValue) {
	        this.asciiValue = asciiValue;
	    }

	    public char getAsciiValue() {
	        return asciiValue;
	    }
	}
	
	private void print() {
		for(char[] line : map) {
			for(char c: line) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void clearConsole() {
	    for (int i = 0; i < 50; i++) {
	        System.out.println();
	    }
	}

}
