package day12;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    private Reader reader;
    private Processor processor;
    private List<List<Character>> data;
    private Map<Character, List<Point>> map = new HashMap<>();
    
    private Test() {
        reader = new Reader();
        data = reader.readPointsFromFile();
        print();
        processor = new Processor(map);
        System.out.println("Sum of costs: " + processor.sum());
      
    }
    
    public static void main(String[] arg) {
        new Test();
    }
    
    private void print() {
        for (int i = 0; i < reader.getRows(); i++) {
            for (int j = 0; j < reader.getColumns(); j++) {
                Character c = data.get(i).get(j);
                System.out.print(c);
                map.computeIfAbsent(c, k -> new ArrayList<>()).add(new Point(j, i));
            }
            System.out.println();
        }
    }
}

