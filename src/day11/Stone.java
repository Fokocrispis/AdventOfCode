package day11;

public class Stone {
	public static final String EVEN = "Even";
	public static final String ODD = "Odd";
	public static final String ZERO = "Zero";
	
	private long value;
	private String type;

	public Stone(long value) {
		this.value = value;
		
		if(value==0) {
			type = ZERO;
		}
		else if(String.valueOf(value).length()%2==0) {
			type = EVEN;
		}
		else {
			type = ODD;
		}
	}
	
	public Tuple split() {
		String temp = Long.toString(value);
		String stone1 = temp.substring(0, temp.length()/2);
		String stone2 = temp.substring(temp.length()/2);
		
		return new Tuple<Long, Long>(Long.valueOf(stone1), Long.valueOf(stone2));
	}
	
	public String getType() {
		return type;
	}
	
	public long getValue() {
		return value;
	}
	
	public void setValue(long value) {
		this.value= value;
	}
}

class Tuple<X, Y> { 
	  public final X x; 
	  public final Y y; 
	  public Tuple(X x, Y y) { 
	    this.x = x; 
	    this.y = y; 
	  } 
	}
