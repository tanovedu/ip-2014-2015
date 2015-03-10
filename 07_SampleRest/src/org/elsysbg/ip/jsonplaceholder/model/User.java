package org.elsysbg.ip.jsonplaceholder.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model is copied by http://jsonplaceholder.typicode.com/
 */
@XmlRootElement
public class User {
	private long id;
	private String email;
	private String password;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
