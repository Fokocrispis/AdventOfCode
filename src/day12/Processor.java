package day12;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Processor {
    private Map<Character, List<Point>> map = new HashMap<>();
    private Map<Character, Integer> neighbours = new HashMap<>();

    public Processor(Map<Character, List<Point>> map) {
        this.map = map;
        process();
    }

    private void process() {
        System.out.println("\nPrinting map contents:");

        for (Map.Entry<Character, List<Point>> entry : map.entrySet()) {
            Character c = entry.getKey();
            List<Point> points = entry.getValue();
            System.out.println("Character: " + c);
            for (Point p : points) {
                System.out.println("\tPoint: " + p);
            }

            List<Set<Point>> regions = findRegions(points);

            int totalCostForChar = 0;
            for (Set<Point> region : regions) {
                int area = region.size();
                int perimeter = computePerimeter(region);
                int cost = area * perimeter;
                totalCostForChar += cost;
            }

            neighbours.put(c, totalCostForChar);
        }
    }

    private List<Set<Point>> findRegions(List<Point> points) {
        Set<Point> all = new HashSet<>(points);
        List<Set<Point>> regions = new ArrayList<>();
        Set<Point> visited = new HashSet<>();

        for (Point start : all) {
            if (!visited.contains(start)) {
                Set<Point> region = new HashSet<>();
                Queue<Point> queue = new LinkedList<>();
                queue.add(start);
                visited.add(start);

                while (!queue.isEmpty()) {
                    Point p = queue.poll();
                    region.add(p);

                    for (Point nbr : getNeighbors(p)) {
                        if (all.contains(nbr) && !visited.contains(nbr)) {
                            visited.add(nbr);
                            queue.add(nbr);
                        }
                    }
                }

                regions.add(region);
            }
        }

        return regions;
    }

    private List<Point> getNeighbors(Point p) {
        List<Point> neighbors = new ArrayList<>();
        neighbors.add(new Point(p.x, p.y - 1));
        neighbors.add(new Point(p.x, p.y + 1));
        neighbors.add(new Point(p.x - 1, p.y));
        neighbors.add(new Point(p.x + 1, p.y));
        return neighbors;
    }

    private int computePerimeter(Set<Point> region) {
        int perimeter = 0;
        for (Point p : region) {
            int localPerimeter = 4;
            if (region.contains(new Point(p.x, p.y - 1))) localPerimeter--;
            if (region.contains(new Point(p.x, p.y + 1))) localPerimeter--;
            if (region.contains(new Point(p.x - 1, p.y))) localPerimeter--;
            if (region.contains(new Point(p.x + 1, p.y))) localPerimeter--;
            perimeter += localPerimeter;
        }
        return perimeter;
    }

    public int sum() {
        int sum = 0;
        for (Map.Entry<Character, Integer> e : neighbours.entrySet()) {
            System.out.println("Character: " + e.getKey() + " -> " + e.getValue());
            sum += e.getValue();
        }
        return sum;
    }
}


