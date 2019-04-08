package main;

public class Recurrences {
	
	public static void main(String[] args) {
		System.out.print("And the base keep " + runnin(8));
	}

	public static String runnin(int n) {
		if (n > 1)
			return "runnin' runnin', and\n" + runnin(n - 1);
		else
			return "runnin' runnin', and";
	}
}
