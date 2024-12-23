package day14;

import java.awt.Point;

public class Robot {
	
	private Point position;
	private Point vector;
	private int rows, columns;
	
	public Robot(Point position, Point vector, Map map) {
		this.position = position;
		this.vector = vector;
		this.rows = map.getMap().size();
		this.columns = map.getMap().get(0).size();
	}
	
	public void update() {
		int newPosX = position.x + vector.x;
		int newPosY = position.y + vector.y;
		if(newPosX<0) {
			newPosX = newPosX + columns;
		}
		else if(newPosX>columns-1) {
			newPosX = newPosX - columns;
		}
		
		if(newPosY<0) {
			newPosY = newPosY + rows;
		}
		else if(newPosY>rows-1) {
			newPosY = newPosY - rows;
		}
		
		this.position = new Point(newPosX, newPosY);
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public Point getVector() {
		return this.vector;
	}

}
