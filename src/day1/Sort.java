package day1;

import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.List;
import java.util.List;

public class Sort {

    static int partition(List<Integer> points, int low, int high) {
        int pivot = points.get(high);
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (points.get(j) < pivot) {
                i++;
                swap(points, i, j);
            }
        }
        swap(points, i + 1, high);
        return i + 1;
    }

    static void quickSort(List<Integer> points, int low, int high) {
        if (low < high) {
            int pi = partition(points, low, high);

            quickSort(points, low, pi - 1);
            quickSort(points, pi + 1, high);
        }
    }

    
    static void bubbleSort(List<Integer> points) {
        int n = points.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (points.get(j) > points.get(j + 1)) {
                    swap(points, j, j + 1);
                }
            }
        }
    }

    static void swap(List<Integer> points, int i, int j) {
        int temp = points.get(i);
        points.set(i, points.get(j));
        points.set(j, temp);
    }

    static int findMax(List<Integer> points) {
        int max = points.get(0);
        for (int num : points) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    static int findMin(List<Integer> points) {
        int min = points.get(0);
        for (int num : points) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
}