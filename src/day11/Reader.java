package day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private static final Path PATH = Paths.get("./resources/Day11_values.txt");

    private final List<Long> data = new ArrayList<>();

    public List<Long> readPointsFromFile() {

        try (BufferedReader br = Files.newBufferedReader(PATH)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                for (String token : tokens) {
                    data.add(Long.parseLong(token));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing a number: " + e.getMessage());
        }

        return data;
    }
}
