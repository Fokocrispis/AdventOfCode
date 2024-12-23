package day15;

import java.awt.Point;
import day15.Tile.TileType;

public class Test {
	private static int WAIT=150;
	
	private Reader reader;
	private Guard guard;
	private Tile[][] map;
	
	public Test() {
		reader = new Reader();
		reader.readFromFile();
		map = reader.getMap();
		
		for (Character c : reader.getInstructions()) {
			System.out.print(c);
		}
		System.out.println("\n");
		
		print();
		
		guard = new Guard(reader.getGuard());
		
		new Thread(() -> {
			for (Character c : reader.getInstructions()) {
				updatePosition(c);	
				print();
				try {
					Thread.sleep(WAIT);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.print("sum of all boxes' GPS coordinates: " + sum());
		}).start();
	}
	
	private void updatePosition(char c) {
		Point currentPos = guard.getPosition();
		Point nextPos = guard.update(c);
		boolean canMove = true;

		if (!reader.getWalls().contains(nextPos) 
		    && nextPos.x >= 0 && nextPos.y >= 0 
		    && nextPos.y < map.length 
		    && nextPos.x < map[0].length) {
			
			if(map[nextPos.y][nextPos.x].isBlock()) {
				canMove = updateBlock(nextPos, c);
			}
			
			if(canMove) {
				map[currentPos.y][currentPos.x].setType(Tile.TileType.EMPTY);
				guard.setPosition(nextPos);
				map[nextPos.y][nextPos.x].setType(Tile.TileType.GUARD);
			}
		}
	}
	
	private boolean updateBlock(Point position, char c) {
		Point temp = new Point(0,0);
		boolean canMove = true;
		switch(c) {
			case '^':
				temp = new Point(position.x, position.y-1);
				if(map[temp.y][temp.x].isBlock()) {
					canMove = updateBlock(temp, c);
				}
				break;
			case '>':
				temp = new Point(position.x+1, position.y);
				if(map[temp.y][temp.x].isBlock()) {
					canMove = updateBlock(temp, c);
				}
				break;
			case 'v':
				temp = new Point(position.x, position.y+1);
				if(map[temp.y][temp.x].isBlock()) {
					canMove = updateBlock(temp, c);
				}
				break;
			case '<':
				temp = new Point(position.x-1, position.y);
				if(map[temp.y][temp.x].isBlock()) {
					canMove = updateBlock(temp, c);
				}
				break;
			}
		if(!map[temp.y][temp.x].isWall()) {
			map[temp.y][temp.x].setType(TileType.BLOCK);
		} else {
			return false;
		}
		return canMove;
	}
	
	private void print() {
		for (int i = 0; i < 50; i++) {
			System.out.println("");
		}
		
		for (Tile[] row : map) {
			for (Tile tile : row) {
				System.out.print(tile.getChar());
			}
			System.out.println();
		}
	}
	
	private int sum() {
		int sum=0;
		for (int rows=0; rows<map.length; rows++) {
			for (int columns=0; columns<map[0].length; columns++) {
				if(map[rows][columns].isBlock())
					sum += rows*100 + columns;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		new Test();
	}
}
