package org.elsysbg.ip.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamExamples {

	private static final String END_MARKER = "END";

	public static void main(String[] args) throws IOException {
//		inputStreamExample();
		bufferedReaderExample();
	}
	
	private static void bufferedReaderExample() throws IOException {
		final InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		// to read whole lien:
		final String wholeLine = reader.readLine();
		System.out.println(wholeLine);
		
		// read until keyword
		int count = 0;
		while(!reader.readLine().equals(END_MARKER)) {
			count ++;
		}
		System.out.println("You have entered " + count);
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
