package day14;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	private static final Path PATH = Paths.get("./resources/Day14_test.txt");
 
    private List<Robot> robots;     

	public List<Robot> readFromFile(Map map) {
		robots = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(PATH)) {
			String line;

			while ((line = br.readLine()) != null) {
				Robot robot;
				line = line.trim();
				if (line.isEmpty())
					continue;
				
				String[] parts = line.split("v=");
				
				String[] position = parts[0].substring(2).split(",");
				int x1 = Integer.parseInt(position[0].trim());
                int y1 = Integer.parseInt(position[1].trim());
				
				String[] vector = parts[1].split(",");
                int x2 = Integer.parseInt(vector[0].trim());
                int y2 = Integer.parseInt(vector[1].trim());
                
                robot = new Robot(new Point(x1, y1), new Point(x2, y2), map);
                robots.add(robot);
            
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return robots;
	}
}

