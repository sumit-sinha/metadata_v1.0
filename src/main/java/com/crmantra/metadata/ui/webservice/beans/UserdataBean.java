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
	
	private SForceObjectInputBean[] objects;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String fetchPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SForceObjectInputBean[] getObjects() {
		return objects;
	}

	public void setObjects(SForceObjectInputBean[] objects) {
		this.objects = objects;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof UserdataBean) {
			UserdataBean usr = (UserdataBean)obj;
			boolean matched = usr.getUsername().equalsIgnoreCase(this.getUsername()) 
					&& usr.fetchPassword().equals(this.fetchPassword()) && ((usr.getObjects() != null && this.getObjects() != null && usr.getObjects().length == this.getObjects().length 
					|| (usr.getObjects() == null && this.getObjects() == null)));
			if (matched) {
				for (int i = 0; this.getObjects() != null && i < this.getObjects().length; i++) {
					if (!this.getObjects()[i].equals(usr.getObjects()[i])) {
						return false;
					}
				}
				
				return true;
			}
		}
		
		return false;
	}
}
