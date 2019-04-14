package main;

public class UnsortedException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public UnsortedException() {
		message = "The list was unsorted.";
	}
	
	public String getMessage() {
		return message;
	}
}
