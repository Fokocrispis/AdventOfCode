package day11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
    private List<Long> data;

    public static void main(String[] args) {
        new Test();
    }

    private Test() {
        data = new Reader().readPointsFromFile();

        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(11);
        List<Future<Long>> futures = new ArrayList<>();

        for (long val : data) {
            futures.add(executor.submit(() -> {
                Stone s = new Stone(val, 1);
                return s.getCount();
            }));
        }

        long total = 0;
        for (Future<Long> future : futures) {
            try {
                total += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;

        System.out.println("Total after " + (Stone.LOOPS+1) + " blinks: " + total);
        System.out.println("Execution time: " + durationMs + " ms");
    }
}


