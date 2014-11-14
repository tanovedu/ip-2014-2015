package org.elsysbg.ip.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;

public class HttpExample {
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10;

	private static final String ENCODING = "UTF-8";
	private static final int HTTP_PORT = 80;
	private static final String PROTOCOL_VERSION = "HTTP/1.1";
	public CharacterHttpResponse createRequest(String host, String method,
			String path) throws UnknownHostException, UnsupportedEncodingException, IOException {
		return createRequest(host, method, path, (String) null);
	}

	public CharacterHttpResponse createRequest(String host, String method,
			String path, Map<String, String> body) throws UnknownHostException, UnsupportedEncodingException, IOException {
		return createRequest(host, method, path, convertParametersToString(body));
	}
	public CharacterHttpResponse createRequest(String host, String method,
			String path, String body)
			throws UnknownHostException, IOException {
		final Socket clientSocket = new Socket(host, HTTP_PORT);
		try {
			final InputStream inputStream = clientSocket .getInputStream();
			final OutputStream outputStream = clientSocket.getOutputStream();
			
			final InputStreamReader inputStreamReader =
				new InputStreamReader(inputStream);
			final BufferedReader in = new BufferedReader(inputStreamReader);
			final PrintWriter out = new PrintWriter(outputStream);

			writeRequest(out, host, method, path, body);
			out.flush();
			
			return parseResponse(in);
		} finally {
			// we should ALWAYS close sockets!
			clientSocket.close();
		}
		
		
	}

	private String convertParametersToString(Map<String, String> body) throws UnsupportedEncodingException {
		final StringBuilder result = new StringBuilder();
		for (Entry<String, String> next : body.entrySet()) {
			if (result.length() > 0) {
				result.append("&");
			}
			result.append(URLEncoder.encode(next.getKey(), ENCODING));
			result.append("=");
			result.append(URLEncoder.encode(next.getValue(), ENCODING));
			
		}
		return result.toString();
	}

	private CharacterHttpResponse parseResponse(BufferedReader in) throws IOException {
		final CharacterHttpResponse result = new CharacterHttpResponse();
		
		//reading status line
		result.setStatusLine(in.readLine());
		
		// reading headers until new line - this is marker for end of headers
		String next;
		while(!(next = in.readLine()).equals("")) {
			result.getHeaders().add(HttpHeader.createFromHeaderLine(next));
		}
		
		// TODO chunked transfer-encoding is not supported!

		// reading body - we already know how many bytes the body is
		// (from the content-length header line)
		final int size = Math.min(MAX_REQUEST_SIZE, result.getContentLength());
		final char[] body = new char[size];
		in.read(body);
		result.setBody(new String(body));
		return result;
	}

	private void writeRequest(PrintWriter out, String host,
			String method, String path, String body) {
		out.printf("%s %s %s\n", method, path, PROTOCOL_VERSION);
		out.printf("Host: %s\n", host);
		if (body != null) {
			out.printf("Content-Length: %d\n", body.length());
			// TODO content type
			out.printf("Content-Type: %s\n", "application/x-www-form-urlencoded");
			// end of header:
			out.printf("\n");
			out.println(body);
		} else {
			// end of request
			out.printf("\n");
		}
	}

}
