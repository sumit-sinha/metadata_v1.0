package com.crmantra.metadata.ui.webservice.beans;

import java.io.Serializable;

/**
 * defines the basic data required for login
 * @author ssinha
 *
 */
public class UserdataBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;
	
	private String[] objects;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getObjects() {
		return objects;
	}

	public void setObjects(String[] objects) {
		this.objects = objects;
	}	
}