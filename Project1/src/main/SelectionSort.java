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
    
    //Initiates the recursive selection sort.
    public int[] recursiveSort(int a[]) {
    	count = 0;
    	startTime = System.nanoTime();
    	a = recursiveSort(a, a.length, 0);
    	endTime = System.nanoTime();
    	
    	return a;
    }
    
    // Recursive algorithm pulled from https://www.geeksforgeeks.org/recursive-selection-sort/
    
    // Recursive selection sort. n is size of a[] and index 
    // is index of starting element. 
    private int[] recursiveSort(int a[], int n, int index) 
    { 
           
        // Return when starting and size are same 
        if (index == n) 
           return a; 
       
        // calling minimum index function for minimum index 
        int k = minIndex(a, index, n-1); 
       
        // Swapping when index and minimum index are not same 
        if (k != index){ 
           // swap 
           int temp = a[k]; 
           a[k] = a[index]; 
           a[index] = temp; 
        } 
        // Recursively calling selection sort function 
        return recursiveSort(a, n, index + 1); 
    }
    
    // Return minimum index 
    int minIndex(int a[], int i, int j) 
    { 
    	count++;
        if (i == j) 
            return i; 
       
        // Find minimum of remaining elements 
        int k = minIndex(a, i + 1, j); 
       
        // Return minimum of current and remaining. 
        return (a[i] < a[k])? i : k; 
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
