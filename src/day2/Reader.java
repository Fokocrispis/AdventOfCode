package day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	
	private List<Report> data = new ArrayList<>();
	
	public Reader() {
	}
	
	public List<Report> readPointsFromFile() {
		try (BufferedReader br = Files.newBufferedReader(Paths.get("./resources/Day2_values.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                Report report = new Report();
                
                for (String part : parts) {
                    report.add(Integer.parseInt(part.trim()));
                }
                data.add(report);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

		return data;
	}
}

