package day04;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {

    private int sum;
    private int count=0;

    public Reader() {
    }

    public List<char[]> readAndCount() {
        List<char[]> linesAsCharArrays = new ArrayList<>();
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get("./resources/Day04_values.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                linesAsCharArrays.add(line.toCharArray());
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        
        return linesAsCharArrays;
    }

    
    public int getsSum() {
    	return sum;
    }
}
