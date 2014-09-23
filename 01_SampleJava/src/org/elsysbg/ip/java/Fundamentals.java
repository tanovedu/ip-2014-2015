package org.elsysbg.ip.java;

public class Fundamentals {
	// to create constant
	private final static String SOME_CONSTANT_VALUE = "constant1";
	
	// to create field
	private String c = "other";
	
	public static void main(String[] args) {
		// to output to console
		System.out.println("Hello world");

		// to create local variable
		String c;
		// to create local variable with initial value
		int a = 1;
		String b = "something";

		// to assign value to variable
		c = b + a;
		System.out.println(c);
		
		// how to create object and assign to variable
		Fundamentals fundamentals = new Fundamentals();
		fundamentals = new Fundamentals();
		
		// final keyword
		final Fundamentals fundamentalsFinal = new Fundamentals();
		// won't work
		// fundamentalsFinal = new Fundamentals();
		
		// how to call method
		fundamentalsFinal.getGreeting();
		
		// how to get result of method invocation
		final String result = fundamentalsFinal.getGreeting();
		
		System.out.println(result);
		
//		won't work - no value
//		String a = 
		fundamentalsFinal.doSomething();
		
		// how to pass arguments to a method
		final String greetingBulgaria = fundamentalsFinal.getGreeting("Bulgaria");
		System.out.println(greetingBulgaria);
		
		if (fundamentalsFinal.isPositive(4)) {
			System.out.println("4: yes it is positive");
		}
		if (fundamentalsFinal.isPositive(-1)) {
			// not reached
			System.out.println("-1: yes it is positive");
		}
		if (fundamentalsFinal.isPositiveElse(4)) {
			System.out.println("4: yes it is positive");
		}
		if (fundamentalsFinal.isPositiveElse(-1)) {
			// not reached
			System.out.println("-1: yes it is positive");
		}
	}
	
	// how to define method:
	public String getGreeting() {
		return "Hello world";
	}
	
	// how to define method without result
	public void doSomething() {
		// nothing
	}
	
	// how to pass arguments to a method
	public String getGreeting(String name) {
		return "Hello " + name;
	}
	
	public boolean isPositive(int n) {
		// if without else
		if (n > 0) {
			// when return is reached method is terminated
			// so else is not needed here
			return true;
		}
		return false;
	}

	public boolean isPositiveElse(int n) {
		// if-else
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

}
