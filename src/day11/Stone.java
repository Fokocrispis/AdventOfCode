package day11;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Stone {
    public static final int LOOPS = 75 - 1;
    private static final int MULT = 2024;

    private static final Map<String, Long> memo = new ConcurrentHashMap<>();

    private long value;
    private int startCount;

    public Stone(long value, int startCount) {
        this.value = value;
        this.startCount = startCount;
    }

    public long getCount() {
        return processStone(value, startCount);
    }

    private long processStone(long currentValue, int currentCount) {
        if (currentCount > LOOPS) {
            return 1;
        }

        String key = currentValue + " | " + currentCount;
        Long memoizedResult = memo.get(key);
        if (memoizedResult != null) {
            return memoizedResult;
        }

        long resultCount;
        if (currentValue == 0L) {
            resultCount = processStone(1L, currentCount + 1);
        } else if (hasEvenNumberOfDigits(currentValue)) {
            long[] splitVals = splitNumber(currentValue);
            long leftCount = processStone(splitVals[0], currentCount + 1);
            long rightCount = processStone(splitVals[1], currentCount + 1);
            resultCount = leftCount + rightCount;
        } else {
            long multiplied = currentValue * MULT;
            resultCount = processStone(multiplied, currentCount + 1);
        }
        memo.put(key, resultCount);
        return resultCount;
    }

    private boolean hasEvenNumberOfDigits(long value) {
        int length = Long.toString(value).length();
        return (length % 2 == 0);
    }

    private long[] splitNumber(long value) {
        int length = (int) Math.log10(value) + 1;
        int halfLength = length / 2;

        long divisor = (long) Math.pow(10, halfLength);
        return new long[]{value / divisor, value % divisor};
    }
}





