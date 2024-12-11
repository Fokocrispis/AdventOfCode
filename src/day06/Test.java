package day06;

public class Test {
	
	private Map map;
	private Reader reader  = new Reader();
	
	private Test() throws InterruptedException {
		map = new Map(reader);
		}
	
	public static void main(String[] arg) throws InterruptedException {
		new Test();
	}
	
//	private void print() {
//		for(char[] line : map.getMap()) {
//			for(char c: line) {
//				System.out.print(c);
//			}
//			System.out.println();
//		}
//	}
}
