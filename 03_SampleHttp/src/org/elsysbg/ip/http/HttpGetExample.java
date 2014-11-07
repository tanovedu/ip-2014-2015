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

	private static final int HTTP_PORT = 80;

	public static void main(String[] args) {

	}
	
	public List<String> createRequest(String host, String path)
			throws UnknownHostException, IOException {
		final Socket clientSocket = new Socket(host, HTTP_PORT);
		final InputStream inputStream = clientSocket .getInputStream();
		final OutputStream outputStream = clientSocket.getOutputStream();
		
		final InputStreamReader inputStreamReader =
			new InputStreamReader(inputStream);
		final BufferedReader in = new BufferedReader(inputStreamReader);
		final PrintWriter out = new PrintWriter(outputStream);

		// TODO SEND HTTP REQUEST
		
		out.flush();
		
		// TODO READ HTTP RESPONSE
		final List<String> result = new LinkedList<String>();
		
		// we should ALWAYS close sockets!
		clientSocket.close();
		
		return result;
	}

}
