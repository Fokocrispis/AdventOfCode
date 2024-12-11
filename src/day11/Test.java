package day11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
    private static final int THREAD_COUNT = 11; 
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    private List<Long> data;
    private List<Stone> stones = new ArrayList<>();
    private long totalStonesCount = 0;

    private ThreadLocal<Long> threadLocalCount = ThreadLocal.withInitial(() -> 0L);

    private Test() {
        data = new Reader().readPointsFromFile(); 
        System.out.println(data);
        populate();
        System.out.println("\nNumber of stones: " + totalStonesCount);
    }

    public static void main(String[] args) {
        new Test();
    }

    private void populate() {
        long startTime = System.nanoTime();
        List<Future<Long>> futures = new ArrayList<>();

        for (Long l : data) {
            Future<Long> future = executorService.submit(() -> {
                Stone s = new Stone(l, 1, this);
                stones.add(s);
                return threadLocalCount.get();
            });
            futures.add(future);
        }

        for (Future<Long> future : futures) {
            try {
                totalStonesCount += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.nanoTime();
        long durationInMilliseconds = (endTime - startTime) / 1_000_000;
        System.out.println("Execution time: " + durationInMilliseconds + " milliseconds");

        executorService.shutdown();
    }

    public void increaseCount() {
        threadLocalCount.set(threadLocalCount.get() + 1);
    }
}

