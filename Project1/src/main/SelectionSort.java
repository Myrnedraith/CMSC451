package main;

public class SelectionSort implements SortInterface {
	private int count;
	private long startTime;
	private long endTime;
	
	
	public SelectionSort() {
		count = 0;
		startTime = 0;
		endTime = 0;
	}
	
	@Override
	public int[] recursiveSort(int[] list) {
		return null;
	}

	@Override
	//Iterative algorithm pulled from https://www.geeksforgeeks.org/selection-sort/
	public int[] iterativeSort(int[] list) {
		startTime = System.nanoTime();
		count = 0;
		
		int n = list.length; 
		  
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) {
            	count++;
                if (list[j] < list[min_idx]) 
                    min_idx = j; 
            }
  
            // Swap the found minimum element with the first 
            // element 
            int temp = list[min_idx]; 
            list[min_idx] = list[i]; 
            list[i] = temp; 
        }
        
        endTime = System.nanoTime();
        return list;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public long getTime() {
		return endTime - startTime;
	}

}
