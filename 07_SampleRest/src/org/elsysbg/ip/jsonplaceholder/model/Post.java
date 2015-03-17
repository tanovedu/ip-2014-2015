package org.elsysbg.ip.jsonplaceholder.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model is copied by http://jsonplaceholder.typicode.com/
 */
@XmlRootElement
public class Post {
	
	private long id;
	private User author;
	private String title;
	private String body;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
