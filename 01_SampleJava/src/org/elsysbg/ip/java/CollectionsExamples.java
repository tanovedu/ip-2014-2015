package org.elsysbg.ip.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionsExamples {

	private static final String KEY_GERMANY = "Germany";
	private static final String KEY_BULGARIA = "Bulgaria";
	private static final int SIZE = 5;

	public static void main(String[] args) {
		arrayWithoutInit();
		arrayWithInit();
		iterateOverArrayFor();
		iterateOverArrayForEach();
		
		listExample();
		mapExample();
		complexMap();
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

	
	private static void listExample() {
		// create object of type ArrayList (or LinkedList)
		// type of variable list is List - this is interface that
		// is used for working with lists
		final List<String> list = new ArrayList<String>();
		
		// to add element to the list
		list.add("First element");
		list.add("Last element");
		
		// to get specific element
		System.out.println(list.get(0));
		
		// better use foreach:
		for (String next : list) {
			System.out.println(next);
		}
		
		// to get size of the list:
		System.out.println("Size: " + list.size());
	}

	private static void mapExample() {
		// create map with key of type String and value of type Integer
		final Map<String, Integer> m = new HashMap<String, Integer>();
		
		// to add value to key
		m.put(KEY_BULGARIA, 7);
		m.put(KEY_GERMANY, 80);
		
		// to get value by key
		final int populationBulgaria = m.get(KEY_BULGARIA);
		
		// formatted output
		System.out.printf("The population of Bulgaria is %d M\n", populationBulgaria);
		
		// iterate over values
		for (int next : m.values()) {
			System.out.println(next);
		}
		
		// iterate over all entries
		for (Entry<String, Integer> next : m.entrySet()) {
			System.out.printf("The population of %s is %d M\n",
					next.getKey(), next.getValue());
		}
		
		// iterate over keys
		for (String next : m.keySet()) {
			System.out.printf("The population of %s is known\n", next);
		}
	}
	
	private static void complexMap() {
		// create map with key of type String and value of type Integer list
		final Map<String, List<Integer>> m = new HashMap<String, List<Integer>>();
		
		final List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		
		m.put("Hello", list);
		
		// iterate over elements
		for (Entry<String, List<Integer>> next : m.entrySet()) {
			System.out.println("Name: " + next.getKey());
			
			// iterate over sub elements
			for (int count : next.getValue()) {
				System.out.println("\tcount: " + count);
			}
		}

	}
	
}
