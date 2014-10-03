package org.elsysbg.ip.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class StreamExamples {

	private static final String END_MARKER = "END";

	public static void main(String[] args) throws IOException {
//		inputStreamExample();
//		bufferedReaderExample();
//		readFromUrl();
		readFromFileInputStream();
	}
	private static void readFromFileInputStream() throws MalformedURLException, IOException {
		final File file = new File("src/org/elsysbg/ip/java/StreamExamples.java");
		// get input stream of given file
		final InputStream input = new FileInputStream(file);
		// then we can use it like any other input stream:
		
		// Setting charset to UTF-8
		final InputStreamReader inputStreamReader = new InputStreamReader(input, Charset.forName("UTF-8"));
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		try {
			String nextLine;
			// read until end of stream
			while((nextLine = reader.readLine()) != null) {
				System.out.println(nextLine);
			}
		} finally {
			// Always close resources!
			reader.close();
		}
		
		// InputStream input can be left open if exception is generated before try-finally, e.g. unknown charset
		// so wrap in try-finally, too
	}

	private static void readFromUrl() throws MalformedURLException, IOException {
		// get input stream of given URL
		final InputStream input = new URL("http://google.com").openStream();
		// then we can use it like any other input stream:
		
		// Setting charset to UTF-8
		final InputStreamReader inputStreamReader = new InputStreamReader(input, Charset.forName("UTF-8"));
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		try {
			String nextLine;
			// read until end of stream
			while((nextLine = reader.readLine()) != null) {
				System.out.println(nextLine);
			}
		} finally {
			// Always close resources!
			reader.close();
		}
		
		// InputStream input can be left open if exception is generated before try-finally, e.g. unknown charset
		// so wrap in try-finally, too
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
