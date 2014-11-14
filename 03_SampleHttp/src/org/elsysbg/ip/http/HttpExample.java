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
	private static final int CHUNK_READ_SIZE = 1024;
	private static final int MAX_CHUNK_SIZE = 1024 * 1024 * 1;
	private static final int MAX_REQUEST_SIZE = MAX_CHUNK_SIZE * 10;

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
		
		// reading body
		if (result.isChunkedTransferEncoding()) {
			parseResponseChunked(in, result);
		} else {
			parseResponseByContentLength(in, result);
		}
		return result;
	}

	private void parseResponseChunked(BufferedReader in,
			CharacterHttpResponse result) throws IOException {
		final StringBuilder body = new StringBuilder();
		final char[] chunk = new char[CHUNK_READ_SIZE];

		// read line by line
		String sizeAsString;
		while((sizeAsString = in.readLine()) != null) {
			final int size = Integer.parseInt(sizeAsString, 16);
			if (size > MAX_CHUNK_SIZE) {
				throw new IllegalStateException("Chunk is too big: " + size +
						", max allowed: " + MAX_CHUNK_SIZE);
			}

			if (size == 0) {
				// end of input
				break;
			}
			
			readChunk(in, body, chunk, size);
		}

		result.setBody(body.toString());
	}

	private void readChunk(BufferedReader in, final StringBuilder body,
			final char[] chunk, int size) throws IOException {
		// read chunk
		while (size > 0) {
			final int readCount = in.read(chunk, 0, Math.min(size, chunk.length));
			if (readCount <= 0) {
				throw new IllegalStateException("No content: " + readCount +
						", expected: " + Math.min(size, chunk.length));
			}
			size -= readCount;
			body.append(chunk, 0, readCount);
			
		}
		// last new line of chunk
		in.readLine();
		// TODO check if it is new line
	}

	private void parseResponseByContentLength(BufferedReader in, final CharacterHttpResponse result)
			throws IOException {
		//  we already know how many bytes the body is
		// (from the content-length header line)
		final int size = Math.min(MAX_REQUEST_SIZE, result.getContentLength());
		final char[] body = new char[size];
		in.read(body);
		result.setBody(new String(body));
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
