package day9;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

import java.util.*;

import java.util.*;

public class Disk {

    private List<String> disk;
    private List<String> processedDisk = new ArrayList<>();
    private List<String> blocks = new ArrayList<>();
    private int size;

    public Disk(List<String> disk) {
        this.disk = disk;
        this.size = disk.size();
        processDisk();
    }

    private void processDisk() {
        int fileId = 0;

        for (int i = 0; i < size; i++) {
            int blockCount = Integer.parseInt(disk.get(i));
            if (blockCount == 0) continue;

            if (i % 2 == 0) {
                for (int j = 0; j < blockCount; j++) {
                    processedDisk.add(String.valueOf(fileId));
                }
                blocks.add(String.valueOf(fileId));
                fileId++;
            } else {
                for (int j = 0; j < blockCount; j++) {
                    processedDisk.add(".");
                }
            }
        }
    }

    public void orderDiskRefined() {
        for (int fileId = blocks.size() - 1; fileId >= 0; fileId--) {
            String blockValue = String.valueOf(fileId);

            int blockStart = processedDisk.indexOf(blockValue);
            if (blockStart == -1) continue;

            int blockSize = 0;
            for (int i = blockStart; i < processedDisk.size() && processedDisk.get(i).equals(blockValue); i++) {
                blockSize++;
            }

            int targetStart = findLeftmostSpan(blockSize, blockStart);
            if (targetStart != -1 && targetStart < blockStart) {
                for (int i = 0; i < blockSize; i++) {
                    processedDisk.set(targetStart + i, blockValue);
                }
                for (int i = blockStart; i < blockStart + blockSize; i++) {
                    processedDisk.set(i, ".");
                }
            }
        }
    }

    private int findLeftmostSpan(int blockSize, int blockStart) {
        int zeroStart = -1;
        int zeroSpan = 0;

        for (int i = 0; i < blockStart; i++) { 
            if (processedDisk.get(i).equals(".")) {
                if (zeroStart == -1) zeroStart = i;
                zeroSpan++;
                if (zeroSpan == blockSize) return zeroStart;
            } else {
                zeroStart = -1;
                zeroSpan = 0;
            }
        }
        return -1; 
    }

    public long checkSum() {
        long sum = 0;
        for (int i = 0; i < processedDisk.size(); i++) {
            String block = processedDisk.get(i);
            if (!block.equals(".")) {
                sum += Integer.parseInt(block) * i;
            }
        }
        return sum;
    }

    public List<String> getProcessedDisk() {
        return processedDisk;
    }
}

