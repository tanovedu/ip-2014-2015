package org.elsysbg.ip.java;

public class CollectionsExamples {

	private static final int SIZE = 5;

	public static void main(String[] args) {
		arrayWithoutInit();
		arrayWithInit();
	}
	
	private static void arrayWithoutInit() {
		// define array
		final int a[] = new int[SIZE];
		
		// update array
		// 0 is the first index
		a[0] = 1;
		// 4 is the last index (size - 1)
		a[SIZE - 1] = 2;
		
		// access array value
		System.out.println(a[0]);
		System.out.println(a[4]);
	}
	private static void arrayWithInit() {
		// define array and assign value
		final int a[] = new int[] {1, 0, 0, 0, 2};
		
		// access array value
		System.out.println(a[0]);
		System.out.println(a[4]);
	}
}
