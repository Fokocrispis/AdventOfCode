package day05;

import java.awt.Point;
import java.util.List;

import java.util.List;

public class Sort {

    static int partition(List<Point> points, int low, int high) {
        Point pivot = points.get(high);
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (points.get(j).x < pivot.x) { 
                i++;
                swap(points, i, j);
            }
        }
        swap(points, i + 1, high);
        return i + 1;
    }

    static void quickSort(List<Point> points, int low, int high) {
        if (low < high) {
            int pi = partition(points, low, high);

            quickSort(points, low, pi - 1);
            quickSort(points, pi + 1, high);
        }
    }

    static void bubbleSort(List<Point> points) {
        int n = points.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (points.get(j).x > points.get(j + 1).x) { 
                    swap(points, j, j + 1);
                }
            }
        }
    }

    static void swap(List<Point> points, int i, int j) {
        Point temp = points.get(i);
        points.set(i, points.get(j));
        points.set(j, temp);
    }

    static Point findMax(List<Point> points) {
        Point max = points.get(0);
        for (Point p : points) {
            if (p.x > max.x) { 
                max = p;
            }
        }
        return max;
    }

    static Point findMin(List<Point> points) {
        Point min = points.get(0);
        for (Point p : points) {
            if (p.x < min.x) { 
                min = p;
            }
        }
        return min;
    }
}
