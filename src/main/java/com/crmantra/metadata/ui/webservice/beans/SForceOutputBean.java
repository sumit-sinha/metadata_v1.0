package com.crmantra.metadata.ui.webservice.beans;

import java.io.Serializable;

/**
 * output bean which maps a user with list of objects
 * @author ssinha
 */
public class SForceOutputBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UserdataBean user;
	
	private SForceMapBean[] objects;
	
	private SForceErrorBean[] messages;

	public UserdataBean getUser() {
		return user;
	}

	public void setUser(UserdataBean user) {
		if (user != null) {
			// hide password
			this.user = user;
			this.user.setPassword("***************");
		}
	}

	public SForceMapBean[] getObjects() {
		return objects;
	}

	public void setObjects(SForceMapBean[] objects) {
		this.objects = objects;
	}
	
	public SForceErrorBean[] getMessages() {
		return messages;
	}

	public void setMessages(SForceErrorBean[] messages) {
		this.messages = messages;
	}
}
