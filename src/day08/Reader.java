package day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reader {
	private int rows,col;
    private Map<Character, List<Point>> characterPositions = new HashMap<>();

    public Map<Character, List<Point>> readMapFromFile() {
        List<char[]> lines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get("./resources/Day08_values.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.toCharArray());
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        char[][] map = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i);
        }
        
        rows=map.length;
        col=map[0].length;

        populateCharacterPositions(map);

        return characterPositions;
    }

    private void populateCharacterPositions(char[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                char c = map[row][col];
                if (c != '.') { 
                    characterPositions
                        .computeIfAbsent(c, k -> new ArrayList<>())
                        .add(new Point(col, row)); 
                }
            }
        }
    }
    
    public int getRows() {
    	return rows;
    }
    
    public int getCol() {
    	return col;
    }
}

