package day05;

import java.util.ArrayList;

public class Node {
	private final int key;
	private ArrayList<Node> childs;
	private ArrayList<Node> predecessors;
		
	public Node(int key) {
		this.key  = key;
		childs = new ArrayList<Node>();
		predecessors = new ArrayList<Node>();
	}
	
	public int getKey() {
		return key;
	}
	
	public void setPredecessor(Node pre) {
		predecessors.add(pre);
	}
	
	public ArrayList<Node> getPredecessor(){
		return predecessors;
	}
	
	public void setChild(Node child) {
		child.setPredecessor(this);
		childs.add(child);
	}
	
	public ArrayList<Node> getChilds(){
		return childs;
	}
}
