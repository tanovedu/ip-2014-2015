package org.elsysbg.ip.java;

import java.util.ArrayList;
import java.util.List;

public class CollectionsExamples {

	private static final int SIZE = 5;

	public static void main(String[] args) {
		arrayWithoutInit();
		arrayWithInit();
		iterateOverArrayFor();
		iterateOverArrayForEach();
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
	private static void iterateOverArrayFor() {
		final int a[] = new int[] {1, 0, 0, 0, 2};
		
		// iterate over array
		for (int i = 0; i < a.length; i++) {
			final int next = a[i];
			
			// output next value
			System.out.println("a[" + i + "]: " + next);
		}
	}
	private static void iterateOverArrayForEach() {
		final int a[] = new int[] {1, 0, 0, 0, 2};
		
		// iterate over array
		for (int next : a) {
			System.out.println(next);
		}
	}

	
	
	
	
	
	
}
