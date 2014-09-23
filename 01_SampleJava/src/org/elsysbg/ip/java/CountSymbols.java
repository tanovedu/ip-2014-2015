package org.elsysbg.ip.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CountSymbols {

	public static void main(String[] args) throws IOException {
		// input and output operations (getting string and printing result) should be separated from program logic (counting symbols)!
		final String line = getLine();
		final int count = countSymbols(line, 'a');
		
		// length() is used to get characters count of a string
		System.out.printf("Count %d of %d (%d %%)", count, line.length(), (count * 100)/line.length());
	}
	
	private static int countSymbols(String line, char c) {
		int result = 0;
		// there are better ways!
		for (int i = 0; i < line.length(); i++){
		    final char next = line.charAt(i);
		    // next and c are characters (not Characters) so we can compare with == 
		    if (next == c) {
		    	result ++;
		    }
		}

		return result;
	}

	private static String getLine() throws IOException {
		final InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		return reader.readLine();
	}

}
