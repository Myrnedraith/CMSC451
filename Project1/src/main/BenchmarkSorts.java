package main;

import java.util.*;

public class BenchmarkSorts {
	private static final int SETS_PER_SIZE = 50;
	
	private ArrayList<Benchmark> benchmarks = new ArrayList<Benchmark>();
	
	class Benchmark {
		int size;
		String type;
		
		int opCount;
		int countVar;
		int time;
		int timeVar;
		
		Benchmark(int size, String type) {
			this.size = size;
			this.type = type;
			
			opCount = 0;
			countVar = 0;
			time = 0;
			timeVar = 0;
		}
	}
	
	
	public BenchmarkSorts(int[] sizes) {
		for(int i = 0; i < sizes.length; i++) {
			benchmarks.add(new Benchmark(sizes[i], "Recursive"));
			benchmarks.add(new Benchmark(sizes[i], "Iterative"));
		}
	}
	
	public void runSorts() {
		
	}
	
	public void displayReport() {
		
	}
}
