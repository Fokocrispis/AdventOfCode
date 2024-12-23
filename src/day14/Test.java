package day14;

import java.awt.Point;
import java.util.List;

public class Test {
	
	private static int LOOPS=100;
	private static int WAIT = 150;

	private Map map;
	private List<Robot> robots;

	public Test() {
		Point dimensions = new Point(11, 7);
//		Point dimensions = new Point(101, 103);
		map = new Map(dimensions);
		robots = new Reader().readFromFile(map);

		new Thread(() -> {
			for (int i = 0; i < LOOPS; i++) {
				
				for (Robot robot : robots) {
					robot.update();
					map.setRobot(robot);
				}
				map.print();
				if (i != LOOPS - 1)
					map.reset();

				try {
					Thread.sleep(WAIT);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("\nSafety factor: " + map.sumQuadrants());
		}).start();
	}

	public static void main(String[] args) {
		new Test();
	}

}
