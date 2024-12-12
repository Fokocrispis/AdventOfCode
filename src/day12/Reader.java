package day12;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private static final Path PATH = Paths.get("./resources/Day12_test.txt");

    private int rows = 0;
    private int columns = 0;

    public List<List<Character>> readPointsFromFile() {

        List<List<Character>> map = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(PATH)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                int col = 0; 
                for (char c : line.toCharArray()) {
                    row.add(c);
                    col++;
                }
                map.add(row);
                columns = col;
                rows++;
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return map;
    }

    public int getRows() {
    	return rows;
    }
    
    public int getColumns() {
    	return columns;
    }
}