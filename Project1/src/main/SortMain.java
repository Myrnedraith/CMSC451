package main;

public class SortMain {
	private static final int[] LENGTHS = {10, 20, 50, 100, 200, 500, 
			1000, 5000, 10000, 50000};
	
	public static void main(String[] args) {
		BenchmarkSorts benchmarker = new BenchmarkSorts(LENGTHS);
		benchmarker.runSorts();
	}

}
