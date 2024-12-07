package day5;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public SafetyManual readPointsFromFile() {
        List<Point> ordering = new ArrayList<>();
        List<List<Integer>> update = new ArrayList<>();
        boolean updating = false;
        int index = -1;

        try (BufferedReader br = Files.newBufferedReader(Paths.get("./resources/Day5_test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                
                if (line.isEmpty()) {
                    updating = true;
                    continue; 
                }

                if (!updating) {
                    String[] parts = line.split("\\|"); 
                    int x = Integer.parseInt(parts[0].trim());
                    int y = Integer.parseInt(parts[1].trim());
                    ordering.add(new Point(x, y));
                } else {
                    if (index == -1 || line.contains(",")) {
                        index++;
                        update.add(new ArrayList<>()); 
                    }
                    String[] parts = line.split(",");
                    for (String part : parts) {
                        int x = Integer.parseInt(part.trim());
                        update.get(index).add(x);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return new SafetyManual(ordering, update);
    }
}
