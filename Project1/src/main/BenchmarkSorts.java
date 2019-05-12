package main;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class BenchmarkSorts {
	private static final int SETS_PER_SIZE = 50;
	private static final int WARMUP = 10000;
	private static final String[] CATEGORIES = 
		{"Average Critical Operation Count",
				"Coefficient of Variance of Count",
				"Average Execution Time",
				"Coefficient of Variance of Time"
	};
	
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
		
		public double averageOps() {
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
		
		try {
			warmup();
		} catch (UnsortedException ue) {
			System.out.println(ue.getMessage());
		}
		
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
			Benchmark recursive = rBenchmarks.get(i);
			
			for (int j = 0; j < SETS_PER_SIZE; j++) {
				testSet = new int[sizes[i]];
				
				for (int k = 0; k < sizes[i]; k++) {
					testSet[k] = rand.nextInt(100);
				}
				
				try {
					recursive.runBenchmark(Arrays.copyOf(testSet, testSet.length));
					iterative.runBenchmark(Arrays.copyOf(testSet, testSet.length));
				}
				catch (UnsortedException ue) {
					System.out.println(ue.getMessage());
				}
			}
		}
		
		
	}
	
	public void displayReport() {
		JFrame frame = new JFrame("Benchmark Table");
		frame.setSize(1366, 768);
		
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		frame.setContentPane(pane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		pane.add(new JLabel("Data Set Size n"), c);
		
		c.gridwidth = 4;
		c.gridx++;
		
		pane.add(new JLabel("Iterative"), c);
		
		c.gridx = 5;
		
		
		pane.add(new JLabel("Recursive"), c);
		
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 1;
		
		pane.add(new JLabel(""), c);
		
		c.gridx++;
		
		for(int x = 0; x <= 1; x++) {
			for(int i = 0; i < CATEGORIES.length; i++) {
				pane.add(new JLabel(CATEGORIES[i]), c);
				c.gridx++;
			}
		}
		
		for(int i = 0; i < sizes.length; i++) {
			c.gridx = 0;
			c.gridy++;
			pane.add(new JLabel(Integer.toString(sizes[i])), c);
			c.gridx++;
			pane.add(new JLabel(Double.toString(iBenchmarks.get(i).averageOps())), c);
			c.gridx++;
			pane.add(new JLabel(Double.toString(iBenchmarks.get(i).varOps())), c);
			c.gridx++;
			pane.add(new JLabel(Double.toString(iBenchmarks.get(i).averageTime())), c);
			c.gridx++;
			pane.add(new JLabel(Double.toString(iBenchmarks.get(i).varTime())), c);
			c.gridx++;
			pane.add(new JLabel(Double.toString(rBenchmarks.get(i).averageOps())), c);
			c.gridx++;
			pane.add(new JLabel(Double.toString(rBenchmarks.get(i).varOps())), c);
			c.gridx++;
			pane.add(new JLabel(Double.toString(rBenchmarks.get(i).averageTime())), c);
			c.gridx++;
			pane.add(new JLabel(Double.toString(rBenchmarks.get(i).varTime())), c);
		}
		
		frame.setVisible(true);
		
	}
	
	private void warmup() throws UnsortedException {
		Benchmark iB = new Benchmark(5, "Iterative");
		Benchmark rB = new Benchmark(5, "Recursive");
		
		int[] dummySet = {3, 6, 2, 10, 3, 7};
		for (int i = 0; i < WARMUP; i++) {
			iB.runBenchmark(Arrays.copyOf(dummySet, dummySet.length));
			rB.runBenchmark(Arrays.copyOf(dummySet, dummySet.length));
		}
	}
}
