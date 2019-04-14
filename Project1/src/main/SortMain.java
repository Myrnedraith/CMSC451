package main;

public class SortMain {
	private static final int[] LENGTHS = {10, 20, 50, 100, 200, 500, 
			1000, 2500, 5000, 10000};
	private static final int WARMUP = 10000;
	
	public static void main(String[] args) {
		BenchmarkSorts benchmarker = new BenchmarkSorts(LENGTHS);
		
		warmup();
		
		benchmarker.runSorts();
		benchmarker.displayReport();
	}
	
	
	private static void warmup() {
		int[] dummyData = {5};
		BenchmarkSorts benchwarmer = new BenchmarkSorts(dummyData);
		
		for(int i = 0; i < WARMUP; i++) {
			benchwarmer.runSorts();
		}
	}
}
