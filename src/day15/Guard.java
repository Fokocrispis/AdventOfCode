package day15;

import java.awt.Point;

public class Guard {
	
	private Point position;
	
	public Guard(Point position) {
		this.position = position;
	}
	
	public Point update(char instruction) {
		switch(instruction) {
		case '^':
			return new Point(position.x, position.y-1);
		case '>':
			return new Point(position.x+1, position.y);
		case 'v':
			return new Point(position.x, position.y+1);
		case '<':
			return new Point(position.x-1, position.y);
		}
		return null;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}

}
