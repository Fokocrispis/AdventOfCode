package day3;

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

	private List<Point> data = new ArrayList<>();
    private boolean isReadingEnabled = true;
    private int sum;
    private int count=0;

    public Reader() {
    }

    public List<Point> readAndCalculateSum() {
        Pattern mulPattern = Pattern.compile("mul\\(\\s*(-?\\d+)\\s*,\\s*(-?\\d+)\\s*\\)");
        StringBuilder buffer = new StringBuilder();
        int sum = 0;

        try (BufferedReader br = Files.newBufferedReader(Paths.get("./resources/Day3_test.txt"))) {
            int ch;
            while ((ch = br.read()) != -1) {
            	char currentChar = (char) ch;
                buffer.append(currentChar);

                if (buffer.toString().contains("do()")) {
                    isReadingEnabled = true;
                    System.out.println("do() command detected - Enabling future mul() commands");
                    buffer.setLength(0); 
                } else {
                    Matcher matcher = mulPattern.matcher(buffer.toString());
                    if (matcher.find() && isReadingEnabled) {
                      	count++;
						int firstValue = Integer.parseInt(matcher.group(1));
						int secondValue = Integer.parseInt(matcher.group(2));
						int result = firstValue * secondValue;
						sum += result;
						data.add(new Point(firstValue, secondValue));
						System.out.println("mul(" + firstValue + ", " + secondValue + ") = " + result);
						buffer.setLength(0);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return data;
    }
    
    public int getsSum() {
    	return sum;
    }
    
    public int getCount() {
    	return count;
    }
}

   