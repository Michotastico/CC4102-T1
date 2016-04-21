package algorithm;

public class Cons {
	public static final String NEW_LINE = System.getProperty("line.separator");
	
	public static final int RADIX = 10; // 2 is binary.
	
	@SuppressWarnings("unused")
	public static String toString(int number){ //Binary or decimal
		String ans = "";
		if(RADIX == 2)
			ans = Integer.toBinaryString(number);
		else
			ans = Integer.toString(number);
		ans = ans + NEW_LINE;
		
		return ans;
	}
}

