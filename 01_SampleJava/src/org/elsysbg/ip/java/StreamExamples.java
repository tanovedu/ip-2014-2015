package org.elsysbg.ip.java;

import java.io.IOException;
import java.io.InputStream;

public class StreamExamples {

	public static void main(String[] args) throws IOException {
		inputStreamExample();
	}
	
	// rethrow exception with throws
	public static void inputStreamExample() throws IOException {
		final InputStream input = System.in;
		
		// to read single character
		final int b = input.read();
		System.out.println(Character.toString ((char) b) + " has ASCII " + b);
		
		final byte a[] = new byte[3];
		// to read more than one character
		input.read(a);
		
		// same as:
		// input.read(a, 0, a.length);
		
		System.out.println(new String(a));
	}

}
