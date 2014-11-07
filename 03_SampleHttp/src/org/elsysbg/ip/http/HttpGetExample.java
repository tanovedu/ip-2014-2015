package org.elsysbg.ip.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

public class HttpGetExample {

	private static final String HTTP_METHOD_GET = "GET";
	private static final int HTTP_PORT = 80;
	private static final String PROTOCOL_VERSION = "HTTP/1.0";

	public static void main(String[] args) throws UnknownHostException, IOException {
		final HttpGetExample example = new HttpGetExample();
		final List<String> response = example.createRequest("example.com",
			HTTP_METHOD_GET, "/index.html");
		
		for (String next : response) {
			System.out.println(next);
		}
	}
	
	public List<String> createRequest(String host, String method,
			String path)
			throws UnknownHostException, IOException {
		final Socket clientSocket = new Socket(host, HTTP_PORT);
		try {
			final InputStream inputStream = clientSocket .getInputStream();
			final OutputStream outputStream = clientSocket.getOutputStream();
			
			final InputStreamReader inputStreamReader =
				new InputStreamReader(inputStream);
			final BufferedReader in = new BufferedReader(inputStreamReader);
			final PrintWriter out = new PrintWriter(outputStream);

			writeRequest(out, host, method, path);
			out.flush();
			
			return parseResponse(in);
		} finally {
			// we should ALWAYS close sockets!
			clientSocket.close();
		}
		
		
	}

	private List<String> parseResponse(BufferedReader in) throws IOException {
		final List<String> result = new LinkedList<String>();
		String next;
		while((next = in.readLine()) != null) {
			result.add(next);
		}
		return result;
	}

	private void writeRequest(PrintWriter out, String host,
			String method, String path) {
		out.printf("%s %s %s\n", method, path, PROTOCOL_VERSION);
		out.printf("\n");
	}

}
