package day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	public List<List<Integer>> readPointsFromFile() {
		
		List<Integer> pointsLeft = new ArrayList<>();
		List<Integer> pointsRight = new ArrayList<>();
		List<List<Integer>> points = new ArrayList<List<Integer>>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get("./resources/Day1_values.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split("   ");
				int x = Integer.parseInt(parts[0].trim());
				int y = Integer.parseInt(parts[1].trim());
				pointsLeft.add(x);
				pointsRight.add(y);
			}
			points.add(pointsLeft);
			points.add(pointsRight);
		} catch (IOException e) {
			System.out.println("Error reading the file: " + e.getMessage());
		}

		return points;
	}
}