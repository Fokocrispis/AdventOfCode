package day9;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Disk {

    private List<String> disk;
    private List<String> processedDisk = new ArrayList<>();
    private List<Integer> zeros = new ArrayList<>();
    private List<String> blocks = new ArrayList<>();
    private int size;

    public Disk(List<String> disk) {
        this.disk = disk;
        this.size = disk.size();
        processDisk();
        
    }

    private void processDisk() {
        int fileId = 0;
        int zeroCounter = 0;

        for (int i = 0; i < size; i++) {
            int blockCount = Integer.parseInt(disk.get(i)); 
            if (blockCount == 0) continue;

            if (i % 2 == 0){ 
                for (int j = 0; j < blockCount; j++) {
                    processedDisk.add(String.valueOf(fileId));
                    blocks.add(String.valueOf(fileId));
                    zeroCounter++;
                }
                fileId++;
            } else { 
                for (int j = 0; j < blockCount; j++) {
                    processedDisk.add("."); 
                    zeros.add(zeroCounter);
                    zeroCounter++;
                }
            }
        }
        System.out.println(zeros);
    }
    
    public void orderDisk() {
        for (int i = 0; i < zeros.size(); i++) {
            int zeroIndex = zeros.get(i);
            String blockValue = blocks.get(blocks.size() - 1 - i); 
            processedDisk.set(zeroIndex, blockValue);
        }
        for(int i = blocks.size(); i < processedDisk.size(); i++) {
        	processedDisk.set(i, ".");
        }
        
        System.out.println("\nSum: " + checkSum());
    }

	private long checkSum() {
		int count = 0;
		long sum = 0;
		for (String s : processedDisk) {
			if (!s.contains(".")) {
				sum += Integer.valueOf(s) * count;
				count++;
			}
		}
		return sum;
	}

    public List<String> getProcessedDisk() {
        return processedDisk;
    }

    public List<String> getDisk() {
        return disk;
    }
}

