package day5;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Processor {
	int sum=0;
	int invalidSum = 0;
	
    List<List<Integer>> update = new ArrayList<>();
    List<List<Integer>> invalidUpdate = new ArrayList<>();
    Map<Integer, Node> nodeMap = new HashMap<>();
    SafetyManual manual;

    public Processor(SafetyManual manual) {
        this.manual = manual;
        this.update = manual.getUpdate();
        nodeMap = createNodes();

        for (List<Integer> l : update) {
            int result = checkUpdates(l);
            if (result > 0) {
                System.out.println("Middle value of valid update: " + result);
                sum+=result;
            } else {
                System.out.println("Invalid update: " + l);
                invalidUpdate.add(l);
            }
        }
        System.out.println("");
        for (List<Integer> l : invalidUpdate) {
        	int result=middleValue(checkUpdatesRec(l));
        	System.out.println("Middle value of corrected invalid update: " + result);
        	invalidSum += middleValue(checkUpdatesRec(l));
        }
    }

    private int checkUpdates(List<Integer> l) {
        for (int i = 0; i < l.size(); i++) {
            int current = l.get(i); 
            Node currentNode = nodeMap.get(current); 

            if (currentNode != null) {
                for (Node child : currentNode.getChilds()) {
                    int childKey = child.getKey();
                    int childIndex = l.indexOf(childKey);
                    if (childIndex != -1 && childIndex < i) {
                        return 0; 
                    }
                }
            }
        }
        return middleValue(l);
    }
    
    private List<Integer> checkUpdatesRec(List<Integer> l) {
        for (int i = 0; i < l.size(); i++) {
            int current = l.get(i);
            Node currentNode = nodeMap.get(current);
            if (currentNode != null) {
                for (Node child : currentNode.getChilds()) {
                    int childKey = child.getKey();
                    int childIndex = l.indexOf(childKey);

                    if (childIndex != -1 && childIndex < i) {
                        l.remove(childIndex); 
                        l.add(Math.min(i + 1, l.size()), childKey);

                        return checkUpdatesRec(l);
                    }
                }
            }
        }
        return l; 
    }

    private int middleValue(List<Integer> l) {
        return l.get(l.size() / 2);
    }

    private Map<Integer, Node> createNodes() {
        Map<Integer, Node> nodesMap = new HashMap<>();
        for (Point p : manual.getOrdering()) {
            nodesMap.putIfAbsent(p.x, new Node(p.x));
            nodesMap.get(p.x).setChild(new Node(p.y));
        }
        return nodesMap;
    }
    
    public int getSum() {
    	return sum;
    }
    
    public int getInvalidSum() {
    	return invalidSum;
    }
}

