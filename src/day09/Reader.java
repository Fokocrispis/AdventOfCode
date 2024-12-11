package day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import day07.Report;

public class Reader {
	private static final Path PATH = Paths.get("./resources/Day09_values.txt");
	
	private List<String> disc = new ArrayList<>();
	
	public List<String> readFromFile() {
		try (BufferedReader br = Files.newBufferedReader(PATH)) {
			String line;
			while ((line = br.readLine()) != null) {
				for(Character c: line.toCharArray())
					disc.add(c.toString());
			}
		} catch (IOException e) {
			System.out.println("Error reading the file: " + e.getMessage());
		}

		return disc;
	}
}
