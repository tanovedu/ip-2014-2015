package org.elsysbg.ip.http;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class HttpPostExample {

	private static final String HTTP_METHOD_POST = "POST";

	public static void main(String[] args) throws UnknownHostException, IOException {
		final HttpExample example = new HttpExample();
		final Map<String, String> body = new HashMap<String, String>();
		body.put("key1", "value1");
		body.put("key2", "value2");
		final CharacterHttpResponse response = example.createRequest("posttestserver.com",
			HTTP_METHOD_POST, "/post.php" + "?dump"/* + "&html"*/, body);
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
