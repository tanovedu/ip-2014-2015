package org.elsysbg.ip.http;

import java.util.LinkedList;
import java.util.List;

public class CharacterHttpResponse {
	// should be in lower case
	private static final String HEADER_CONTENT_LENGTH = "content-length";
	
	private String statusLine;
	private final List<HttpHeader> headers = new LinkedList<HttpHeader>();
	private String body;
	
	public String getStatusLine() {
		return statusLine;
	}
	
	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}
	
	public List<HttpHeader> getHeaders() {
		return headers;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getBody() {
		return body;
	}

	public int getContentLength() {
		for (HttpHeader next : headers) {
			// in HTTP header names are case insensitive
			if (next.getName().toLowerCase().equals(HEADER_CONTENT_LENGTH)) {
				final int result = Integer.parseInt(next.getValue());
				// it should be checked for very big value
				return result;
			}
		}
		return 0;
	}
	
}
