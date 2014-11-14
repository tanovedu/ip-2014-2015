package org.elsysbg.ip.http;

import java.io.IOException;
import java.net.UnknownHostException;

public class HttpGetExample {

	private static final String HTTP_METHOD_GET = "GET";

	public static void main(String[] args) throws UnknownHostException, IOException {
		final HttpExample example = new HttpExample();
		// example.com or www.google.bg for chunked response
		final CharacterHttpResponse response = example.createRequest("example.com",
			HTTP_METHOD_GET, "/index.html");
		
		System.out.println(new String(response.getStatusLine()));
		for (HttpHeader next : response.getHeaders()) {
			System.out.printf("%s: %s\n", next.getName(), next.getValue());
		}
		System.out.println(new String(response.getBody()));
// or using for
//		for (char next : response.getBody()) {
//			System.out.print(next);
//		}
	}

}
