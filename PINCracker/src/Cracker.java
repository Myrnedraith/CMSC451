import java.util.Arrays;

public class Cracker {
	private static final int[] PIN = {9,9,9,8,5,4,3};
	private static final int OPTIONS = 10;
	private static final int LENGTH = PIN.length;
	
	private static int[] attempt;
	
	public static void main(String[] args) {
		
		attempt = new int[LENGTH];
		
		for(int i = 0; i < LENGTH; i++) {
			attempt[i] = 0;
		}
		
		findCombo(0);
		
		System.out.println("PIN found. It is " + Arrays.toString(attempt));
	}
	
	public static void findCombo(int depth) {
		
		if(Arrays.equals(attempt, PIN)) {
			return;
		}
		else {
			if (depth < LENGTH - 1) {
				for (int i = 0; i < OPTIONS; i++) {
					attempt[depth] = i;
					System.out.println(Arrays.toString(attempt));
					if (Arrays.equals(attempt, PIN))
						return;
					
					findCombo(depth + 1);
					
					if (Arrays.equals(attempt, PIN))
						return;
				}
			}
			else {
				for (int i = 0; i < OPTIONS; i++) {
					attempt[depth] = i;
					System.out.println(Arrays.toString(attempt));
					if (Arrays.equals(attempt, PIN))
						return;
				}
				attempt[depth] = 0;
			}
		}
	}

}
