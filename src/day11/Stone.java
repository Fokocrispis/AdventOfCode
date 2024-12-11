package day11;

import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.*;

public class Stone {
    private static final int LOOPS = 25;
    private static final int MULT = 2024;

    private static final Map<Long, long[]> memoizedSplits = new ConcurrentHashMap<>();
    private static final Map<Long, Long> memoizedMultiplications = new ConcurrentHashMap<>();
    private static final Map<Long, long[]> memoizedResults = new ConcurrentHashMap<>();

    private int recursiveCount = 0;
    private long value;

    public Stone(long value, int count, Test test) {
        this.value = value;
        this.recursiveCount = count + 1;

        processStone(value, test, recursiveCount);
    }

    private void processStone(long currentValue, Test test, int currentCount) {
        if (currentCount > LOOPS) {
            test.increaseCount();
            return;
        }

        long[] memoized = memoizedResults.get(currentValue);
        if (memoized != null) {
            for (long result : memoized) {
                processStone(result, test, currentCount + 1);
            }
            return;
        }

        if (currentValue == 0) {
            memoizedResults.put(currentValue, new long[]{1L});
            processStone(1, test, currentCount + 1);
        } else if (String.valueOf(currentValue).length() % 2 == 0) {
            long[] splitValues = memoizedSplits.computeIfAbsent(currentValue, this::split);
            memoizedResults.put(currentValue, splitValues);
            for (long result : splitValues) {
                processStone(result, test, currentCount + 1);
            }
        } else {
            long multipliedValue = memoizedMultiplications.computeIfAbsent(currentValue, v -> v * MULT);
            memoizedResults.put(currentValue, new long[]{multipliedValue});
            processStone(multipliedValue, test, currentCount + 1);
        }
    }

    private long[] split(long value) {
        int length = (int) Math.log10(value) + 1;
        int halfLength = length / 2;

        long divisor = (long) Math.pow(10, halfLength);
        return new long[]{value / divisor, value % divisor};
    }
}



