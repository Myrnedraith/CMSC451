package main;

import java.math.*;
import java.util.*;

public class BenchmarkSorts {
	private static final int SETS_PER_SIZE = 50;
	
	private ArrayList<Benchmark> iBenchmarks = new ArrayList<Benchmark>();
	private ArrayList<Benchmark> rBenchmarks = new ArrayList<Benchmark>();
	private SelectionSort sorter = new SelectionSort();
	
	private int[] sizes;
	
	class Benchmark {
		int size;
		int runs;
		long totalTime;
		long totalOps;
		String type;
		
		ArrayList<Long> times;
		ArrayList<Long> ops;
		
		Benchmark(int size, String type) {
			this.size = size;
			this.type = type;
			
			runs = 0;
			totalTime = 0;
			totalOps = 0;
			
			times = new ArrayList<Long>();
			ops = new ArrayList<Long>();
		}
		
		public void runBenchmark(int[] set) throws UnsortedException {
			long time;
			long op;
			
			
			if (type.equals("Iterative")) {
				sorter.iterativeSort(set);
			}
			else {
				sorter.recursiveSort(set);
			}
			
			for (int i = 1; i < set.length; i++) {
				if (set[i] < set[i - 1]) {
					throw new UnsortedException();
				}
			}
			
			runs++;
			
			time = sorter.getTime();
			op = sorter.getCount();
			
			times.add(time);
			ops.add(op);
			
			totalTime += time;
			totalOps += op;
		}
		
		public double averageTime() {
			return totalTime/runs;
		}
		
		public long averageOps() {
			return totalOps/runs;
		}
		
		public double varTime() {
			double sqrSum = 0;
			double dev;
			double mean = averageTime();
			
			for (long time : times) {
				sqrSum += Math.pow(mean - time, 2);
			}
			
			dev = Math.sqrt(sqrSum / (runs - 1));
			
			return dev/mean;
		}
		
		public double varOps() {
			double sqrSum = 0;
			double dev;
			double mean = averageOps();
			
			for (long op : ops) {
				sqrSum += Math.pow(mean - op, 2);
			}
			
			dev = Math.sqrt(sqrSum / (runs - 1));
			
			return dev/mean;
		}
		
		public String toString() {
			return "Type: " + type + " Size: " + size +
					"\nAverage Ops: " + averageOps() + " Variance of Count: " + varOps() +
					"\nAverage Time: " + averageTime() + " Variance of Time: " + varTime();
		}
	}
	
	
	public BenchmarkSorts(int[] sizes) {
		this.sizes = sizes;
		
		for(int i = 0; i < sizes.length; i++) {
			rBenchmarks.add(new Benchmark(sizes[i], "Recursive"));
			iBenchmarks.add(new Benchmark(sizes[i], "Iterative"));
		}
	}
	
	public void runSorts() {
		int[] testSet;
		Random rand = new Random();
		
		for (int i = 0; i < sizes.length; i++) {
			Benchmark iterative = iBenchmarks.get(i);
			//Benchmark recursive = rBenchmarks.get(i);
			
			for (int j = 0; j < SETS_PER_SIZE; j++) {
				testSet = new int[sizes[i]];
				
				for (int k = 0; k < sizes[i]; k++) {
					testSet[k] = rand.nextInt(100);
				}
				
				try {
					//recursive.runBenchmark(testSet);
					iterative.runBenchmark(Arrays.copyOf(testSet, testSet.length));
				}
				catch (UnsortedException ue) {
					System.out.println(ue.getMessage());
				}
			}
		}
		
		
	}
	
	public void displayReport() {
		for (Benchmark b:iBenchmarks) {
			System.out.println(b.toString());
		}
	}
}
