package day6;

import java.awt.Point;
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
import java.util.List;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private int col, row;
    private List<Point> blocks = new ArrayList<>();

    public char[][] readMapFromFile() {
        List<char[]> lines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get("./resources/Day6_values.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.toCharArray());
                col = line.length();
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        char[][] map = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i);
        }
        row = lines.size();

        populateBlocks(map);

        return map;
    }

    private void populateBlocks(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '#') {
                    blocks.add(new Point(j, i)); 
                }
            }
        }
    }

    public List<Point> getBlocks() {
        return blocks;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}

