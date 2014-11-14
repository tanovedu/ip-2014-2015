package org.elsysbg.ip.http;

import java.util.LinkedList;
import java.util.List;

public class CharacterHttpResponse {
	// these constants should be in lower case:
	private static final String HEADER_CONTENT_LENGTH = "content-length";
	private static final String HEADER_TRANSFER_ENCODING = "transfer-encoding";
	private static final String HEADER_VALUE_TRANSFER_ENCODING_CHUNKED = "chunked";
	
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

	private String getHeaderValue(String headerName) {
		for (HttpHeader next : headers) {
			// in HTTP header names are case insensitive
			if (next.getName().toLowerCase().equals(headerName)) {
				return next.getValue();
			}
		}
		return null;
	}
	public int getContentLength() {
		final String headerValue = getHeaderValue(HEADER_CONTENT_LENGTH);
		if (headerValue != null) {
			// it should be checked for very big value
			return Integer.parseInt(headerValue);
		}
		return 0;
	}
	
	public boolean isChunkedTransferEncoding() {
		final String headerValue = getHeaderValue(HEADER_TRANSFER_ENCODING);
		if (headerValue != null) {
			return headerValue.toLowerCase().equals(HEADER_VALUE_TRANSFER_ENCODING_CHUNKED);
		}
		return false;
	}
	
}
