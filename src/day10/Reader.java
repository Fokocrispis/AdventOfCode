package day10;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private static final Path PATH = Paths.get("./resources/Day10_values.txt");

    private final List<Point> zeros = new ArrayList<>();
    private int rows = 0;
    private int columns = 0;

    public List<List<Integer>> readPointsFromFile() {

        List<List<Integer>> map = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(PATH)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Integer> row = new ArrayList<>();
                int col = 0; 
                for (char c : line.toCharArray()) {
                    int x = Character.getNumericValue(c);
                    row.add(x);

                    if (x == 0) {
                        zeros.add(new Point(col, rows));
                    }
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
    
    public List<Point> getZeros() {
        return zeros;
    }
}
