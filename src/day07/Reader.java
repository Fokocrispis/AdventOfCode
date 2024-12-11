package day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	private static final Path PATH = Paths.get("./resources/Day07_values.txt");
	
	private List<Report> data = new ArrayList<>();
	
	public List<Report> readReportsFromFile() {
		try (BufferedReader br = Files.newBufferedReader(PATH)) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(":");
				
				List<Long> operands = new ArrayList<>();
				for(String s: parts[1].trim().split(" ")) {
					operands.add(Long.parseLong(s));
				}
				
				long calibrator = Long.parseLong(parts[0].trim());	
				data.add(new Report(calibrator, operands));
			}
		} catch (IOException e) {
			System.out.println("Error reading the file: " + e.getMessage());
		}

		return data;
	}
}
